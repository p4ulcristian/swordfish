(ns swordfish.router
  (:require
    [reagent.core :as reagent :refer [atom]]
    [reitit.frontend :as reitit]
    [swordfish.views.pages.home :refer [home-page]]))

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

(defn page-for [route]
  (case route
    :index #'home-page
    "else"))