(ns swordfish.views.main
  (:require
    [accountant.core :as accountant]
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [reagent.core :as reagent :refer [atom]]
    ;[reagent.dom :as rdom]
    [reagent.session :as session]
    ;[reitit.frontend :as reitit]
    [swordfish.css.home :as css-home]
    [swordfish.css.utils :as css-utils]
    [swordfish.css.utils :as css-utils]
    [swordfish.db :as db]
    [swordfish.router :refer [path-for]]
    [swordfish.setup]
    [swordfish.views.utils :as utils]))



(defn menu-link [name the-key]
  [:div {:class (x-class css-utils/vertical-align)}
   [:a {:data-selected "true"
        :class (x-class css-home/menu-item (db/this-page? the-key))
        :href (path-for the-key)} name]])

(defn mobile-menu []
  [:button {:class (x-class css-utils/mobile-menu)
            :on-click #(db/toggle-menu)} [:span {:class "fas fa-bars"}]])


(defn navbar []
  [:header
   [:div {:class [(x-class css-utils/content-width) (x-class css-home/navbar)]}
    [mobile-menu]
    [:a {:href "/" :class (x-class css-home/logo-container)}
     [:img {:class (x-class css-home/logo) :src "/img/logo.svg"}]]
    [:div {:class (x-class css-home/nav-line)}]
    [:div {:class (x-class css-home/menu)}
     [menu-link "HOME" :index]
     [menu-link "SHOP" :shop]
     [menu-link "FACT" :fact]
     [menu-link "FAQ" :faq]
     [menu-link "CONTACT" :contact]]]])

(defn footer-link [title href]
  [:a {:href href
       :class [(x-class css-utils/padding "10px")
               (x-class css-home/footer-link)
               (x-class css-utils/vertical-align)]}
   title])

(defn footer-links []
  [:div {:class [(x-class css-home/footer-links)]}
   [footer-link "PRIVACY POLICY" "/privacy-policy"]
   [footer-link "SHIPPING & HANDLING" "/shipping-handling"]
   [footer-link "RETURN POLICY" "/return-policy"]
   [footer-link "FAQ" "/faq"]])


(defn social-icon [class url]
  [:div {:class (x-class css-utils/padding "5px 10px")}
   [:a {:href url :target "_blank" :class (x-class css-home/social-icon-href)}
    [:span {:class class}]]])

(defn copyright-social-icons []
  [:div {:class [(x-class css-home/copyright-social-icons)]}
   [social-icon "fab fa-facebook" swordfish.setup/facebook-link]
   [social-icon "fab fa-youtube" swordfish.setup/youtube-link]
   [social-icon "fab fa-instagram" swordfish.setup/instagram-link]])

(defn copyright []
  [:div {:class [(x-class css-home/copyright) (x-class css-utils/vertical-align)]}
   [:div
    [:div {:class [(x-class css-utils/text-color "grey") (x-class css-utils/font-size "10px")]}
     "© 2020, Swordfish Fins."]
    [copyright-social-icons]]])

(defn footer-paying []
  [:div {:class [(x-class css-home/footer-paying-container)]}

   [:img {:src "/img/paying.png" :class (x-class css-home/footer-paying)}]])

(defn footer []
  [:footer {:class [(x-class css-home/footer)]}
   [:div {:class [(x-class css-utils/footer-sections)
                  (x-class css-utils/content-width)]}
    [footer-paying]
    [footer-links]
    [copyright]]])

(defn background-logo []
  [:img {:src "/img/background-logo.svg"
         :class (x-class css-home/background-logo)}])



;; -------------------------
;; Page mounting component





(defn current-page []
  (fn []
    (let [page (:current-page (session/get :route))]
      [:div  {:class (x-class css-home/background)}
       [background-logo]
       [:div {:class (x-class css-utils/for-footer-design)}
        [footer]
        [:div  {:class (x-class css-utils/for-page-design)}
         [navbar]
         [page]]]])))
         ;[background-logo]]]])))
