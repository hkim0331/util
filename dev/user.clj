(ns user
  (:require
   [util.core :as u]
   [util.bench :as b]
   [criterium.core :refer [with-progress-reporting quick-bench]]))

#_(defmacro quick [expr]
    `(with-progress-reporting
       (quick-bench ~expr :verbose)))

#_(defmacro quick [expr]
    `(with-progress-reporting
       (quick-bench ~expr))) ; without :verbose

(defmacro quick [expr]
  `(quick-bench ~expr)) ; without progress reporting

(comment
  (time (u/factor-integer 203269561935987))
  (quick (u/factor-integer 203269561935987))
  (b/time+ (u/factor-integer 203269561935987))
  (u/tarai 10 5 5)

  (b/time+ (u/tarai      15 5 0))
  ;=> Time per call: 15.18 s   Alloc per call: 112b   Iterations: 1

  (b/time+ (u/tarai-memo 15 5 0))
  ;=> Time per call: 66.29 us   Alloc per call: 117,384b   Iterations: 30226

  (b/time+ (u/tarai-lazy 15 5 0))
  ;=> Time per call: 18.95 us   Alloc per call: 15,208b   Iterations: 105601

  (b/heap)
  :rcf)

(println "dev/user.clj loaded")

