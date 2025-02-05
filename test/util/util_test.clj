(ns util.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [util.core :as u]))

(deftest prime?-test
  (testing "prime?"
    (is (= (map u/prime? (range 100))
           (map u/prime?-slow (range 100))))))

