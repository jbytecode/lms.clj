(ns lmsreg.ols
  (:require [clojure.core.matrix :refer :all]))

(defn add-ones-to-x [x]
  (let
   [n (count x)
    p (if (= (dimensionality x) 1) 1 (count (first x)))
    newp (inc p)
    ones (take n (repeat 1))
    f (flatten (map #(conj [%1 %2])  ones x))
    mat (reshape f (list n newp))] mat))

(defn ols-fit [x y]
  (let
   [xt (transpose x)
    betas (mmul (mmul (inverse (mmul xt x)) xt) y)] betas))

(defn ols [x y]
  (let
   [xt (transpose x)
    betas (mmul (mmul (inverse (mmul xt x)) xt) y)
    fitteds (mmul x betas)
    residuals (sub y fitteds)
    squared-residuals (map #(Math/pow %1 2) residuals)]
    {:betas betas
     :fitteds fitteds
     :residuals residuals
     :squared-residuals squared-residuals}))

