
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.server-auth.permission-handler
    (:require [mid-fruits.candy   :refer [param return]]
              [mid-fruits.keyword :as keyword]
              [mid-fruits.map     :as map]
              [mid-fruits.vector  :as vector]
              [x.server-db.api    :as db]))



;; -- Names -------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @name user-roles
;  ["admin" "user-id" ...]
;
; @name document
;  {:account/id 1 :account/permissions {"ro" ["admin" "user-id"]}}
;
; @name document-permissions
;  {"ro" ["admin" "user-id"]}
;
; @name function-permissions
;  ["ro" "rw"]



;; -- Converters --------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn document->document-permissions
  ; @param (map) document
  ;
  ; @example
  ;  (auth/document->permissions {:permissions {...}})
  ;  => {...}
  ;
  ; @return (map)
  [document]
  (let [namespace (db/document->namespace document)]
       (get document (keyword/add-namespace namespace "permissions"))))



;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn contains-user-role?
  "Decides if role is present on user"
  ; @param (vector) user-roles
  ; @param (string) user-role
  ;
  ; @usage
  ;  (auth/contains-user-role? ["admin" "user-id"] "admin")
  ;
  ; @return (boolean)
  [user-roles user-role]
  (vector/contains-item? user-roles user-role))

(defn contains-permission?
  "Decides if the owned permission is sufficient to handle the document"
  ; @param (vector) function-permissions
  ; @param (string) permission-owned
  ;
  ; @usage
  ;  (auth/contains-permission? ["ro" "rw"] "ro")
  ;
  ; @return (boolean)
  [function-permissions permission-owned]
  (vector/contains-item? function-permissions permission-owned))

(defn permission-granted?
  "Decides if the document can be handled"
  ; @param (vector) actual-user-roles
  ; @param (map) document-permissions
  ; @param (vector) function-permissions
  ;
  ; @usage
  ;  (auth/permission-granted? ["admin" "user-id"]
  ;                            {"admin" "ro" "user-id" "rw"}
  ;                            ["ro" "rw"])
  ;
  ; @return (boolean)
  [actual-user-roles document-permissions function-permissions]
  (boolean (some (fn [[user-role user-permission]]
                     (if (contains-permission? function-permissions user-permission)
                         (contains-user-role?  actual-user-roles user-role)))
                 (param document-permissions))))

(defn gate-of-moria
  ; @param (vector) user-roles
  ; @param (map) document
  ; @param (vector) function-permissions
  ;
  ; @return (map or string)
  [user-roles document function-permissions]
  (if (permission-granted? (param user-roles)
                           (document->document-permissions document)
                           (param function-permissions))
      (return document)
      (return "Speak friend, and enter")))
