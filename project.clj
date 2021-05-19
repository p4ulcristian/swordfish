(defproject swordfish "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[clj-commons/cljss "1.6.4"]
                 [com.google.javascript/closure-compiler-unshaded "v20190325"]
                 [com.google.javascript/closure-compiler-unshaded "v20200830"]
                 [herb "0.10.0"]
                 [hiccup "1.0.5"]
                 [kwladyka/form-validator-cljs "1.2.1"]
                 [metosin/jsonista "0.2.6"]
                 [metosin/reitit "0.5.1"]
                 [org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.773" :scope "provided"]
                 [org.clojure/google-closure-library "0.0-20190213-2033d5d9"]
                 [pez/clerk "1.0.0"]
                 [reagent "1.0.0"]
                 [reagent-utils "0.3.3"]
                 [ring "1.8.1"]
                 [ring-server "0.5.0"]
                 [ring/ring-defaults "0.3.2"]
                 [thheller/shadow-cljs "2.11.17"]
                 [venantius/accountant "0.2.5" :exclusions [org.clojure/tools.reader]]
                 [yogthos/config "1.1.7"]]
  :plugins [[lein-environ "1.1.0"]
            [lein-shadow "0.1.7"]
            [lein-asset-minifier "0.4.6"
             :exclusions [org.clojure/clojure]]]
  :ring {:handler swordfish.handler/app
         :uberwar-name "swordfish.war"}
  :min-lein-version "2.5.0"
  :uberjar-name "swordfish.jar"
  :main swordfish.server
  :clean-targets ^{:protect false}
  [:target-path
   [:cljsbuild :builds :app :compiler :output-dir]
   [:cljsbuild :builds :app :compiler :output-to]]
  :source-paths ["src/clj" "src/cljc" "src/cljs"]
  :resource-paths ["resources" "target/cljsbuild"]
  :minify-assets [[:css {:source "resources/public/css/site.css"
                         :target "resources/public/css/site.min.css"}]
                  [:css {:source "resources/public/css/normalize.css"
                         :target "resources/public/css/normalize.min.css"}]]
  :shadow-cljs {:builds
                {:app {:target :browser
                       :modules {:app {:init-fn swordfish.core/init!}}
                       :output-dir       "resources/public/js"
                       :devtools {:watch-dir "resources/public"
                                  :after-load swordfish.core/mount-root}}
                 :app-ready {:target :browser
                             :compiler-options {:infer-externs :auto
                                                :optimizations :advanced
                                                :pseudo-names true}
                             :modules {:app {:init-fn swordfish.core/init!}}
                             :output-dir       "resources/public/js"}}}
  :profiles {:uberjar {:hooks [minify-assets.plugin/hooks]
                       :source-paths ["env/prod/clj"]
                       :prep-tasks ["clean"
                                    "compile"
                                    ["shadow" "compile" "app-ready"]]
                       :env {:production true}
                       :aot :all
                       :omit-source true}})
