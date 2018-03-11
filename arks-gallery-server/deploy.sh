#!/bin/sh
export DOMAIN=https://us-central1-boxp-tk.cloudfunctions.net; lein cljsbuild once prod && gcloud beta functions deploy arks_gallery_server --source . --trigger-http
