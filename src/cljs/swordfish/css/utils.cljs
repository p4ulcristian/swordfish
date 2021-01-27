(ns swordfish.css.utils)


(def color-scheme
  (atom {:wall-color "#181819"
         :text-color "#A6A2A2"
         :highlight-color  "#2B83C7"
         :price-color "#2B83C7"}))

(defn color [the-key]
  (get @color-scheme the-key))

(defn padding [param]
           {:padding param})

(defn width [param]
           {:width param})

(defn font-size [param]
           {:font-size param})

(defn position  [param]
           {:position param})

(defn text-color [param]
           {:color param})

(defn vertical-align []
           {:display "flex"
            :align-items "center"})

(defn content-width []
           {:max-width "1200px" :margin "auto"
            :position "relative"})


(defn flex []
           {:display "flex"})
