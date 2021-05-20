
;; -- Namespace ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(ns swordfish.server-db.handlers.default-handlers
  (:require [com.wsscode.pathom3.connect.operation :as pco :refer [defresolver defmutation]]
            [clojure.string                        :as str]
            [mid-fruits.candy                      :refer [param]]
            [mid-fruits.string                     :as string]
            [mid-fruits.vector                     :as vector]
            [x.server-db.api                       :as db]
            [x.server-environment.api              :as environment]
            [swordfish.server-auth.api           :as auth]
            [swordfish.server-db.handlers.engine :as engine]
            [swordfish.server-db.env-handler     :as env-handler]))



;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(def fruits        (atom (environment/get-local-collection :fruits        :fruit)))
(def user-accounts (atom (environment/get-local-collection :user_accounts :account)))
(def user-profiles (atom (environment/get-local-collection :user_profiles :profile)))



;; -- Mutations ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defmutation add-user-profile
  ; This resolver comes back with the user filtered by the given id
  ;
  ; @param (map) user-profile-data
  [user-profile-data]
  {::pco/op-name 'default/add-user-profile}
  (swap! user-profiles vector/conj-item user-profile-data))

(defmutation add-fruit
  ; @param (map) fruit-data
  [fruit-data]
  {::pco/op-name 'default/add-fruit}
  (swap! fruits vector/conj-item fruit-data))



;; -- Resolvers ---------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defresolver user-profile-by-id
  ; @param (map) user-profile-data
  ;  {:profile/id (string)}
  ;
  ; @usage
  ;  [{[:profile/id 1] [:profile/first-name]}]
  ;
  ; @return (map)
  [env {:keys [profile/id]}]
  {::pco/output [:profile/first-name
                 :profile/last-name
                 :profile/birthday]}
  (let [user-roles (env-handler/env->user-roles env)
        document   (db/match-document @user-profiles {:profile/id id})]
       (auth/gate-of-moria user-roles document engine/resolver-permissions)))

(defresolver birth-year
  "This resolver comes back with birth-year, which is processed from birthday"
  ; @param (map) ?
  ;
  ; @usage
  ;  [{[:profile/id 1] [:profile/first-name :profile/birth-year]}]
  ;
  ; @return (map)
  ;  {:profile/birth-year (string)}
  [{:keys [profile/birthday]}]
  {:profile/birth-year (string/before-first-occurence birthday "-")})

(defresolver full-name
  ; @param (map) ?
  ;
  ; @usage
  ;  []
  ;
  ; @return (map)
  ;  {:profile/full-name (string)}
  [{:keys [profile/first-name profile/last-name]}]
  {:profile/full-name (str first-name " " last-name)})

(defresolver all-user-profiles
  ; This resolver comes back with all the users available
  ;
  ; @param (map) env
  ; @param (map) ?
  ;
  ; @usage
  ;  [:all-users]
  ;
  ; @return (map)
  ;  {:all-user-profiles (maps in vector)}
  [env _]
  {:all-user-profiles @user-profiles})

(defresolver get-fruits
  ; This resolver has parameters for filtering
  ;
  ; @param (map) env
  ; @param (map) ?
  ;
  ; @usage
  ;  [(:fruits {:skip 0 :max-count 1 :pattern {:fruit/color "yellow" } :sort-key :id})]
  ;
  ; @return (map)
  ;  {:fruits (maps in vector)}
  [env _]
  {:fruits (-> (param @fruits)
               (db/match-documents  (env-handler/env->param env :pattern))
               (db/trim-collection (env-handler/env->param env :max-count)
                                   (env-handler/env->param env :skip))
               (db/sort-collection (env-handler/env->param env :sort-key)))})



;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(def handlers
     [add-fruit
      add-user-profile
      all-user-profiles
      birth-year
      full-name
      get-fruits
      user-profile-by-id])
