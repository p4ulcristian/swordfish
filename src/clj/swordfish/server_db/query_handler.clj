
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.server-db.query-handler
    (:require [mid-fruits.candy   :refer [param]]
              [mid-fruits.map     :as map]
              [mid-fruits.reader  :as reader]
              [mid-fruits.vector  :as vector]
              [server-fruits.http :as http]
              [swordfish.server-db.handlers.default-handlers :as default-handlers]
              [swordfish.server-db.handlers.universal :as universal]

              ; Pathom stuff
              [com.wsscode.pathom3.connect.indexes            :as pci]
              [com.wsscode.pathom3.interface.smart-map        :as psm]
              [com.wsscode.pathom3.connect.built-in.resolvers :as pbir]
              ;[com.wsscode.pathom.viz.ws-connector.core       :as pvc]
              ;[com.wsscode.pathom.viz.ws-connector.pathom3    :as p.connector]
              [com.wsscode.pathom3.interface.eql              :as p.eql]))



;; -- Configuration -----------------------------------------------------------
;; ----------------------------------------------------------------------------

;(def CONNECT_PARSER? true)

(def SAMPLE-RESOLVERS
     [(pbir/constantly-resolver  :pi Math/PI)
      (pbir/single-attr-resolver :pi :tau #(* 2 %))])



;; -- Converters --------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn request->query
  ; @param (map) request
  ;  {:transit-params (map)
  ;   {:query (string)}}
  ;
  ; @return (*)
  [request]
  (reader/string->mixed (http/request->transit-param request :query)))



;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(def registry
  [SAMPLE-RESOLVERS
   default-handlers/handlers
   universal/handlers])

(def eql-env
     (cond-> (pci/register registry)))
             ;(param CONNECT_PARSER?)))
             ; Give your environment a unique parser-id, this will ensure
             ; reconnects work as expected
             ;(p.connector/connect-env {::pvc/parser-id `env})))

(defn process!
  ; @param (map) request
  [request]
  (let [query (request->query request)]
       (p.eql/process (assoc eql-env :request request)
                      (param query))))
