(ns clojure_tic_tac_toe.core.rules
  (:require [clojure_tic_tac_toe.core.board :refer :all]))

(defn winning-token [board]
  (let [winning-row (filterv #(every? #{(first %)} %) (board-full-slices board))]
    (when winning-row
      (get-in winning-row [0 0]))))

(defn is-draw [board]
  (and (not-any? #{" "} (flatten board))
       (not (winning-token board))))

(defn is-game-over [board]
  (if (or (is-draw board) (winning-token board))
    true
    false))
