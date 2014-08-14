(ns clojure_tic_tac_toe.rules
  (:require [clojure_tic_tac_toe.board :refer :all]))

(defn winning-token [board]
  (let [winning-row (filterv #(every? #{(first %)} %) (board-full-slices board))]
    (when winning-row
      (get-in winning-row [0 0]))))

(defn is-draw [board]
  (if-not (= (winning-token board) nil)
    false
    (not-any? #{" "} (flatten board))))
