
; WARNING! THIS IS AN OUTDATED VERSION OF A MONO-TEMPLATE FILE!



;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.server-router.default-routes
  (:require
    [mid-fruits.candy              :refer [param]]
    [swordfish.server-views.api :as views]
    [server-fruits.http            :as http]
    [x.server-core.api             :as a]))



;; -- Configuration -----------------------------------------------------------
;; ----------------------------------------------------------------------------

; No method matched
(def METHOD-NOT-ALLOWED
  #(http/html-wrap {:body   (views/main %)
                    :status (param 404)}))

; Handler returned nil
(def NOT-ACCEPTABLE
  #(http/html-wrap {:body   (views/main %)
                    :status (param 404)}))

; No route matched – {:status 200} handled at client side
(def NOT-FOUND
  #(http/html-wrap {:body   (views/main %)
                    :status (param 200)}))



;; -- Lifecycle events --------------------------------------------------------
;; ----------------------------------------------------------------------------

(a/reg-event-fx
  ::on-app-init-events
  {:dispatch-n [[:x.server-sync/set-default-route! :method-not-allowed METHOD-NOT-ALLOWED]
                [:x.server-sync/set-default-route! :not-acceptable     NOT-ACCEPTABLE]
                [:x.server-sync/set-default-route! :not-found          NOT-FOUND]]})

(a/reg-lifecycles
  ::lifecycles
  {:on-app-init [::on-app-init-events]})
