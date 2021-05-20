(ns swordfish.server-environment.mongodb
  (:require
    monger.joda-time
    [clj-time.core :as t]
    [clj-time.format :as f]
    [monger.core :as mg]
    [monger.collection :as mc]
    [monger.query :as query]
    [mid-fruits.keyword :as keyword]
    [mid-fruits.random :as random]
    [monger.operators :refer [$regex]]
    [monger.conversion :refer [from-db-object]]
    [mid-fruits.gestures :as gestures])
  (:import
    [com.mongodb MongoOptions ServerAddress]
    org.bson.types.BSONTimestamp))


;; -- DB Connection -----------------------------------------------------------
;; ----------------------------------------------------------------------------


(def db-name "swordfish")
(def ^MongoOptions opts (mg/mongo-options {:threads-allowed-to-block-for-connection-multiplier 300}))
(def ^ServerAddress sa  (mg/server-address "127.0.0.1" 27017))
(def conn (mg/connect sa opts))
(def db (mg/get-db conn db-name))


;; -- DB Helpers --------------------------------------------------------------
;; ----------------------------------------------------------------------------


;If the value is a Date Object, converts it to string.
(defn db-date->str [value]
  (if (= org.joda.time.DateTime (type value))
    (str value)
    value))


;Iterates through the document, converts Date to string
(defn document-values->string [document]
  (reduce
    (fn [processed-documents [actual-document-key actual-document-value]]
      (if
        (map? actual-document-value)
        (assoc processed-documents actual-document-key
          (document-values->string actual-document-value))
        (assoc processed-documents actual-document-key
          (-> actual-document-value db-date->str))))

    {} document))


;Iterates through a document-vector, stringifies Date
(defn db-items->str [document-vector]
  (mapv
    (fn [document] (document-values->string document))
    document-vector))


;Stringifies document
(defn db-item->str [document]
  (document-values->string document))


(defn _id->id [document]
  (let [id (:_id document)]
    (-> document
      (dissoc :_id)
      (assoc  :id id))))


(defn id->_id [document]
  (let [id (:id document)]
    (-> document
      (dissoc :id)
      (assoc  :_id id))))


(defn ids->_ids [document-vector]
  (mapv id->_id document-vector))


(defn _ids->ids [document-vector]
  (mapv _id->id document-vector))


(defn document->non-namespaced-document [document]
  (reduce (fn [processed [actual-key actual-value]]
            (assoc processed
              (keyword (name actual-key))
              actual-value))
    {}
    document))


(defn document->namespaced-document [document namespace]
  (reduce (fn [processed [actual-key actual-value]]
            (assoc processed
              (keyword/add-namespace namespace actual-key)
              actual-value))
    {}
    document))


(defn documents->namespaced-documents [documents namespace]
  (mapv
    (fn [document] (document->namespaced-document document namespace))
    documents))


(defn time-stamp []
  (t/to-time-zone (t/now) (t/time-zone-for-id "Europe/Budapest")))

(defn add-id-and-time-stamp [document]
  (assoc document
    :_id         (random/generate-string)
    :created-at  (time-stamp)))

(defn assoc-order [document index]
  (assoc document :order index))

;; -- DB Base Functions -------------------------------------------------------
;; ----------------------------------------------------------------------------


;; Add-and-update--------------------------------------------------------------


(defn add-update-document [{:keys [coll document namespace]}]
  (let [no-namespace-document (document->non-namespaced-document document)
        has-id? (contains? no-namespace-document :id)
        mongo-document (if has-id?
                         (id->_id no-namespace-document)
                         (add-id-and-time-stamp no-namespace-document))]
    (->
      (mc/save-and-return db coll mongo-document)
      db-item->str
      _id->id
      (document->namespaced-document namespace))))


(defn add-update-document-with-order [{:keys [coll document namespace]}]
  (let [new-index             (mc/count db coll)
        no-namespace-document (document->non-namespaced-document document)
        has-id?               (contains? no-namespace-document :id)
        mongo-document        (if has-id?
                                (id->_id no-namespace-document)
                                (-> (add-id-and-time-stamp no-namespace-document)
                                    (assoc-order new-index)))]
    (->
      (mc/save-and-return db coll mongo-document)
      db-item->str
      _id->id
      (document->namespaced-document namespace))))


;; Removing--------------------------------------------------------------------
(defn remove-document-by-id [{:keys [coll id]}]
  (mc/remove-by-id db coll id)
  id)


(defn remove-document-by-id-with-order [{:keys [coll id]}]
  (let [this-index (:order (mc/find-map-by-id db coll id))
        update-indexes (mc/update db coll {:order {"$gt" this-index}} {"$inc" {:order -1}} {:multi true})]
    (remove-document-by-id {:coll coll :id id})))


;; Duplicate--------------------------------------------------------------------
(defn duplicate [{:keys [coll id namespace]}]
  (let [all-names (mapv :name (mc/find-maps db coll {} {:name 1 :_id 0}))
        this-item (dissoc (mc/find-map-by-id db coll id) :_id)
        this-name (:name this-item)
        copy-text "másolat"
        new-name  (try
                    (gestures/item-label->duplicated-item-label this-name all-names "másolat")
                    (catch Exception e
                      (println e)))
        new-item  (add-id-and-time-stamp (assoc this-item :name new-name))]

    (->
      (mc/save-and-return db coll new-item)
      db-item->str
      _id->id
      (document->namespaced-document namespace))))


(defn duplicate-with-order [{:keys [coll id namespace]}]
  (let [all-names      (mapv :name (mc/find-maps db coll {} {:name 1 :_id 0}))
        this-item      (dissoc (mc/find-map-by-id db coll id) :_id)
        this-index     (:order this-item)
        new-index      (inc this-index)
        this-name      (:name this-item)
        copy-text      "másolat"
        new-name       (try
                         (gestures/item-label->duplicated-item-label this-name all-names "másolat")
                         (catch Exception e
                           (println e)))
        new-item       (->
                         (add-id-and-time-stamp (assoc this-item :name new-name))
                         (assoc-order new-index))
        update-indexes (mc/update db coll {:order {"$gt" this-index}} {"$inc" {:order 1}} {:multi true})]
    (->
      (mc/save-and-return db coll new-item)
      db-item->str
      _id->id
      (document->namespaced-document namespace))))

;; Re-order--------------------------------------------------------------------
(defn re-order [{:keys [coll new-order]}]
  (doseq [[this-id this-index] new-order]
    (mc/update db coll {:_id this-id} {"$set" {:order this-index}}))
  new-order)

;; Finding---------------------------------------------------------------------
(defn find-documents [{:keys [coll query namespace]}]
  (->
    (mc/find-maps db coll query)
    db-items->str
    _ids->ids
    (documents->namespaced-documents namespace)))


(defn find-document [{:keys [coll query namespace]}]
  (->
    (mc/find-one-as-map db coll query)
    db-item->str
    _id->id
    (document->namespaced-document namespace)))


(defn find-document-by-id [{:keys [coll id namespace]}]
  (->
    (mc/find-map-by-id db coll id)
    db-item->str
    _id->id
    (document->namespaced-document namespace)))


;; -- DB Advanced Functions ---------------------------------------------------
;; ----------------------------------------------------------------------------


(defn db-obj->edn-map [value]
  (from-db-object value true))

(defn get-from-aggregation [value]
  (->
    (:firstBatch (:cursor (db-obj->edn-map value)))
    db-items->str
    _ids->ids))

(defn search-aggregation [coll pipeline]
  (get-from-aggregation (mg/command db {:aggregate coll
                                        :pipeline  pipeline
                                        :collation {:locale "hu" :numericOrdering true}
                                        :cursor    {}})))



(defn count-documents-with-pipeline [{:keys [coll search-term search-key]}]
  (mc/count db coll {"$and" [{search-key {"$regex" search-term "$options" "i"}}]}))


(defn find-documents-with-pipeline [{:keys [coll skip max-count sort-map namespace search-term search-key]}]
  (->
    (search-aggregation coll
      [{"$match" {"$and" [{search-key {"$regex"   search-term
                                       "$options" "i"}}]}}
       {"$sort" sort-map}
       {"$skip" skip}
       {"$limit" max-count}])
    (documents->namespaced-documents namespace)))

