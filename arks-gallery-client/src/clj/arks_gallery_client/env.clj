(ns arks-gallery-client.env
  (:require [environ.core :refer [env]]))

(defmacro cljs-env [kw]
  (let [e (env kw)]
    (when-not (= "" e)
      e)))
