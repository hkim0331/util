(ns hkim0331.util.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [hkim0331.util.core :as u]))

(deftest power-test
  (testing "power"
    (is (= (u/power 2 0) 1))
    (is (= (u/power 2 10) 1024))))

(deftest prime?-test
  (testing "prime?"
    (is (= (map u/prime? (range 100))
           (map u/prime' (range 100))))))

(deftest divisors-test
  (testing "divisors"
    (dotimes [_ 10]
      (let [r (rand-int 1000000)]
        (is (= (set (u/divisors r)) (set (u/divisors' r))))))))

(deftest factor-integer-test
  (testing "divisors"
    (dotimes [_ 10]
      (let [r (rand-int 1000000)]
        (is (= r (reduce * (u/factor-integer r))))))))

(deftest reverse-all-test
  (testing "reverse-all"
    (is (= (u/reverse-all 0) 0))
    (is (= (u/reverse-all [0]) [0]))
    (is (= (u/reverse-all [1 [2 [3 4 5]] [6 7] 8 9])
           [9 8 [7 6] [[5 4 3] 2] 1]))))

(deftest flatten-all-test
  (testing "flatten-all"
    (is (= (u/flatten-all []) []))
    (is (= (u/flatten-all [1 2 3]) [1 2 3]))
    (is (= (u/flatten-all [[1] [2] [3]]) [1 2 3]))
    (is (= (u/flatten-all [[1 2] [3 [4] [[5]]]]) [1 2 3 4 5]))))

(deftest binary-search-test
  (testing "binary-search"
    (is (= 500 (u/binary-search (vec (range 500)) 500)))
    (is (= nil (u/binary-search (vec (range 500)) 499)))
    (is (= nil (u/binary-search (vec (range 500)) 501)))))

