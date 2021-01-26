(ns swordfish.views.home
  (:require
    [reagent.core :as reagent :refer [atom]]
    [swordfish.css.home :as css-home]
    [swordfish.css.utils :as css-utils]
    [accountant.core :as accountant]))


(defn price-card-price []
  [:div {:class [(css-utils/font-size "50px") (css-utils/padding "10px 0px")]}
   "€950"])

(defn shop-now-text []
  [:div
   [:div {:class [(css-utils/font-size "18px")]} "SHOP"]
   [:div  {:class [(css-utils/font-size "20px")]} "NOW"]])

(defn price-card []
  [:div {:class (css-home/price-box)}
   [price-card-price]
   [:div {:class [(css-utils/vertical-align) (css-utils/padding "10px")]}
    [shop-now-text]]])

(defn social-icon [class url]
  [:div
   {:class (css-home/social-icon)}
   [:a {:href url :target "_blank" :class (css-home/social-icon-href)}
    [:span {:class class}]]])

(defn social-icons []
  [:div {:class (css-home/social-icons)}
   [social-icon "fab fa-facebook" "https://www.facebook.com/swordfishfins"]
   [social-icon "fab fa-youtube" "https://www.youtube.com/channel/UCT17xq2yVM77mdiXMkzd1hg"]
   [social-icon "fab fa-instagram" "https://www.instagram.com/swordfishfins/"]])

(defn down-arrow []
  [:div {:class (css-home/down-arrow)} [:img {:src "/img/down-arrow.png"}]])

(defn badge [img-url title desc]
  [:div {:class (css-home/one-badge-container)}
   [:div {:class (css-home/one-badge)}
    [:div [:img {:class (css-home/one-badge-image)
                 :src img-url :width "100"}]]
    [:div {:class (css-home/badge-content-container)}
     [:div {:class (css-home/one-badge-title)} title]
     [:div {:class (css-home/one-badge-desc)} desc]]]])

(defn badges []
  [:div {:class (css-home/badges-container)}
   [badge "/img/main-page-badge-1.svg" "SIZE" [:div "All fins custom made " [:div "for perfect fit"]]]
   [badge "/img/main-page-badge-2.svg" "WARRANTY" [:div "5 Years " [:div "manufacturer warranty"]]]
   [badge "/img/main-page-badge-3.svg" "SHIPPING" [:div "FREE worldwide" [:div "delivery"]]]])


(defn wakizashi []
  [:div {:class (css-home/wakizashi)}
   [:img {:src "/img/wakizashi.svg"}]
   [:p {:class (css-home/p-font)}
    [:p "This is the area I have to fill with marketing text that makes you want to wear a Swordfish fin so bad you
       can’t resist hitting that add to cart button"]]
   [price-card]])

(defn contact-description []
  [:div {:class (css-home/contact-description)}
   [:p "Interested in our progress? If not, just let us know and we will send you adverts"]
   [:p "of other fin manufacturers. If you are, however,"]
   [:p "please put your e-mail address below to receive weekly updates!"]])

(defn no-spam-no-nonsense []
  [:div {:class [(css-home/nospam) (css-utils/flex)]}
   [:div {:class [(css-utils/padding "0px 30px") (css-utils/vertical-align)]}
    [:img {:width 50 :src "/img/favicon.png"}]]

   [:div {:class (css-utils/position "relative")}
    [:div {:class (css-home/newsletter-text)} "NEWSLETTER"]
    [:div {:class (css-home/nospam-text)} "NO SPAM,"]
    [:div {:class (css-home/nospam-text)} "NO NONSENSE"]]])


(defn subscribe-button []
  [:button {:class (css-home/subscribe-button)} "SUBSCRIBE"])

(defn contact-inputs []
  [:div {:class (css-home/contact-inputs)}
   [:input {:class [(css-home/contact-input) (css-home/contact-input-name)]
            :placeholder "Name"}]
   [:input {:class [(css-home/contact-input) (css-home/contact-input-name)]
            :placeholder "Your email"}]
   [subscribe-button]])

(defn contact-social-icon [url]
  [:div {:class (css-home/contact-social-icon)}
   [:a {:href ""}
    [:img {:src url}]]])


(defn contact-social-icons []
  [:div {:class (css-home/contact-social-icons)}
   [contact-social-icon "/img/icons/youtube.svg"]
   [contact-social-icon "/img/icons/facebook.svg"]
   [contact-social-icon "/img/icons/instagram.svg"]])

(defn contact []
  [:div
   [no-spam-no-nonsense]
   [contact-description]
   [contact-inputs]
   [contact-social-icons]])


(defn background-logo-left []
  [:img {:src "/img/background-logo.svg"
         :class (css-home/background-logo-left)}])

(defn home-page []
  (fn []
    [:div {:class [(css-utils/content-width)]}
     [:div {:class (css-home/home-container)}
      [:img {:width "100%" :src "/img/main-product.png"}]
      [social-icons]
      [down-arrow]]
     [wakizashi]
     [badges]
     [contact]
     [background-logo-left]]))



