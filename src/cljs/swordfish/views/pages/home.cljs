(ns swordfish.views.pages.home
  (:require
    [reagent.core :as reagent :refer [atom]]
    ;[reagent.dom :as rdom]
    ;[reagent.session :as session]
    [swordfish.views.css :as css]
    [accountant.core :as accountant]))


(defn price-card-price []
  [:div {:class [(css/font-size "50px") (css/padding "10px 0px")]}
   "€950"])

(defn shop-now-text []
  [:div
   [:div {:class [(css/font-size "18px")]} "SHOP"]
   [:div  {:class [(css/font-size "20px")]} "NOW"]])

(defn price-card []
  [:div {:class (css/price-box)}
   [price-card-price]
   [:div {:class [(css/vertical-align) (css/padding "10px")]}
    [shop-now-text]]])

(defn social-icon [class url]
  [:div
   {:class (css/social-icon)}
   [:a {:href url :target "_blank" :class (css/social-icon-href)}
    [:span {:class class}]]])

(defn social-icons []
  [:div {:class (css/social-icons)}
   [social-icon "fab fa-facebook" "https://www.facebook.com/swordfishfins"]
   [social-icon "fab fa-youtube" "https://www.youtube.com/channel/UCT17xq2yVM77mdiXMkzd1hg"]
   [social-icon "fab fa-instagram" "https://www.instagram.com/swordfishfins/"]])

(defn down-arrow []
  [:div {:class (css/down-arrow)} [:img {:src "/img/down-arrow.png"}]])

(defn badge [img-url title desc]
  [:div {:class (css/one-badge-container)}
   [:div {:class (css/one-badge)}
    [:div [:img {:class (css/one-badge-image)
                 :src img-url :width "100"}]]
    [:div {:class (css/badge-content-container)}
     [:div {:class (css/one-badge-title)} title]
     [:div {:class (css/one-badge-desc)} desc]]]])

(defn badges []
  [:div {:class (css/badges-container)}
   [badge "/img/main-page-badge-1.svg" "SIZE" [:div "All fins custom made " [:div "for perfect fit"]]]
   [badge "/img/main-page-badge-2.svg" "WARRANTY" [:div "5 Years " [:div "manufacturer warranty"]]]
   [badge "/img/main-page-badge-3.svg" "SHIPPING" [:div "FREE worldwide" [:div "delivery"]]]])


(defn wakizashi []
  [:div {:class (css/wakizashi)}
   [:img {:src "/img/wakizashi.svg"}]
   [:p {:class (css/p-font)}
    [:p "This is the area I have to fill with marketing text that makes you want to wear a Swordfish fin so bad you
       can’t resist hitting that add to cart button"]]
   [price-card]])

(defn contact-description []
  [:div {:class (css/contact-description)}
   [:p "Interested in our progress? If not, just let us know and we will send you adverts"]
   [:p "of other fin manufacturers. If you are, however,"]
   [:p "please put your e-mail address below to receive weekly updates!"]])

(defn no-spam-no-nonsense []
  [:div {:class [(css/nospam) (css/flex)]}
   [:div {:class [(css/padding "10px") (css/vertical-align)]}
    [:img {:width 50 :src "/img/favicon.png"}]]

   [:div {:class (css/position "relative")}
    [:div {:class (css/newsletter-text)} "NEWSLETTER"]
    [:div {:class (css/nospam-text)} "NO SPAM,"]
    [:div {:class (css/nospam-text)} "NO NONSENSE"]]])

(defn contact-inputs []
  [:div
   [:input {:placeholder "Name"}]
   [:input {:placeholder "Your email"}]
   [:button "SUBSCRIBE"]])

(defn contact-social-icon [url]
  [:div {:class (css/contact-social-icon)}
   [:a {:href ""}
    [:img {:src url}]]])


(defn contact-social-icons []
  [:div {:class (css/contact-social-icons)}
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
         :class (css/background-logo-left)}])

(defn home-page []
  (fn []
    [:div {:class [(css/content-width)]}
     [:div {:class (css/home-container)}
      [:img {:width "100%" :src "/img/main-product.png"}]
      [social-icons]
      [down-arrow]]
     [wakizashi]
     [badges]
     [contact]
     [background-logo-left]]))



