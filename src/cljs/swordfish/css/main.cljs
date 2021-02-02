(ns swordfish.css.main
  (:require
    [swordfish.db :as db]
    [swordfish.css.utils :as utils :refer [color]]))

(defn footer-paying-container []
  (with-meta
    {:padding "10px 0px"}
    (utils/max-width-media
      (:l utils/size)
      {:flex-basis      "100%"
       :order           2
       :display         "flex"
       :justify-content "center"})))

(defn footer-paying []
  {:background ""
   :padding    "10px"
   :height     "25px"})

(defn footer []
  (merge utils/roboto
         {:background "white"
          :font-size  "14px"
          :width      "100%"}))

(defn footer-link []
  (with-meta
    {:flex-basis "content"
     :text-align "center"}
    {:pseudo {:hover {:color (color :highlight-color)}}}))

(defn footer-links []
  (with-meta
    {:padding         "0px 0px 0px 0px"
     :display         "flex"
     :flex-wrap       "wrap"
     :flex-grow       1
     :justify-content "flex-start"}
    (utils/max-width-media
      (:l utils/size)
      {:flex-basis      "100%"
       :order           1
       :justify-content "center"})))

(defn copyright-social-icons []
  {:display         "flex"
   :justify-content "center"})

(defn copyright []
  (with-meta {:display         "flex"
              :margin-right    "10px"
              :justify-content "flex-end"}
             (utils/max-width-media (:l utils/size) {:order           0
                                                     :flex-basis      "100%"
                                                     :padding         "10px"
                                                     :justify-content "center"})))


(defn mobile-menu-item [active?]
  {:flex-grow  1
   :color      (if active? (color :highlight-color) (color :text-color))
   :margin     "10px"
   :width      "100%"
   :text-align "center"})

(defn mobile-menu []
  (merge
    (utils/menu-animation-style)
    {:position       "absolute"
     :overflow       "hidden"
     :width          "100%"
     :top            "80px"
     :display        "flex"
     :flex-direction "column"
     :z-index        4
     :background     "rgba(0,0,0,0.9)"
     :background-image "linear-gradient(147deg, #000000 0%, #434343 74%)"}))

(defn mobile-menu-button []
  {:padding   "0px 20px"
   :z-index   5
   :cursor    "pointer"
   :color     "white"
   :font-size "25px"})

(defn footer-sections [l?]
  (if l?
    {:display   "flex"
     :flex-wrap "wrap"}
    {:justify-content "center"}))

(defn for-footer-design []
  {:display        "flex"
   :min-height     "100vh"
   :flex-direction "column-reverse"})

(defn for-page-design []
  {:flex-grow 1})


(defn logo-container []
  (if (db/m?)
    {:align-items "center"
     :display     "flex"
     :z-index     "2"}
    {:position  "absolute"
     :left      "100%"
     :transform "translateX(-100%)"}))


(defn logo []
  (if (db/s?)
    {:width   "280px"
     :padding "0px 30px"}
    {:width   "200px"
     :padding "5px 20px"}))



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

(defn menu-item-active [active?]
  (if active? (color :highlight-color)
              (color :text-color)))

(defn menu-item [active?]
  (with-meta {:text-decoration "none"}
             {:pseudo {:link    {:color (color :text-color)}
                       :visited {:color (if active? (color :highlight-color)
                                                    (color :text-color))}
                       :hover   {:color  (color :highlight-color)
                                 :cursor "pointer"}}}))

(defn nav-line []
  {:height        "50%"
   :flex-grow     1
   :z-index       "2"
   :border-bottom (str "1px solid " (color :text-color))})


