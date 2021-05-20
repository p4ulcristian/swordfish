
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.admin.app-views.playground
    (:require [mid-fruits.candy      :refer [param return]]
              [x.app-core.api        :as a :refer [r]]
              [x.app-db.api          :as db]
              [x.app-developer.api   :as developer]
              [x.app-elements.api    :as elements]
              [x.app-environment.api :as environment]
              [x.app-ui.api :as ui]
              [swordfish.admin.app-views.control-bar :refer [view] :rename {view control-bar}]))



;; -- Subscriptions -----------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- get-view-props
  ; @return (map)
  ;  {:browser-online? (boolean)
  ;   :developer-mode-detected? (boolean)}
  [db _]
  ; TEMP
  ; (return db)
  {:eql-answer               (:eql-answer db)
   :browser-online?          (r environment/browser-online? db)
   :developer-mode-detected? (r developer/developer-mode-detected? db)})

(a/reg-sub ::get-view-props get-view-props)

(defn- get-eql-params
  [db _]
  {:query (:eql-query db)})

(a/reg-sub ::get-eql-params get-eql-params)

(defn- button-disabled?
  [db _]
  (r environment/scroll-direction-btt? db))

(a/reg-sub ::button-disabled? button-disabled?)



;; -- Effect events -----------------------------------------------------------
;; ----------------------------------------------------------------------------

(a/reg-event-fx
  ::send-eql-request!
  (fn [{:keys [db]} _]
      [:x.app-sync/send-request!
       ::http-test
       {:method      :post
        :params      (r get-eql-params db)
        :target-path [:eql-answer]
        :uri         "/db/query"}]))



;; -- Components --------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn send-request-button []
  [elements/button
   ::button
   {:label      "Send request"
    :process-id ::http-test
    :variant    :outlined
    :on-click   [::send-eql-request!]}])

(defn query-button
  [query]
  [:a {:on-click #(a/dispatch [:x.app-db/set-item! [:eql-query] query])
       :style    {:cursor "pointer"}}
      (param query)])

(defn- playground
  ; @param (keyword) box-id
  ; @param (map) view-props
  ;
  ; @return (component)
  [_ {:keys [eql-answer]}]
  [:<>
   [elements/multiline-field {:value-path [:eql-query] :style {:min-width "500px"}}]
   [send-request-button]
   [elements/separator {:size :xxl}]
   [elements/horizontal-line {:color :highlight}]
   [elements/label {:content "Answer from the server" :font-size :xxl}]
   [elements/text  {:content (str eql-answer) :horizontal-align :left}]
   [elements/separator {:size :xxl}]
   [elements/horizontal-line {:color :highlight}]
   [elements/label {:content "Examples" :font-size :xxl}]
   [elements/label {:content "Resolvers" :font-size :l}]
   [query-button (str "[(:documents " {:coll             "items"
                                       :namespace        :item
                                       :search-term      ""
                                       :search-key       :name
                                       :sort-map         {:sort-key :order
                                                          :sort-direction 1}
                                       :skip-page        0
                                       :max-count        10} ")]")]
   [query-button "[{[:profile/id \"Anonymous\"] [:profile/first-name]}]"]
   [query-button "[{[:profile/id \"Anonymous\"] [:profile/first-name]}]"]
   [query-button "[{[:profile/id \"Anonymous\"] [:profile/first-name :profile/birthday]}]"]
   [query-button "[{[:profile/id \"Anonymous\"] [:profile/full-name :profile/birth-year]}]"]
   [query-button "[:all-user-profiles]"]
   [query-button "[(:fruits {:skip 0 :max-count 1 :pattern {:fruit/color \"yellow\" } :sort-key :id})]"]
   [elements/separator {:size :m :direction :horizontal}]
   [elements/label {:content "Mutations" :font-size :l}]
   [query-button (str "[(universal/add-update-with-order " {:collection "items" :document {:name "Paul"} :namespace :item} ")]")]
   [query-button (str "[(universal/duplicate-with-order " {:id "*insert-id-here*" :collection "items" :namespace :item} ")]")]
   [query-button (str "[(universal/delete-with-order " {:collection "items" :id "*insert-id-here*"} ")]")]
   [query-button "[(default/add-fruit        {:fruit/id 9 :fruit/name \"pineapple\" :fruit/price 300 :fruit/color \"yellow\"})]"]
   [query-button "[(default/add-user-profile {:profile/id 4 :profile/email-address \"paul931224@gmail.com\" :profile/first-name \"Paul\"})]"]
   [elements/separator {:size :m :direction :horizontal}]])

(defn- view
  ; @param (keyword) surface-id
  ; @param (map) view-props
  ;
  ; @return (component)
  [_ _]
  [:<> [elements/box {:content    #'playground
                      :icon       :sports_esports
                      :label      "Eql tester"
                      :horizontal-align :left
                      :subscriber [::get-view-props]}]])



;; -- Lifecycle events --------------------------------------------------------
;; ----------------------------------------------------------------------------

(a/reg-event-fx
  ::render!
  [:x.app-ui/set-surface!
   ::view
   {:content          #'view
    :control-bar      {:content #'control-bar}
    :label-bar   {:content       #'ui/go-back-surface-label-bar
                  :content-props {:label "Playground"}}}])

   ;:subscriber       [::get-view-props]

(a/reg-event-fx
  ::initialize!
  {:dispatch-n
   [[:x.app-sync/add-route!
     ::route
     {:restricted? true
      :route-event    [::render!]
      :route-template "/admin/playground"
      :route-title "Playground"}]]})

(a/reg-lifecycles
  ::lifecycles
  {:on-app-boot [::initialize!]})
