(ns lmsreg.datasets
  (:require [clojure.core.matrix :refer :all]))

(def toy-dataset-1
  {:x  (matrix [[1 1]
                [1 2]
                [1 3]
                [1 4]
                [1 5]
                [1 60]])
   :y (matrix [2 4 6 8 10 12])})

(def phones-dataset
  {:year (matrix [50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73])

   :calls (matrix [4.4   4.7   4.7   5.9   6.6   7.3   8.1   8.8  10.6  12.0  13.5  14.9
                   16.1  21.2 119.0 124.0 142.0 159.0 182.0 212.0  43.0  24.0  27.0  29.0])})

