(ns swordfish.css.faq
  (:require
    [swordfish.css.utils :refer [color] :as utils]))

;have-questions?

(defn contact-button []
  {:background  (color :highlight-color)
   :color       "white"
   :padding     "10px 30px"
   :height      "100px"
   :cursor      "pointer"
   :margin-left "10px"})



(defn have-questions-button-container []
  (with-meta
    {:padding         "30px 0px"
     :display         "flex"
     :flex-grow       1
     :flex-wrap       "wrap"
     :justify-content "flex-end"
     :align-items     "center"
     :height          "fit-content"}
    {:media {(utils/media-width {:max-width (:l utils/size)})
             {:flex-basis      "100%"
              :width           "100%"
              :justify-content "center"}
             (utils/media-width {:max-width (:s utils/size)})
             {:height "200px"}}}))


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
    {:border-bottom "1px solid white"
     :margin-bottom "100px"
     :padding-top   "150px"
     :display       "flex"
     :flex-wrap     "wrap"
     :margin        "0px 20px"
     :padding       "20px"}))

;accordion classes

(defn icon-container []
  {:background      "#39394D"
   :border-radius   "5px"
   :display         "flex"
   :justify-content "center"
   :align-items     "center"
   :height          "55px"
   :width           "55px"})

(defn icon [open?]
  {:background-size     "contain"
   :background-repeat   "no-repeat"
   :background-position "center"
   :background-image    (if open?
                          "url(/img/minus.png)"
                          "url(/img/plus.png)")
   :height              "20px"
   :width               "20px"})

(defn accordion-description []
  {:font-size   "15px"
   :line-height "25px"
   :padding     "20px 45px 10px 20px"})

(defn accordion-title-text []
  (with-meta
    {:display         "flex"
     :flex-direction  "column"
     :flex-grow       1
     :font-size       "17px"
     :font-weight     "bold"
     :line-height     "25px"
     :padding         "0px 20px"
     :justify-content "center"}
    {:media {(utils/media-width {:max-width (:s utils/size)})
             {:font-size   "14px"
              :line-height "18px"}}}))

(defn accordion-title []
  {:display         "flex"
   :justify-content "flex-end"})

(defn accordion []
  (merge utils/roboto
         {:margin-top    "30px"
          :background    "#1D1D1F"
          :border-radius "10px"
          :color         (color :text-color)}))

(defn accordion-column []
  (with-meta
    {:flex      "0 50%"
     :padding   "0px 50px"
     :width     "50%"
     :flex-grow 1}
    {:media {(utils/media-width {:max-width (:l utils/size)})
             {:flex "0 100%"}
             ;:padding   "0px 40px"}
             (utils/media-width {:max-width (:m utils/size)})
             {;:flex      "0 100%"
              :padding "0px 20px"}}}))



(defn accordions []
  (with-meta
    {:display   "flex"
     :flex-wrap "wrap"
     :padding   "50px 50px"
     :color     "white"}
    {:media {(utils/media-width {:max-width (:l utils/size)})
             {:padding "0px 0px"}}}))



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

