(ns util.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [util.core :as u]))

(deftest prime?-test
  (testing "prime?"
    (is (= (map u/prime? (range 100))
           (map u/prime?-slow (range 100))))))

(deftest power-test
  (testing "power"
    (is (= (u/power 2 0) 1))
    (is (= (u/power 2 10) 1024))))
