(ns swordfish.handler
  (:require
   [reitit.ring :as reitit-ring]
   [swordfish.middleware :refer [middleware]]
   [hiccup.page :refer [include-js include-css html5]]
   [config.core :refer [env]]))

(def mount-target
  [:div#app])


(defn head []
  [:head
   [:title "Swordfish"]
   [:link {:rel "icon" :type "image/png" :href "/img/favicon.png"}]
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))])

(defn loading-page []
  (html5
   (head)
   [:body {:class "body-container" :style "background: #181819;"}
    mount-target
    (include-js "https://kit.fontawesome.com/f4781bfeea.js")
    (include-js "/js/app.js")
    [:script "swordfish.core.init_BANG_()"]]))


(defn index-handler
  [_request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (loading-page)})

(def app
  (reitit-ring/ring-handler
   (reitit-ring/router
    [["/" {:get {:handler index-handler}}]
     ["/contact" {:get {:handler index-handler}}]
     ["/faq" {:get {:handler index-handler}}]
     ["/fact" {:get {:handler index-handler}}]
     ["/shop" {:get {:handler index-handler}}]

     ["/items"
      ["" {:get {:handler index-handler}}]
      ["/:item-id" {:get {:handler index-handler
                          :parameters {:path {:item-id int?}}}}]]
     ["/about" {:get {:handler index-handler}}]])
   (reitit-ring/routes
    (reitit-ring/create-resource-handler {:path "/" :root "/public"})
    (reitit-ring/create-default-handler))
   {:middleware middleware}))
