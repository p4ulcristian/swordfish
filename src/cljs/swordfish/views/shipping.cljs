(ns swordfish.views.shipping
  (:require
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [reagent.core :as reagent :refer [atom]]
    [swordfish.css.shop :as css]
    [swordfish.css.utils :as css-utils]
    [swordfish.db :as db]
    [swordfish.views.materialui :as ui]
    ["@material-ui/core" :as mat]
    ["@material-ui/core/locale" :refer (huHU)]
    [swordfish.views.utils :as utils]))


(def primary-color "#2B83C7")
(def theme (ui/create-mui-theme (clj->js {:palette
                                          {:type      "dark"
                                           :primary   {:main primary-color}
                                           :secondary {:main "#FF822E"}}})

                                huHU))


(defn shipping-input [title keys]
  [ui/text-input title keys])



(defn shipping []
  [:div
   [:> mat/ThemeProvider {:theme theme}
    [:div {:class (x-class css/shipping-fields-container)}
     [:h1 {:class (x-class css/cart-title)} "SHIPPING"]
     [:div
       [:div {:class (x-class css/shipping-fields)}
        [:div [ui/country-select "Country" [:shipping-form :country]]]
        [shipping-input "valami " [:shipping-form :country]]
        [shipping-input "valami " [:shipping-form :country]]
        [shipping-input "valami " [:shipping-form :country]]
        [shipping-input "valami " [:shipping-form :country]]
        [shipping-input "valami " [:shipping-form :country]]]]]]])
