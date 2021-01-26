(ns swordfish.css.shop
  (:require
    [swordfish.css.utils :refer [color]]
    [cljss.core :refer-macros [defstyles defkeyframes font-face] :as css]))


;;main-description

(defstyles one-description-img []
           {:margin-left "10px"
            :width "20px"})

(defstyles one-description []
           {:text-align "left"
            :display "flex"
            :justify-content "center"})

(defstyles one-description-content []
           {:font-family "'Roboto', sans-serif"
            :color (color :highlight-color)})

(defstyles one-description-title []
           {:display "flex"
            :color "lightgrey"
            :padding "10px 0px"
            :font-family "'Oswald', sans-serif"})

(defstyles one-description-container []
           {:width "150px"})


;;Main product

(defstyles main-product-card []
           {:border-radius "25px"
            :height "500px"
            :width "500px"
            :background-image "url(/img/products/main.png)"
            :background-size "contain"
            :background-repeat "no-repeat"
            :background-position "center"
            :background-color "#1D1D1F"})

(defstyles main-product-container []
           {:flex-basis "33%"})

(defstyles main-description []
           {:flex-basis "33%"
            :display "flex"
            :justify-content "space-evenly"
            :padding "0px 30px"
            :flex-direction "column"})

(defstyles main-section []
           {:display "flex"
            :justify-content "center"})

;;Unordered

(defstyles spacer []
           {:border-bottom (str "1px solid " (color :highlight-color))
            :width "100%"})

(defstyles wakizashi-grey-container []
           {:padding "40px 20px"
            :width "100%"
            :flex-basis "33%"})

(defstyles wakizashi-grey []
           {:background-size "contain"
            ;:width "30px"
            :width "100%"
            :height "100%"
            :background-repeat "no-repeat"
            :background-position "center"
            :background-image "url(/img/wakizashi-grey.png)"})