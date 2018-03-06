(ns cljs.user
  (:require [com.stuartsierra.component :as component]
            [arks-gallery-server.system :refer [arks-gallery-server-system]]))

(def system (atom nil))

(defn init []
  (reset! system
    (arks-gallery-server-system {})))

(defn start []
  (swap! system component/start))

(defn stop []
  (swap! system
    (fn [s] (when s (component/stop s)))))

(defn go []
  (init)
  (start))

(defn reset []
  (stop)
  (go))
