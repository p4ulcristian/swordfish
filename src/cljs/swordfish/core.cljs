(ns swordfish.core
  (:require
    [reagent.core :as reagent :refer [atom]]
    [reagent.dom :as rdom]
    [reagent.session :as session]
    [reitit.frontend :as reitit]
    [clerk.core :as clerk]
    [swordfish.db :as db]
    [swordfish.router :refer [router page-for]]
    [swordfish.views.main :as main]
    [accountant.core :as accountant]))

;; -------------------------
;; Initialize app

(defn mount-root []
  (rdom/render [main/current-page] (.getElementById js/document "app")))

(defn init! []
  (clerk/initialize!)
  (db/init-viewport-listener)
  (db/set-body-scroll-state "sf-document-element" "overflow-y" "scroll")
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (let [match (reitit/match-by-path router path)
             current-page (:name (:data match))
             route-params (:path-params match)]
         (db/change-page current-page route-params)
         (reagent/after-render clerk/after-render!)
         (session/put! :route {:current-page (page-for current-page)
                               :route-params route-params})
         (clerk/navigate-page! path)))
     :path-exists?
     (fn [path]
       (boolean (reitit/match-by-path router path)))})
  (accountant/dispatch-current!)
  (mount-root))