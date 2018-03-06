(ns arks-gallery-server.system
  (:require [com.stuartsierra.component :as component]
            [cljs.nodejs :as nodejs]
            [arks-gallery-server.app.server :as server]
            [arks-gallery-server.app.schema-provider :as schema-provider]
            [arks-gallery-server.app.resolvers]))

(nodejs/enable-util-print!)

(defn arks-gallery-server-system
  [config-options]
  (component/system-map
    :app-server (component/using
                  (server/->AppServerComponent nil nil)
                  [:app-schema-provider])
    :app-schema-provider (schema-provider/->AppSchemaProviderComponent nil)))

(defn -main []
  (component/start (arks-gallery-server-system {})))

(set! *main-cli-fn* -main)
(set! (.-exports js/module) #js{:arksGalleryServer -main})
