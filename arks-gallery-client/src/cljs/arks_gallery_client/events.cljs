(ns arks-gallery-client.events
  (:require-macros [arks-gallery-client.env :refer [cljs-env]])
  (:require [re-frame.core :as re-frame]
            [re-graph.core :as re-graph]
            [arks-gallery-client.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/reg-event-fx
  ::initialize-graphql
  (fn [{:keys [db] :as coeffects} _]
    (let [endpoint (or (cljs-env :graphql-endpoint) "http://localhost:9000/graphql")]
      {:dispatch [::re-graph/init {:http-url endpoint
                                   :ws-url nil}]})))

(re-frame/reg-event-fx
  ::query
  (fn [{:keys [db] :as coeffects} [_ query]]
    {:dispatch [::re-graph/query
                query
                {}
                [::on-success-query]]}))

(re-frame/reg-event-db
  ::on-success-query
  (fn [db [_ res]]
    (merge db (:data res))))
