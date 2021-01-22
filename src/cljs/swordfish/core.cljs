(ns swordfish.core
  (:require
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]
   [reagent.session :as session]
   [reitit.frontend :as reitit]
   [clerk.core :as clerk]
   [swordfish.css :as css]
   [accountant.core :as accountant]))



(def router
  (reitit/router
   [["/" :index]
    ["/shop" :shop]
    ["/fact" :fact]
    ["/faq" :faq]
    ["/contact" :contact]]))

(defn path-for [route & [params]]
  (if params
    (:path (reitit/match-by-name router route params))
    (:path (reitit/match-by-name router route))))

;; -------------------------
;; Page components


(defn price-card []
  [:div {:class (css/price-box)}
   [:div {:class [(css/font-size "50px")
                  (css/padding "10px 0px")]}
    "€950"]
   [:div {:class [(css/vertical-align)
                  (css/font-size "20px")
                  (css/padding "10px")]}
    [:div
     [:div  "SHOP"]
     [:div  "NOW"]]]])

(defn home-page []
  (fn []
    [:div {:class (css/main-product-container)}
     [:div {:class (css/wakizashi)}
      [:img {:class (css/wakizashi-img)
             :src "/img/wakizashi.svg"}]
      [:p {:class (css/p-font)}
       [:p "This is the area I have to fill with marketing text that makes you want to wear a Swordfish fin so bad you
       can’t resist hitting that add to cart button"]]
      [price-card]]

     [:img {:class (css/main-product)
            :src "/img/main-product.png"}]]))

(defn page-for [route]
  (case route
    :index #'home-page
    "else"))



(defn menu-link [name href]
  [:div {:class (css/vertical-align)}
   [:a {:class (css/menu-item)
        :href href} name]])

(defn navbar []
  [:header
   [:div {:class (css/navbar)}
    [:a {:href "/"}
        [:img {:class (css/logo) :src "/img/logo.svg"}]]
    [:div {:class (css/nav-line)}]
    [:div {:class (css/menu)}
     [menu-link "HOME" (path-for :index)]
     [menu-link "SHOP" (path-for :shop)]
     [menu-link "FACT" (path-for :fact)]
     [menu-link "FAQ" (path-for :faq)]
     [menu-link "CONTACT" (path-for :contact)]]]])


(defn footer []
  [:footer {:class (css/footer)}
   [:img {:src "/img/paying.png" :class (css/footer-paying)}]])

(defn background-logo []
  [:img {:src "/img/background-logo.svg"
         :class (css/background-logo)}])


;; -------------------------
;; Page mounting component

(defn current-page []
  (fn []
    (let [page (:current-page (session/get :route))]
      [:div {:class (css/background)}
       [background-logo]
       [navbar]
       [page]
       [footer]])))

;; -------------------------
;; Initialize app

(defn mount-root []
  (css/remove-styles!)
  (rdom/render [current-page] (.getElementById js/document "app")))



(defn init! []
  (clerk/initialize!)
  (accountant/configure-navigation!
   {:nav-handler
    (fn [path]
      (let [match (reitit/match-by-path router path)
            current-page (:name (:data  match))
            route-params (:path-params match)]
        (reagent/after-render clerk/after-render!)
        (session/put! :route {:current-page (page-for current-page)
                              :route-params route-params})
        (clerk/navigate-page! path)))

    :path-exists?
    (fn [path]
      (boolean (reitit/match-by-path router path)))})
  (accountant/dispatch-current!)
  (mount-root))
