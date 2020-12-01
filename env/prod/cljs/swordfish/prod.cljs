(ns swordfish.prod
  (:require [swordfish.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
