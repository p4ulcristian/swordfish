(ns swordfish.site.views.contact
  (:require
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [reagent.core :as reagent :refer [atom]]
    [swordfish.site.css.contact :as css]
    [swordfish.site.css.utils :as css-utils]
    [swordfish.site.db :as db]
    [swordfish.site.setup]
    [swordfish.site.views.utils :as utils]))

(defn questions? []
  [:div {:class (x-class css/questions?)}
   "QUESTIONS? OBSERVATIONS? INQUIRIES?"])

(defn fill-the-form []
  [:div {:class (x-class css/fill-the-form)}
   [:div "FILL IN THE FORM BELOW"]
   [:div "AND WE’LL GET BACK TO YOU ASAP!"]])

(defn with-label [label-name container]
  [:div {:class (x-class css-utils/padding "0px 10px")}
   [:div {:class (x-class css/label-name)} label-name]
   container])

(defn subscribe-button []
  [:div {:class (x-class css/subscribe-button-container)}
   [:button {:class    (x-class css/subscribe-button)
             :on-click #()}
    "SEND"]])

(defn form []
  [:div {:class (x-class css/form)}
   [:div {:class (x-class css/form-content)}
    [:div {:class (x-class css/form-top)}
     [with-label "Name" [:input {:on-change #(db/edit-db [:contact-form :name] (-> % .-target .-value))
                                 :class     (x-class css/input)}]]
     [with-label "Email" [:input {:on-change #(db/edit-db [:contact-form :email] (-> % .-target .-value))
                                  :class     (x-class css/input)}]]]
    [with-label "Message" [:textarea {:on-change #(db/edit-db [:contact-form :message] (-> % .-target .-value))
                                      :class     (x-class css/message)}]]]])

(defn info []
  [:div {:class (x-class (fn [] {:margin-bottom "60px"}))}
   [:div {:class (x-class css-utils/margin "30px 0px")}
    [utils/contact-social-icons]]
   [:div {:class (x-class css/link)} [:a {:href "tel:+36303119696"} "+36 30 311 9696"]]
   [:div {:class (x-class css/link)} [:a {:href (str "mailto:" swordfish.site.setup/email-link)}
                                      swordfish.site.setup/email-link]]])

(defn contact []
  [:div {:class (x-class css-utils/page-in-animation)}
   [questions?]
   [fill-the-form]
   [form]
   [subscribe-button]
   [info]])
