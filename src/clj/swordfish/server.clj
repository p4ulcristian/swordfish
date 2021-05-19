(ns swordfish.server
    (:require
     [swordfish.handler :refer [app]]
     [config.core :refer [env]]
     [ring.adapter.jetty :refer [run-jetty]]
     [shadow.cljs.devtools.server :as server]
     [shadow.cljs.devtools.api :as shadow])
    (:gen-class))

(defonce server (atom nil))

(defn -main [& args]
  (let [port 3200]
    (when-not (nil? @server)
      (@server)
      (reset! server nil))
    (reset! server (run-jetty #'app
                               {:port port :join? false}))))

(defn dev [{:keys [shadow-build]}]
  (-main)
  (server/stop!)
  (server/start!)
  ;(shadow/compile :app)
  (shadow/watch shadow-build))