(ns swordfish.views.faq
  (:require
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [reagent.core :as reagent :refer [atom]]
    [swordfish.css.faq :as css]
    [swordfish.css.utils :as css-utils]
    [swordfish.db :as db]))


(defn title []
  [:div {:class (x-class css/title)}
   [:div {:class (x-class css/title-top)}
    [:div "FREQUENTLY"]
    [:div {:class (x-class css/title-center)} "(AND NOT SO)"]]
   [:div "FREQUENTLY ASKED QUESTIONS"]])

(defn icon [the-key]
  [:button {:class (x-class css/icon-container)
            :on-click #(db/toggle-accordion the-key)}
   [:span {:class (x-class css/icon (db/open-accordion? the-key))}]])


(defn accordion-title [title]
  [:div {:class (x-class css/accordion-title)}
   [:div {:class (x-class css/accordion-title-text)}
    [:div  title]]
   [icon title]])

(defn accordion-description [description]
  [:div {:class (x-class css/accordion-description)} description])

(defn accordion [title description]
  [:div
   [:div {:class (x-class css/accordion)}
    [accordion-title title]
    (if (db/open-accordion? title)
      [accordion-description description])]])


(defn left []
  [:div
   [accordion "Which monofin should I choose? "
    "Great question! First we set clear objectives and
    committed to reaching our goal no matter what! We
    faced hundreds of challenges along the way, but
    persevered to stay true to our promise we made
    ourselves in the previous sentence. Was it worth
    it? Course it was, someone had to put an end to
     this inefficiency nonsense, it just so happened to
     be us."]
   [accordion "Is it bragging if it’s true?"
    "Great question! First we set clear objectives and
    committed to reaching our goal no matter what! We
    faced hundreds of challenges along the way, but
    persevered to stay true to our promise we made
    ourselves in the previous sentence. Was it worth
    it? Course it was, someone had to put an end to
     this inefficiency nonsense, it just so happened to
      be us."]
   [accordion "How did you guys pull this off?"
    "Great question! First we set clear objectives and
    committed to reaching our goal no matter what! We
    faced hundreds of challenges along the way, but
    persevered to stay true to our promise we made
    ourselves in the previous sentence. Was it worth
    it? Course it was, someone had to put an end to
     this inefficiency nonsense, it just so happened to
      be us."]])

(defn right []
  [:div
   [accordion "What do some of the words mean in the first answer?"
    "Great question! First we set clear objectives and
    committed to reaching our goal no matter what! We
    faced hundreds of challenges along the way, but
    persevered to stay true to our promise we made
    ourselves in the previous sentence. Was it worth
    it? Course it was, someone had to put an end to
     this inefficiency nonsense, it just so happened to
      be us."]
   [accordion "Why is Swordfish so much more efficient than "
    "Great question! First we set clear objectives and
    committed to reaching our goal no matter what! We
    faced hundreds of challenges along the way, but
    persevered to stay true to our promise we made
    ourselves in the previous sentence. Was it worth
    it? Course it was, someone had to put an end to
     this inefficiency nonsense, it just so happened to
      be us."]
   [accordion "Are Katana and Wakizashi fins interchangeable?"
    "Great question! First we set clear objectives and
    committed to reaching our goal no matter what! We
    faced hundreds of challenges along the way, but
    persevered to stay true to our promise we made
    ourselves in the previous sentence. Was it worth
    it? Course it was, someone had to put an end to
     this inefficiency nonsense, it just so happened to
      be us."]])

(defn accordions []
  [:div {:class (x-class css/accordions)}
   [left]
   [right]])




(defn contact-buttons []
  [:div {:class (x-class css/have-questions-button-container)}
   [:div [:a {:class (x-class css/contact-button)} "Connect via " [:b " e-mail"]]]
   [:div [:a {:class (x-class css/contact-button)} "Connect via " [:b " Facebook"]]]])

(defn have-questions-text []
  [:div {:class (x-class css/have-questions-text-container)}
   [:div {:class (x-class css/have-questions-didnt)} "DIDN’T FIND WHAT YOU WERE LOOKING FOR?"]
   [:div {:class (x-class css/have-questions-ask-us)} "ASK US DIRECTLY!"]])

(defn have-questions? []
  [:div {:class (x-class css/have-questions?)}
   [have-questions-text]
   [contact-buttons]])

(defn faq []
  [:div  {:class [(x-class css-utils/page-in-animation) (x-class css-utils/content-width)]}
   [title]
   [accordions]
   [have-questions?]])