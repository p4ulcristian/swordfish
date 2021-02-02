(ns swordfish.css.utils
  (:require [herb.core :refer [<class <id defkeyframes] :rename {<class x-class <id x-id}]))


(def color-scheme
  (atom {:wall-color      "#181819"
         :text-color      "#A6A2A2"
         :highlight-color "#2B83C7"
         :price-color     "#2B83C7"}))

(def size
  {:xs "450px"
   :s  "600px"
   :m  "850px"
   :l  "1100px"})

(defn color [the-key]
  (get @color-scheme the-key))

(defn media-width [width-map]
  (merge {:screen :only} width-map))

(defn min-width-media [width style]
  {:media {{:screen :only :min-width width} style}})

(defn max-width-media [width style]
  {:media {{:screen :only :max-width width} style}})

(def stroked-word
  {:-webkit-text-fill-color   "transparent"                 ; /* Will override color (regardless of order) */
   :-webkit-text-stroke-width "1px"                         ;
   :-webkit-text-stroke-color "white"})

(def oswald
  {:font-family "'Oswald', sans-serif"})

(def roboto
  {:font-family "'Roboto', sans-serif"})

(defn padding [param]
  {:padding param})

(defn margin [param]
  {:padding param})

(defn width [param]
  {:width param})

(defn font-size [param]
  {:font-size param})

(defn position [param]
  {:position param})

(defn text-color [param]
  {:color param})

(defn vertical-align []
  {:display     "flex"
   :align-items "center"})


(defn content-width []
  {:max-width "1200px" :margin "auto"
   :position  "relative"})

(defn flex []
  {:display "flex"})

;animations

(defkeyframes pulse-animation
              [:from {:opacity 0}]
              [:to {:opacity 1}])

(defkeyframes menu-animation
              [:from {:transform "translateY(-100%)"}]
              [:to {:transform "translateY(0)"}])

(defn page-in-animation
  []
  {:animation  [[pulse-animation "1s"]]
   :transition "all 1s ease-out"})

(defn menu-animation-style
  []
  {:animation  [[menu-animation "0.4s"]]
   :transition "all 1s ease-in"})
