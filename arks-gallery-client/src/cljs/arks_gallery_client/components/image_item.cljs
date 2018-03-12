(ns arks-gallery-client.components.image-item
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]))

(def image-width-size
  "49vw")

(defn image-item-view
  [image]
  (let [width image-width-size
        height (str "calc(" image-width-size "*(" (:height image) "/" (:width image) "))")]
    [:li {:style {:width width
                  :height height
                  :background-color (or (:placeholder_color image) "#fff")
                  :background-image (str "url(" (:url image) ")")
                  :background-size (str width " " height)
                  :display "inline-block"
                  :margin-bottom "1vw"}}]))

(def image-item
  (with-meta image-item-view {}))
