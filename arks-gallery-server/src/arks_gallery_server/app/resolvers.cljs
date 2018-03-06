(ns arks-gallery-server.app.resolvers
  (:require [com.stuartsierra.component :as component]))

(def authors [{:id 1 :firstName "Tom" :lastName "Coleman"}
              {:id 2 :firstName "Sashiko" :lastName "Stubailo"}])

(def posts [{:id 1 :authorId 1 :title "Introdution to GraphQL" :votes 2}
            {:id 2 :authorId 2 :title "GraphQL Rocks" :votes 3}
            {:id 3 :authorId 2 :title "Advanced GraphQL" :votes 1}])

(def resolve-functions
  (clj->js
    {:Query
     {:posts (fn [_ _] posts)
      :author (fn [_ r]
                (->> authors
                     (filter #(= (.-id %) (.-id r)))
                     first))}
     :Mutation
     {:upvotePost (fn [_ r]
                    (->> posts
                         (filter #(= (.-id %) (.-postId r)))
                         first))}
     :Author
     {:posts (fn [author]
               (->> posts
                    (filter #(= (.-id %) (.-id author)))
                    first))}
     :Post
     {:author (fn [post]
                (->> authors
                     (filter #(= (.-id %) (.-authorId post)))
                     first))}}))
