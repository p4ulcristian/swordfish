(ns swordfish.admin.app-views.engine
  (:require [app-fruits.reagent :refer [ratom lifecycles]]
            [mid-fruits.candy :refer [param return]]
            [mid-fruits.random :as random]
            [x.app-core.api :as a]))


;Setting up the observer for the Infinite loader component.
(defn setup-observer! [id callback]
  (let [options         {};:root        (.getElementById js/document "scroll-content")
                         ;:root-margin "0px"
                         ;:threshold   0}
        observer        (js/IntersectionObserver.
                          #(let [in-view? (.-isIntersecting (aget % 0))]
                             (if in-view? (callback)))
                          (param options))
        this-element    (.getElementById js/document id)]
    (.observe observer this-element)))


;Infinite loader component. If it gets it the view it triggers a callback
(defn infinite-loader [callback]
  (let [observer (ratom nil)
        element-id (random/generate-string)]
    (lifecycles
      {:component-did-mount #(reset! observer (setup-observer! element-id callback))
       ;:component-will-unmount #(.disconnect @observer)
       :reagent-render      (fn [] [:div {:id    element-id
                                          :style {:display :flex
                                                  :justify-content :center
                                                  :height  "100px" :width "100%"}}
                                    [:div.lds-ring
                                     [:div]
                                     [:div]
                                     [:div]
                                     [:div]]])})))


(defn get-namespaced-id [document]
  (:id (reduce (fn [processed [actual-key actual-value]]
                 (assoc processed
                   (keyword (name actual-key))
                   actual-value))
         {}
         document)))

(a/reg-event-fx
  ::initialize!
  {:dispatch-n
   [[:x.app-environment.css-handler/add-external-source! "css/loading.css"]]})


(a/reg-lifecycles
  ::lifecycles
  {:on-app-boot [::initialize!]})
