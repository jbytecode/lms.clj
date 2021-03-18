(ns lmsreg.lms-test
  (:require [clojure.test :refer :all]
            [lmsreg.core :refer :all]
            [lmsreg.ols :refer :all]
            [lmsreg.lms :refer :all]
            [lmsreg.datasets :refer :all]
            [clojure.core.matrix :refer  :all]))

(deftest lms-random-test-500
  (testing "Least Median of Squares - Random algorithm with 500 subsets"
    (is (is-vector-approx [0.0 2.0]
                          (let
                           [x1 [1 2 3 4 5 6 7 8 9 100]
                            x (add-ones-to-x x1)
                            y [2 4 6 8 10 12 14 16 18 20]
                            betas (:betas (lms-random x y 500))]
                            betas) 0.1))))

(deftest lms-random-test-automatic-samples
  (testing "Least Median of Squares - Random algorithm with unknown number of subsets"
    (is (is-vector-approx [0.0 2.0]
                          (let
                           [x1 [1 2 3 4 5 6 7 8 9 100]
                            x (add-ones-to-x x1)
                            y [2 4 6 8 10 12 14 16 18 20]
                            betas (:betas (lms-random x y))]
                            betas) 0.1))))

(deftest lms-random-test-no-intercept
  (testing "Least Median of Squares - Single variable, no intercept"
    (is (is-scaler-approx 0.182
                          (:betas
                           (lms-random
                            (:year phones-dataset)
                            (:calls phones-dataset) 1000)) 0.1))))

