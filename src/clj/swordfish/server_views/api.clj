
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.server-views.api
    (:require [swordfish.server-views.admin :as admin]
              [swordfish.server-views.main  :as main]))



;; -- Redirects ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

; project-name.server-views.admin
(def admin admin/view)

; project-name.server-views.main
(def main main/view)
