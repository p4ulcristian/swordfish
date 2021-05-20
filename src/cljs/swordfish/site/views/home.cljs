(ns swordfish.site.views.home
  (:require
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [reagent.core :as reagent :refer [atom]]
    [swordfish.site.css.home :as css-home]
    [swordfish.site.css.utils :as css-utils]
    [swordfish.site.db :as db]
    [swordfish.site.setup]
    [swordfish.site.views.utils :refer [down-arrow]]
    [swordfish.site.views.utils :as utils]))

(defn price-card-price []
  [:div {:class [(x-class css-utils/font-size "50px") (x-class css-utils/padding "10px 0px")]}
   "€950"])

(defn shop-now-text []
  [:div
   [:div {:class [(x-class css-utils/font-size "18px")]} "SHOP"]
   [:div {:class [(x-class css-utils/font-size "20px")]} "NOW"]])

(defn price-card []
  [:div {:class (x-class css-home/price-box-container)}
   [:a {:href "/shop/wakizashi"}
       [:div {:class (x-class css-home/price-box)}
        [price-card-price]
        [:div {:class [(x-class css-utils/vertical-align) (x-class css-utils/padding "10px")]}
         [shop-now-text]]]]])


(defn badge [img-url title desc]
  [:div {:class (x-class css-home/one-badge-container)}
   [:div {:class (x-class css-home/one-badge)}
    [:div [:img {:class (x-class css-home/one-badge-image)
                 :src   img-url :width "90"}]]
    [:div {:class (x-class css-home/badge-content-container)}
     [:div {:class (x-class css-home/one-badge-title)} title]
     [:div {:class (x-class css-home/one-badge-desc)} desc]]]])

(defn badges []
  [:div {:class (x-class css-home/badges-container)}
   [badge "/img/main-page-badge-1.svg" "SIZE" [:div "All fins custom made " [:div "for perfect fit"]]]
   [badge "/img/main-page-badge-2.svg" "WARRANTY" [:div "5 Years " [:div "manufacturer warranty"]]]
   [badge "/img/main-page-badge-3.svg" "SHIPPING" [:div "FREE worldwide" [:div "delivery"]]]])


(defn wakizashi []
  [:div {:class (x-class css-home/wakizashi)}
   [:div {:class (x-class css-home/wakizashi-img)}
    [:img {:src "/img/wakizashi.svg"}]]
   [:div {:class (x-class css-home/wakizashi-description)}
    [:p "This is the area I have to fill with marketing text that makes you want to wear a Swordfish fin so bad you
       can’t resist hitting that add to cart button"]]
   [price-card]])


(defn contact-description []
  [:div {:class (x-class css-home/contact-description)}
   [:p "Interested in our progress? If not, just let us know and we will send you adverts"]
   [:p "of other fin manufacturers. If you are, however,"]
   [:p "please put your e-mail address below to receive weekly updates!"]])

(defn no-spam-no-nonsense []
  [:div {:class [(x-class css-home/nospam)]}
   [:div {:class [(x-class css-home/nospam-logo) (x-class css-utils/vertical-align)]}
    [:img {:width 50 :src "/img/favicon.png"}]]

   [:div {:class (x-class css-utils/position "relative")}
    [:div {:class (x-class css-home/newsletter-text)} "NEWSLETTER"]
    [:div {:class (x-class css-home/nospam-text)} "NO SPAM,"]
    [:div {:class (x-class css-home/nospam-text)} "NO NONSENSE"]]])


(defn subscribe-button []
  [:button {:class (x-class css-home/subscribe-button)} "SUBSCRIBE"])

(defn contact-inputs []
  [:div {:class (x-class css-home/contact-inputs)}
   [:div {:class (x-class css-home/contact-inputs-container)}
    [:div [:input {:class       [(x-class css-home/contact-input)]
                   :placeholder "Name"}]]
    [:div [:input {:class       [(x-class css-home/contact-input)]
                   :placeholder "Your email"}]]
    [subscribe-button]]])



(defn contact []
  [:div
   [no-spam-no-nonsense]
   [contact-description]
   [contact-inputs]
   [:div {:class (x-class css-utils/margin "60px 0px 60px 0px")}
    [utils/contact-social-icons]]])


(defn background-logo-left []
  [:img {:src   "/img/background-logo.svg"
         :class (x-class css-home/background-logo-left)}])


(defn main-section []
  [:div {:class (x-class css-home/main-section)}
   [:div {:class (x-class css-home/main-section-img-container)}
    [:img {:src "/img/main-product.png"}]]
   [:div {:class (x-class css-home/wakizashi-container)}
    [wakizashi]]
   [down-arrow]
   (if (db/s?)
     [utils/social-icons]
     [:div {:class (x-class css-utils/margin "10px 0px")}
      [utils/contact-social-icons]])])

(defn home-page []
  (fn []
    [:div {:class [(x-class css-utils/content-width)
                   (x-class css-utils/page-in-animation)]}
     [main-section]
     [badges]
     [contact]
     [background-logo-left]]))



