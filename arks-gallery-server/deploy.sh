#!/bin/sh
lein cljsbuild once prod && gcloud beta functions deploy arks_gallery_server --source . --trigger-http
