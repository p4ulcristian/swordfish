(ns swordfish.router
  (:require
    [reagent.core :as reagent :refer [atom]]
    [reitit.frontend :as reitit]
    [swordfish.views.home :refer [home-page]]
    [swordfish.views.shop :refer [shop]]
    [swordfish.views.fact :refer [fact]]
    [swordfish.views.faq :refer [faq]]
    [swordfish.views.contact :refer [contact]]))

(def router
  (reitit/router
    [["/" :index]
     ["/shop/:product" :shop]
     ["/fact" :fact]
     ["/faq" :faq]
     ["/contact" :contact]]))

(defn path-for [route & [params]]
  (if params
    (:path (reitit/match-by-name router route params))
    (:path (reitit/match-by-name router route))))

(defn page-for [route]
  (case route
    :index #'home-page
    :shop #'shop
    :fact #'fact
    :faq #'faq
    :contact #'contact
    "else"))