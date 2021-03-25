(ns lmsreg.csv-test
  (:require [clojure.test :refer :all]
            [lmsreg.csv :as csv]))

(deftest read-csv-from-string-test
  (testing "read-csv-string"
    (let
     [csv-text "COL1;COL2;COL3
CELL11;CELL12;CELL13
CELL21;Cell22;Cell23
Cell31;Cell32;Cell33
"
      csv (csv/read-csv-string csv-text #";")
      first-row (first csv)
      second-row (second csv)
      third-row (last (take 3 csv))
      is1 (is (= (:COL1 first-row) "CELL11"))
      is2 (is (= (:COL2 first-row) "CELL12"))
      is3 (is (= (:COL3 first-row) "CELL13"))
      is4 (is (= (:COL1 second-row) "CELL21"))
      is5 (is (= (:COL2 second-row) "Cell22"))
      is6 (is (= (:COL3 second-row) "Cell23"))
      is7 (is (= (:COL1 third-row) "Cell31"))
      is8 (is (= (:COL2 third-row) "Cell32"))
      is9 (is (= (:COL3 third-row) "Cell33"))])))