(ns swordfish.css.fact
  (:require
    [swordfish.css.utils :refer [color] :as utils]))

(defn watch-our []
  (merge utils/oswald
         utils/stroked-word
         {:font-size                 "40px"
          :font-weight               "bold"
          :text-align                "center"}))

(defn indiegogo []
  (merge utils/oswald
         {:color       "white"
          :font-size   "20px"
          :font-weight "bold"
          :text-align  "center"
          :padding     "20px 0px"}))

(defn youtube-logo []
  {:display         "flex"
   :justify-content "center"})

(defn youtube-embed []
  {:display         "flex"
   :justify-content "center"
   :padding         "40px 0px 100px 0px"})


;parts-section

(defn description []
  (merge utils/roboto
         {:color   "#A6A2A2"
          :padding "20px 50px 30px 50px"}))

(defn title-start []
  {:margin-left       "10px"})

(defn title-end []
  (merge utils/stroked-word
         {:margin-left               "5px"
          :margin-right              "15px"}))


(defn title [direction]
  (merge utils/oswald
         {:color           "white"
          :justify-content (case direction
                             :left "flex-end"
                             :right "flex-start"
                             "flex-start")
          :display         "flex"
          :font-size       "25px"
          :font-weight     "bold"}))


(defn title-container []
  {:background      "#39394D"
   :display         "flex"
   :flex-direction  "column"
   :justify-content "center"
   :height          "40px"})

(defn section-img [img-url]
  {:background-image    (str "url(" img-url ")")
   :background-size     "contain"
   :background-repeat   "no-repeat"
   :background-position "center"
   :height              "100%"
   :width               "100%"})

(defn section-content []
  {:display         "flex"
   :flex-direction  "column"
   :justify-content "center"})

(defn section []
  (with-meta
    {:background         "#1D1D1F"
     :-webkit-box-shadow "0 10px 6px -6px #111"
     :-moz-box-shadow    "0 10px 6px -6px #111"
     :box-shadow         "0 10px 6px -6px #111"
     :border-radius      "10px"
     :display            "flex"
     :flex-wrap          "wrap"
     :margin-bottom      "50px"
     :min-height         "200px"}
    {:combinators {[:> :div] {:flex      "0 50%"
                              :width     "50%"
                              :flex-grow 1}}}))

(defn fact []
  {:margin-top "150px"
   :padding    "0px 100px"})

