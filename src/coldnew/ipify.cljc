(ns coldnew.ipify
  "Clojure/Script lib for retrive public ip information from https://www.ipify.org/ ."
  (:require [coldnew.ipify.impl :as impl]))

(defn get-public-ip
  "Get public-ip from https://www.ipify.org/.

  You can add extra parameter to specify return data type:

    (get-public-ip TYPE)

  Different `TYPE` return different data format: (default: edn)

    :json  return json string like {\"ip\": \"98.207.254.136\"}
    :text  return ip in string format directly, eg: \"98.207.254.136\"
    :edn   return edn format like {:ip \"98.207.254.136\"}

  In ClojureScript, this function return a core.async channel, so you need to retrive your data in `go` loop.

    (go
      (let [resp (<! (get-public-ip))]
        (println \"ip: \" (:ip resp))))
  "
  ;; return edn format
  ([] (impl/get-public-ip))
  ;; return format according to `type`
  ([type]
   (case type
     :json (impl/get-public-ip-json)
     :text (:ip (impl/get-public-ip))
     ;; default type is edn format
     (impl/get-public-ip))))
