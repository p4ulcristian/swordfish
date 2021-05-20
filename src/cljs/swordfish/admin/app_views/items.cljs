;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------


(ns swordfish.admin.app-views.items
  (:require [swordfish.admin.app-views.engine :as engine]
            [swordfish.admin.app-views.control-bar :refer [view] :rename {view control-bar}]
            [app-fruits.reagent :refer [ratom lifecycles]]
            [x.app-core.api :as a :refer [r]]
            [x.app-components.api :as components]
            [x.app-db.api :as db]
            [x.app-elements.api :as elements]
            [x.app-environment.api :as environment]
            [x.app-plugins.api :as plugins]
            [x.app-ui.api :as ui]))




;; -- Config ------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(def collection "items")
(def namespace-name :item)
(def get-resolver :documents)
(def add-update-mutation "universal/add-update-with-order")
(def delete-mutation "universal/delete-with-order")
(def duplicate-mutation "universal/duplicate-with-order")
(def re-order-mutation "universal/re-order")
(def partition-id :swordfish/item)
(def id-key :item/id)
(def add-label "Add item")
(def edit-label "Edit item")
(def non-available "There is no items available")
(def default-search-props {:coll             collection
                           :namespace        namespace-name
                           :search-term      ""
                           :search-key       :name
                           :sort-map         {:sort-key :order
                                              :sort-direction 1}
                           :skip-page        0
                           :max-count        10
                           :filters          {}})


;; -- Subscriptions -----------------------------------------------------------
;; ----------------------------------------------------------------------------


(defn get-view-props [db _]
  {:editor-open? (r db/get-item db [:editor-form :open?])
   :viewport-small? (r environment/viewport-profiles-match? db [:xxs :xs :s :m])})

(a/reg-sub ::get-view-props get-view-props)

;; -- Events ------------------------------------------------------------------
;; ----------------------------------------------------------------------------


(a/reg-event-fx
  ::load-items!
  (fn [{:keys [db]} _]
    (let [search-props (:search-props db)
          query (str "[(" get-resolver " " search-props ")]")]
      [:swordfish.admin.app-views.data-handler/send-get-eql!
       {:query      query
        :on-success [:swordfish.admin.app-views.data-handler/handle-get-response! partition-id get-resolver]}])))

(a/reg-event-fx
  ::search-items!
  (fn [{:keys [db]} _]
    {:dispatch-n
     [[:x.app-db/set-item! [partition-id] {}]
      [:x.app-db/set-item! [:search-props :skip-page] 0]
      [::load-items!]]}))


(a/reg-event-fx
  ::save!
  (fn [{:keys [db]} _]
    (let [item (r db/get-item db [:editor-form :item])
          query (str "[(" add-update-mutation " " {:collection collection :document item :namespace namespace-name} ")]")]
      [:swordfish.admin.app-views.data-handler/send-eql!
       {:query      query
        :on-success [:swordfish.admin.app-views.data-handler/handle-add-update-response!  partition-id add-update-mutation]}])))

(a/reg-event-fx
  ::duplicate!
  (fn [{:keys [db]} [_ item]]
    (let [id     (get item id-key)
          query  (str "[(" duplicate-mutation " " {:id id :collection collection :namespace namespace-name} ")]")]
      [:swordfish.admin.app-views.data-handler/send-eql!
       {:query      query
        :on-success [:swordfish.admin.app-views.data-handler/handle-add-update-response!  partition-id duplicate-mutation]}])))

(a/reg-event-fx
  ::delete!
  (fn [{:keys [db]} _]
    (let [item (r db/get-item db [:editor-form :item])
          item-id (engine/get-namespaced-id item)
          query (str "[(" delete-mutation " " {:collection collection :id item-id} ")]")]
      [:swordfish.admin.app-views.data-handler/send-eql!
       {:query      query
        :on-success [:swordfish.admin.app-views.data-handler/handle-delete-response!  partition-id delete-mutation]}])))


(a/reg-event-fx
  ::re-order!
  (fn [{:keys [db]} []]
    (let [new-order (r db/get-data-order db partition-id)
          query (str "[(" re-order-mutation " " {:collection collection :new-order new-order} ")]")]
      [:swordfish.admin.app-views.data-handler/send-eql!
       {:query      query
        :on-success [:swordfish.admin.app-views.data-handler/handle-re-order-response!]}])))
;; -- Components --------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn flex-container [style components]
  [:div {:style (merge {:display         :flex
                        :flex-direction  :row
                        :width           :100%
                        :justify-content :flex-start}
                  style)}
   components])

(defn add-button []
  [elements/button {:label    add-label
                    :variant  :transparent
                    :color    :none
                    :icon     :add_circle_outline
                    :on-click [:x.app-db/set-item! [:editor-form] {:title add-label
                                                                   :open? true :item {}}]}])


(defn save-button []
  [elements/button {:label    "Mentés"
                    :on-click [::save!]}])


(defn back-button []
  [elements/button {:layout   :icon-button
                    :icon     :arrow_back
                    :variant  :transparent
                    :color    :none
                    :on-click [:x.app-db/set-item! [:editor-form :open?] false]}])


(defn search-field []
  [elements/search-field {:on-interval     [::search-items!]
                          :on-empty        [::search-items!]
                          :interval-period 1000
                          :value-path      [:search-props :search-term]}])

;[:search-props :text]
;[:search-props :sort-key]
;[:search-props :sort-direction]
;[:search-props :skip-page]
;[:search-props :page-count]
;[:search-props :filters]


(defn are-you-sure? []
  [:div
   [elements/separator {:size :l}]
   [elements/text {:content "Delete for sure?" :font-size :xxl}]
   [elements/separator {:size :l}]
   [flex-container {:justify-content :space-between}
    [:<>
     [elements/button {:label "Cancel" :color :muted :on-click [:x.app-ui/close-all-popup!]}]
     [elements/separator {:size :l :orientation :vertical}]
     [elements/button {:label "Accept" :on-click {:dispatch-n
                                                  [[::delete!]
                                                   [:x.app-ui/close-all-popup!]]}}]]]
   [elements/separator {:size :m}]])


(defn delete-button []
  [elements/button {:icon     :delete
                    :variant  :transparent
                    :layout   :icon-button
                    :color    :warning
                    :title    "Delete"
                    :on-click [:x.app-ui/add-popup!
                               ::view
                               {:content        #'are-you-sure?
                                :layout         :boxed
                                :primary-button {:preset :close-icon-button}}]}])


(defn form-footer []
  [:<>
   [flex-container {:justify-content :space-evenly}
    [save-button]]
   [elements/separator {:size :l :orientation :horizontal}]])


(defn form-field [label the-key]
  [elements/text-field {:label label :value-path [:editor-form :item the-key]}])


(defn form-content []
  [:div {:style {:align-items :center}}
   [elements/separator {:size :xl}]
   [form-field "Name" :item/name]
   [form-field "Description" :item/description]
   [elements/separator {:size :l :orientation :horizontal}]])


(defn form-header []
  [flex-container {:justify-content :space-between}
   [:<> [back-button]
    [elements/text {:content @(a/subscribe [:x.app-db/get-item [:editor-form :title]])}]
    [delete-button]]])


(defn form []
  [:div
   [elements/separator {:size :l}]
   [elements/box {:content
                  [:div {:style {:width :100%}}
                   [form-header]
                   [form-content]
                   [form-footer]]}]])




(defn edit-sticker [id]
  {:icon     :edit
   :title    "Edit"
   :on-click [:x.app-db/set-item!
              [:editor-form]
              {:title edit-label
               :open? true
               :item @(a/subscribe [:x.app-db/get-item [partition-id :data-items id]])}]})


(defn duplicate-sticker [id]
  {:icon     :content_copy
   :title    "Szerkesztés"
   :on-click [::duplicate!
              @(a/subscribe [:x.app-db/get-item [partition-id :data-items id]])]})


(defn card [_ {:item/keys [id name description] :as view-props}]
  [:div {:style {:width "200px"}}
   [elements/text {:content name :layout :fit :font-size :l :font-weight :bold}]
   [elements/horizontal-line {}]
   [elements/text {:content id          :layout :fit :font-size :xs}]
   [elements/text {:content description :layout :fit :font-size :s}]])



(defn card-wrapper [id]
  [elements/card
   {:min-width     :xs
    :content       #'card
    :subscriber     [:x.app-db/get-item (db/path partition-id id)]
    :stickers       [(duplicate-sticker id)
                     (edit-sticker id)]}])

(defn card-wrapper-sortable [example-id {:keys [content-props] :as example-props}]
  (let [id (keyword (get content-props id-key))]
    [elements/card
     {:min-width     :xs
      :content       #'card
      :subscriber     [:x.app-db/get-item (db/path partition-id id)]
      :stickers       [(duplicate-sticker id)
                       (edit-sticker id)]}]))


(defn sortable-cards []
  (let [empty-partition? (a/subscribe [:x.app-db/partition-empty? partition-id])
        sort-key (a/subscribe [:x.app-db/get-item [:search-props :sort-map :sort-key]])
        partition-data (a/subscribe [:x.app-db/get-partition partition-id])]
    (fn []
      [:<>
       (if @empty-partition?
         [elements/text {:content non-available :font-size :l :font-weight :bold}]
         [:div
          (if (= :order @sort-key)
            [plugins/sortable {:partition-id partition-id
                               :on-order-change [::re-order!]
                               :style {:display :flex :flex-wrap :wrap :justify-content :center}
                               :element #'card-wrapper-sortable}]
            [flex-container {:flex-wrap :wrap :justify-content :center}
             [:<> (map
                    (fn [id] ^{:key id}[:div [card-wrapper id]])
                    (:data-order @partition-data))]])])])))





(defn sorting-options []
  [flex-container {}
   [elements/select {:label "Rendezés"
                     :on-select  [::search-items!]
                     :value-path [:search-props :sort-map]
                     :icon :sort
                     :options [{:label "By name A-Z"
                                :value {:sort-key :name
                                        :sort-direction 1}}
                               {:label "By name Z-A"
                                :value {:sort-key :name
                                        :sort-direction -1}}
                               {:label "Custom order"
                                :value {:sort-key :order
                                        :sort-direction 1}}]}]])


(defn items-box []
  (let [view-props  (a/subscribe [::get-view-props])
        loader?     (a/subscribe [:x.app-db/get-item [:infinite-loader?]])]
    (fn []
      [:<>
       [elements/separator {:size :s :orientation :horizontal}]
       [flex-container (if (:viewport-small? @view-props)
                         {:justify-content :space-between :flex-wrap :wrap}
                         {:justify-content :space-between})
        [:<> [add-button]
             [search-field]]]
       [sorting-options]
       [elements/horizontal-line {}]
       [elements/separator {:size :s :orientation :horizontal}]
       [elements/separator {:size :m :orientation :horizontal}]
       [sortable-cards]
       (if @loader?
         [engine/infinite-loader #(a/dispatch-n [[:swordfish.admin.app-views.data-handler/disable-infinite-loader!]
                                                 [::load-items!]])])])))

(defn view []
  (let [view-props (a/subscribe [::get-view-props])]
    (fn []
      (if (:editor-open? @view-props)
        [form]
        [elements/box {:min-width :xxl
                       :content #'items-box}]))))


;; -- Lifecycle events --------------------------------------------------------
;; ----------------------------------------------------------------------------


(a/reg-event-fx ::render!
  [:x.app-ui/set-surface!
   ::view
   {:content     #'view
    :control-bar {:content #'control-bar}
    :label-bar   {:content       #'ui/go-back-surface-label-bar
                  :content-props {:label "Items"}}}])


(a/reg-event-fx
  ::initialize!
  {:dispatch-n
   [[:x.app-environment.css-handler/add-external-source! "css/loading.css"]
    [:swordfish.admin.app-views.data-handler/enable-infinite-loader!]
    [:x.app-db/set-item! [partition-id :data-items] {}]
    [:x.app-db/set-item! [:search-props] default-search-props]
    [:x.app-sync/add-route!
     ::route
     {:restricted?    true
      :route-event    [::render!]
      :on-leave-event [:x.app-db/set-item! [:editor-form :open?] false]
      :route-template "/admin/items"
      :route-title    "Items"}]]})


(a/reg-lifecycles
  ::lifecycles
  {:on-app-boot [::initialize!]})
