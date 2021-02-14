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
    [swordfish.views.utils :as utils]
    [form-validator.core :as fv]))

;This is needed because we use reagent.
(def primary-color "#2B83C7")
(def theme (ui/create-mui-theme (clj->js {:palette
                                          {:type      "dark"
                                           :primary   {:main primary-color}
                                           :secondary {:main "#FF822E"}
                                           :error     {:main "#f02849"}}})

                                huHU))

(defn shipping-input [{:keys [title name validate-func error-text]}]
  (let [get-value (fn [a] (.-value (.-target a)))]
    (fn []
      [ui/form-control {:fullWidth true}
       [ui/text-field {:variant    "outlined"
                       :value (get (:names->value @db/form)
                                   name)
                       :error      (fv/?show-message db/form :first-name)
                       :helperText "Mi  újság_"
                       :onChange   (partial fv/event->names->value! db/form)
                       :type       "text"
                       :name       name
                       :label      title}]])))

(defn payment []
  [:div {:class (x-class css/shipping-fields-container)}
   [:h1 {:class (x-class css/cart-title)} "PAYMENT"]
   [ui/radio-group {:aria-label "payment" :name "gender1" :value (db/get-in-db [:payment]) :color "primary" :onChange (fn [e new] (db/edit-db [:payment] new))}
    [ui/form-control-label {:value   "paypal"
                            :style   {:color "white"}
                            :control (utils/as-el [ui/radio {:color "primary"}])
                            :label   "Paypal"}]
    [ui/form-control-label {:value   "inperson"
                            :style   {:color "white"}
                            :control (utils/as-el [ui/radio {:color "primary"}])
                            :label   "In person"}]]])


(defn buy-it []
  [:button {:on-click #(db/edit-db [:email?] true)
            :class    (x-class css/add-to-cart-button)}
   "Buy it"])


;:input (partial input {:form form
;                       :spec->msg spec->msg
;:checkbox (partial checkbox {:form form
;                             :spec->msg spec->msg
;:radio (partial radio {:form form
;                       :spec->msg spec->msg
;:select (partial select {:form form
;                         :spec->msg spec->msg}))

(defn shipping []
  [:div {:class (x-class css/shipping-fields-container)}
   [:h1 {:class (x-class css/cart-title)} "SHIPPING"]
   [:div {:style {:color "white"}}
    (str @db/form)
    ;(str (fv/?show-message form :email))
    (str (fv/form-valid? db/form))]
   [:div
    [:div {:class (x-class css/shipping-fields)}
     [shipping-input {:title "First name"
                      :name  :first-name}]
     [shipping-input {:title "Last name"
                      :name  :last-name}]
     [shipping-input {:title "Email"
                      :name  :email}]
     [shipping-input {:title "Confirm email"
                      :name  :confirm-email}]
     [ui/country-select [:shipping-form :country]]
     [shipping-input {:title "City"
                      :name  :city}]
     [shipping-input {:title "State"
                      :name  :state}]
     [shipping-input {:title "Zip code"
                      :name  :zip}]
     [ui/phone-number
      [:shipping-form :phone-number]
      [:shipping-form :phone-country-code]]]]])

(defn shipping-and-payment []
  [:div {:class (x-class css/shipping-and-payment-container) :style {:margin-bottom "150px"}}
   [:> mat/ThemeProvider {:theme theme}
    [shipping]
    [payment]
    [buy-it]]])
