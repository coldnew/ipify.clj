(ns coldnew.ipify.impl
  "ClojureScript implement for ipify.

  For browser, we use jsonp to fetch data.
  For node.js, we use https module to fetch data."
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [coldnew.ipify.common :refer [api-url-json api-url-jsonp json->edn]]
            [cljs.core.async :as async]
            [cljs-http.client :as http])
  (:import [goog.net Jsonp]
           [goog Uri]))

;; detect current running platform is nodejs or browser
(defonce is-nodejs?
  (or (not (exists? js/window)) (= *target* "nodejs")))

;; since cljs-http can't work on nodejs, we implement our method here.
(defn nodejs-get
  [url]
  (let [https (js/require "https")
        body (atom [])
        c (async/chan)
        req (.get https url (fn [res]
                              (.setEncoding res "utf8")
                              (.on res "data" (fn [chunk]
                                                (swap! body conj chunk)))
                              (.on res "end" (fn []
                                               (async/put! c (apply str @body))
                                               ))))]
    ;; FIXME: error handler
    ;; (.on req "error" (fn [e]))
    ;; return channel
    c))

;; we use jsonp to retrive data from browser
#_(defn browser-get
    [url]
    (let [c (async/chan)]
      (go
        (let [rsp (async/<! (http/jsonp api-url-jsonp {:callback-name "callback" :timeout 3000}))]
          (async/put! c (:body rsp))))
      ;; return channel
      c))

(defn jsonp-get
  [url]
  (let [c (async/chan)
        success-handler (fn [res] (async/put! c res))
        error-handler (fn [res] (.log js/console "ERROR: jsonp-get"))
        jsonp (goog.net.Jsonp. (goog.Uri. url) "callback")]
    (.send jsonp nil success-handler error-handler)
    ;; return channel
    c))

(defn browser-get
  [url]
  (let [c (async/chan)]
    (go
      (let [rsp (async/<! (jsonp-get url))]
        (async/put! c (js/JSON.stringify rsp))))
    ;; retrun cahnnel
    c))

(defn- get-data
  "Common function to retrive data and return https result."
  []
  (if is-nodejs?
    (nodejs-get  api-url-json)
    (browser-get api-url-jsonp)))

(defn get-public-ip-json
  "Retrive public in json format"
  []
  (get-data))

(defn get-public-ip
  "Retrive public in edn format"
  []
  (let [c (async/chan)]
    (go
      (let [rsp (async/<! (get-public-ip-json))]
        (async/put! c (json->edn rsp))))
    ;; return channel
    c))
