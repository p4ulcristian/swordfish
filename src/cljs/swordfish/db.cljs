(ns swordfish.db
  (:require [reagent.core :as reagent :refer [atom]]))

(def db (atom {:page :home
               :accordions []}))

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


;;;Routes

(defn change-page [the-key]
  (swap! db assoc :page the-key))

(defn get-page []
  (:page @db))

(defn this-page? [page]
  (if (= page (get-page)) true false))

