(ns lmsreg.stats-test
  (:require [clojure.test :refer :all]
            [lmsreg.stats :as stats]))

(deftest mean-test
  (testing "Arithmetic mean"
    (is (= 21/2
           (stats/mean [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20])))))

(deftest vars-test
  (testing "Variance"
    (is (= 0.0
           (stats/vars [1 1 1 1 1 1])))))

(deftest sd-test
  (testing "Standard deviation"
    (is (= 0.0
           (stats/vars [1 1 1 1 1 1])))))

(deftest median-test
  (testing "Median"
    (is (= 3
           (stats/median [50 4 3 2 1])))
    (is (= 7/2
           (stats/median [1 2 3 4 50 60])))))

(deftest quantile-test
  (testing "Quantile"
    (let [x [1 2 3 4 5 6 7 8 9 10]]
      (is (= 1.0
             (stats/quantile x 0.0)))
      (is (= 10.0
             (stats/quantile x 1.0)))
      (is (= (double (stats/median x))
             (stats/quantile x 0.5)))
      (is (= 3.25
             (stats/quantile x 0.25))))))


