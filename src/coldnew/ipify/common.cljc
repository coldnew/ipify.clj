(ns coldnew.ipify.common
  "Common data for this lib."
  (:require [clojure.pprint :refer [cl-format]]
            #?(:clj [cheshire.core :refer [parse-string]])))

;; ipify api url
(defonce api-url "https://api.ipify.org")

;; ipify api-url for retrive json data
(defonce api-url-json (cl-format nil "~a?format=json" api-url))

;; ipify api-url for retrive data via jsonp method
(defonce api-url-jsonp (cl-format nil "~a?format=jsonp" api-url))

(defn json->edn
  "Convert json data to edn format"
  [val]
  #?(:clj  (parse-string val true)
     :cljs (js->clj val)))