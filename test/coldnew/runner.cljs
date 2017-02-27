(ns coldnew.runner
  (:require [cljs.test :as test]
            [doo.runner :refer-macros [doo-all-tests doo-tests]]
            [coldnew.ipify-test]))

(doo-tests 'coldnew.ipify-test)
