(ns lmsreg.lts-test
  (:require [clojure.test :refer :all]
            [lmsreg.ols :as ols]
            [lmsreg.datasets :as datasets]
            [lmsreg.lts :as lts]))

(deftest lts-objective
  (testing "LTS objective function"
    (is (= (lts/lts-objective
            (ols/add-ones-to-x (:year datasets/phones-dataset))
            (:calls datasets/phones-dataset)
            [-56 1.16])  6.51679999999998))))