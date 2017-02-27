(ns coldnew.ipify.impl
  "Clojure implement for ipify."
  (:require [coldnew.ipify.common :refer [api-url-json json->edn]]
            [clj-http.client :as http]))

(defn- get-data
  "Common function to retrive data and return http result."
  []
  (http/get api-url-json {:accept :json}))

(defn get-public-ip-json
  "Retrive public in json format"
  []
  (-> (get-data) :body))

(defn get-public-ip
  "Retrive public in edn format"
  []
  (json->edn (get-public-ip-json)))
