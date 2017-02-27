# ipify-clj
[![CircleCI](https://circleci.com/gh/coldnew/ipify-clj.svg?style=svg)](https://circleci.com/gh/coldnew/ipify-clj)
[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/coldnew/ipify-clj/master/LICENSE)

An **unofficial** client library for [https://www.ipify.org](https://www.ipify.org): A Simple IP Address API.

[![Clojars Project](http://clojars.org/coldnew/ipify/latest-version.svg)](http://clojars.org/coldnew/ipify)

## Supported Platforms

This library is designed for **both** Clojure/ClojureSript, you need following minimal version:

* Clojure 1.8.0 ↑
* ClojureScript 1.9.473 ↑

## Usage (Clojure)

In Clojure, we use synchronize method to retrive data from `https://api.ipify.org`. Just call `(get-public-ip)` and it'll return your public ip in edn format.

```clojure
(ns ipify-test.core
  (:require [coldnew.ipify :as ipify]))

(defn -main []
  ;; default return edn data
  (println "My public ip (default):" (ipify/get-public-ip))       ; => {:ip "98.207.254.136"}
  ;; You can specify the return type you want
  (println "My public ip (text):" (ipify/get-public-ip :text)) ; => "98.207.254.136"
  (println "My public ip (json):" (ipify/get-public-ip :json)) ; => {\"ip\": \"98.207.254.136\"}
  (println "My public ip (edn):"  (ipify/get-public-ip :edn))  ; => {:ip "98.207.254.136"}
  ) 
```

## Usage (ClojureScript on Browser/Node.js)

Use on ClojureScript under node.js is the same as browser, this library will use nodejs's [https module](https://nodejs.org/api/https.html) to fetch the data instead of jsonp.

FIXME: TODO


## License

Copyright © 2017 Yen-Chin, Lee <<coldnew.tw@gmail.com>>

Distributed under the [MIT License](http://opensource.org/licenses/MIT).
