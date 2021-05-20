
; WARNING! THIS IS AN OUTDATED VERSION OF A MONO-TEMPLATE FILE!



;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.core
  (:require
    [shadow.cljs.devtools.server :as server]
    [shadow.cljs.devtools.api    :as shadow]
    [swordfish.server-router.api]
    [x.server-core.boot-loader   :refer [run-app!]])
  (:gen-class))



;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn -main
  ; WARNING! NON-PUBLIC! DO NOT USE!
  ;
  ; @param (list of *) args
  [& args]
  (run-app!)
  (println "#swordfish - Server started"))

(defn dev
  ; WARNING! NON-PUBLIC! DO NOT USE!
  ;
  ; @param (map) options
  ;  {:shadow-build (keyword)}
  ;
  ; @usage
  ;  (dev {:shadow-build :my-build})
  [{:keys [shadow-build]}]
  (-main)
  (server/stop!)
  (server/start!)
  (shadow/watch shadow-build)
  (println "#swordfish - Development mode started"))
