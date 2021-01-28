(ns swordfish.css.utils)


(def color-scheme
  (atom {:wall-color      "#181819"
         :text-color      "#A6A2A2"
         :highlight-color "#2B83C7"
         :price-color     "#2B83C7"}))

(defn color [the-key]
  (get @color-scheme the-key))

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
