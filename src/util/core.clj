(ns util.core
  (:require [clojure.math :as math]))

; power
; (pow a b) returns double.
; (power n m) returns int.

(defn sq [x] (* x x))

(defn power [base n]
  (cond
    (zero? n) 1
    (even? n) (sq (power base (quot n 2)))
    :else (* base (power base (dec n)))))

; factor integer
(defn- fi-aux [n d ret]
  (cond
    (< n (* d d)) (if (= n 1)
                    ret
                    (conj ret n))
    (zero? (rem n d)) (recur (quot n d) d (conj ret d))
    :else (recur n (inc (inc d)) ret)))

(defn- div-2 [n ret]
  (if (zero? (rem n 2))
    (recur (quot n 2) (conj ret 2))
    [n ret]))

(defn factor-integer [n]
  (if (< n 2)
    [n]
    (let [[n ret] (div-2 n [])]
      (fi-aux n 3 ret))))

; prime?
; FIXME: why using factor-integer is fast?
(defn prime?-using-factor-integer [n]
  (if (< n 3)
    (= n 2)
    (= [n] (factor-integer n))))

; (defn prime?-slow [n]
;   (cond
;     (< n 3) (= n 2)
;     (even? n) false
;     (some #(zero? (rem n %)) (range 3 (math/sqrt n) 2)) false
;     :else true))

(defn- prime?-aux [n d]
  (cond
    (< n (* d d)) true
    (zero? (rem n d)) false
    :else (recur n (inc (inc d)))))

(defn prime? [n]
  (cond
    (< n 3) (= n 2)
    (even? n) false
    :else (let [[n _] (div-2 n [])]
            (prime?-aux n 3))))

; (defn- prime-sub? [i n rt]
;   (if (or (zero? (rem n i)) (zero? (rem n (+ i 2))))
;     false
;     )
; (defn prime? [n]
;   (cond
;     (= n 2) true
;     (= n 3) true
;     (= n 5) true
;     (zero? (rem n 2)) false
;     (zero? (rem n 3)) false
;     :else (prime-sub?

; cartesian product

; divisors

;; primes
;; Excerpted from "Programming Clojure, Third Edition",
;; published by The Pragmatic Bookshelf.
;; Copyrights apply to this code. It may not be used to create training material,
;; courses, books, articles, and the like. Contact us if you are in doubt.
;; We make no guarantees that this code is fit for any purpose.
;; Visit http://www.pragmaticprogrammer.com/titles/shcloj3 for more book information.
(def primes
  (concat
   [2 3 5 7]
   (lazy-seq
    (let [primes-from
          (fn primes-from [n [f & r]]
            (if (some #(zero? (rem n %))
                      (take-while #(<= (* % %) n) primes))
              (recur (+ n f) r)
              (lazy-seq (cons n (primes-from (+ n f) r)))))
          wheel (cycle [2 4 2 4 6 2 6 4 2 4 6 6 2 6  4  2
                        6 4 6 8 4 2 4 2 4 8 6 4 6 2  4  6
                        2 6 6 4 2 4 6 2 6 4 2 4 2 10 2 10])]
      (primes-from 11 wheel)))))

; fold
; https://stackoverflow.com/questions/16800255/how-do-we-do-both-left-and-right-folds-in-clojure
(defn fold-l
  [f val coll]
  (if (empty? coll)
    val
    (fold-l f (f val (first coll)) (rest coll))))

(defn fold-r
  [f val coll]
  (if (empty? coll)
    val
    (f (fold-r f val (rest coll)) (first coll))))

; tarai
(defn tarai
  [x y z]
  (if (<= x y)
    y
    (tarai (tarai (dec x) y z)
           (tarai (dec y) z x)
           (tarai (dec z) x y))))

(defn tarai-memo [x y z]
  (let [memo (atom {})]
    (letfn [(tarai [x y z]
              (or (get @memo [x y z])
                  (if (<= x y)
                    y
                    (let [result (tarai (tarai (- x 1) y z)
                                        (tarai (- y 1) z x)
                                        (tarai (- z 1) x y))]
                      (swap! memo assoc [x y z] result)
                      result))))]
      (tarai x y z))))

(defn tarai-lazy [x y z]
  (letfn [(tarai [fx fy fz]
            (if (<= (fx) (fy))
              (fy)
              (tarai (fn [] (tarai (fn [] (- (fx) 1)) fy fz))
                     (fn [] (tarai (fn [] (- (fy) 1)) fz fx))
                     (fn [] (tarai (fn [] (- (fz) 1)) fx fy)))))]
    (tarai (fn [] x) (fn [] y) (fn [] z))))

