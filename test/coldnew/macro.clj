(ns coldnew.macro
  "Helper macro for testing coldnew.ipify."
  (:require [coldnew.ipify.common :refer [api-url]])
  (:import [java.net URL]
           [java.util Scanner]))

(defmacro get-public-ip
  "Retrive public ip from https://www.ipify.org/. This is modified on ipify.org's java example."
  []
  (let [url (java.net.URL. api-url)
        scan  (.useDelimiter (java.util.Scanner. (.openStream url) "UTF-8") "\\A")]
    (.next scan)))
