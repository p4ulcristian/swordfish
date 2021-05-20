
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.admin.app-views.main
    (:require [x.app-core.api :as a]
              [x.app-elements.api :as elements]
              [x.app-ui.api :as ui]
              [swordfish.admin.app-views.control-bar :refer [view] :rename {view control-bar}]))



;; -- Components --------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn view
  []
  [:div [:h1 "Welcome to mono-template"]
        [:div [:a {:href "/admin/playground"} "This is the playground for eql"]]])

;; -- Lifecycle events --------------------------------------------------------
;; ----------------------------------------------------------------------------

(a/reg-event-fx ::render!
  [:x.app-ui/set-surface!
   ::view
   {:content #'view
    :control-bar {:content #'control-bar}
    :label-bar   {:content       #'ui/go-back-surface-label-bar
                  :content-props {:label "Control Panel"}}}])

(a/reg-event-fx
  ::initialize!
  {:dispatch-n
   [[:x.app-sync/add-route!
     ::route
     {:restricted? true
      :route-event    [::render!]
      :route-template "/admin"
      :route-title "Control Panel"}]]})


(a/reg-lifecycles
  ::lifecycles
  {:on-app-boot [::initialize!]})
