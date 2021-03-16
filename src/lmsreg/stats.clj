(ns lmsreg.stats)

(defn sum "Sums all elements of numeric vector x"
  [x]
  (apply + x))

(defn mean
  "Returns arithmetic mean of numeric vector x"
  [x]
  (/ (sum x) (count x)))

(defn vars "Returns sample variance of numeric vector x"
  [x]
  (let
   [xbar        (mean x)
    n           (count x)
    sq          (map #(Math/pow (- %1 xbar) 2) x)
    sumsq       (sum sq)
    denom       (/ 1 (- n 1))
    vr          (* sumsq denom)] vr))

(defn sd "Returns standard deviation of numeric vector x"
  [x]
  (Math/sqrt (vars x)))

(defn median "Return median of numeric vector x"
  [x]
  (let [sorted   (vec (sort x))
        len      (count x)
        index1   (int (Math/floor (/ len 2)))
        index2   (dec (int (Math/ceil (/ len 2))))
        med      (if (odd? len) (sorted index1) (mean [(sorted index1) (sorted index2)]))]
    med))

(defn quantile "Sample quantile"
  [x p]
  (let [n       (count x)
        h       (* (- n 1) p)
        hdown   (int (Math/floor h))
        hup     (int (Math/ceil h))
        sortedx (vec (sort x))
        xup     (get sortedx hup)
        xdown   (get sortedx hdown)
        result  (+ xdown (* (- h hdown) (- xup xdown)))] result))
