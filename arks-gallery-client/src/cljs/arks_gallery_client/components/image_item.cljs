(ns arks-gallery-client.components.image-item
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]))

(def image-base-size
  "36vw")

(defn image-item-view
  [image]
  (let [width (:width image)
        height (:height image)]
    [:li {:style {:list-style-type "none"
                  :width (str "calc(" image-base-size "*2*" (* 1.0 (/ width (+ width height))) ")")
                  :height (str "calc(" image-base-size "*2*" (* 1.0 (/ height (+ width height))) ")")
                  :background-color (or (:placeholder_color image) "#fff")
                  :background-image (str "url(" (:url image) ")")
                  :background-size (str width "px " height "px")}}]))

(def image-item
  (with-meta image-item-view {}))
