(ns lmsreg.stats-test
  (:require [clojure.test :refer :all]
            [lmsreg.stats :refer :all]))

(deftest mean-test
  (testing "Arithmetic mean"
    (is 10
        (mean [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20]))))

(deftest vars-test
  (testing "Variance"
    (is 0
        (vars [1 1 1 1 1 1]))))

(deftest sd-test
  (testing "Standard deviation"
    (is 0
        (vars [1 1 1 1 1 1]))))

(deftest median-test
  (testing "Median"
    (is 3
        (median [50 4 3 2 1]))
    (is 3.5
        (median [1 2 3 4 50 60]))))

(deftest quantile-test
  (testing "Quantile"
    (let [x [1 2 3 4 5 6 7 8 9 10]]
      (is 1
          (quantile x 0.0))
      (is 10
          (quantile x 1.0))
      (is (median x)
          (quantile x 1.0))
      (is 2.5
          (quantile x 0.25)))))


