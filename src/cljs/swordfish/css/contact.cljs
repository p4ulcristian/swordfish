(ns swordfish.css.contact
  (:require
    [swordfish.css.utils :refer [color] :as utils]))

(defn questions? []
  (merge utils/stroked-word
         utils/oswald
         {:font-size     "35px"
          :margin-top    "100px"
          :margin-bottom "20px"
          :text-align    "center"
          :font-weight   "bold"}))

(defn fill-the-form []
  (merge utils/oswald
         {:color         "white"
          :font-size     "30px"
          :font-weight   "bold"
          :margin-bottom "70px"
          :text-align    "center"}))

(defn label-name []
  (merge utils/roboto
         {:padding "10px 0px"
          :color   "#A6A2A2"}))

(defn input []
  (merge utils/roboto
         {:border        "1px solid #A6A2A2"
          :border-radius "5px"
          :background    (color :wall-color)
          :color         "lightgrey"
          :padding       "10px"
          :width         "100%"}))

(defn message []
  (merge utils/roboto
         {:border        "1px solid #A6A2A2"
          :border-radius "5px"
          :padding       "10px"
          :background    (color :wall-color)
          :color         "lightgrey"
          :width         "100%"
          :height        "400px"}))

(defn form []
  {:display         "flex"
   :justify-content "center"})

(defn form-content []
  {:width "800px"})

(defn link []
  (with-meta
    (merge utils/oswald
           {:color           "white"
            :font-size       "20px"
            :line-height     "30px"
            :display         "flex"
            :justify-content "center"})
    {:pseudo {:active  {:color "white"}
              :link    {:color "white"}
              :visited {:color "white"}
              :hover   {:color  (color :highlight-color)
                        :cursor "pointer"}}}))

(defn form-top []
  (with-meta
    {:display         "flex"
     :justify-content "space-between"
     :margin-bottom   "10px"}
    {:combinators {[:> :div] {:flex "0 50%"}}}))

(defn subscribe-button-container []
  (merge utils/oswald
         {:display         "flex"
          :justify-content "center"
          :margin-top      "40px"
          :margin-bottom   "100px"}))

(defn subscribe-button []
  (merge utils/oswald
         {:background (color :highlight-color)
          :color      "white"
          :cursor     "pointer"
          :padding    "10px 30px"}))




