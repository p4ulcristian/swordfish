(ns swordfish.css.home
  (:require
    [swordfish.db :as db]
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
   :top             "-150px"})


(defn background-logo-left []
  {:background-size "cover"
   :bottom          0
   :height          "500px"
   :left            0
   :max-width       "100%"
   :pointer-events  "none"
   :position        "absolute"})
;:transform       "rotate(10deg)"})

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
             {:pseudo {:active  {:color "red"}
                       :link    {:color (color :text-color)}
                       :visited {:color (color :text-color)}
                       :hover   {:color  (color :highlight-color)
                                 :cursor "pointer"}}}))

(defn nav-line []
  {:height        "50%"
   :flex-grow     1
   :z-index       "2"
   :border-bottom (str "1px solid " (color :text-color))})




;wakizashi-price-card
(defn price []
  {:display          "flex"
   :font-weight      "bold"
   :background-color (color :price-color)})

(defn price-box-container []
  (with-meta (merge utils/oswald
                    {:display         "flex"
                     :justify-content "flex-start"
                     :font-family     "'Oswald', sans-serif"
                     :color           "white"})
             (utils/max-width-media (:l utils/size)
                                    {:justify-content "center"})))

(defn price-box []
  {:display          "flex"
   :background-color (color :price-color)
   :padding          "0px 10px 0px 20px"
   :width            "fit-content"})



(defn wakizashi-description []
  (let [style (merge utils/roboto
                     {:line-height    "25px"
                      :padding        "30px 10px 30px 10px"
                      :text-transform "uppercase"
                      :color          (color :text-color)})]
    (if (db/l?) style
                (merge style {:text-align "center"
                              :font-size  "14px"}))))

(defn wakizashi-img []
  (if (not (db/s?))
    {:width   "300px"
     :padding "0px 20px"
     :margin  "auto"}
    {:padding "0px 20px"}))


(defn wakizashi-container []
  (if (db/l?)
    {:justify-content "center"
     :display         "flex"
     :position        "absolute"
     :left            "5%"
     :margin-top      "60px"}
    {:position   "relative"
     :margin-top "0px"}))



(defn wakizashi []
  (if (db/s?)
    {:width     "350px"
     :font-size "15px"
     :margin    "auto"}
    {:width     "100%"
     :font-size "14px"
     :margin    "auto"}))


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
  (let [style {:right     "0px"
               :top       "50%"
               :transform "translateY(-50%)"
               :position  "absolute"}]
    (if (db/l?)
      style
      (merge style {:right "5px"}))))

(defn main-section-img-container []
  (let [style {:flex-grow       1
               :display         "flex"
               :margin-top      "60px"
               :flex-direction  "column"
               :justify-content "center"
               :width           "100%"}]
    (if (db/l?)
      style
      (merge style {:margin-top "0px"
                    :padding    "50px"
                    :flex-grow  0}))))

(defn main-section []
  (let [style {:width          "100%" :position "relative"
               :display        "flex"
               :flex-direction "column"
               :flex-grow      1
               :min-height     "calc(100vh - 70px)"}]
    (if (db/l?)
      style
      (merge style {:min-height "inherit"
                    :height     "auto"}))))


(defn one-badge-container []
  {:flex-basis "400px" :padding "20px"})


(defn one-badge []
  (let [style {:background    "#1D1D1F"
               :display       "flex"
               :border-radius "30px"
               :padding       "20px"}]
    (if (db/s?)
      style
      (merge style {:display "block" :text-align "center"}))))

(defn one-badge-image []
  {:padding "10px"
   :margin  "auto"})

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
  (if (db/l?)
    {:display    "flex" :justify-content "center"
     :flex-wrap  "wrap"
     :margin-top "50px"}
    {:display    "flex" :justify-content "center"
     :flex-wrap  "wrap"
     :margin-top "0px"}))

(defn badge-content-container []
  {:padding "0px 0px 0px 20px"})

(defn down-arrow []
  {:bottom   0 :display "flex" :justify-content "center"
   :position "relative"
   :padding  "40px 0px"})

(defn contact-description []
  (with-meta
    {:color       (color :text-color)
     :font-family "'Roboto', sans-serif"
     :text-align  "center"
     :margin      "40px 0px"}
    (utils/max-width-media (:m utils/size)
                           {:padding "0px 20px"})))

(defn contact-social-icon []
  {:padding "10px"})

(defn contact-social-icons []
  {:display         "flex"
   :justify-content "center"})

(defn newsletter-text []
  (with-meta {:color       "white"
              :width       "fit-content"
              :position    "absolute"
              :left        "0"
              :font-size   "30px"
              :font-family "'Oswald', sans-serif"
              :transform   "rotate(-90deg) translate(-50%, -200%)"}
             {:media {(utils/media-width {:max-width (:m utils/size)})
                      {:font-size "20px"}
                      (utils/media-width {:max-width (:xs utils/size)})
                      {:font-size "14px"
                       :transform "rotate(-90deg) translate(-50%, -100%)"}}}))

(defn nospam-text []
  (with-meta (merge utils/stroked-word
                    utils/oswald
                    {:color        "white"
                     :padding-left "30px"
                     :position     "relative"
                     :font-weight  "bold"
                     :font-size    "75px"})
             {:media {(utils/media-width {:max-width (:m utils/size)})
                      {:font-size "50px"}
                      (utils/media-width {:max-width (:xs utils/size)})
                      {:font-size    "35px"
                       :padding-left "30px"}}}))

(defn nospam []
  (with-meta
    {:justify-content "center"
     :margin-top      "120px"
     :display         "flex"}
    {:media {(utils/media-width {:max-width (:m utils/size)})
             {:flex-wrap  "wrap"
              :margin-top "20px"}}}))


(defn nospam-logo []
  (with-meta
    {:justify-content "center"
     :padding         "20px"}
    {:media {(utils/media-width {:max-width (:m utils/size)})
             {:flex-basis "100%"}}}))

(defn contact-inputs []
  (with-meta
    {:display         "flex"
     :justify-content "center"}
    {:media {(utils/media-width {:max-width (:s utils/size)})
             {:flex-wrap "wrap"}}}))

(defn contact-input-name []
  {})

(defn contact-input []
  (with-meta
    {:background    (color :wall-color)
     :border        "1px solid lightgrey"
     :border-radius "3px"
     :color         "lightgrey"
     :margin-left   "10px"
     :font-family   "'Roboto', sans-serif"
     :padding       "10px"
     :z-index       "1"}
    (utils/max-width-media (:s utils/size)
                           {:flex-basis    "90%"
                            :margin-left   "0px"
                            :margin-bottom "20px"})))

(defn contact-input-email []
  {:flex-basis "500px"
   :color      "lightgrey"})


(defn subscribe-button []
  (with-meta
    {:font-family "'Oswald', sans-serif"
     :color       "white"
     :transform   "translateX(-2px)"
     :position    "relative"
     :z-index     "0"
     :padding     "10px 20px"
     :border      (str "1px solid " (color :highlight-color))
     :background  (color :highlight-color)}
    (utils/max-width-media (:s utils/size)
                           {:flex-basis    "90%"
                            :margin-bottom "20px"
                            :transform     "translateX(0px)"})))