(ns swordfish.views.fact
  (:require
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [reagent.core :as reagent :refer [atom]]
    [swordfish.css.fact :as css]
    [swordfish.css.utils :as css-utils]))

(defn title [title-start title-end direction]
  [:div {:class (x-class css/title-container)}
   [:div {:class (x-class css/title direction)}
    [:div {:class (x-class css/title-start)} title-start]
    [:div {:class (x-class css/title-end)} title-end]]])

(defn section-description [description]
  [:div {:class (x-class css/description)}
   (str description)])

(defn section-img [img-url direction]
  [:div {:class (x-class css/section-img-container direction)}
   [:div {:class (x-class css/section-img img-url)}]])

(defn section-content [title-start title-end description direction]
  [:div {:class (x-class css/section-content direction)}
   [:div
    [title title-start title-end direction]
    [section-description description]]])

(defn section [[title-start title-end] img-url description direction]
  [:section {:class (x-class css/section)}
   [:<>
    [section-content title-start title-end description direction]
    [section-img img-url direction]]])

(defn sections []
  [:div {:class (x-class css/fact)}
   [section ["EASY" "PEASY"] "/img/facts/1.png"
    "The two wings of the Swordfish can be
    removed and reassembled without the need
    for any tools, making them easy to transport."
    :right]
   [section ["CLEVER" "TITLE"] "/img/facts/2.png"
    "The fin’s stiffness (and thereby the force required to swim)
     is adjustable with the extendable connector attaching the fin
      to the foot holder. By fine tuning the fins to your immediate
      requirements, you can use Swordfish for long distance energy
      saving by shortening the "
    :left]
   [section ["OPTIMIZING" "ASPECT RATIO"] "/img/facts/1.png"
    "Today’s competition fins (the dolphin and its improved version the
    carrot fin) have an aspect ratio of approximately 1:1, which
    means that the length and the with of the fin is almost identical.
    The aspect ratio of the Swordfish spans from 6 to 16 depending on the model. \n"
    :right]
   [section ["FINDING THE" "RIGHT SHAPE"] "/img/facts/3.png"
    "Today’s fins are flexible, despite their relative stiffness, and
     during usage they take on a wave shape thus developing thrust.
     \nThe shape of the wave is constantly being modified by the swimmer’s
      muscle power, which radically reduces the fin’s efficiency."
    :left]
   [section ["LIGHTER" "THAN LIGHT"] "/img/facts/4.png"
    "Today’s fins are flexible, despite their relative stiffness, and
     during usage they take on a wave shape thus developing thrust.
     \nThe shape of the wave is constantly being modified by the swimmer’s
      muscle power, which radically reduces the fin’s efficiency."
    :right]])

(defn watch-us []
  [:div
   [:div {:class (x-class css/watch-our)} "WATCH OUR"]
   [:div {:class (x-class css/indiegogo)} "INDIEGOGO CAMPAIGN VIDEO FOR MORE FACTS"]
   [:div {:class (x-class css/youtube-logo)}
    [:a {:href "https://www.youtube.com/channel/UCT17xq2yVM77mdiXMkzd1hg"}
     [:img {:src "/img/youtube-icon-fact.png"}]]]])

(defn youtube-embed []
  [:div {:class (x-class css/youtube-embed)}
   [:iframe
    {:width             "560"
     :height            "315"
     :src               "https://www.youtube.com/embed/hC2pI9R2AME"
     :frame-border      "0"
     :allow             "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
     :allow-full-screen true}]])

(defn fact []
  [:div {:class [(x-class css-utils/page-in-animation) (x-class css-utils/content-width)]}
   [sections]
   [watch-us]
   [youtube-embed]])