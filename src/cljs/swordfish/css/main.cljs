(ns swordfish.css.main
  (:require
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

(defn mobile-menu-button []
  {:padding   "0px 20px"
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


