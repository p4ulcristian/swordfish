
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.site.app-views.main
    (:require [x.app-core.api :as a]))


;; -- Components --------------------------------------------------------------
;; ----------------------------------------------------------------------------


(defn view
  []
  [:div [:h1 "Welcome to mono-template"]
        [:div [:a {:href "/about"} "Tell me about it"]]
        [:div [:a {:href "/admin/playground"} "This is the playground for eql (also admin)"]]])


;; -- Lifecycle events --------------------------------------------------------
;; ----------------------------------------------------------------------------


(a/reg-event-fx ::render!
  [:x.app-ui/set-surface!
   ::view
   {:content #'view
    :secondary-button {:preset :menu-icon-button}}])


(a/reg-event-fx
  ::on-app-boot-events
  {:dispatch-n [[:x.app-sync/set-home! {:route-event [::render!]}]]})


(a/reg-lifecycles
  ::lifecycles
  {:on-app-boot [::on-app-boot-events]})
