(ns swordfish.compile
    (:require [hf.depstar :as hf]
              [shadow.cljs.devtools.server :as server]
              [shadow.cljs.devtools.api    :as shadow]
              [clojure.java.io :as io :refer [file delete-file]])
    (:gen-class))


(defn directory-exists? [directory-path]
  (let [directory (file directory-path)]
       (and (.exists directory)
            (.isDirectory directory))))

(defn file-exists? [filepath]
  (let [file (file filepath)]
       (and (.exists file)
            (not (.isDirectory file)))))

(defn delete-directory
  "Recursively delete a directory."
  [^java.io.File file]
  (when (.isDirectory file)
    (doseq [file-in-dir (.listFiles file)]
      (delete-directory file-in-dir)))
  (if (or (directory-exists? file) (file-exists? file))
    (delete-file file)))

(defn make-directory [path]
  (.mkdir (java.io.File. path)))

(defn ready [{:keys [java javascript]}]
   (delete-directory (file "resources/public/js/core"))
   (make-directory "resources/public/js/core")
   (doseq [one-javascript javascript]
     (shadow/release one-javascript))
   (hf/jar java))
