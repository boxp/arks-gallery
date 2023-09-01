(defproject arks-gallery-server "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.11.121"]
                 [environ "1.1.0"]]

  :plugins [[lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]
            [lein-figwheel "0.5.13"]]

  :source-paths ["src"]

  :clean-targets ["index.js"
                  "target"]

  :figwheel {
    :reload-clj-files {:clj true}
  }

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src" "dev"]
              :figwheel {
                :on-jsload "cljs.user/reload"
              }
              :compiler {
                :main cljs.user
                :asset-path "target/js/compiled/dev"
                :output-to "target/js/compiled/arks_gallery_server.js"
                :output-dir "target/js/compiled/dev"
                :target :nodejs
                :npm-deps {:apollo-server-express "^1.1.3"
                           :body-parser "^1.18.2"
                           :express "^4.16.1"
                           :firebase-admin "^5.4.1"
                           :firebase-functions "^0.7.0"
                           :graphql "^0.11.7"
                           :graphql-tools "^2.2.1"}
                :install-deps true
                :optimizations :none
                :source-map-timestamp true}}
             {:id "prod"
              :source-paths ["src"]
              :compiler {
                :main arks-gallery-server.app.server
                :output-to "index.js"
                :target :nodejs
                :optimizations :simple}}]}

  :profiles {:dev {:dependencies [[figwheel-sidecar "0.5.13"]
                                  [com.cemerick/piggieback "0.2.2"]]
                   :source-paths ["src" "dev"]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
