(ns lmsreg.ols-test
  (:require [clojure.test :refer :all]
            [lmsreg.core :refer :all]
            [lmsreg.ols :refer :all]
            [lmsreg.lms :refer :all]
            [lmsreg.datasets :refer :all]
            [clojure.core.matrix :refer  :all]))

;; Update this test
;; (is (= ....))
(deftest add-one-to-x-test
  (testing "Adding ones to design matrix"
    (is (= [1 1] (first (add-ones-to-x [1 2 3 4 5]))))
    (is (= [1 2] (second (add-ones-to-x [1 2 3 4 5]))))))

(deftest ols-fit-test
  (testing "Ordinary least squares - fit only"
    (is (is-vector-approx [0.0 2.0]
                          (let
                           [x1 [1 2 3 4 5 6 7 8 9 10]
                            x (add-ones-to-x x1)
                            y [2 4 6 8 10 12 14 16 18 20]
                            betas (ols-fit x y)]
                            betas) 0.01))))

(deftest ols-test
  (testing "Ordinary least squares - Fitted response"
    (is (is-vector-approx [2 4 6 8 10 12 14 16 18 20]
                          (let
                           [x1 [1 2 3 4 5 6 7 8 9 10]
                            x (add-ones-to-x x1)
                            y [2 4 6 8 10 12 14 16 18 20]
                            reg (ols x y)
                            response (:fitteds reg)] response) 0.1))))
