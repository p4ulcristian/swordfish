(ns swordfish.views.utils
  (:require
    [herb.core :refer [<class <id] :rename {<class x-class <id x-id}]
    [swordfish.css.home :as css-home]))



(defn down-arrow []
  [:div {:class (x-class css-home/down-arrow)}
   [:img {:src "/img/down-arrow.png"}]])
