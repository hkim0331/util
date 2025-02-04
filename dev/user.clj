(ns user
  (:require
   [util.core :as u]
   [util.bench :as b]
   [criterium.core :refer [with-progress-reporting quick-bench]]))

(defmacro quick [expr]
  `(with-progress-reporting
     (quick-bench ~expr))) ; :verbose

(comment
  (time (u/factor-integer 203269561935987))
  (quick (u/factor-integer 203269561935987))
  (b/time+ (u/factor-integer 203269561935987))
  (u/tarai 10 5 5)
  (b/heap)
  :rcf)
