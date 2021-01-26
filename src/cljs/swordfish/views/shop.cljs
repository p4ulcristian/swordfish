(ns swordfish.views.shop
  (:require
    [reagent.core :as reagent :refer [atom]]
    [swordfish.css.utils :as css-utils]
    [swordfish.css.shop :as css]))


(defn wakizashi-logo []
  [:div {:class (css/wakizashi-grey-container)}
   [:div {:class (css/wakizashi-grey)}]])

(defn main-product-title [title]
  [:div title])

(defn main-product-type [title]
  [:div title])

(defn main-product-price [title]
  [:div title])

(defn product-photos [type photos]
  [:div
   (str photos)])

(defn main-product []
  [:div {:class (css/main-product-container)}
   [:div {:class (css/main-product-card)}
    [:div
     [main-product-title "SWORDFISH WAKIZASHI"]
     [main-product-type "Carbon fibre monofin"]
     [main-product-price "€ 950"]]
    [product-photos :bottom ["/img/main-product.png"]]]])

(defn spacer []
  [:div {:class (css/spacer)}])

(defn one-description [title logo description]
  [:div {:class (css/one-description)}
   [:div {:class (css/one-description-container)}
    [:div
     [:div {:class (css/one-description-title)}
      [:div title]
      [:img {:src logo :class (css/one-description-img)}]]]
    [:div {:class (css/one-description-content)}
     description]]])

(defn main-description []
  [:div {:class (css/main-description)}
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
  [:div  {:class (css/main-section)}
   [wakizashi-logo]
   [main-product]
   [main-description]])

(defn shop []
  [:div {:class (css-utils/content-width)}
   [main-section]])