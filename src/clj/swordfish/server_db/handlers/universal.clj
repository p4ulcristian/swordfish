(ns swordfish.server-db.handlers.universal
  (:require
    [com.wsscode.pathom3.connect.operation :as pco :refer [defresolver defmutation]]
    [swordfish.server-environment.api :as mongodb]
    [swordfish.server-db.env-handler     :as env-handler]))


;; -- Resolvers ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn make-pipeline-map [env]
  (let [coll            (env-handler/env->param env :coll)
        namespace       (env-handler/env->param env :namespace)
        search-term     (env-handler/env->param env :search-term)
        search-key      (env-handler/env->param env :search-key)
        max-count       (env-handler/env->param env :max-count)
        skip            (* max-count (env-handler/env->param env :skip-page))
        sort-key        (:sort-key       (env-handler/env->param env :sort-map))
        sort-direction  (:sort-direction (env-handler/env->param env :sort-map))
        sort-map        {sort-key sort-direction}]
    {:coll        coll
     :search-term search-term
     :search-key  search-key
     :sort-map    sort-map
     :max-count   max-count
     :skip        skip
     :namespace   namespace}))

(defresolver get-documents
  ; This resolver has parameters for filtering
  ;
  ;@param (map) env
  ;@param (map) ?
  ;
  ;@usage
  ;  [(:fruits {:skip 0 :max-count 1 :pattern {:fruit/color "yellow" } :sort-key :id})]
  ;
  ;@return (map)
  ;{:fruits (maps in vector)}
  [env _]
  {:documents (let [pipeline-map (make-pipeline-map env)]
                {:count  (mongodb/count-documents-with-pipeline pipeline-map)
                 :result (mongodb/find-documents-with-pipeline  pipeline-map)})})


;; -- Mutations ---------------------------------------------------------------
;; ----------------------------------------------------------------------------


;; Add-and-update---------------------------------------------------------------
(defmutation add-update-document [{:keys [collection document namespace]}]
  {::pco/op-name 'universal/add-update}
  (mongodb/add-update-document {:coll collection :document document :namespace namespace}))

(defmutation add-update-document-with-order [{:keys [collection document namespace]}]
  {::pco/op-name 'universal/add-update-with-order}
  (mongodb/add-update-document-with-order {:coll collection :document document :namespace namespace}))


;; Removing---------------------------------------------------------------------
(defmutation delete-document [{:keys [collection id]}]
  {::pco/op-name 'universal/delete}
  (mongodb/remove-document-by-id {:coll collection :id id}))

(defmutation delete-document-with-order [{:keys [collection id]}]
  {::pco/op-name 'universal/delete-with-order}
  (mongodb/remove-document-by-id-with-order {:coll collection :id id}))


;; Duplicate--------------------------------------------------------------------
(defmutation duplicate [{:keys [id collection namespace]}]
  {::pco/op-name 'universal/duplicate}
  (mongodb/duplicate {:coll collection :id id :namespace namespace}))

(defmutation duplicate-with-order [{:keys [id collection namespace]}]
  {::pco/op-name 'universal/duplicate-with-order}
  (mongodb/duplicate-with-order {:coll collection :id id :namespace namespace}))

;; Re-order--------------------------------------------------------------------
(defmutation re-order [{:keys [collection new-order]}]
  {::pco/op-name 'universal/re-order}
  (let [order-for-mongo (map-indexed (fn [index id] [(name id) index]) new-order)]
    (mongodb/re-order {:coll collection :new-order order-for-mongo})))



(def handlers
  [add-update-document
   add-update-document-with-order
   delete-document
   delete-document-with-order
   duplicate
   duplicate-with-order
   get-documents
   re-order])
