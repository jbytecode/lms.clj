(ns lmsreg.lts
  (:require
   [clojure.core.matrix :as matrix]
   [lmsreg.ols :as ols]))

(defn lts-objective
  "
    Calculates LTS (Least Trimmed Squares) objective function 
    for a given design matrix x, response vector y, and 
   regression parameters betas. 
    "
  [x y betas]
  (let
   [n         (count y)
    p         (if (= (matrix/dimensionality x) 1) 1 (count (first x)))
    h         (/ (+ n p 1) 2)
    residuals (matrix/sub y (matrix/mmul x betas))
    sumsqres  (->> residuals
                   (map #(Math/pow %1 2.0))
                   (sort)
                   (take h)
                   (apply +))] sumsqres))


(defn c-step [x y indices]
  (let
   [n             (count y)
    p             (if (= (matrix/dimensionality x) 1) 1 (count (first x)))
    h             (/ (+ n p 1) 2)
    xsub          (vec (map #(nth x %1) indices))
    ysub          (vec (map #(nth y %1) indices))
    betas         (ols/ols-fit xsub ysub)
    residuals     (matrix/sub y (matrix/mmul x betas))
    sqres         (map-indexed #(vec [%1 (Math/pow %2 2.0)]) residuals)
    ordered-couples  (sort-by second sqres)
    best-indices (->> ordered-couples
                      (take h)
                      (map first))] best-indices))

(def c-step-memoized (memoize c-step))

(defn iterate-csteps [x y initial-indices steps]
  (->>
   (iterate #(c-step-memoized x y %1) initial-indices)
   (take steps)
   (last)
   (sort)))

(defn objective-for-indices [x y indices]
  (let
   [n             (count y)
    p             (if (= (matrix/dimensionality x) 1) 1 (count (first x)))
    h             (/ (+ n p 1) 2)
    xsub          (vec (map #(nth x %1) indices))
    ysub          (vec (map #(nth y %1) indices))
    betas         (ols/ols-fit xsub ysub)
    residuals     (matrix/sub y (matrix/mmul x betas))
    sqres         (map #(Math/pow %1 2.0) residuals)
    sumsqres      (apply + (take h (sort sqres)))]
    sumsqres))

;(use '[lmsreg.datasets :as datasets])
;(def x (ols/add-ones-to-x (:year datasets/phones-dataset)))
;(def y (:calls datasets/phones-dataset))
;(objective-for-indices x y [0 1 2 3 4 5 6 7 8 9 10 11 12 13])