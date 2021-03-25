(ns lmsreg.csv
  (:require [clojure.string :as str])
  (:gen-class))

(defn read-csv-string [csv-string sep]
  (let [rows (->
              csv-string
              (str/split #"\n"))
        headers (str/split (first rows) sep)
        headers-clean (map #(str/replace %1 #"\s" "_") headers)
        kws (map keyword headers-clean)
        data (map #(str/split %1 sep) (rest rows))
        mapped-data (map #(zipmap kws %1) data)] mapped-data))

(defn read-csv-file [filename sep]
  (->
   filename
   (slurp)
   (read-csv-string sep)))
