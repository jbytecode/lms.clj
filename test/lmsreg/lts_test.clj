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

(deftest lts-single-c-step
  (testing "Single Concentration step for LTS"
    (is (=
         (-> (lts/c-step
              [[1 1] [1 2] [1 3] [1 4] [1 5] [1 6]]
              [2 4 6 8 11 120]
              [3 4])
             (sort))

         (list 0 1 2 3 4)))

    (is (=
         (-> (lts/c-step
              [[1 1] [1 2] [1 3] [1 4] [1 5] [1 6]]
              [2 4 6 8 11 120]
              [2 1])
             (sort))

         (list 0 1 2 3 4)))))


(deftest iterate-csteps
  (testing "Iterate c-steps for a given subset"
    (is (=
         (let
          [x (ols/add-ones-to-x (:year datasets/phones-dataset))
           y (:calls datasets/phones-dataset)] (lts/iterate-csteps x y [0 1 2 3 4] 500))

         (list 0 1 2 3 4 5 6 7 8 9 10 11 12 21)))))

(deftest calculate-objective-for-indices
  (testing "Calculate objective for indices"
    (is (=
         (let
          [x (ols/add-ones-to-x (:year datasets/phones-dataset))
           y (:calls datasets/phones-dataset)]
           (lts/objective-for-indices x y [0 1 2 3 4 5 6 7 8 9 10 11 12 13]))
         9.428227895179448))))