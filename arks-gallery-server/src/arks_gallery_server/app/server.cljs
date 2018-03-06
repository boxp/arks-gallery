(ns arks-gallery-server.app.server
  (:require [com.stuartsierra.component :as component]
            [firebase-functions :refer [https]]
            [body-parser :as body-parser]
            [express :as express]
            [apollo-server-express :refer [graphqlExpress graphiqlExpress]]
            ["graphql/utilities/schemaPrinter" :refer [printSchema]]))

(defn server
  [c]
  (doto (express)
    (.use "/graphql"
          (body-parser/json)
          (graphqlExpress #js{"schema" (-> c :app-schema-provider :excutable-schema)
                              "context" #js{}}))
    (.use "graphiql"
          (graphiqlExpress #js{"endpointURL" "api/graphql"}))
    (.use "/schema"
          (fn [req res]
            (.set res "Content-Type" "text/plain")
            (.send res (printSchema (-> c :app-schema-provider :excutable-schema)))))))


(defrecord AppServerComponent [api app-schema-provider]
  component/Lifecycle
  (start [this]
    (println "hello!")
    (-> this
        (assoc :api (server this))))
  (stop [this]
    (-> this
        (dissoc :api))))
