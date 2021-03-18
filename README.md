# lmsreg

Initially implements random Least Median of Squares regression estimator.

## Installation

Add the dependency 

```clojure
[lmsreg.core "0.1.0"]
```

in your project.clj file.


## Examples

```clojure
lmsreg.core=> phones-dataset 
{
    :year [50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73], 
    :calls [4.4 4.7 4.7 5.9 6.6 7.3 8.1 8.8 10.6 12.0 13.5 14.9 16.1 21.2 119.0 124.0 
    142.0 159.0 182.0 212.0 43.0 24.0 27.0 29.0]}

(lms-random 
    (add-ones-to-x (:year phones-dataset))
    (:calls phones-dataset) 
    3000)

{
    :betas [-57.1561224489798 1.1719387755102029], 
    :indices (6 12 22), 
    :objective 0.8899419512708223}
```

