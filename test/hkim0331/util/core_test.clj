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

