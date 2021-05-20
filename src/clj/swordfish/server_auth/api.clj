
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.server-auth.api
    (:require [swordfish.server-auth.permission-handler :as permission-handler]))



;; -- Redirects ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(def gate-of-moria permission-handler/gate-of-moria)
