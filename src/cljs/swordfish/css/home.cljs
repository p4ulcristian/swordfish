(ns swordfish.css.home
  (:require
        [swordfish.css.utils :refer [color]]
        [cljss.core :refer-macros [defstyles defkeyframes font-face] :as css]))


(defstyles background []
           {:height "content"
            :background-color (color :wall-color)})

(defstyles background-logo []
           {:right 0
            :top "-150px"
            :height "500px"
            :position "absolute"
            :pointer-events "none"
            :transform "rotate(10deg)"
            :background-size "cover"})

(defstyles background-logo-left []
           {:left 0
            :bottom 0

            :height "500px"
            :position "absolute"
            :pointer-events "none"
            :transform "rotate(10deg)"
            :background-size "cover"})


;;First animation
(defkeyframes spin [from to]
              {:from {:transform (str "rotate(" from "deg)")}
               :to   {:transform (str "rotate(" to "deg)")}})


(defstyles logo-container []
           {:z-index "2"
            :display "flex"
            :align-items "center"})


(defstyles logo []
           {:width "300px"
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
            :&:hover {:color (color :highlight-color)
                      :cursor "pointer"}})



(defstyles nav-line []
           {:height "50%"
            :flex-grow 1
            :z-index "2"
            :border-bottom (str "1px solid " (color :text-color))})

(defstyles wakizashi []
           {:position "absolute"
            :height "30px"
            :width "350px"
            :font-size "15px"
            :left "5%"
            :top  "120px"})




(defstyles footer-paying []
           {:background ""
            :padding "10px"
            :height "25px"})

(defstyles footer []
           {:background "white"
            :font-size "14px"
            ;:height "50px"
            :width "100%"})

(defstyles footer-link []
           {:flex-basis "content"})

(defstyles p-font []
           {:font-family "'Roboto', sans-serif"
            :line-height "25px"
            :text-transform "uppercase"
            :color (color :text-color)})

(defstyles copyright []
           {:flex-grow 1
            :display "flex"
            :margin-right "10px"
            :justify-content "flex-end"})

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

(defstyles social-icon []
           {:width "50px"
            :display "flex"
            :justify-content "center"
            :font-size "25px"
            :margin-bottom "30px"})


(defstyles social-icon-href []
           {:color (color :text-color)
            :&:hover {:color (color :highlight-color)}})

(defstyles social-icons []
           {:right "40px" :top "50%"
            :transform "translateY(-100%)"
            :position "absolute"})

(defstyles home-container []
           {:width "100%" :position "relative"})

(defstyles one-badge-container []
           {:flex-basis "30%" :padding "20px"})



(defstyles one-badge []
           {:background "#1D1D1F"
            :display "flex"
            :border-radius "30px"
            :padding "20px 0px 20px 20px"})

(defstyles one-badge-image []
           {:padding "10px"})

(defstyles one-badge-title []
           {:margin-top "10px"
            :margin-bottom "20px"
            :font-family "'Oswald', sans-serif"
            :font-size "20px"
            :color (color :text-color)})

(defstyles one-badge-desc []
           {:color (color :highlight-color)
            :font-family "'Roboto', sans-serif"
            :font-size "18px"})

(defstyles badges-container []
           {:display "flex" :justify-content "center"
            :margin-top "50px"})

(defstyles badge-content-container []
           {:padding "0px 0px 0px 20px"})

(defstyles down-arrow []
           {:bottom 0 :display "flex" :justify-content "center"})




(defstyles contact-description []
           {:color (color :text-color)
            :font-family "'Roboto', sans-serif"
            :text-align "center"
            :margin "40px 0px"})

(defstyles contact-social-icon []
           {:padding "10px"})

(defstyles contact-social-icons []
           {:display "flex"
            :margin "60px 0px 180px 0px"
            :justify-content "center"})

(defstyles copyright-social-icons []
           {:display "flex"
            :justify-content "center"})

(defstyles newsletter-text []
           {:color "white"
            :width "fit-content"
            :position "absolute"
            :left "0"
            :font-size "30px"
            :font-family "'Oswald', sans-serif"
            :transform "rotate(-90deg) translate(-50%, -200%)"})

(defstyles nospam-text []
           {:color "white"
            :padding-left "30px"
            :position "relative"
            :font-family "'Oswald', sans-serif"
            :font-weight "bold"
            :font-size "75px"
            :-webkit-text-fill-color "transparent"; /* Will override color (regardless of order) */
            :-webkit-text-stroke-width "1px";
            :-webkit-text-stroke-color "white"});})

(defstyles nospam []
           {:justify-content "center"
            :margin-top "120px"})

(defstyles contact-inputs []
           {:display "flex"
            :justify-content "center"})

(defstyles contact-input-name []
          {})

(defstyles contact-input []
           {:background "transparent"
            :border "1px solid lightgrey"
            :border-radius "3px"
            :color "lightgrey"
            :margin-left "10px"
            :font-family "'Roboto', sans-serif"
            :padding "10px"
            :z-index "1"})

(defstyles contact-input-email []
           {:flex-basis "500px"
            :color "lightgrey"})


(defstyles subscribe-button []
           {:font-family "'Oswald', sans-serif"
            :color "white"
            :transform "translateX(-2px)"
            :position "relative"
            :z-index "0"
            :padding "10px 20px"
            :border (str "1px solid " (color :highlight-color))
            :background (color :highlight-color)})