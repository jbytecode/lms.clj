(ns lmsreg.lms
  (:require
   [clojure.core.matrix :refer :all]
   [lmsreg.ols :refer :all]))

(defn lms-objective
  "
    Calculates LMS objective function for a given design matrix x,
    response vector y, and regression parameters betas. 
    "
  [x y betas]
  (let
   [n  (count x)
    p (count (first x))
    h  (/ (+ n p 1) 2)
    residuals (sub y (mmul x betas))
    sqresiduals (sort (map #(Math/pow %1 2) residuals))] (nth sqresiduals (dec h))))

(defn lms-random-subset-of-k [x y k]
  (let
   [n (count y)
    indices (take k (distinct (repeatedly #(rand-int n))))
    sub-x (map #(nth x %1) indices)
    sub-y (map #(nth y %1) indices)
    betas (ols-fit sub-x sub-y)
    objective (lms-objective x y betas)] {:betas betas :indices indices :objective objective}))

(defn lms-random [x y nsamples]
  (let [n (count y)
        p (count (first x))
        p1 (inc p)
        samples (take nsamples (repeatedly #(future (lms-random-subset-of-k x y (max p1 (rand-int n))))))
        best  (first (sort-by #(:objective (deref %1)) samples))] @best))
