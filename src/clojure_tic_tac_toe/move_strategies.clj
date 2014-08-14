(ns clojure_tic_tac_toe.move-strategies
  (:require [clojure_tic_tac_toe.board :refer :all]
            [clojure_tic_tac_toe.rules :refer :all]))

(defn is-coordinate-open [coordinate board]
  (if (some #{coordinate} (open-coordinates board))
    true
    false))

(defn coordinate-from-integer [integer]
  (cond
    (< integer 3) [0 (mod integer 3)]
    (< integer 6) [1 (mod integer 3)]
    :else [2 (mod integer 3)]))

(defn solicit-move [board]
  (println "Pick a space (0-8):")
  (let [input (read-line)]
    (if (and (re-find #"^[0-8]$" input) (is-coordinate-open (coordinate-from-integer (read-string input)) board))
      (coordinate-from-integer (read-string input))
      (solicit-move board))))

(defn score [board]
  (cond
    (is-draw board) 0
    (= (winning-token board) "O") 1
    (= (winning-token board) "X") -1))

(defn random-move [board]
  (rand-nth (open-coordinates board)))
