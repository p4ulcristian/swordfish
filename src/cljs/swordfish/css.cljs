(ns swordfish.css
  (:require
        [cljss.core :refer-macros [defstyles defkeyframes font-face] :as css]))


(defn remove-styles! []
  (css/remove-styles!))

(def color-scheme
  (atom {:wall-color "#181819"
         :text-color "#A6A2A2"
         :price-color "#2B83C7"}))


(defn color [the-key]
  (get @color-scheme the-key))

(defstyles background []
           {:min-height "100vh"
            :background-color (color :wall-color)})

(defstyles background-logo []
           {:right 0
            :top "-150px"
            :height "500px"
            :position "fixed"
            :pointer-events "none"
            :transform "rotate(10deg)"
            :background-size "cover"})


;;First animation
(defkeyframes spin [from to]
              {:from {:transform (str "rotate(" from "deg)")}
               :to   {:transform (str "rotate(" to "deg)")}})


(defstyles logo []
           {:width "300px"
            :z-index "2"
            :padding "0px 30px"})

(defstyles navbar []
           {:padding-top "30px"
            :display "flex"

            :height "70px"})

(defstyles menu []
           {:display "flex"
            :height "100%"
            :flex-basis "500px"
            :z-index "2"
            :font-family "'Oswald', sans-serif"
            :font-weight "600"
            :font-size "20px"
            :justify-content "space-around"})

(defstyles menu-item []
           {:color (color :text-color)
            :text-decoration "none"
            :&:hover {:color "white"
                      :cursor "pointer"}})

(defstyles nav-line []
           {:height "50%"
            :flex-grow 1
            :z-index "2"
            :border-bottom (str "1px solid " (color :text-color))})

(defstyles vertical-align []
           {:display "flex"
            :align-items "center"})

(defstyles main-product []
           {:padding "0px 50px"
            :margin-top "-40px"
            :width "900px"})

(defstyles wakizashi []
           {:position "absolute"
            :height "30px"
            :width "350px"
            :font-size "15px"
            :left "5%"
            :top  "230px"})

(defstyles wakizashi-img []
           {});:height "40px"})


(defstyles main-product-container []
           {:display "flex"
            :justify-content "center"})


(defstyles footer-paying []
           {:background ""
            :padding "10px"})

(defstyles footer []
           {:background "white"
            :height "50px"
            :width "100%"})

(defstyles p-font []
           {:font-family "'Roboto', sans-serif"
            :line-height "25px"
            :text-transform "uppercase"
            :color (color :text-color)})


(defstyles padding [param]
           {:padding param})

(defstyles font-size [param]
           {:font-size param})

(defstyles price []
  {:display "flex"
   :font-weight "bold"
   :background-color (color :price-color)})

(defstyles price-box []
           {:display "flex"
            :background-color (color :price-color)
            :width "max-content"
            :font-family "'Oswald', sans-serif"
            :justify-content "space-around"
            :padding "0px 10px 0px 20px"
            :color "white"})