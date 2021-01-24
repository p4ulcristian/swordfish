(ns swordfish.views.css
  (:require
        [cljss.core :refer-macros [defstyles defkeyframes font-face] :as css]))


(defn remove-styles! []
  (css/remove-styles!))

(def color-scheme
  (atom {:wall-color "#181819"
         :text-color "#A6A2A2"
         :highlight-color  "#2B83C7"
         :price-color "#2B83C7"}))


(defn color [the-key]
  (get @color-scheme the-key))

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
            :&:hover {:color (:highlight-color @color-scheme)
                      :cursor "pointer"}})



(defstyles nav-line []
           {:height "50%"
            :flex-grow 1
            :z-index "2"
            :border-bottom (str "1px solid " (color :text-color))})

(defstyles vertical-align []
           {:display "flex"
            :align-items "center"})


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

(defstyles p-font []
           {:font-family "'Roboto', sans-serif"
            :line-height "25px"
            :text-transform "uppercase"
            :color (color :text-color)})


(defstyles padding [param]
           {:padding param})

(defstyles font-size [param]
           {:font-size param})

(defstyles position  [param]
           {:position param})

(defstyles text-color [param]
           {:color param})

(defstyles copyright []
           {:flex-grow 1
            :display "flex"
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
           {:color (:text-color @color-scheme)
            :&:hover {:color (:highlight-color @color-scheme)}})

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
            :color (:text-color @color-scheme)})

(defstyles one-badge-desc []
           {:color (:highlight-color @color-scheme)
            :font-family "'Roboto', sans-serif"
            :font-size "18px"})

(defstyles badges-container []
           {:display "flex" :justify-content "center"
            :margin-top "50px"})

(defstyles badge-content-container []
           {:padding "0px 0px 0px 20px"})

(defstyles down-arrow []
           {:bottom 0 :display "flex" :justify-content "center"})

(defstyles flex []
           {:display "flex"})

(defstyles content-width []
           {:max-width "1200px" :margin "auto"
            :position "relative"})

(defstyles contact-description []
           {:color (:text-color @color-scheme)
            :font-family "'Roboto', sans-serif"
            :text-align "center"})

(defstyles contact-social-icon []
           {:padding "10px"})

(defstyles contact-social-icons []
           {:display "flex"
            :justify-content "center"})

(defstyles copyright-social-icons []
           {:display "flex"
            :justify-content "center"})

(defstyles newsletter-text []
           {:color "white"
            :width "fit-content"
            :position "absolute"
            :left "0"
            :font-size "20px"
            :font-family "'Oswald', sans-serif"
            :transform "rotate(-90deg) translate(-50%, -150%)"})

(defstyles nospam-text []
           {:color "white"
            :padding-left "30px"
            :position "relative"
            :font-family "'Oswald', sans-serif"
            :font-size "50px"
            :-webkit-text-fill-color "transparent"; /* Will override color (regardless of order) */
            :-webkit-text-stroke-width "1px";
            :-webkit-text-stroke-color "white"});})

(defstyles nospam []
           {:justify-content "center"
            :margin-top "50px"})