(defproject arks-gallery "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.async "0.2.395"]
                 [environ "1.1.0"]
                 [com.stuartsierra/component "0.3.2"]
                 [com.walmartlabs/lacinia "0.25.0-rc-2"]
                 [org.clojure/tools.namespace "0.2.10"]]
  :profiles
  {:dev {:source-paths ["src" "dev"]}
   :uberjar {:main arks-gallery.system}})
