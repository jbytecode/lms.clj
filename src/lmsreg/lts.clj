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
   [n         (count x)
    p         (if (= (matrix/dimensionality x) 1) 1 (count (first x)))
    h         (/ (+ n p 1) 2)
    residuals (matrix/sub y (matrix/mmul x betas))
    sumsqres  (->> residuals
                   (map #(Math/pow %1 2.0))
                   (sort)
                   (take h)
                   (apply +))] sumsqres))




