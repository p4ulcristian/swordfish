(ns swordfish.site.views.main
  (:require
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [reagent.core :as reagent :refer [atom]]
    [swordfish.site.css.home  :as css-home]
    [swordfish.site.css.main  :as css-main]
    [swordfish.site.css.utils :as css-utils]
    [swordfish.site.db :as db]
    [swordfish.site.setup]
    [swordfish.site.views.utils :as utils]
    [x.app-core.api :as a]))


(defn background-logo []
  [:img {:src   "/img/background-logo.svg"
         :class (x-class css-home/background-logo)}])


(def links [["HOME" :index]
            ["SHOP" :shop]
            ["FACT" :fact]
            ["FAQ" :faq]
            ["CONTACT" :contact]])


(defn menu-link [name the-key params]
  [:div {:class (x-class css-utils/vertical-align)}
   [:a {:data-selected "true"
        :class         (x-class css-main/menu-item (db/this-page? the-key))}]
        ;:href          (path-for the-key {:product "main"})} name]
   (if (and
         (= name "SHOP")
         (not (empty? (db/get-cart-items))))
     [:span {:class (x-class css-main/menu-item-badge)}
      (count (db/get-cart-items))])])
(defn mobile-menu-link [name the-key]
  [:div {:class (x-class css-main/mobile-menu-item (db/this-page? the-key))}
   [:a {:data-selected "true"
        :on-click      #(db/toggle-menu)}]])
        ;:href          (path-for the-key {:product "main"})} name]])


(defn mobile-menu []
  [:div  {:class [(x-class css-main/mobile-menu)
                  (if (db/menu-open?) (x-class css-main/mobile-menu-opened))]}
   [:div {:class (x-class css-main/mobile-menu-overlay)}]
   [:div {:class (x-class css-main/mobile-menu-content)}
    (for [link links]
      ^{:key (second link)} [mobile-menu-link (first link) (second link)])
    [background-logo]]])

(defn mobile-menu-button []
  [:button {:class    (x-class css-main/mobile-menu-button)
            :on-click #(db/toggle-menu)} [:span {:class "fas fa-bars"}]])

(defn menu []
  [:div {:class (x-class css-main/menu)}
   (for [link links]
     ^{:key (second link)} [menu-link (first link) (second link)])])

(defn navbar []
  [:header
   [:div {:class [(x-class css-utils/content-width) (x-class css-main/navbar)]}
    (if (not (db/m?))
      [:<>
       [mobile-menu-button]])
    [:a {:href "/" :class (x-class css-main/logo-container)}
     [:img {:class (x-class css-main/logo) :src "/img/logo.svg"}]]
    (if (db/m?)
      [:<>
       [:div {:class (x-class css-main/nav-line)}]
       [menu]])]])

(defn footer-link [title href]
  [:a {:href  href
       :class [(x-class css-utils/padding "10px")
               (x-class css-main/footer-link)
               (x-class css-utils/vertical-align)]}
   title])

(defn footer-links []
  [:div {:class [(x-class css-main/footer-links)]}
   [footer-link "PRIVACY POLICY" "/privacy-policy"]
   [footer-link "SHIPPING & HANDLING" "/shipping-handling"]
   [footer-link "RETURN POLICY" "/return-policy"]
   [footer-link "FAQ" "/faq"]])

(defn social-icon [class url]
  [:div {:class (x-class css-utils/padding "5px 10px")}
   [:a {:href url :target "_blank" :class (x-class css-home/social-icon-href)}
    [:span {:class class}]]])

(defn copyright-social-icons []
  [:div {:class [(x-class css-main/copyright-social-icons)]}
   [social-icon "fab fa-facebook" swordfish.site.setup/facebook-link]
   [social-icon "fab fa-youtube" swordfish.site.setup/youtube-link]
   [social-icon "fab fa-instagram" swordfish.site.setup/instagram-link]])

(defn copyright []
  [:div {:class [(x-class css-main/copyright) (x-class css-utils/vertical-align)]}
   [:div
    [:div {:class [(x-class css-utils/text-color "grey") (x-class css-utils/font-size "10px")]}
     "© 2020, Swordfish Fins."]
    [copyright-social-icons]]])

(defn footer-paying []
  [:div {:class [(x-class css-main/footer-paying-container)]}
   [:img {:src "/img/paying.png" :class (x-class css-main/footer-paying)}]])

(defn footer []
  [:footer {:class [(x-class css-main/footer)]}
   [:div {:class [(x-class css-main/footer-sections (db/l?))
                  (x-class css-utils/content-width)]}
    [footer-paying]
    [footer-links]
    [copyright]]])

;; -------------------------
;; Page mounting component

(defn view []
  (fn []
    (let [page :index]
      [:div {:class (x-class css-home/background)}
       [background-logo]
       [:div {:class (x-class css-main/for-footer-design)}
        [footer]
        [:div {:class (x-class css-main/for-page-design)}
         [navbar]
         [page]]]
       [mobile-menu]])))

(a/reg-event-fx ::render!
  [:x.app-ui/set-surface!
   ::view
   {:content #'view
    :secondary-button {:preset :menu-icon-button}}])


(a/reg-event-fx
  ::on-app-boot-events
  {:dispatch-n [[:x.app-sync/set-home! {:route-event [::render!]}]]})


(a/reg-lifecycles
  ::lifecycles
  {:on-app-boot [::on-app-boot-events]})