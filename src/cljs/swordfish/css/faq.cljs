(ns swordfish.css.faq
  (:require
    [swordfish.css.utils :refer [color] :as utils]))

;have-questions?

(defn contact-button []
  {:background  (color :highlight-color)
   :color       "white"
   :cursor      "pointer"
   :margin-left "20px"
   :padding     "10px 30px"})

(defn have-questions-button-container []
  {:display         "flex"
   :flex-grow       1
   :justify-content "flex-end"
   :align-items     "center"})

(defn have-questions-text-container []
  {:font-size   "25px"
   :color       "white"
   :line-height "45px"
   :font-weight "bold"})

(defn have-questions-didnt []
  (merge utils/stroked-word
         {:display "flex"}))

(defn have-questions-ask-us []
  {:display "flex"})

(defn have-questions? []
  (merge
    utils/oswald
    {:border-bottom  "1px solid white"
     :margin-bottom  "100px"
     :margin-top     "150px"
     :display        "flex"
     :padding-bottom "10px"}))

;accordion classes

(defn icon-container []
  {:background      "#39394D"
   :border-radius   "5px"
   :display         "flex"
   :justify-content "center"
   :align-items "center"
   :height          "55px"
   :width           "55px"})

(defn icon [open?]
  {:background-size  "contain"
   :background-repeat "no-repeat"
   :background-position "center"
   :background-image (if open?
                       "url(/img/minus.png)"
                       "url(/img/plus.png)")
   :height           "20px"
   :width            "20px"})

(defn accordion-description []
  {:font-size   "15px"
   :line-height "25px"
   :padding     "20px 45px 10px 20px"})

(defn accordion-title-text []
  {:display         "flex"
   :flex-direction  "column"
   :flex-grow       1
   :font-size       "17px"
   :font-weight     "bold"
   :line-height     "25px"
   :padding-left    "20px"
   :justify-content "center"})

(defn accordion-title []
  {:display         "flex"
   :justify-content "flex-end"})

(defn accordion []
  (merge utils/roboto
         {:margin-top    "30px"
          :background    "#1D1D1F"
          :border-radius "10px"
          :color         (color :text-color)}))

(defn accordions []
  (with-meta
    {:display   "flex"
     :flex-wrap "wrap"
     :padding   "0px 50px"
     :color     "white"}
    {:combinators {[:> :div] {:flex      "0 50%"
                              :padding   "0px 50px"
                              :width     "50%"
                              :flex-grow 1}}}))

;title

(defn title []
  (merge utils/oswald
         {:color       "white"
          :font-size   "30px"
          :font-weight "bold"
          :line-height "40px"
          :padding     "80px 0px"
          :text-align  "center"}))

(defn title-top []
  {:display         "flex"
   :justify-content "center"})

(defn title-center []
  (merge utils/stroked-word
         {:margin-left "5px"}))

