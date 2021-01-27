(ns swordfish.views.shop
  (:require
    [reagent.core :as reagent :refer [atom]]
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [swordfish.css.utils :as css-utils]
    [swordfish.css.shop :as css]
    [swordfish.views.icons :as icons]))


(defn wakizashi-logo []
  [:div {:class (x-class css/wakizashi-grey-container)}
   [:div {:class (x-class css/wakizashi-grey)}]])

(defn product-title [title]
  [:div {:class (x-class css/product-title)} title])

(defn product-type [title]
  [:div  {:class (x-class css/product-type)} title])

(defn product-price [title]
  [:div {:class (x-class css/product-price)} title])

(defn product-photos [type photos]
  [:div (str photos)])

(defn main-product []
  [:div {:class (x-class css/main-product-container)}
   [:div {:class (x-class css/main-product-card)}
    [:div
     [product-title "SWORDFISH WAKIZASHI"]
     [product-type "CARBON FIBRE MONOFIN"]
     [product-price "€ 950"]]
    [product-photos :bottom ["/img/main-product.png"]]]])

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
   [main-product]
   [main-description]])

(defn shop []
  [:div {:class (x-class css-utils/content-width)}
   [main-section]])