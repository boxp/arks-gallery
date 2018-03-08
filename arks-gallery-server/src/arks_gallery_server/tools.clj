(ns arks-gallery-server.tools)

(defmacro load-schema
  [path]
  (let [schema (slurp path)]
    schema))
