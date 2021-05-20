(ns swordfish.site.views.shipping
  (:require
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [reagent.core :as reagent :refer [atom]]
    [swordfish.site.css.shop :as css]
    [swordfish.site.css.utils :as css-utils]
    [swordfish.site.db :as db]
    [swordfish.site.views.materialui :as ui]
    ["@material-ui/core" :as mat]
    [swordfish.site.views.utils :as utils]
    [form-validator.core :as fv]))

;This is needed because we use reagent.

(defn number-to-string [number]
  (.fromCodePoint js/String number))

(defn country-code-to-flag [code]
  (apply str
         (for [letter (clojure.string/upper-case code)]
           (number-to-string
             (+ (.charCodeAt letter 0) 127397)))))


(defn get-from-json [json the-key]
  (get (js->clj json :keywordize-keys true) the-key))



(defn update-form-check [name]
  (swap! db/form #(update % :names->show (fn [names->show]
                                           (conj names->show name)))))

(defn update-form [name value]
  (do
    (swap! db/form #(assoc-in % [:names->value name] value))
    (fv/validate-form db/form)))


(defn country-select [{:keys [title name]}]
  @db/form
  [ui/auto-complete
   {:on-change             (fn [event new-value] (update-form name (get-from-json new-value :label)))
    :on-blur               (fn [e] (update-form-check name))
    ;:on-input-change       (fn [event new-value] (update-form name (get-from-json new-value :label)))
    :clear-on-blur         true
    :type                  "text"
    :name                  name
    :label                 title
    :options               (sort-by :label ui/country-names)
    :renderOption          (fn [option]
                             (let [{:keys [code label phone]} (js->clj option :keywordize-keys true)]
                               (utils/as-el [:div
                                             [:span {:style {:margin-right "4px"}}
                                              (country-code-to-flag code)]
                                             label])))      ;" - +" phone])))
    :autoHighlight         true
    :getOptionLabel        (fn [item] (str (get (js->clj item) "label" "")))
    :filterSelectedOptions true
    :freeSolo              true
    :renderInput           (fn [^js params]
                             (set! (.-error params) (fv/?show-message db/form name))
                             (set! (.-helperText params) (str (fv/?show-message db/form name db/form-spec) " "))
                             (set! (.-label params) "Country")
                             (set! (.-variant params) "outlined")
                             (reagent/create-element mat/TextField params))}])

(defn shipping-input [{:keys [title name]}]
  (let [get-value (fn [a] (.-value (.-target a)))]
    (fn [{:keys [title name]}]
      [ui/form-control {:fullWidth true}
       [ui/text-field {:variant    "outlined"
                       :value      (get (:names->value @db/form) name)
                       :on-blur    (fn [e] (update-form-check name))
                       :error      (fv/?show-message db/form name)
                       :helperText (str (fv/?show-message db/form name db/form-spec) " ")
                       :on-change  (fn [e] (update-form name (.-value (.-target e))))
                       :type       "text"
                       :name       name
                       :label      title}]])))

(defn phone-number []
  @db/form
  [:div {:style {:flex "100%" :color "white"}}
   [ui/auto-complete
    {:style                 {:width "200px"}
     :options               (sort-by :label (mapv (fn [a] (assoc a :phone (str "+" (:phone a))))
                                                  ui/country-names))
     :on-blur               (fn [event new-value]
                              (update-form-check :phone-country-code))
     :on-change             (fn [event new-value]
                              (update-form :phone-country-code (str (get-from-json new-value :phone))))
     :on-input-change       (fn [new-value]
                              (update-form :phone-country-code (str (.-value (.-target new-value)))))
     :renderOption          (fn [option]
                              (let [{:keys [code label phone]} (js->clj option :keywordize-keys true)]
                                (utils/as-el [:div
                                              [:span (country-code-to-flag code)]
                                              phone])))
     :autoHighlight        true
     :getOptionLabel        (fn [item] (str (get (js->clj item) "phone" "")))
     :filterSelectedOptions true
     :freeSolo              true
     :renderInput           (fn [^js params]
                              (set! (.-label params) "Country code")
                              (set! (.-variant params) "outlined")
                              (set! (.-error params) (fv/?show-message db/form :phone-country-code))
                              (set! (.-helperText params) (str (fv/?show-message db/form :phone-country-code db/form-spec) " "))
                              (reagent/create-element mat/TextField params))}]
   [:div {:style {:color         "white"
                  :display       "flex"
                  :justify-content "center"
                  :flex-direction "column"
                  :align-content "center"
                  :padding       "0px 10px"
                  :height        "55px"
                  :font-size "22px"
                  :font-weight   "bold"}}
    [:div " - "]]
   [shipping-input {:title "Phone number"
                    :name  :phone-number}]])



(defn payment []
  @db/form
  [:div {:class (x-class css/shipping-fields-container)}
   [:h1 {:class (x-class css/cart-title)} "PAYMENT"]
   [ui/form-control
    {:error (fv/?show-message db/form :payment)}
    [ui/radio-group {:aria-label "payment"
                     :name       "payment"
                     :value      (get (:names->value @db/form) :payment)
                     :color      "primary"
                     :onChange   (fn [e new] (update-form :payment new))}
     [ui/form-control-label {:value   "paypal"
                             :style   {:color "white"}
                             :control (utils/as-el [ui/radio {:color "primary"}])
                             :label   "Paypal"}]
     [ui/form-control-label {:value   "inperson"
                             :style   {:color "white"}
                             :control (utils/as-el [ui/radio {:color "primary"}])
                             :label   "In person"}]]
    [ui/form-helper-text (str (fv/?show-message db/form :payment db/form-spec))]]])


(defn buy-it []
  [:button {:on-click #(do
                         (fv/show-all db/form)
                         (if (fv/form-valid? db/form)
                           (do
                             (db/edit-db [:email?] true)
                             (db/edit-db [:cart] []))))
            :class    (if (fv/form-valid? db/form)
                        (x-class css/shipping-button)
                        (x-class css/add-to-cart-button))}
   (if (fv/form-valid? db/form)
     "Buy it" "Review order")])


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
   [:div {:style {:color "white"}}]
   ;(str @db/form)
   ;(str (fv/?show-message form :email))
   ;(str (fv/form-valid? db/form))]
   [:div
    [:div {:class (x-class css/shipping-fields)}
     [shipping-input {:title "First name"
                      :name  :first-name}]
     [shipping-input {:title "Last name"
                      :name  :last-name}]
     [shipping-input {:title "Email"
                      :name  :email}]
     [shipping-input {:title "Confirm email"
                      :name  :email-confirm}]
     [country-select {:title "Confirm email"
                      :name  :country}]
     [shipping-input {:title "City"
                      :name  :city}]
     [shipping-input {:title "State"
                      :name  :state}]
     [shipping-input {:title "Zip code"
                      :name  :zip}]
     [phone-number]]]])

(defn shipping-and-payment []
  [:div {:class (x-class css/shipping-and-payment-container) :style {:margin-bottom "150px"}}
   [:> mat/ThemeProvider {:theme ui/theme}
    [shipping]
    [payment]
    [buy-it]]])
