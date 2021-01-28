(ns swordfish.views.utils
  (:require
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [swordfish.css.home :as css-home]
    [swordfish.setup]))



(defn down-arrow []
  [:div {:class (x-class css-home/down-arrow)}
   [:img {:src "/img/down-arrow.png"}]])


(defn contact-social-icon [url]
  [:div {:class (x-class css-home/contact-social-icon)}
   [:a {:href ""}
    [:img {:src url}]]])


(defn contact-social-icons []
  [:div {:class (x-class css-home/contact-social-icons)}
   [contact-social-icon "/img/icons/youtube.svg"]
   [contact-social-icon "/img/icons/facebook.svg"]
   [contact-social-icon "/img/icons/instagram.svg"]])

(defn social-icon [class url]
  [:div
   {:class (x-class css-home/social-icon)}
   [:a {:href url :target "_blank" :class (x-class css-home/social-icon-href)}
    [:span {:class class}]]])

(defn social-icons []
  [:div {:class (x-class css-home/social-icons)}
   [social-icon "fab fa-facebook" swordfish.setup/facebook-link]
   [social-icon "fab fa-youtube" swordfish.setup/youtube-link]
   [social-icon "fab fa-instagram" swordfish.setup/instagram-link]])
