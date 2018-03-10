(ns arks-gallery-client.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [arks-gallery-client.events :as events]
            [arks-gallery-client.routes :as routes]
            [arks-gallery-client.views :as views]
            [arks-gallery-client.components.top :refer [top]]
            [arks-gallery-client.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [top]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (re-frame/dispatch-sync [::events/initialize-graphql])
  (dev-setup)
  (mount-root))
