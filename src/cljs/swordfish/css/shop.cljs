(ns swordfish.css.shop
  (:require
    [swordfish.css.utils :refer [color]]))


;product-card
(defn product-title []
           {:font-family "'Oswald', sans-serif"
            :text-align "right"
            :font-size "20px"
            :color "grey"
            :letter-spacing "7px"
            :line-height "30px"})

(defn product-type []
           {:font-family "'Oswald', sans-serif"
            :text-align "right"
            :font-size "30px"
            :color "lightgrey"})

(defn product-price []
           {:font-family "'Oswald', sans-serif"
            :text-align "right"
            :margin-top "10px"
            :font-size "50px"
            :color "lightgrey"})

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
           {:display     "flex"
            :color       "lightgrey"
            :padding     "10px 0px"
            :font-family "'Oswald', sans-serif"})

(defn one-description-container []
           {:width "150px"})


;;Main product

(defn main-product-card []
           {:border-radius       "25px"
            :height              "500px"
            :width               "500px"
            :background-image    "url(/img/products/main.png)"
            :background-size     "contain"
            :background-repeat   "no-repeat"
            :background-position "center"
            :background-color    "#1D1D1F"})

(defn main-product-container []
           {:flex-basis "33%"})

(defn main-description []
           {:flex-basis      "33%"
            :display         "flex"
            :justify-content "space-evenly"
            :padding         "0px 30px"
            :flex-direction  "column"})

(defn main-section []
           {:display         "flex"
            :justify-content "center"})

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
            :width               "100%"
            :height              "100%"
            :background-repeat   "no-repeat"
            :background-position "center"
            :background-image    "url(/img/wakizashi-grey.png)"})