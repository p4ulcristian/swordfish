(ns swordfish.css.contact
  (:require
    [swordfish.db :as db]
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
         {:color         (color :muted-white)
          :font-size     "30px"
          :font-weight   "bold"
          :margin-bottom "70px"
          :text-align    "center"}))

(defn label-name []
  (merge utils/roboto
         {:padding "10px 0px"
          :color   (color :text-color-two)}))

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
            :display         "flex"
            :font-size       "20px"
            :justify-content "center"
            :letter-spacing  "1.2px"
            :line-height     "30px"})
    {:pseudo {:active  {:color "white"}
              :link    {:color "white"}
              :visited {:color "white"}
              :hover   {:color  "#999"
                        :cursor "pointer"}}}))

(defn form-top []
  (with-meta
    {:display         "flex"
     :justify-content "space-between"
     :margin-bottom   "10px"
     :flex-wrap       "wrap"}
    {:combinators {[:> :div] {:flex (if (not (db/s?))
                                      "0 100%"
                                      "0 50%")}}}))

(defn subscribe-button-container []
  (merge utils/oswald
         {:display         "flex"
          :justify-content "center"
          :margin-bottom   "100px"
          :margin-top      "40px"}))


(defn subscribe-button []
  (merge utils/oswald
         {:background (color :highlight-color)
          :color      "white"
          :cursor     "pointer"
          :padding    "10px 0"
          :width           "250px"}))




