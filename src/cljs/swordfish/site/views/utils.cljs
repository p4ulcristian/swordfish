(ns swordfish.site.views.utils
  (:require
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [reagent.core :as r]
    [swordfish.site.css.home :as css-home]
    [swordfish.site.db :as db]
    [swordfish.site.setup]))

(defn down-arrow []
  [:div {:class (x-class css-home/down-arrow)}
   [:img {:src "/img/down-arrow.png"}]])

(defn contact-social-icon [url href]
  [:div {:class (x-class css-home/contact-social-icon)}
   [:a {:class (x-class css-home/social-icon-circle)
        :href href :target "_blank"}
    [:span {:class [url]}]]])

(defn contact-social-icons []
  [:div {:class (x-class css-home/contact-social-icons)}
   [contact-social-icon "fab fa-facebook-f" swordfish.site.setup/facebook-link]
   [contact-social-icon "fab fa-youtube"    swordfish.site.setup/youtube-link]
   [contact-social-icon "fab fa-instagram"  swordfish.site.setup/instagram-link]])

(defn social-icon [class url]
  [:div
   {:class (x-class css-home/social-icon)}
   [:a {:href url :target "_blank" :class (x-class css-home/social-icon-href)}
    [:span {:class class}]]])

(defn social-icons []
  [:div {:class (x-class css-home/social-icons)}
   [social-icon "fab fa-facebook"  swordfish.site.setup/facebook-link]
   [social-icon "fab fa-youtube"   swordfish.site.setup/youtube-link]
   [social-icon "fab fa-instagram" swordfish.site.setup/instagram-link]])

(defn as-el [el] (r/as-element el))
