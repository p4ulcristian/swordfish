(ns swordfish.views.shop
  (:require
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [reagent.core :as reagent :refer [atom]]
    [swordfish.css.utils :as css-utils]
    [swordfish.css.shop :as css]
    [swordfish.db :as db]
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
    (for [photo photos] ^{:key (random-uuid)}
                        [product-photo photo])]])

(defn product [{:keys [id title type price photos]}]
  [:a {:href (str "/shop/" id)
       :class (x-class css/product-card (first photos))}
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
   [main-product [product (db/get-main-product)]]
   [main-description]])

(defn one-part [item]
  [:div
   {:class (x-class css/one-part)}
   [product item]])

(defn parts-section []
  [:div {:class (x-class css/parts-section)}
   (for [this (db/get-rest-products)]
     ^{:key (:id this)} [one-part this])])

(defn product-details [product-name]
  [:div "Product data: " (db/get-product product-name) product-name])

(defn shop []
  (let [this-product (:product (db/get-query-params))]
    [:div {:class [(x-class css-utils/page-in-animation) (x-class css-utils/content-width)]}
     (if (= "main" this-product)
       [:<>
        [main-section]
        [down-arrow]
        [parts-section]]
       [product-details this-product])]))