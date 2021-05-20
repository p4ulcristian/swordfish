
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.site.app-views.about
    (:require [x.app-core.api :as a]))



;; -- Components --------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn view
  []
  [:div {:style {:padding "30px" :text-align "center"}}
        [:h1 "About mono-template"]
        [:div [:em "\" Great power comes with great responsibility \""]]
        [:div [:strong "/╲/\\╭( ͡° ͡° ͜ʖ ͡° ͡°)╮/\\╱\\"]]])



;; -- Lifecycle events --------------------------------------------------------
;; ----------------------------------------------------------------------------

(a/reg-event-fx
  ::render!
  [:x.app-ui/set-surface! ::view {:content #'view}])

(a/reg-event-fx
  ::on-app-boot-events
  {:dispatch-n [[:x.app-sync/add-route! ::route {:route-template "/about"
                                                 :route-event [::render!]}]]})

(a/reg-lifecycles
  ::lifecycles
  {:on-app-boot [::on-app-boot-events]})
