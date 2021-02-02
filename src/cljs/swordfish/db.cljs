(ns swordfish.db
  (:require [reagent.core :as reagent :refer [atom]]))

(def db (atom {:accordions []
               :menu       false
               :page       :home}))

(defn conj-set-vec [list item]
  (vec (set (conj list item))))

;accordion functions

(defn open-accordion [the-key]
  (swap! db assoc :accordions (conj-set-vec (:accordions @db) the-key)))

(defn close-accordion [the-key]
  (swap! db assoc :accordions (vec (remove #(= the-key %) (:accordions @db)))))

(defn open-accordion? [the-key]
  (not (empty? (filter #(= % the-key) (:accordions @db)))))

(defn toggle-accordion [the-key]
  (if (open-accordion? the-key)
    (close-accordion the-key)
    (open-accordion the-key)))

;viewport functions
(defn add-event-listener [el type callback]
  (.addEventListener el type callback true))

(defn remove-event-listener [el type callback]
  (.removeEventListener el type callback true))

(def document-element (.-documentElement js/document))

(defn get-viewport-width []
  (max (.-clientWidth document-element)))
;(.-innerWidth js/window)))

(defn get-viewport-height []
  (max (.-clientHeight document-element)))
;(.-innerHeight js/window)))

(defn get-viewport-data []
  (swap! db assoc :viewport
         {:width  (get-viewport-width)
          :height (get-viewport-height)}))

(defn xs? [] (> (-> @db :viewport :width) 300))
(defn s? [] (> (-> @db :viewport :width) 500))
(defn m? [] (> (-> @db :viewport :width) 800))
(defn l? [] (> (-> @db :viewport :width) 1100))

(defn add-viewport-listener [func]
  (add-event-listener js/window "resize" func))

(defn init-viewport-listener []
  (get-viewport-data)
  (add-viewport-listener get-viewport-data))

;;;Routes

(defn change-page [the-key]
  (swap! db assoc :page the-key))

(defn get-page []
  (:page @db))

(defn this-page? [page]
  (if (= page (get-page)) true false))

;menu

(defn toggle-menu []
  (swap! db assoc :menu (not (:menu @db))))

(defn menu-open? []
  (:menu @db))

