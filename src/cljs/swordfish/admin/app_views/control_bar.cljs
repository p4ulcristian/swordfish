
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.admin.app-views.control-bar
  (:require [x.app-elements.api :as elements]))



;; -- Components --------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn control-bar []
  [:div {:style {:display "flex"}}
   [elements/separator {:orientation :vertical :size :s}]
   [elements/button {:color :none
                     :layout :row
                     :variant :transparent
                     :label "Control panel"
                     :on-click [:x.app-sync/go-to! "/admin"]}]
   [elements/separator {:orientation :vertical :size :s}]
   [elements/button {:color :none
                     :layout :row
                     :variant :transparent
                     :label "Items"
                     :on-click [:x.app-sync/go-to! "/admin/items"]}]
   [elements/separator {:orientation :vertical :size :s}]
   [elements/button {:color :none
                     :layout :row
                     :variant :transparent
                     :label "Playground"
                     :on-click [:x.app-sync/go-to! "/admin/playground"]}]])



(defn view []
  [control-bar])
