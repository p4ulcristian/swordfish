(ns swordfish.site.db
  (:require [reagent.core :as reagent :refer [atom]]
            [form-validator.core :as fv]
            [cljs.spec.alpha :as s]
            [clojure.test.check.generators :as gen]))

(s/def ::not-empty (fn [a]
                     (and
                       (not (= a ""))
                       (not (= a nil)))))
(s/def ::only-letters (partial re-matches #"[a-zA-Z ]+"))
(s/def ::only-numbers (partial re-matches #"[0-9+/]+"))
(s/def ::city ::not-empty)
(s/def ::country ::not-empty)
(s/def ::email (s/and ::not-empty string? (partial re-matches #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$")))
(s/def ::email-confirm ::not-empty)
(s/def ::first-name (s/and ::not-empty ::name ::only-letters))
(s/def ::last-name (s/and ::not-empty ::name ::only-letters))
(s/def ::name (s/and string? (fn [a] (> 20 (count a)))))
(s/def ::phone-country-code (s/and (partial re-matches #"^[0-9\-\+]+")
                                   (fn [a] (> 20 (count a))))) ;csak szam es plusz
(s/def ::phone-number (s/and ::not-empty (partial re-matches #"^[0-9 \-]+") (fn [a] (> 15 (count a)))))
(s/def ::state ::not-empty)
(s/def ::zip ::not-empty)
(s/def ::payment ::not-empty)

(s/def ::form (s/keys :req-un
                      [::city
                       ::country
                       ::email
                       ::email-confirm
                       ::first-name
                       ::last-name
                       ::phone-country-code
                       ::phone-number
                       ;::state
                       ::zip
                       ::payment]))

;This just shows you some possibilities.

;(s/def ::password-not-empty not-empty)
;(s/def ::password-length #(<= 6 (count %)))
;(s/def ::password (s/and string? ::password-not-empty ::password-length))
;
;(s/def ::checkbox-with-value (s/and ::checked))
;(s/def ::checkbox-without-value (s/and ::checked))
;
;(s/def ::select-one (s/and ::selected #{"green"}))
;(s/def ::select-multiple (partial some #{"cat"}))
;
;(s/def ::radio #{"red"})

(defn ?email-confirm [form name]
  "Example of validator using multiple inputs values.
  form - atom returned by form-init
  name - name of the input which call event"
  (let [password (get-in @form [:names->value :email])
        password-repeat (get-in @form [:names->value name])]
    (when-not (= password password-repeat)
      [:email-confirm ::email-not-equal])))

(swap! fv/conf #(merge % {:atom reagent/atom}))

(def form-spec {::name               "Name is too long."
                ::only-letters       "Name should contain only letters."
                ::not-empty          "Field is empty"
                ::email              "Email is in wrong format"
                ::phone-country-code "(+36, 0036)"
                ::email-not-equal    "Confirmed email is different."})

(def form
  (fv/init-form
    {:names->value      {:first-name         ""
                         :last-name          ""
                         :email              ""
                         :email-confirm      ""
                         :country            ""
                         :city               ""
                         :state              ""
                         :zip                ""
                         :phone-number       ""
                         :phone-country-code ""
                         :payment            ""}
     :form-spec         ::form
     :names->validators {:email-confirm [?email-confirm]}}))

(def db (atom {:accordions   []
               :menu         false
               :page         :index
               :cart         {}
               :contact-form {:name    ""
                              :email   ""
                              :message ""}
               :products     [;Main Product
                              {:id     "wakizashi"
                               :title  "SWORDFISH WAKIZASHI"
                               :type   "CARBON FIBRE MONOFIN"
                               :price  950
                               :photos ["/img/products/main.png"
                                        "/img/products/1.png"
                                        "/img/products/2.png"]}
                              ;Other products
                              {:id     "central-slider"
                               :title  "SWORDFISH"
                               :type   "CENTRAL SLIDER"
                               :price  120
                               :photos ["/img/products/1.png"
                                        "/img/products/1.png"
                                        "/img/products/2.png"]}
                              {:id     "brass-axle"
                               :title  "SWORDFISH WAKIZASHI"
                               :type   "REPLACEMENT BRASS AXLE 8X150MM"
                               :price  5
                               :photos ["/img/products/2.png"
                                        "/img/products/1.png"
                                        "/img/products/2.png"]}
                              {:id     "carbon-fibre"
                               :title  "SWORDFISH WAKIZASHI"
                               :type   "CARBON FIBRE BLADE SET"
                               :price  480
                               :photos ["/img/products/3.png"
                                        "/img/products/1.png"
                                        "/img/products/2.png"]}
                              {:id     "feet"
                               :title  "SWORDFISH FEET SECTION"
                               :type   "POLYURETHANE & CARBON COMPOSITE"
                               :price  480
                               :photos ["/img/products/4.png"
                                        "/img/products/1.png"
                                        "/img/products/2.png"]}]}))



(defn conj-set-vec [list item]
  (vec (set (conj list item))))

;accordion functions

(defn open-accordion [the-key]
  (swap! db assoc :accordions (conj-set-vec (:accordions @db) the-key)))

(defn close-accordion [the-key]
  (swap! db assoc :accordions (vec (remove #(= the-key %) (:accordions @db)))))

(defn open-accordion? [the-key]
  (not (empty? (filter #(= % the-key) (:accordions @db)))))

(defn toggle-accordion [the-key]
  (if (open-accordion? the-key)
    (close-accordion the-key)
    (open-accordion the-key)))

;viewport functions
(defn add-event-listener [el type callback]
  (.addEventListener el type callback true))

(defn remove-event-listener [el type callback]
  (.removeEventListener el type callback true))

(def document-element (.-documentElement js/document))

(defn get-viewport-width []
  (max (.-clientWidth document-element)))
;(.-innerWidth js/window)))

(defn get-viewport-height []
  (max (.-clientHeight document-element)))
;(.-innerHeight js/window)))

(defn get-viewport-data []
  (swap! db assoc :viewport
         {:width  (get-viewport-width)
          :height (get-viewport-height)}))

(defn xs? [] (> (-> @db :viewport :width) 300))
(defn s? [] (> (-> @db :viewport :width) 500))
(defn m? [] (> (-> @db :viewport :width) 800))
(defn l? [] (> (-> @db :viewport :width) 1100))

(defn add-viewport-listener [func]
  (add-event-listener js/window "resize" func))

(defn init-viewport-listener []
  (get-viewport-data)
  (add-viewport-listener get-viewport-data))

;;;Routes

(defn get-page []
  (:page @db))

(defn this-page? [page]
  (if (= page (get-page)) true false))

;menu

(defn get-element-by-id
  ; @param (string) id
  ; @param (DOM element)(opt) parent-element
  ;
  ; @return (DOM-element or nil)
  [id & [parent-element]]
  (.getElementById (or parent-element js/document) id))

(defn set-body-scroll-state [element-id style-name style-value]
  (if-let [element (get-element-by-id element-id)]
    (aset (.-style element) style-name style-value)))

(defn toggle-menu []
  (if (:menu @db)
    (set-body-scroll-state "sf-document-element" "overflow-y" "scroll")
    (set-body-scroll-state "sf-document-element" "overflow-y" "hidden"))
  (swap! db assoc :menu (not (:menu @db))))

(defn menu-open? []
  (:menu @db))

(defn get-query-params []
  (:query-params @db))

(defn get-main-product []
  (first (:products @db)))

(defn get-product [the-name]
  (first (filter #(= the-name (:id %))
                 (:products @db))))

(defn edit-db [the-keys the-value]
  (swap! db assoc-in the-keys the-value))

(defn get-in-db [the-keys]
  (get-in @db the-keys))

(defn add-to-cart []
  (swap! db assoc-in [:cart (:id (:current-product @db))]
         (:current-product @db)))

(defn delete-from-cart [{:keys [id] :as item}]
  (swap! db assoc :cart (dissoc (:cart @db) id)))



(defn get-cart-items []
  (:cart @db))

(defn get-product-quantity []
  (let [number (-> @db :current-product :quantity)]
    (if number number 0)))

(defn change-product-quantity [quantity]
  (swap! db assoc-in [:current-product :quantity] quantity))

(defn dec-product-quantity []
  (swap! db assoc-in [:current-product :quantity] (max 0 (dec (get-product-quantity)))))

(defn inc-product-quantity []
  (swap! db assoc-in [:current-product :quantity] (inc (get-product-quantity))))

(defn get-rest-products []
  (vec (rest (:products @db))))

(defn set-current-product [params]
  (swap! db assoc :current-product
         (assoc (get-product (:product params)) :quantity 1)))

(defn change-page [the-key params]
  (swap! db assoc :page the-key :query-params params)
  (if (= the-key :shop)
    (set-current-product params)))