
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.site.core
    (:require [x.app-core.boot-loader]
              [swordfish.site.views.api]))



;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(def router
  [["/" :index]
   ["/shop/:product" :shop]
   ["/fact" :fact]
   ["/faq" :faq]
   ["/contact" :contact]])

(defn boot-app!
  []
  (x.app-core.boot-loader/boot-app!))

(defn render-app!
  []
  (x.app-core.boot-loader/render-app!))
