(ns clojure_tic_tac_toe.core.move-strategies
  (:require [clojure_tic_tac_toe.core.board :refer :all]
            [clojure_tic_tac_toe.core.rules :refer :all]))

(defn integer-from-input [input]
  (try
    (Integer/parseInt input)
    (catch NumberFormatException e nil)))

(defn coordinate-from-integer [integer]
  (cond
    (< integer 3) [0 (mod integer 3)]
    (< integer 6) [1 (mod integer 3)]
    :else [2 (mod integer 3)]))

(defn input-on-board [input]
  (re-find #"^[0-8]$" input))

(defn is-input-valid-move [input board]
  (and (input-on-board input) (is-coordinate-open (coordinate-from-integer (integer-from-input input)) board)))

(defn solicit-move [board]
  (println "Pick a space (0-8):")
  (let [input (read-line)]
    (if (is-input-valid-move input board)
      (coordinate-from-integer (integer-from-input input))
      (solicit-move board))))

(defn- score [board]
  (cond
    (is-draw board) 0
    (= (winning-token board) "O") 1
    (= (winning-token board) "X") -1))

(defn random-move [board]
  (rand-nth (open-coordinates board)))

(defn minimax [board maximizing]
  (if (is-game-over board)
    (score board)
    (if maximizing
      (let [worst-score -1]
        (apply max (conj (map #(minimax (place-token "O" % board) false) (open-coordinates board)) worst-score)))
      (let [worst-score 1]
        (apply min (conj (map #(minimax (place-token "X" % board) true) (open-coordinates board)) worst-score))))))

(defn minimax-move [board]
  (let [candidates (open-coordinates board)
        scores (map #(minimax (place-token "O" % board) false) candidates)
        best-score (apply max scores)
        best-score-indexes (keep-indexed #(if (= best-score %2) %1) scores)]
    (nth candidates (first best-score-indexes))))
