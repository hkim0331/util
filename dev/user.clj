(ns user
  (:require
   [clojure.math :as m]
   [util.core :as u]
   [util.bench :as b]))

(time (u/factor-integer 203269561935987))
(<  203269561935987 (u/power 2 55))
(time (u/factor-integer (- (u/power 2 55) 1)))

(comment
  (defn count-primes [f start end]
    (count (filter f (range start end))))

  (def end (u/power 2N 55))
  (def start (- end 100))
  (time (map u/factor-integer (range start end)))

  (time (u/factor-integer 203269561935987))
  (count (u/factor-integer start))
  (b/time+ (count-primes u/prime? start end))
  (b/time+ (count-primes u/prime?-slow start end))

  (count-primes u/prime? start end)
  (count-primes u/prime?-slow start end)
  (def inc2 (comp inc inc))

  ((comp inc inc) 4)

  (inc2 4)
  (inc2 10)
  :rcf)

(comment
  (map u/prime? (range 10))
  (b/time+ (u/prime? (- (m/pow 2 31) 1)))
  (b/time+ (u/prime?-using-factor (- (m/pow 2 32) 1)))
  (btime+ (u/prime? (- (m/pow 2 32) 1)))
  (b/quick  (u/prime? (- (m/pow 2 31) 1)))
  (time (u/prime? (- (m/pow 2 32) 1)))
  (filter u/prime? (range 100))
  (u/factor-integer 24)
  (map u/factor-integer (range 10))
  (filter u/prime?-using-factor (range 100))
  (b/time+ u/prime? (- (u/pow2 31) 1))
  (b/time+ (u/factor-integer 203269561935987))
  (b/with-progress (u/factor-integer 203269561935987))
  (b/quick (u/factor-integer 203269561935987))
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

; (println "dev/user.clj loaded")
