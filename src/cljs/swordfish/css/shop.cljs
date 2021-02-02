(ns swordfish.css.shop
  (:require
    [swordfish.css.utils :refer [color] :as utils]
    [swordfish.db :as db]))


;parts-section
(defn one-part []
  {:flex          "0 50%"
   :margin-bottom "50px"})


(defn parts-section []
  {:display         "flex"
   :flex-wrap       "wrap"
   :padding-bottom  "50px"
   :justify-content "center"
   :padding-top     "30px"})


;product-card

(defn product-photos-container []
  {:flex-grow 1})

(defn product-photo-container []
  (with-meta {:border        (str "1px solid " (color :text-color))
              :border-radius "12px"
              :margin-left   "15px"}
             {:pseudo {:hover {:cursor "pointer"}}}))

(defn product-photo []
  {:height "50px"
   :width  "50px"})

(defn product-photos []
  {:bottom   "10px"
   :display  "flex"
   :left     "10px"
   :position "absolute"})

(defn product-title []
  (let [style {:color          "grey"
               :font-family    "'Oswald', sans-serif"
               :font-size      "16px"
               :letter-spacing "7px"
               :line-height    "30px"
               :padding        "10px 25px 0px 0px"
               :text-align     "right"}]
    (if (db/s?)
      style
      (merge style {:font-size      "12px"
                    :letter-spacing "5px"
                    :padding        "10px 10px 0px 0px"}))))

(defn product-type []
  (let [style {:color       (color :text-color)
               :font-family "'Oswald', sans-serif"
               :font-size   "25px"
               :text-align  "right"
               :padding     "0px 25px 20px 0px"}]
    (if (db/s?)
      style
      (merge style {:font-size "15px"
                    :padding   "0px 10px 0px 0px"}))))

(defn product-price []
  (let [style {:color       (color :text-color)
               :font-family "'Oswald', sans-serif"
               :font-size   "40px"
               :padding     "0px 25px 10px 0px"
               :text-align  "right"}]
    (if (db/s?)
      style
      (merge style {:font-size "20px"
                    :padding   "0px 10px 0px 0px"}))))

(defn product-details-title []
  (let [style {:color          "grey"
               :font-family    "'Oswald', sans-serif"
               :font-size      "18px"
               :letter-spacing "7px"
               :line-height    "30px"
               :padding        "10px 25px 0px 0px"
               :text-align     "left"}]
    (if (db/s?)
      style
      (merge style {:font-size      "14px"
                    :letter-spacing "5px"
                    :padding        "10px 10px 0px 0px"}))))

(defn product-details-type []
  (let [style {:color       (color :text-color)
               :font-family "'Oswald', sans-serif"
               :font-size   "25px"
               :text-align  "left"
               :padding     "0px 25px 20px 0px"}]
    (if (db/s?)
      style
      (merge style {:font-size "15px"
                    :padding   "0px 10px 0px 0px"}))))

(defn product-details-price []
  (let [style {:color       (color :text-color)
               :font-family "'Oswald', sans-serif"
               :display     "flex"
               :font-size   "35px"
               :padding     "0px 25px 10px 0px"
               :text-align  "left"}]
    (if (db/s?)
      style
      (merge style {:font-size "20px"
                    :padding   "0px 10px 0px 0px"}))))

(defn quantity-input []
  {:border     (str "1px solid " (color :text-color))
   :height     "50px"
   :width      "50px"
   :text-align "center"
   :color      (color :text-color)})

(defn quantity-modify []
  {:border          (str "1px solid " (color :text-color))
   :cursor          "pointer"
   :height          "50px"
   :width           "50px"
   :display         "flex"
   :flex-direction  "column"
   :justify-content "center"
   :text-align      "center"
   :color           (color :text-color)})

(defn quantity-group []
  {:display        "flex"
   :flex-direction "flex-end"})

(defn quantity-label []
  {:flex-grow       1
   :font-size       "26px"
   :display         "flex"
   :color           (color :text-color)
   :flex-direction  "column"
   :justify-content "center"})




;;main-description

(defn one-description-icon []
  {:margin-left  "10px"
   :stroke       "#2B83C7"
   :stroke-width "5"
   :width        "20px"})

(defn one-description []
  {:display         "flex"
   :text-align      "left"
   :justify-content "center"})

(defn one-description-content []
  {:font-family "'Roboto', sans-serif"
   :color       (color :highlight-color)})

(defn one-description-title []
  {:color       "lightgrey"
   :display     "flex"
   :font-family "'Oswald', sans-serif"
   :padding     "10px 0px"})


(defn one-description-container []
  {:width "150px"})


;;Main product

(defn product-card [url]
  (with-meta
    {:background-image    (str "url(" url ")")
     :background-size     "contain"
     :background-repeat   "no-repeat"
     :background-position "center"
     :background-color    "#1D1D1F"
     :border-radius       "25px"
     :cursor              "pointer"
     :display             "flex"
     :margin              "auto"
     :flex-direction      "column"
     :height              (cond
                            (db/l?) "500px"
                            (db/m?) "500px"
                            (db/s?) "500px"
                            (db/xs?) "300px"
                            :else "300px")
     :transition          "0.1s ease"
     :width               (cond
                            (db/l?) "500px"
                            (db/m?) "500px"
                            (db/s?) "500px"
                            (db/xs?) "300px"
                            :else "300px")}
    {:pseudo {:hover {:transform "translate(0px, 10px)"}}}))

(defn product-details-card [url]
  {:background-image    (str "url(" url ")")
   :background-size     "contain"
   :background-repeat   "no-repeat"
   :background-position "center"
   :background-color    "#1D1D1F"
   :border-radius       "25px"
   :cursor              "pointer"
   :display             "flex"
   :width               (if (db/s?) "420px" "300px")
   :height              (if (db/s?) "420px" "300px")
   :margin              "20px auto"
   :flex-direction      "column"})

(defn one-cart []
  (merge utils/roboto
         {:color "white"}))

(defn add-to-cart-button []
  (merge utils/oswald
         {:background      (color :highlight-color)
          :padding         "10px 20px"
          :width           "100%"
          :color           "white"
          :font-size       "20px"
          :margin          "15px 0px"
          :justify-content "center"
          :display         "flex"}))

(defn guarantee-label []
  (merge utils/roboto
         {:color   (color :text-color)
          :padding "10px 10px"}))

(defn buying-description []
  (with-meta (merge utils/roboto
                    {:color         (color :text-color)
                     :padding       "10px 50px"
                     :width         "100%"
                     :margin-bottom "50px"})
             {:combinators {[:> :p] {:margin    "10px"
                                     :font-size (if (not (db/xs?)) "20px" "14px")}}}))

(defn detail-categories []
  (with-meta (merge utils/oswald
                    {:color           (color :text-color)
                     :padding         "10px 50px"
                     :width           "100%"
                     :display         "flex"
                     :justify-content "space-around"
                     :margin-bottom   "50px"})
             {:pseudo      {:hover {:color {}}}
              :combinators {[:> :div] {:margin "10px"}}}))


(defn icon []
  {:margin-left  "10px"
   :stroke       "#2B83C7"
   :stroke-width "5"
   :width        "20px"})

(defn product-details-buying []
  {:flex    "0 50%"
   :padding "20px 30px"})

(defn product-details-quantity []
  {:display "flex" :font-family "'Oswald', sans-serif"
   :color   (color :text-color)})


(defn product-details []
  (if (db/m?)
    {:display    "flex"
     :margin-top "60px"}
    {:margin-top "60px"}))




(defn main-product-container []
  (if (db/l?)
    {:flex-basis "33%"}
    {:flex-basis      "100%"
     :display         "flex"
     :justify-content "center"}))


(defn main-description []
  (with-meta
    {:display         "flex"
     :flex-basis      "33%"
     :flex-direction  "column"
     :justify-content "space-evenly"
     :padding         "0px 30px"}
    {:media {(utils/media-width {:max-width (:m utils/size)})
             {:flex-basis "50%"
              :margin-top "30px"}}}))

(defn main-section []
  (with-meta
    {:display         "flex"
     :justify-content "center"
     :margin-top      "100px"
     :margin-bottom   "50px"}
    {:media {(utils/media-width {:max-width (:m utils/size)})
             {:flex-wrap "wrap"}}}))

;;Unordered

(defn spacer []
  {:border-bottom (str "1px solid " (color :highlight-color))
   :padding       "5px"
   :width         "100%"})

(defn wakizashi-grey-container []
  (with-meta
    {:padding    "40px 20px"
     :width      "100%"
     :flex-basis "33%"}
    {:media {(utils/media-width {:max-width (:m utils/size)})
             {:display "none"}}}))

(defn wakizashi-grey []
  {:background-size     "contain"
   :background-repeat   "no-repeat"
   :background-position "center"
   :background-image    "url(/img/wakizashi-grey.png)"
   :height              "100%"
   :width               "100%"})