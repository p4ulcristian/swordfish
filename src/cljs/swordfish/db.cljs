(ns swordfish.db
  (:require [reagent.core :as reagent :refer [atom]]))

(def db (atom {:accordions []}))

(defn conj-set-vec [list item]
  (vec (set (conj list item))))

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
