
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.admin.core
  (:require [x.app-core.boot-loader]
            [swordfish.admin.app-views.api]))



;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn boot-app!
  []
  (x.app-core.boot-loader/boot-app!))

(defn render-app!
  []
  (x.app-core.boot-loader/render-app!))
