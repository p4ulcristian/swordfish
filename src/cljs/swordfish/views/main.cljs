(ns swordfish.views.main
  (:require
    [reagent.core :as reagent :refer [atom]]
    ;[reagent.dom :as rdom]
    [reagent.session :as session]
    ;[reitit.frontend :as reitit]
    [swordfish.views.css :as css]
    [swordfish.router :refer [path-for]]
    [accountant.core :as accountant]))


(defn menu-link [name href]
  [:div {:class (css/vertical-align)}
   [:a {:class (css/menu-item)
        :href href} name]])

(defn navbar []
  [:header
   [:div {:class [(css/content-width) (css/navbar)]}
    [:a {:href "/" :class (css/logo-container)}
     [:img {:class (css/logo) :src "/img/logo.svg"}]]
    [:div {:class (css/nav-line)}]
    [:div {:class (css/menu)}
     [menu-link "HOME" (path-for :index)]
     [menu-link "SHOP" (path-for :shop)]
     [menu-link "FACT" (path-for :fact)]
     [menu-link "FAQ" (path-for :faq)]
     [menu-link "CONTACT" (path-for :contact)]]]])

(defn footer-link [title href]
  [:div {:class [(css/padding "10px")
                 (css/vertical-align)]}
   title])

(defn footer-links []
  [:div {:class [(css/padding "0px 0px 0px 20px") (css/flex)]}
   [footer-link "PRIVACY POLICY" ""]
   [footer-link "SHIPPING & HANDLING" ""]
   [footer-link "RETURN POLICY" ""]
   [footer-link "FAQ" ""]
   [footer-link "MY PERSONAL DATA" ""]])

(defn social-icon [class url]
  [:div {:class (css/padding "5px 10px")}
   [:a {:href url :target "_blank" :class (css/social-icon-href)}
    [:span {:class class}]]])

(defn copyright-social-icons []
  [:div {:class [(css/copyright-social-icons)]}
   [social-icon "fab fa-facebook" "https://www.facebook.com/swordfishfins"]
   [social-icon "fab fa-youtube" "https://www.youtube.com/channel/UCT17xq2yVM77mdiXMkzd1hg"]
   [social-icon "fab fa-instagram" "https://www.instagram.com/swordfishfins/"]])

(defn copyright []
  [:div {:class [(css/copyright) (css/vertical-align)]}
   [:div
    [:div {:class [(css/text-color "grey") (css/font-size "10px")]}
     "© 2020, Swordfish Fins."]
    [copyright-social-icons]]])

(defn footer []
  [:footer {:class [ (css/footer)]}
   [:div {:class [(css/flex) (css/content-width)]}
    [:div {:class [(css/padding "10px 0px")]}
     [:img {:src "/img/paying.png" :class (css/footer-paying)}]]
    [footer-links]
    [copyright]]])

(defn background-logo []
  [:img {:src "/img/background-logo.svg"
         :class (css/background-logo)}])



;; -------------------------
;; Page mounting component

(defn current-page []
  (fn []
    (let [page (:current-page (session/get :route))]
      [:div {:class [(css/background)]}
       [background-logo]
       [navbar]
       [page]
       [footer]])))
