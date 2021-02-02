(ns swordfish.db
  (:require [reagent.core :as reagent :refer [atom]]))

(def db (atom {:accordions []
               :menu       false
               :page       :index
               :cart       {}
               :products   [;Main Product
                            {:id     "wakizashi"
                             :title  "SWORDFISH WAKIZASHI"
                             :type   "CARBON FIBRE MONOFIN"
                             :price  "€ 950"
                             :photos ["/img/products/main.png"
                                      "/img/products/1.png"
                                      "/img/products/2.png"]}
                            ;Other products
                            {:id     "central-slider"
                             :title  "SWORDFISH"
                             :type   "CENTRAL SLIDER"
                             :price  "€ 120"
                             :photos ["/img/products/1.png"
                                      "/img/products/1.png"
                                      "/img/products/2.png"]}
                            {:id     "brass-axle"
                             :title  "SWORDFISH WAKIZASHI"
                             :type   "REPLACEMENT BRASS AXLE 8X150MM"
                             :price  "€ 5"
                             :photos ["/img/products/2.png"
                                      "/img/products/1.png"
                                      "/img/products/2.png"]}
                            {:id     "carbon-fibre"
                             :title  "SWORDFISH WAKIZASHI"
                             :type   "CARBON FIBRE BLADE SET"
                             :price  "€ 480"
                             :photos ["/img/products/3.png"
                                      "/img/products/1.png"
                                      "/img/products/2.png"]}
                            {:id     "feet"
                             :title  "SWORDFISH FEET SECTION"
                             :type   "POLYURETHANE & CARBON COMPOSITE"
                             :price  "€ 480"
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

(defn toggle-menu []
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

(defn add-to-cart []
  (swap! db assoc-in [:cart (keyword (str (random-uuid)))]
         (:current-product @db)))

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
  (swap! db assoc :current-product (get-product (:product params))))

(defn change-page [the-key params]
  (swap! db assoc :page the-key :query-params params)
  (if (= the-key :shop)
    (set-current-product params)))