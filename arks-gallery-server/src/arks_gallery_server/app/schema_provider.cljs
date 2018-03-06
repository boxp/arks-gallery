(ns arks-gallery-server.app.schema-provider
  (:require [com.stuartsierra.component :as component]
            [graphql-tools :refer [makeExcutableSchema]]
            [arks-gallery-server.app.resolvers :refer [resolve-functions]]))

(def schema "
type Author {
  id: Int! # the ! means that every author object _must_ have an id
  firstName: String
  lastName: String
  posts: [Post] # the list of Posts by this author
}
type Post {
  id: Int!
  title: String
  author: Author
  votes: Int
}
# the schema allows the following query:
type Query {
  posts: [Post]
  author(id: Int!): Author
}
# this schema allows the following mutation:
type Mutation {
  upvotePost (
    postId: Int!
  ): Post
}
")

(defrecord AppSchemaProviderComponent [excutable-schema]
  component/Lifecycle
  (start [this]
    ;; this)
    (try
      (-> this
          (assoc :excutable-schema (makeExcutableSchema #js{"typeDefs" schema
                                                            "resolvers" resolve-functions})))
      (catch Exception e (do (println e) this))))
  (stop [this]
    (-> this
        (dissoc :excutable-schema))))
