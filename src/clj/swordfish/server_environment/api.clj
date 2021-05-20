(ns swordfish.server-environment.api
  (:require [swordfish.server-environment.mongodb :as mongodb]))

(def add-update-document                        mongodb/add-update-document)
(def add-update-document-with-order             mongodb/add-update-document-with-order)
(def remove-document-by-id                      mongodb/remove-document-by-id)
(def remove-document-by-id-with-order           mongodb/remove-document-by-id-with-order)
(def duplicate                                  mongodb/duplicate)
(def duplicate-with-order                       mongodb/duplicate-with-order)
(def re-order                                   mongodb/re-order)

(def find-document                              mongodb/find-document)
(def find-documents                             mongodb/find-documents)
(def find-documents-with-pipeline               mongodb/find-documents-with-pipeline)
(def count-documents-with-pipeline              mongodb/count-documents-with-pipeline)
