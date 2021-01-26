(ns swordfish.views.main
  (:require
    [reagent.core :as reagent :refer [atom]]
    ;[reagent.dom :as rdom]
    [reagent.session :as session]
    ;[reitit.frontend :as reitit]
    [swordfish.css.home :as css-home]
    [swordfish.css.utils :as css-utils]
    [swordfish.router :refer [path-for]]
    [accountant.core :as accountant]))


(defn menu-link [name href]
  [:div {:class (css-utils/vertical-align)}
   [:a {:class (css-home/menu-item)
        :href href} name]])

(defn navbar []
  [:header
   [:div {:class [(css-utils/content-width) (css-home/navbar)]}
    [:a {:href "/" :class (css-home/logo-container)}
     [:img {:class (css-home/logo) :src "/img/logo.svg"}]]
    [:div {:class (css-home/nav-line)}]
    [:div {:class (css-home/menu)}
     [menu-link "HOME" (path-for :index)]
     [menu-link "SHOP" (path-for :shop)]
     [menu-link "FACT" (path-for :fact)]
     [menu-link "FAQ" (path-for :faq)]
     [menu-link "CONTACT" (path-for :contact)]]]])

(defn footer-link [title href]
  [:div {:class [(css-utils/padding "10px")
                 (css-home/footer-link)
                 (css-utils/vertical-align)]}
   title])

(defn footer-links []
  [:div {:class [(css-utils/flex)
                 (css-utils/padding "0px 0px 0px 20px")]}
   [footer-link "PRIVACY POLICY" ""]
   [footer-link "SHIPPING & HANDLING" ""]
   [footer-link "RETURN POLICY" ""]
   [footer-link "FAQ" ""]
   [footer-link "MY PERSONAL DATA" ""]])

(defn social-icon [class url]
  [:div {:class (css-utils/padding "5px 10px")}
   [:a {:href url :target "_blank" :class (css-home/social-icon-href)}
    [:span {:class class}]]])

(defn copyright-social-icons []
  [:div {:class [(css-home/copyright-social-icons)]}
   [social-icon "fab fa-facebook" "https://www.facebook.com/swordfishfins"]
   [social-icon "fab fa-youtube" "https://www.youtube.com/channel/UCT17xq2yVM77mdiXMkzd1hg"]
   [social-icon "fab fa-instagram" "https://www.instagram.com/swordfishfins/"]])

(defn copyright []
  [:div {:class [(css-home/copyright) (css-utils/vertical-align)]}
   [:div
    [:div {:class [(css-utils/text-color "grey") (css-utils/font-size "10px")]}
     "© 2020, Swordfish Fins."]
    [copyright-social-icons]]])

(defn footer []
  [:footer {:class [ (css-home/footer)]}
   [:div {:class [(css-utils/flex) (css-utils/content-width)]}
    [:div {:class [(css-utils/padding "10px 0px")]}
     [:img {:src "/img/paying.png" :class (css-home/footer-paying)}]]
    [footer-links]
    [copyright]]])

(defn background-logo []
  [:img {:src "/img/background-logo.svg"
         :class (css-home/background-logo)}])



;; -------------------------
;; Page mounting component


(defn current-page []
  (fn []
    (let [page (:current-page (session/get :route))]
      [:div {:class [(css-home/background)]}
       [background-logo]
       [navbar]
       [page]
       [footer]])))
