(ns lmsreg.core
  (:gen-class))

(use 'clojure.core.matrix)
(use 'lmsreg.datasets)
(use 'lmsreg.stats)
(use 'lmsreg.ols)
(use 'lmsreg.lms)

(defn -main [& args]
  (let
   [x (add-ones-to-x (:year phones-dataset))
    y (:calls phones-dataset)
    lms (lms-random x y 1000)]
    (println lms)))