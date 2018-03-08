(ns cljs.user
  (:require [arks-gallery-server.app.server :refer [server]]
            [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(defonce p (atom 9000))
(defonce s (atom nil))

(defn -serve
  [port]
  (let [server (if port
                 (.listen (server) port)
                 (.listen (server) @p))]
    (->> server .address .-port (println "listening to PORT:"))
    server))

(defn start
  [port]
  (when-not @s
    (when port (reset! p port))
    (reset! s (-serve port))))

(defn stop []
  (when @s
    (-> @s .close)
    (reset! s nil)))

(defn reload []
  (stop)
  (start @p))

(defn -main
  [& args]
  (let [port (some-> args first js/parseInt)]
    (start port)))

(set! *main-cli-fn* -main)
