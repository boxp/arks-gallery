(ns arks-gallery-client.components.top
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [venia.core :as v]
            [arks-gallery-client.components.image-grid :refer [image-grid]]
            [arks-gallery-client.events :as events]
            [arks-gallery-client.subs :as subs]))

(def top-query
  (v/graphql-query {:venia/queries
                    [[:images [:id
                               :url
                               :width
                               :height
                               :placeholder_color
                               [:owner [:name
                                        [:ship [:num]]
                                        :profile_img_url]]]]]}))

(defn top-view []
  (let [images (re-frame/subscribe [::subs/images])]
    [:div#top
     [image-grid @images]]))

(def top
  (with-meta top-view
             {:component-did-mount #(re-frame/dispatch-sync [::events/query top-query])}))
