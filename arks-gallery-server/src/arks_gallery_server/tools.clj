(ns arks-gallery-server.tools
  (:require [environ.core :refer [env]]))

(defmacro load-schema
  [path]
  (let [schema (slurp path)]
    schema))

(defmacro cljs-env [kw]
  (let [e (env kw)]
    (when-not (= "" e)
      e)))
