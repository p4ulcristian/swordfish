(ns swordfish.views.shop
  (:require
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [reagent.core :as reagent :refer [atom]]
    [swordfish.css.shop :as css]
    [swordfish.css.utils :as css-utils]
    [swordfish.db :as db]
    [swordfish.views.icons :as icons]
    [swordfish.views.shipping :as shipping]
    [swordfish.views.utils :refer [down-arrow]]))


(defn wakizashi-logo []
  [:div {:class (x-class css/wakizashi-grey-container)}
   [:div {:class (x-class css/wakizashi-grey)}]])

(defn product-details-title [title]
  [:div {:class (x-class css/product-details-title)} title])

(defn product-title [title]
  [:div {:class (x-class css/product-title)} title])

(defn product-details-type [title]
  [:div {:class (x-class css/product-details-type)} title])

(defn product-type [title]
  [:div {:class (x-class css/product-type)} title])


(defn quantity-input []
  [:div {:class (x-class css/quantity-group)}
   [:div {:on-click #(db/dec-product-quantity)
          :class    (x-class css/quantity-modify)}
    [:span.fas.fa-minus]]
   [:div [:input {:class     (x-class css/quantity-input)
                  :on-change #(db/change-product-quantity (-> % .-target .-value))
                  :value     (db/get-product-quantity)}]]
   [:div {:on-click #(db/inc-product-quantity)
          :class    (x-class css/quantity-modify)}
    [:span.fas.fa-plus]]])

(defn product-details-price [title]
  [:div {:class (x-class css/product-details-price)}
   [:div {:class (x-class css-utils/vertical-align)}
    [:div title]]
   [:div  {:class (x-class css/stars)}
    [:img {:src "/img/stars.png" :width "130px"}]]])


(defn product-details-quantity []
  [:div {:class (x-class css/product-details-quantity)}
   [:div {:class (x-class css/quantity-label)} "Quantity"]
   [quantity-input]])


(defn product-price [title]
  [:div {:class (x-class css/product-price)} title])

(defn product-photo [photo image-atom]
  [:div {:on-click #(do
                      (.stopPropagation %)
                      (reset! image-atom photo))



         :class (x-class css/product-photo-container)}
   [:img {:class (x-class css/product-photo)
          :src   photo}]])

(defn product-photos [photos image-atom]
  [:div {:class (x-class css/product-photos)}
   (for [photo photos] ^{:key (random-uuid)}
                       [product-photo photo image-atom])])

(defn product [{:keys [id title type price photos]}]
  (let [image-atom (atom (first photos))]
    (fn [{:keys [id title type price photos]}]
      [:div
       [:a {:href  (str "/shop/" id)
            :class (x-class css/product-card @image-atom)}
        [product-title title]
        [product-type type]
        [product-price price]]
       [product-photos photos image-atom]])))

(defn product-in-details [{:keys [id title type price photos]}]
  (let [image-atom (atom (first photos))]
    (fn [{:keys [id title type price photos]}]
      [:div {:class (x-class css/product-details-card (first photos))}
       [product-photos photos image-atom]])))

(defn add-to-cart-button []
  [:button {:on-click #(db/add-to-cart)
            :class (x-class css/add-to-cart-button)}
   "ADD TO CART"])

(defn one-guarantee [icon text]
  [:div {:class (x-class css-utils/flex)}
   [:div {:class (x-class css-utils/vertical-align)} icon]
   [:div {:class (x-class css/guarantee-label)}
    text]])

(defn guarantees []
  [:div
   [one-guarantee [icons/plane (x-class css/icon)] "Free worldwide delivery"]
   [one-guarantee [icons/shield (x-class css/icon)] "5 Years manufacturer warranty"]
   [one-guarantee [icons/leg (x-class css/icon)] "All fins custom made for the perfect fit. \nCheck feet size and customization for more details."]])
   ;[:div [:img {:src "/img/safe-checkout.png" :width "350px"}]]])

(defn product-details-buying [{:keys [title type price]}]
  [:div {:class (x-class css/product-details-buying)}
   [product-details-title title]
   [product-details-type type]
   [product-details-price price]
   [product-details-quantity]
   [add-to-cart-button]
   [guarantees]])

(defn product-description []
  [:div {:class (x-class css/buying-description)}
   [:h1 "SWORDFISH WAKIZASHI"]
   [:p "This is the area I have to fill with marketing text that makes you want to wear a Swordfish fin so bad you can’t resist hitting that add to cart button. Truth is that if learning about what we created, seeing it in action and in the making didn’t make you feel the urge to grow a sword, swim head on towards your fears, crush them and conquer yourself - than reading this probably won’t either. "]
   [:p "Swordfish is salvation to you when it comes to fin swimming, that’s clear, but what are you to us? On paper yes, you could be a customer - our accountant will call you one - but to us (Berci and Ananda) you are a fellow soul who believes they can do better, who understands there’s a better way. It sounds super cheesy, I know, but the truth is we want only the best for you, that’s why we didn’t stop development when Swordfish was just a little better than other monofins out there.\n"]
   [:p "If you aren’t sure whether Swordfish is the right choice for you, go head and check our FAQ to find our recommendation for other manufacturers. "]
   [:p "Want to try both blades? Head over to our Facts page to see the difference between the two available options. The Swordfish feet section module works with both our Katana and Wakizashi blades, they’re easily interchangeable. You can purchase Katana blades as an addition later, or spend less by getting them both in a bundle now!\n"]
   [:p "To find out more about Swordfish follow us on YouTube or see our Facts page.And remember, as Berci says; If you only have one breath, use it wisely! Enjoy your dive!"]])

(defn detail-categories []
  [:div {:class (x-class css/detail-categories)}
   [:div "DESCRIPTION"]
   [:div "TECH SPEC"]
   [:div "FEET SIZE"]
   [:div "WARRANTY"]
   [:div "CUSTOMIZATION"]
   [:div "SHIPPING"]])

(defn product-details [product-name]
  (let [this-product (db/get-product product-name)]
    [:div
     [:div {:class (x-class css/product-details)}
      [product-in-details this-product]
      [product-details-buying this-product]]
     [down-arrow]
     [detail-categories]
     [product-description this-product]]))

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


(defn one-cart [{:keys [title type price quantity]}]
  [:div
   {:class [ (x-class css/one-cart)]}
   [:div  {:class [ (x-class css/one-cart-title)]}
    title " - " type]
   [:div {:class [ (x-class css/one-cart-price)]}
    price " x " quantity]])

(defn cart [shipping-form?]
  (if
    (not (empty? (db/get-cart-items)))
    [:div {:class (x-class css/cart)}
     [:h1 {:class (x-class css/cart-title)}
          "CART"]
     [:div {:class (x-class css/cart-content)}
      (for [[cart-key cart-item] (db/get-cart-items)]
        ^{:key cart-key} [one-cart cart-item])]
     [:div {:class (x-class css/cart-total)}
        [:div "Total: "]
        [:div {:class (x-class css/cart-total-price)}
         "420 euro"]]
     [:button {:on-click #(reset! shipping-form? true)
               :class (x-class css/shipping-button)}
      "SHIPPING AND PAYMENT"]]))

(defn shop []
  (let [shipping-form? (atom false)
        this-product (:product (db/get-query-params))]
    (fn []
      [:div {:class [(x-class css-utils/page-in-animation) (x-class css-utils/content-width)]}
       [cart shipping-form?]
       (if @shipping-form?
         [shipping/shipping]
         (if (= "main" this-product)
           [:<>
            [main-section]
            [down-arrow]
            [parts-section]]
           [product-details this-product]))])))