(ns lmsreg.core-test
  (:require [clojure.test :refer :all]
            [lmsreg.core :refer :all]
            [lmsreg.ols :refer :all]
            [lmsreg.lms :refer :all]
            [clojure.core.matrix :refer  :all]))

(deftest add-one-to-x-test
  (testing "Adding ones to design matrix"
    (is [[1 1] [1 2] [1 3] [1 4] [1 5]]
        (add-ones-to-x [1 2 3 4 5]))))

(deftest ols-test
  (testing "Ordinary least squares - fit only"
    (is [0.0 2.0]
        (let
         [x1 [1 2 3 4 5 6 7 8 9 10]
          x (add-ones-to-x x1)
          y [2 4 6 8 10 12 14 16 18 20]
          betas (ols-fit x y)]
          betas))))

(deftest lms-random-test
  (testing "Least Median of Squares - Random algorithm with 500 subsets"
    (is [0.0 2.0]
        (let
         [x1 [1 2 3 4 5 6 7 8 9 100]
          x (add-ones-to-x x1)
          y [2 4 6 8 10 12 14 16 18 20]
          betas (lms-random x y 500)]
          betas))))