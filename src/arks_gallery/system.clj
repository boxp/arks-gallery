(ns arks-gallery.system
  (:require [com.stuartsierra.component :as component]
            [environ.core :refer [env]])
  (:gen-class))

(defn arks-gallery-system
  [{:keys [arks-gallery-example-port
           arks-gallery-my-webapp-port] :as conf}]
  (component/system-map))

(defn load-config []
  {:arks-gallery-example-port (-> (or (env :arks-gallery-example-port) "8000") Integer/parseInt)
   :arks-gallery-my-webapp-port (-> (or (env :arks-gallery-my-webapp-port) "8080") Integer/parseInt)})

(defn -main []
  (component/start
    (arks-gallery-system (load-config))))
