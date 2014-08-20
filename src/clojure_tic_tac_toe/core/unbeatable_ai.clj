(ns clojure_tic_tac_toe.core.unbeatable-ai
  (:require [clojure_tic_tac_toe.core.board :refer :all]
            [clojure_tic_tac_toe.core.rules :refer :all]))

(defn- score [board]
  (cond
    (is-draw board) 0
    (= (winning-token board) "O") 1
    (= (winning-token board) "X") -1))

(defn minimax [board maximizing]
  (if (is-game-over board)
    (score board)
    (if maximizing
        (apply max (conj (map #(minimax (place-token "O" % board) false) (open-coordinates board)) -1))
        (apply min (conj (map #(minimax (place-token "X" % board) true) (open-coordinates board)) 1)))))

(defn minimax-move [board]
  (let [candidates (open-coordinates board)
        scores (map #(minimax (place-token "O" % board) false) candidates)
        best-score (apply max scores)
        best-score-indexes (keep-indexed #(if (= best-score %2) %1) scores)]
    (nth candidates (first best-score-indexes))))
