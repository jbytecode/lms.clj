(ns lmsreg.core
  (:require [clojure.core.matrix :refer :all]
            [lmsreg.datasets :refer :all]
            [lmsreg.stats :refer :all]
            [lmsreg.ols :refer :all]
            [lmsreg.lms :refer :all])
  (:gen-class))

(defn is-vector-approx [x y eps]
  (< (apply + (map #(Math/pow %1 2) (sub x y))) eps))

(defn is-scaler-approx [x y eps]
  (< (Math/pow (- x y) 2) eps))

(defn -main [& args]
  (let
   [x (add-ones-to-x (:year phones-dataset))
    y (:calls phones-dataset)
    lms (lms-random x y 1000)]
    (println lms)))