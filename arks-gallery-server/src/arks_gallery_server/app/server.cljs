(ns arks-gallery-server.app.server
  (:require [arks-gallery-server.app.schema-provider :refer [executable-schema]]
            [firebase-functions :refer [https]]
            [body-parser :as body-parser]
            [express :as express]
            [apollo-server-express :refer [graphqlExpress graphiqlExpress]]
            ["graphql/utilities/schemaPrinter" :refer [printSchema]]))

(defn server []
  (doto (express)
    (.use "/graphql"
          (body-parser/json)
          (graphqlExpress #js{"schema" executable-schema
                              "context" #js{}}))
    (.use "/graphiql"
          (graphiqlExpress #js{"endpointURL" "/api/graphql"}))
    (.use "/schema"
          (fn [req res]
            (.set res "Content-Type" "text/plain")
            (.send res (printSchema executable-schema))))))

(set! (.-exports js/module) #js{:arks_gallery_server (server)})
