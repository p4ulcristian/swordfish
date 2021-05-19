(ns swordfish.handler
  (:require
    [reitit.ring :as reitit-ring]
    [hiccup.page :refer [include-js include-css html5]]
    [config.core :refer [env]]))

(def mount-target
  [:div#app])




(defn head []
  [:head
   [:title "Swordfish Fins"]
   [:link {:rel "icon" :type "image/png" :href "/img/favicon.png"}]
   [:link {:href "https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap"
           :rel  "stylesheet"}]
   [:link {:href "https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap"
           :rel  "stylesheet"}]
   [:meta {:charset "utf-8"}]
   [:meta {:name    "theme-color"
           :content "#181819"}]
   [:meta {:name    "viewport"
           :content "width=320, initial-scale=1"}]
   (include-css "/css/normalize.css")
   (include-css "/css/site.css")])

(defn loading-page []
  (html5
    {:id    "sf-document-element"}
    (head)
    [:body {:id    "sf-body-container"
            :class "body-container"}
     mount-target
     (include-js "https://kit.fontawesome.com/f4781bfeea.js")
     (include-js "/js/core/site.js")]))

(defn index-handler
  [_request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (loading-page)})

(def app
  (reitit-ring/ring-handler
    (reitit-ring/router
      [["/" {:get {:handler index-handler}}]
       ["/contact" {:get {:handler index-handler}}]
       ["/faq" {:get {:handler index-handler}}]
       ["/fact" {:get {:handler index-handler}}]
       ["/shop"
        ["" {:get {:handler index-handler}}]
        ["/:a" {:get {:handler index-handler}}]]])
    (reitit-ring/routes
      (reitit-ring/create-resource-handler {:path "/" :root "/public"})
      (reitit-ring/create-default-handler))))
    ;{:middleware middleware}))
