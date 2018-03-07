(ns cljs.user
  (:require [arks-gallery-server.app.server :refer [server]]))

(defn -serve
  [& args]
  (let [port (some-> args first js/parseInt)]
    (if port
      (->> (.listen (server) port)
           .address
           .-port
           (println "listening to PORT:")
      (println "port number has no exists.")))))

(set! *main-cli-fn* -serve)
