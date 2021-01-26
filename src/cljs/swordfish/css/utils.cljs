(ns swordfish.css.utils
  (:require
    [cljss.core :refer-macros [defstyles defkeyframes font-face] :as css]))


(defn remove-styles! []
  (css/remove-styles!))

(def color-scheme
  (atom {:wall-color "#181819"
         :text-color "#A6A2A2"
         :highlight-color  "#2B83C7"
         :price-color "#2B83C7"}))

(defn color [the-key]
  (get @color-scheme the-key))

(defstyles padding [param]
           {:padding param})

(defstyles width [param]
           {:width param})

(defstyles font-size [param]
           {:font-size param})

(defstyles position  [param]
           {:position param})

(defstyles text-color [param]
           {:color param})

(defstyles vertical-align []
           {:display "flex"
            :align-items "center"})

(defstyles content-width []
           {:max-width "1200px" :margin "auto"
            :position "relative"})


(defstyles flex []
           {:display "flex"})
