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

(defn for-footer-design []
  {:display        "flex"
   :min-height     "100vh"
   :flex-direction "column-reverse"})

(defn for-page-design []
  {:flex-grow 1})

(defn flex []
  {:display "flex"})


(defkeyframes pulse-animation
              [:from {:opacity 0}]
              [:to {:opacity 1}])

;; In garden, if you use a single vector [arg1 arg1] you get a comma separated
;; string, if you add a second vector is gets space separated
(defn page-in-animation
  []
  {:animation  [[pulse-animation "1s"]]
   :transition "all 1s ease-out"})



(defn mobile-menu []
  (with-meta {:padding   "0px 20px"
              :cursor    "pointer"
              :color     "white"
              :font-size "25px"}
             (min-width-media (:m size) {:display "none"})))

(defn footer-sections []
  (with-meta {:display   "flex"
              :flex-wrap "wrap"}
             (max-width-media
               (:l size)
               {:justify-content "center"})))

