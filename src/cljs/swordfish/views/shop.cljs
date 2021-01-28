(ns swordfish.views.shop
  (:require
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [reagent.core :as reagent :refer [atom]]
    [swordfish.css.utils :as css-utils]
    [swordfish.css.shop :as css]
    [swordfish.views.icons :as icons]
    [swordfish.views.utils :refer [down-arrow]]))


(defn wakizashi-logo []
  [:div {:class (x-class css/wakizashi-grey-container)}
   [:div {:class (x-class css/wakizashi-grey)}]])

(defn product-title [title]
  [:div {:class (x-class css/product-title)} title])

(defn product-type [title]
  [:div {:class (x-class css/product-type)} title])

(defn product-price [title]
  [:div {:class (x-class css/product-price)} title])

(defn product-photo [photo]
  [:div {:class (x-class css/product-photo-container)}
   [:img {:class (x-class css/product-photo)
          :src   photo}]])


(defn product-photos [photos]
  [:div {:class (x-class css/product-photos-container)}
   [:div {:class (x-class css/product-photos)}
    (for [photo photos] [product-photo photo])]])

(defn product [{:keys [title type price photos]}]
  [:div {:class (x-class css/product-card (first photos))}
   [:div
    [product-title title]
    [product-type type]
    [product-price price]]
   [product-photos photos]])

(defn main-product [product]
  [:div {:class (x-class css/main-product-container)}
   product])

(defn spacer []
  [:div {:class (x-class css/spacer)}])

; @param (string) title
; @param (string) logo
;    This is an url for the logo: "/img/something.png"
; @param (string or vector) description
;
; @return (hiccup-vector)

(defn one-description [title logo description]
  [:div {:class (x-class css/one-description)}
   [:div {:class (x-class css/one-description-container)}
    [:div
     [:div {:class (x-class css/one-description-title)}
      [:div title]
      (icons/plane (x-class css/one-description-icon))]]
    [:div {:class (x-class css/one-description-content)}
     description]]])

(defn main-description []
  [:div {:class (x-class css/main-description)}
   [one-description "SHIPPING" "/img/main-page-badge-1.svg"
    [:<>
     [:div "FREE worldwide"]
     [:div "delivery"]]]
   [spacer]
   [one-description "WARRANTY" "/img/main-page-badge-2.svg"
    [:<>
     [:div "5 Years manufacturer"]
     [:div "warranty"]]]
   [spacer]
   [one-description "SIZE" "/img/main-page-badge-3.svg"
    [:<>
     [:div "All fins custom made"]
     [:div "for perfect fit"]]]])

(defn main-section []
  [:div {:class (x-class css/main-section)}
   [wakizashi-logo]
   [main-product [product {:title  "SWORDFISH WAKIZASHI"
                           :type   "CARBON FIBRE MONOFIN"
                           :price  "€ 950"
                           :photos ["/img/products/main.png"
                                    "/img/products/1.png"
                                    "/img/products/2.png"]}]]
   [main-description]])

(defn parts-section []
  [:div {:class (x-class css/parts-section)}
   [:div [product {:title  "SWORDFISH"
                   :type   "CENTRAL SLIDER"
                   :price  "€ 120"
                   :photos ["/img/products/1.png"
                            "/img/products/1.png"
                            "/img/products/2.png"]}]]
   [:div [product {:title  "SWORDFISH WAKIZASHI"
                   :type   "REPLACEMENT BRASS AXLE 8X150MM"
                   :price  "€ 5"
                   :photos ["/img/products/2.png"
                            "/img/products/1.png"
                            "/img/products/2.png"]}]]
   [:div [product {:title  "SWORDFISH WAKIZASHI"
                   :type   "CARBON FIBRE BLADE SET"
                   :price  "€ 480"
                   :photos ["/img/products/3.png"
                            "/img/products/1.png"
                            "/img/products/2.png"]}]]
   [:div [product {:title  "SWORDFISH FEET SECTION"
                   :type   "POLYURETHANE & CARBON COMPOSITE"
                   :price  "€ 480"
                   :photos ["/img/products/4.png"
                            "/img/products/1.png"
                            "/img/products/2.png"]}]]])

(defn shop []
  [:div {:class (x-class css-utils/content-width)}
   [main-section]
   [down-arrow]
   [parts-section]])