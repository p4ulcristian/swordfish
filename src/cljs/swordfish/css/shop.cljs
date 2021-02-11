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


(defn product-photo-container []
  (with-meta {:border        (str "1px solid " "#555")
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
  (let [style {:color       (color :text-color-two)
               :font-family "'Oswald', sans-serif"
               :font-size   "25px"
               :text-align  "right"
               :padding     "0px 25px 20px 0px"}]
    (if (db/s?)
      style
      (merge style {:font-size "15px"
                    :padding   "0px 10px 0px 0px"}))))

(defn product-price []
  (let [style {:color       (color :text-color-two)
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
  (let [style {:color       (color :text-color-two)
               :font-family "'Oswald', sans-serif"
               :font-size   "25px"
               :text-align  "left"
               :padding     "0px 25px 20px 0px"}]
    (if (db/s?)
      style
      (merge style {:font-size "15px"
                    :padding   "0px 10px 0px 0px"}))))

(defn product-details-price []
  (let [style {:color       (color :text-color-two)
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
  {:border-left  (str "1px solid" (color :text-color-two))
   :border-right (str "1px solid" (color :text-color-two))
   :height       "50px"
   :width        "50px"
   :text-align   "center"

   :color        (color :text-color-two)})

(defn quantity-modify []
  {
   :cursor          "pointer"
   :height          "50px"
   :width           "50px"
   :display         "flex"
   :flex-direction  "column"
   :justify-content "center"
   :text-align      "center"
   :color           (color :text-color-two)})

(defn quantity-group []
  {:display        "flex"
   :border         (str "1px solid " (color :text-color-two))
   :flex-direction "flex-end"})

(defn quantity-label []
  {:flex-grow       1
   :font-size       "26px"
   :display         "flex"
   :color           (color :text-color-two)
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
    {:pseudo {:hover {:transform "scale(1.05)"}}}))

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
         {:color         "white"
          :display       "flex"
          :margin-bottom "10px"}))


(defn one-cart-price []
  (merge utils/roboto
         {:flex-grow  1
          :text-align "right"}))

(defn one-cart-title []
  (merge utils/roboto
         {:width     "300px"
          :font-size "14x"}))

(defn cart-title []
  (merge utils/oswald
         {:color "white"}))

(defn cart-total []
  {:display   "flex"
   :color     "white"
   :font-size "24px"})

(defn cart-total-price []
  {:text-align "right"
   :flex-grow  1})

(defn cart []
  (merge utils/oswald
         {:padding   "0px 20px"
          :margin    "80px auto"
          :max-width "500px"}))

(defn cart-content []
  {:border-bottom  (str "1px solid " (color :text-color))
   :padding-bottom "10px"
   :margin-bottom  "10px"})

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

(defn shipping-button []
  (merge
    (add-to-cart-button)
    {:background "#25c075"}))



(defn guarantee-label []
  (merge utils/roboto
         {:color   (color :text-color)
          :padding "10px 10px"}))

(defn stars []
  (with-meta
    {:transform "translateY(-5px)"}

    {:combinators {[:> :img] {:width "110px"}}}))

(defn buying-description []
  (with-meta (merge utils/roboto
                    {:color         (color :text-color)
                     :padding       "10px 50px"
                     :width         "100%"
                     :margin-bottom "50px"})
             {:combinators {[:> :h1] {:color (color :text-color-two)}
                            [:> :p]  {:margin    "10px"
                                      :font-size (if (not (db/xs?)) "20px" "14px")}}}))

(defn detail-categories []
  (with-meta (merge utils/oswald
                    {:color           (color :text-color-two)
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

(defn shipping-fields-container []
  {:max-width "500px"
   :padding   "0px 20px"
   :margin    "auto"})

(defn shipping-fields []
  (with-meta
    {:display         "flex"
     :justify-content "center"
     :width           "100%"
     :flex-direction  "row"
     :flex-wrap       "wrap"}
    {:combinators {[:> :div] {:flex            "0 50%"
                              :display         "flex"
                              :justify-content "center"
                              :margin-bottom "30px"}}}))

