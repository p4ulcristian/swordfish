
; WARNING! THIS IS AN OUTDATED VERSION OF A MONO-TEMPLATE FILE!



;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.server-router.routes
  (:require
    [server-fruits.http            :as http]
    [swordfish.server-db.api    :as db]
    [swordfish.server-views.api :as views]
    [x.server-core.api             :as a]))



;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(def ROUTES
  {:main
   {:route-template "/"
    :get #(http/html-wrap {:body (views/main %)})
    :js "site.js"}

   :admin
   {:route-template "/admin"
    :get #(http/html-wrap {:body (views/main %)})
    :js "admin.js"}

   :admin/page
   {:route-template "/admin/:page-id"
    :get #(http/html-wrap {:body (views/main %)})
    :js "admin.js"}

   :db/query
   {:route-template "/db/query"
    :post #(http/map-wrap {:body (db/process! %)})}})



;; -- Lifecycle events --------------------------------------------------------
;; ----------------------------------------------------------------------------

(a/reg-event-fx
  ::on-app-init-events
  [:x.server-sync/add-routes! ROUTES])

(a/reg-lifecycles
  ::lifecycles
  {:on-app-init [::on-app-init-events]})
