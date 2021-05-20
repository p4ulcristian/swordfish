(ns swordfish.site.css.fact
  (:require
    [swordfish.site.css.utils :refer [color] :as utils]))

(defn watch-our []
  (merge utils/oswald
         utils/stroked-word
         {:font-size   "40px"
          :font-weight "bold"
          :text-align  "center"}))

(defn indiegogo []
  (merge utils/oswald
         {:color       "white"
          :font-size   "20px"
          :font-weight "bold"
          :text-align  "center"
          :padding     "20px 30px"}))

(defn youtube-logo []
  {:display         "flex"
   :justify-content "center"})

(defn youtube-embed []
  {:display         "flex"
   :justify-content "center"
   :padding         "40px 20px 100px 20px"})


;parts-section

(defn description []
  (merge utils/roboto
         {:color   "#A6A2A2"
          :padding "20px"}))

(defn title-start []
  {:color       "#FCFCFC"
   :margin-left "10px"})

(defn title-end []
  {:color        "#FCFCFC"
   :margin-left  "5px"
   :margin-right "15px"})


(defn title [direction]
  (with-meta
    (merge utils/oswald
           {:color           "white"
            :justify-content (case direction
                               :left "flex-end"
                               :right "flex-start"
                               "flex-start")
            :display         "flex"
            :font-size       "22px"
            :font-weight     "bold"})
    {:media {(utils/media-width {:max-width (:m utils/size)})
             {:font-size "20px"}}}))


(defn title-container []
  {:background      "#39394D"
   :display         "flex"
   :flex-direction  "column"
   :justify-content "center"
   :height          "40px"})

(defn section-img-container [direction]
  (with-meta
    {:flex      "0 50%"
     :width     "50%"
     :flex-grow 1
     :order     (case direction
                  :left 0
                  :right 1)}
    {:media {(utils/media-width {:max-width (:m utils/size)})
             {:flex  "0 100%"
              :width "100%"
              :order 0}}}))

(defn section-img [img-url]
  (with-meta
    {:background-image    (str "url(" img-url ")")
     :background-size     "contain"
     :background-repeat   "no-repeat"
     :background-position "center"
     :height              "100%"
     :width               "100%"}
    {:media {(utils/media-width {:max-width (:m utils/size)})
             {:height "200px"}}}))


(defn section-content [direction]
  (with-meta
    {:display         "flex"
     :flex-direction  "column"
     :order           (case direction
                        :left 1
                        :right 0)
     :justify-content "center"
     :flex            "0 50%"
     :width           "50%"
     :flex-grow       1
     :padding         "0px 30px"}
    {:media {(utils/media-width {:max-width (:m utils/size)})
             {:flex       "0 100%"
              :width      "100%"
              :padding    "0px 0px"
              :order      1
              :margin-top "10px"
              :height     "200px"}}}))

(defn section []
  {:background         "#1D1D1F"
   :-webkit-box-shadow "0 10px 6px -6px #111"
   :-moz-box-shadow    "0 10px 6px -6px #111"
   :box-shadow         "0 10px 6px -6px #111"
   :border-radius      "15px"
   :display            "flex"
   :flex-wrap          "wrap"
   :margin-bottom      "50px"
   :min-height         "300px"})


(defn fact []
  (with-meta
    {:margin-top "60px"
     :padding    "0px 100px"}
    {:media {(utils/media-width {:max-width (:m utils/size)})
             {:padding    "20px"
              :margin-top "20px"}}}))

