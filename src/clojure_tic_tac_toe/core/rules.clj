(ns clojure_tic_tac_toe.core.rules
  (:require [clojure_tic_tac_toe.core.board :refer :all]))

(defn winning-token [board]
  (let [winning-row (filterv #(every? #{(first %)} %) (board-full-slices board))]
    (when winning-row
      (get-in winning-row [0 0]))))

(defn is-draw [board]
  (if (winning-token board)
    false
    (not-any? #{" "} (flatten board))))

(defn is-game-over [board]
  (if (or (is-draw board) (winning-token board))
    true
    false))
