(ns swordfish.views.main
  (:require
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    ;demo purpose
    [reagent.core :as reagent :refer [atom]]
    ;[reagent.dom :as rdom]
    [reagent.session :as session]
    ;[reitit.frontend :as reitit]
    [swordfish.css.home :as css-home]
    [swordfish.utils :as utils]
    [swordfish.css.utils :as css-utils]
    [swordfish.router :refer [path-for]]
    [accountant.core :as accountant]))


(defn menu-link [name href]
  [:div {:class (x-class css-utils/vertical-align)}
   [:a {:data-selected "true"
        :class (x-class css-home/menu-item)
        :href href} name]])

(defn navbar []
  [:header
   [:div {:class [(x-class css-utils/content-width) (x-class css-home/navbar)]}
    [:a {:href "/" :class (x-class css-home/logo-container)}
     [:img {:class (x-class css-home/logo) :src "/img/logo.svg"}]]
    [:div {:class (x-class css-home/nav-line)}]
    [:div {:class (x-class css-home/menu)}
     [menu-link "HOME" (path-for :index)]
     [menu-link "SHOP" (path-for :shop)]
     [menu-link "FACT" (path-for :fact)]
     [menu-link "FAQ" (path-for :faq)]
     [menu-link "CONTACT" (path-for :contact)]]]])

(defn footer-link [title href]
  [:div {:class [(x-class css-utils/padding "10px")
                 (x-class css-home/footer-link)
                 (x-class css-utils/vertical-align)]}
   title])

(defn footer-links []
  [:div {:class [(x-class css-utils/flex)
                 (x-class css-utils/padding "0px 0px 0px 20px")]}
   [footer-link "PRIVACY POLICY" ""]
   [footer-link "SHIPPING & HANDLING" ""]
   [footer-link "RETURN POLICY" ""]
   [footer-link "FAQ" ""]
   [footer-link "MY PERSONAL DATA" ""]])

(defn social-icon [class url]
  [:div {:class (x-class css-utils/padding "5px 10px")}
   [:a {:href url :target "_blank" :class (x-class css-home/social-icon-href)}
    [:span {:class class}]]])

(defn copyright-social-icons []
  [:div {:class [(x-class css-home/copyright-social-icons)]}
   [social-icon "fab fa-facebook" "https://www.facebook.com/swordfishfins"]
   [social-icon "fab fa-youtube" "https://www.youtube.com/channel/UCT17xq2yVM77mdiXMkzd1hg"]
   [social-icon "fab fa-instagram" "https://www.instagram.com/swordfishfins/"]])

(defn copyright []
  [:div {:class [(x-class css-home/copyright) (x-class css-utils/vertical-align)]}
   [:div
    [:div {:class [(x-class css-utils/text-color "grey") (x-class css-utils/font-size "10px")]}
     "© 2020, Swordfish Fins."]
    [copyright-social-icons]]])

(defn footer []
  [:footer {:class [ (x-class css-home/footer)]}
   [:div {:class [(x-class css-utils/flex) (x-class css-utils/content-width)]}
    [:div {:class [(x-class css-utils/padding "10px 0px")]}
     [:img {:src "/img/paying.png" :class (x-class css-home/footer-paying)}]]
    [footer-links]
    [copyright]]])

(defn background-logo []
  [:img {:src "/img/background-logo.svg"
         :class (x-class css-home/background-logo)}])



;; -------------------------
;; Page mounting component

(defn herb-try []
  {:background "red"
   :color "white"})

(defn current-page []
  (fn []
    (let [page (:current-page (session/get :route))]
      [:div  {:class (x-class css-home/background)}
       [background-logo]
       [navbar]
       [page]
       [footer]])))
