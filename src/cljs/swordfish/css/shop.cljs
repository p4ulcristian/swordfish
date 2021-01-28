(ns swordfish.css.shop
  (:require
    [swordfish.css.utils :refer [color] :as utils]))


;parts-section

(defn parts-section []
  (with-meta
    {:display        "flex"
     :flex-wrap      "wrap"
     :padding-bottom "50px"
     :padding-top    "30px"}
    {:combinators {[:> :div] {:flex            "0 50%"
                              :display         "flex"
                              :justify-content "center"
                              :margin-bottom   "50px"}}}))


;product-card
(defn product-photos-container []
  {:flex-grow 1})

(defn product-photo-container []
  (with-meta {:border        (str "1px solid " (color :text-color))
              :border-radius "12px"
              :margin-left   "15px"}
             {:pseudo {:hover {:cursor "pointer"}}}))

(defn product-photo []
  {:height "50px"
   :width  "50px"})

(defn product-photos []
  {:bottom   "10px"
   :display  "flex"
   :left     "10px"
   :position "absolute"})


(defn product-title []
  {:color          "grey"
   :font-family    "'Oswald', sans-serif"
   :font-size      "16px"
   :letter-spacing "7px"
   :line-height    "30px"
   :padding        "10px 25px 0px 0px"
   :text-align     "right"})

(defn product-type []
  {:color       (color :text-color)
   :font-family "'Oswald', sans-serif"
   :font-size   "25px"
   :text-align  "right"
   :padding     "0px 25px 20px 0px"})

(defn product-price []
  {:color       (color :text-color)
   :font-family "'Oswald', sans-serif"
   :font-size   "40px"
   :padding     "0px 25px 10px 0px"
   :text-align  "right"})

;;main-description

(defn one-description-icon []
  {:margin-left  "10px"
   :stroke       "#2B83C7"
   :stroke-width "5"
   :width        "20px"})

(defn one-description []
  {:display         "flex"
   :text-align      "left"
   :justify-content "center"})

(defn one-description-content []
  {:font-family "'Roboto', sans-serif"
   :color       (color :highlight-color)})

(defn one-description-title []
  {:color       "lightgrey"
   :display     "flex"
   :font-family "'Oswald', sans-serif"
   :padding     "10px 0px"})


(defn one-description-container []
  {:width "150px"})


;;Main product

(defn product-card [url]
  {:background-image    (str "url(" url ")")
   :background-size     "contain"
   :background-repeat   "no-repeat"
   :background-position "center"
   :background-color    "#1D1D1F"
   :border-radius       "25px"
   :display             "flex"
   :flex-direction      "column"
   :height              "500px"
   :width               "500px"})

(defn main-product-container []
  {:flex-basis "33%"})

(defn main-description []
  {:display         "flex"
   :flex-basis      "33%"
   :flex-direction  "column"
   :justify-content "space-evenly"
   :padding         "0px 30px"})

(defn main-section []
  {:display         "flex"
   :justify-content "center"
   :margin-top      "100px"
   :margin-bottom   "50px"})

;;Unordered

(defn spacer []
  {:border-bottom (str "1px solid " (color :highlight-color))
   :width         "100%"})

(defn wakizashi-grey-container []
  {:padding    "40px 20px"
   :width      "100%"
   :flex-basis "33%"})

(defn wakizashi-grey []
  {:background-size     "contain"
   :background-repeat   "no-repeat"
   :background-position "center"
   :background-image    "url(/img/wakizashi-grey.png)"
   :height              "100%"
   :width               "100%"})