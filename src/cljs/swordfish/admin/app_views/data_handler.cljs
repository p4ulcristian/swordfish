;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.admin.app-views.data-handler
  (:require [swordfish.admin.app-views.engine :as engine]
            [mid-fruits.vector :as vector]
            [x.app-core.api :as a :refer [r]]
            [x.app-db.api :as db]
            [x.app-sync.api :as sync]))


;; -- Subscriptions -----------------------------------------------------------
;; ----------------------------------------------------------------------------


(defn get-stored-documents [db [_ partition-id]]
  ;(get-in db [partition-id :data-items]))
  ;db/path makes it shorter to get the data
  (get-in db (db/path partition-id)))


;; -- Events ------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn get-resolver-from-response [resolver response]
  (get response resolver))


(defn get-mutation-from-response [mutation-name response]
  (get response (symbol mutation-name)))


(defn get-response [db request-id]
  (r sync/get-request-response db request-id))


(defn document-vector->document-map [collection]
  (reduce
    (fn [processed actual-document]
      (assoc processed
        (engine/get-namespaced-id actual-document)
        actual-document))
    {}
    collection))

(a/reg-event-db
  ::increase-skip-page!
  (fn [db _]
    (let [skip-page (get-in db [:search-props :skip-page])
          increased-skip-page (inc skip-page)]
      (assoc-in db [:search-props :skip-page] increased-skip-page))))


(a/reg-event-db
  ::enable-infinite-loader!
  (fn [db [_ partition-id]]
    (let [all-document-count    (get-in db [:document-count partition-id])
          stored-document-count (count (get-in db [partition-id :data-order]))]
      (if (or
            (= nil all-document-count)
            (< stored-document-count all-document-count))
        (assoc-in db [:infinite-loader?] true)
        (assoc-in db [:infinite-loader?] false)))))

(a/reg-event-db
  ::disable-infinite-loader!
  (fn [db _]
    (assoc-in db [:infinite-loader?] false)))


(a/reg-event-db
  ::store-documents!
  (fn [db [_ partition-id resolver request-id]]
    (let [response               (get-response db request-id)
          documents-with-count   (get-resolver-from-response resolver response)
          server-documents-count (:count documents-with-count)
          server-documents       (:result documents-with-count)
          client-documents       (r get-stored-documents db partition-id)]
          ;Same thing, other perspective
          ;client-documents  (get-stored-documents db [nil partition-id])
      (-> db
        (db/set-item!          [_ [:document-count partition-id] server-documents-count])
        (db/import-collection! [_ partition-id server-documents])))))

;x.mid-db.transfer-handler/import-collection!

(a/reg-event-fx
  ::handle-get-response!
  (fn [{:keys [db]} [_ partition-id resolver request-id]]
    {:dispatch-n [[::increase-skip-page!]
                  [::store-documents! partition-id resolver request-id]
                  [::enable-infinite-loader! partition-id]]}))


(defn already-in-partition? [document-ids document-id]
  (vector/contains-item? document-ids document-id))

(a/reg-event-db
  ::handle-add-update-response!
  (fn [db [_ partition-id mutation request-id]]
    (let [response (get-response db request-id)
          document (get-mutation-from-response mutation response)
          document-ids (get-in db [partition-id :data-order])
          document-id (keyword (engine/get-namespaced-id document))]
      (-> db
        ((fn [db]
           (if
             (already-in-partition? document-ids document-id)
             (assoc-in db [partition-id :data-items document-id] document)
             (db/add-data-item! db [nil partition-id document-id document]))))
        (assoc-in [:editor-form] {:open? false})))))


(a/reg-event-db
  ::handle-delete-response!
  (fn [db [_ partition-id mutation request-id]]
    (let [response (get-response db request-id)
          id (keyword (get-mutation-from-response mutation response))
          all-items (get-in db [partition-id :data-items])
          new-all-items (dissoc all-items id)]
      (-> db
        (db/remove-data-item! [nil partition-id id])
        (assoc-in [:editor-form] {:open? false})))))

(a/reg-event-fx
  ::handle-re-order-response!
  (fn [cofx [_ request-id]]
    [:x.app-ui/blow-bubble! {:content "Sikeres újrarendezés."}]))

(a/reg-event-fx
  ::send-get-eql!
  (fn [{:keys [db]} [_ {:keys [query on-success on-failure request-id]}]]
    [:x.app-sync/send-request!
     {:method      :post
      :params      {:query query}
      :on-success  on-success
      :on-failure  on-failure
      :uri         "/db/query"}]))

(a/reg-event-fx
  ::send-eql!
  (fn [{:keys [db]} [_ {:keys [query on-success on-failure]}]]
    [:x.app-sync/send-request!
     {:method      :post
      :params      {:query query}
      :on-success  on-success
      :on-failure  on-failure
      :uri         "/db/query"}]))
