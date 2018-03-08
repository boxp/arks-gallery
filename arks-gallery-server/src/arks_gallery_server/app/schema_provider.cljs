(ns arks-gallery-server.app.schema-provider
  (:require-macros [arks-gallery-server.tools :refer [load-schema]])
  (:require [graphql-tools :refer [makeExecutableSchema]]
            [arks-gallery-server.app.resolvers :refer [resolve-functions]]))

(def schema (load-schema "../resources/arks-gallery-schema.graphql"))

(def executable-schema
  (makeExecutableSchema #js{"typeDefs" schema
                            "resolvers" resolve-functions}))
