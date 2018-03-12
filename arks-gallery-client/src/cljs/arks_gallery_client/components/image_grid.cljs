(ns arks-gallery-client.components.image-grid
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [arks-gallery-client.components.image-item :refer [image-item]]))

(defn image-grid-view
  [images]
  [:ul.image-grid {:style {:column-count 2
                           :column-gap "10px"
                           :column-fill "auto"
                           :-webkit-column-count 2
                           :-webkit-column-gap "1vw"}}
   (map (fn [image] ^{:key image} [image-item image]) images)])

(def image-grid
  (with-meta image-grid-view {}))
