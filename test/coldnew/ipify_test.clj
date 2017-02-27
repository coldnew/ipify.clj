(ns coldnew.ipify-test
  (:require [clojure.test :as t]
            [coldnew.ipify :as ipify]
            [coldnew.macro :as helper]
            [clojure.pprint :refer [cl-format]]))

(t/deftest get-public-ip-test

  (t/testing "public-ip in EDN format"
    (t/is (= (ipify/get-public-ip :edn) {:ip (helper/get-public-ip)}))
    (t/is (= (ipify/get-public-ip)      {:ip (helper/get-public-ip)})))

  (t/testing "public-ip in TEXT format"
    (t/is (= (ipify/get-public-ip :text) (helper/get-public-ip))))

  (t/testing "public-ip in JSON format"
    (t/is (= (ipify/get-public-ip :json) (cl-format nil "{\"ip\":\"~a\"}" (helper/get-public-ip))))))