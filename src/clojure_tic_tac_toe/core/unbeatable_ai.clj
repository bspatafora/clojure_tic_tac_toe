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
      (apply max (conj (map #(minimax (place-token "O" % board) false) (board-open-coordinates board)) -1))
      (apply min (conj (map #(minimax (place-token "X" % board) true) (board-open-coordinates board)) 1)))))

(defn- coordinate-scores [coordinates board]
  (map #(minimax (place-token "O" % board) false) coordinates))

(defn- score-and-coordinate-pairs [coordinates board]
  (map vector (coordinate-scores coordinates board) coordinates))

(defn- highest-scoring-coordinate [score-and-coordinate-pairs]
  (second (last (sort score-and-coordinate-pairs))))

(defn minimax-move [board]
  (highest-scoring-coordinate (score-and-coordinate-pairs (board-open-coordinates board) board)))
