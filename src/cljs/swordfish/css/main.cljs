(ns swordfish.css.main
  (:require
    [swordfish.db :as db]
    [swordfish.css.utils :as utils :refer [color]]))

(defn footer-paying-container []
  (with-meta
    {:padding "10px 0px"}
    (utils/max-width-media
      (:l utils/size)
      {:display         "flex"
       :flex-basis      "100%"
       :justify-content "center"
       :order           2})))

(defn footer-paying []
  {:background ""
   :height     "25px"
   :padding    "10px"})

(defn footer []
  (merge utils/roboto
         {:background "white"
          :font-size  "14px"
          :width      "100%"}))

(defn footer-link []
  (with-meta
    {:flex-basis  "content"
     :text-align  "center"
     :line-height "46px"
     :font-size   "12px"}
    {:pseudo {:hover {:color (color :highlight-color)}}}))

(defn footer-links []
  (with-meta
    {:display         "flex"
     :flex-grow       1
     :flex-wrap       "wrap"
     :justify-content "flex-start"
     :padding         "0px 0px 0px 0px"}
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
              :justify-content "flex-end"
              :margin-right    "10px"}
             (utils/max-width-media (:l utils/size) {:order           0
                                                     :flex-basis      "100%"
                                                     :padding         "10px"
                                                     :justify-content "center"})))

(defn mobile-menu-item [active?]
  {:align-content   "center"
   :color           (if active? (color :highlight-color) (color :text-color))
   :display         "flex"
   :flex-direction  "column"
   :flex-grow       1
   :font-weight     "bold"
   :height          "30px"
   :justify-content "center"
   :margin          "10px"
   :text-align      "center"
   :width           "100%"})

(defn mobile-menu-overlay []
  {:background-color "rgba(0,0,0,.85)"
   :height           "100%"
   :left             0
   :position         "absolute"
   :top              0
   :width            "100%"})

(defn mobile-menu-content []
  (merge utils/oswald
         {:align-items     "center"
          :color           "white"
          :display         "flex"
          :flex-direction  "column"
          :height          "100%"
          :justify-content "center"
          :left            0
          :position        "absolute"
          :top             0
          :width           "100%"}))


(defn mobile-menu []
  {:height     "100vh"
   :left       0
   :position   "fixed"
   :top        0
   :transform  "translateY(-100%)"
   :transition "0.2s ease-in-out"
   :width      "100%"})

(defn mobile-menu-opened []
  {:transform "translateY(0%)"})

(defn mobile-menu-button []
  {:color     "white"
   :cursor    "pointer"
   :font-size "25px"
   :padding   "0px 20px"
   :z-index   5})

(defn footer-sections [l?]
  (if l?
    {:display   "flex"
     :flex-wrap "wrap"}
    {:justify-content "center"}))

(defn for-footer-design []
  {:display        "flex"
   :flex-direction "column-reverse"
   :min-height     "100vh"})

(defn for-page-design []
  {:flex-grow 1})


(defn logo-container []
  (if (db/m?)
    {:align-items "center"
     :display     "flex"
     :z-index     "2"}
    {:left      "100%"
     :position  "absolute"
     :transform "translateX(-100%)"}))


(defn logo []
  (if (db/s?)
    {:padding "0px 30px"
     :width   "280px"}
    {:padding "5px 20px"
     :width   "200px"}))

(defn navbar []
  {:display     "flex"
   :height      "70px"
   :padding-top "30px"})

(defn menu []
  {:display         "flex"
   :flex-basis      "500px"
   :font-family     "'Oswald', sans-serif"
   :font-size       "20px"
   :font-weight     "600"
   :height          "100%"
   :justify-content "space-around"
   :z-index         "2"})

(defn menu-item-active [active?]
  (if active? (color :highlight-color)
              (color :text-color-two)))

(defn menu-item-badge []
  {:position        "absolute"
   :background      "#f02849"
   :border-radius   "50%"
   :top             0
   :right           0
   :height          "15px"
   :width           "15px"
   :color           "white"
   :font-size       "10px"
   :display         "flex"
   :align-content   "center"
   :justify-content "center"
   :z-index 1000
   :transform "translate(80%, 40%)"
   :line-height "11px"
   :padding "2px"})

(defn menu-item [active?]
  (with-meta {:text-decoration "none"
              :color           {:color (color :text-color-two)}}
             {:pseudo {:link    {:color (color :text-color-two)}
                       :active  {:color (color :text-color-two)}
                       :visited {:color (if active? (color :highlight-color)
                                                    (color :text-color))}
                       :hover   {:color  (color :highlight-color)
                                 :cursor "pointer"}}}))

(defn nav-line []
  {:border-bottom (str "1px solid " (color :text-color))
   :flex-grow     1
   :height        "50%"
   :z-index       "2"})


