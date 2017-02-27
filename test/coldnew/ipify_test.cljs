(ns coldnew.ipify-test
  (:require-macros [coldnew.macro :as helper]
                   [cljs.core.async.macros :refer [go]])
  (:require [cljs.test :as t :include-macros true]
            [coldnew.ipify :as ipify]
            [clojure.pprint :refer [cl-format]]
            [cljs.core.async :as async]))

(t/deftest ^:async get-public-ip-test

  (t/testing "public-ip in EDN format"
    (t/async done
             (go
               (let [rsp (async/<! (ipify/get-public-ip :edn))]
                 (t/is (= rsp {:ip (helper/get-public-ip)})))

               (let [rsp (async/<! (ipify/get-public-ip))]
                 (t/is (= rsp  {:ip (helper/get-public-ip)})))

               (done))))

  (t/testing "public-ip in TEXT format"
    (t/async done
             (go
               (let [rsp (async/<! (ipify/get-public-ip :text))]
                 (t/is (= rsp (helper/get-public-ip))))
               (done))))

  (t/testing "public-ip in JSON format"
    (t/async done
             (go
               (let [rsp (async/<! (ipify/get-public-ip :json))]
                 (t/is (= rsp (cl-format nil "{\"ip\":\"~a\"}" (helper/get-public-ip)))))
               (done))))
  )
