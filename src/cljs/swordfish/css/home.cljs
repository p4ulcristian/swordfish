(ns swordfish.css.home
  (:require
    [swordfish.css.utils :as utils :refer [color]]))


(defn background []
  {:background-color (color :wall-color)
   :height           "content"})

(defn background-logo []
  {:background-size "cover"
   :height          "500px"
   :position        "absolute"
   :pointer-events  "none"
   :right           0
   :top             "-150px"
   :transform       "rotate(10deg)"})


(defn background-logo-left []
  {:background-size "cover"
   :bottom          0
   :height          "500px"
   :left            0
   :pointer-events  "none"
   :position        "absolute"
   :transform       "rotate(10deg)"})

(defn logo-container []
  {:z-index     "2"
   :display     "flex"
   :align-items "center"})


(defn logo []
  {:width   "300px"
   :padding "0px 30px"})

(defn navbar []
  {:padding-top "30px"
   :display     "flex"

   :height      "70px"})

(defn menu []
  {:display         "flex"
   :height          "100%"
   :flex-basis      "500px"
   :z-index         "2"
   :font-family     "'Oswald', sans-serif"
   :font-weight     "600"
   :font-size       "20px"
   :justify-content "space-around"})

(defn menu-item []
  (with-meta {:color           (str (color :text-color))
              :text-decoration "none"}
             {:pseudo {:active  {:color (str (color :text-color))}
                       :link    {:color (str (color :text-color))}
                       :visited {:color (str (color :text-color))}
                       :hover   {:color  (color :highlight-color)
                                 :cursor "pointer"}}}))

(defn nav-line []
  {:height        "50%"
   :flex-grow     1
   :z-index       "2"
   :border-bottom (str "1px solid " (color :text-color))})

(defn wakizashi []
  {:position  "absolute"
   :height    "30px"
   :width     "350px"
   :font-size "15px"
   :left      "5%"
   :top       "120px"})




(defn footer-paying []
  {:background ""
   :padding    "10px"
   :height     "25px"})

(defn footer []
  {:background "white"
   :font-size  "14px"
   ;:height "50px"
   :width      "100%"})

(defn footer-link []
  {:flex-basis "content"})

(defn wakizashi-description []
  (merge utils/roboto
         {:line-height    "25px"
          :padding        "30px 0px 30px 0px"
          :text-transform "uppercase"
          :color          (color :text-color)}))

(defn copyright []
  {:flex-grow       1
   :display         "flex"
   :margin-right    "10px"
   :justify-content "flex-end"})

(defn price []
  {:display          "flex"
   :font-weight      "bold"
   :background-color (color :price-color)})

(defn price-box []
  {:display          "flex"
   :background-color (color :price-color)
   :width            "max-content"
   :font-family      "'Oswald', sans-serif"
   :justify-content  "space-around"
   :padding          "0px 10px 0px 20px"
   :color            "white"})

(defn social-icon []
  {:width           "50px"
   :display         "flex"
   :justify-content "center"
   :font-size       "25px"
   :margin-bottom   "30px"})


(defn social-icon-href []
  (with-meta
    {}
    {:pseudo {:link    {:color (str (color :text-color))}
              :visited {:color (str (color :text-color))}
              :active  {:color (str (color :text-color))}
              :hover   {:color (color :highlight-color)}}}))


(defn social-icons []
  {:right     "40px" :top "50%"
   :transform "translateY(-100%)"
   :position  "absolute"})

(defn home-container []
  {:width "100%" :position "relative"})

(defn one-badge-container []
  {:flex-basis "30%" :padding "20px"})



(defn one-badge []
  {:background    "#1D1D1F"
   :display       "flex"
   :border-radius "30px"
   :padding       "20px 0px 20px 20px"})

(defn one-badge-image []
  {:padding "10px"})

(defn one-badge-title []
  {:margin-top    "10px"
   :margin-bottom "20px"
   :font-family   "'Oswald', sans-serif"
   :font-size     "20px"
   :color         (color :text-color)})

(defn one-badge-desc []
  {:color       (color :highlight-color)
   :font-family "'Roboto', sans-serif"
   :font-size   "18px"})

(defn badges-container []
  {:display    "flex" :justify-content "center"
   :margin-top "50px"})

(defn badge-content-container []
  {:padding "0px 0px 0px 20px"})

(defn down-arrow []
  {:bottom 0 :display "flex" :justify-content "center"})




(defn contact-description []
  {:color       (color :text-color)
   :font-family "'Roboto', sans-serif"
   :text-align  "center"
   :margin      "40px 0px"})

(defn contact-social-icon []
  {:padding "10px"})

(defn contact-social-icons []
  {:display         "flex"
   :margin          "60px 0px 180px 0px"
   :justify-content "center"})

(defn copyright-social-icons []
  {:display         "flex"
   :justify-content "center"})

(defn newsletter-text []
  {:color       "white"
   :width       "fit-content"
   :position    "absolute"
   :left        "0"
   :font-size   "30px"
   :font-family "'Oswald', sans-serif"
   :transform   "rotate(-90deg) translate(-50%, -200%)"})

(defn nospam-text []
  (merge utils/stroked-word
         {:color                     "white"
          :padding-left              "30px"
          :position                  "relative"
          :font-family               "'Oswald', sans-serif"
          :font-weight               "bold"
          :font-size                 "75px"}))

(defn nospam []
  {:justify-content "center"
   :margin-top      "120px"})

(defn contact-inputs []
  {:display         "flex"
   :justify-content "center"})

(defn contact-input-name []
  {})

(defn contact-input []
  {:background    (color :wall-color)
   :border        "1px solid lightgrey"
   :border-radius "3px"
   :color         "lightgrey"
   :margin-left   "10px"
   :font-family   "'Roboto', sans-serif"
   :padding       "10px"
   :z-index       "1"})

(defn contact-input-email []
  {:flex-basis "500px"
   :color      "lightgrey"})


(defn subscribe-button []
  {:font-family "'Oswald', sans-serif"
   :color       "white"
   :transform   "translateX(-2px)"
   :position    "relative"
   :z-index     "0"
   :padding     "10px 20px"
   :border      (str "1px solid " (color :highlight-color))
   :background  (color :highlight-color)})