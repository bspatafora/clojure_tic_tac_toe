(ns clojure_tic_tac_toe.console.player
  (:require [clojure_tic_tac_toe.core.board :refer :all]
            [clojure_tic_tac_toe.console.presenter :refer :all]))

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
  (and
    (input-on-board input)
    (is-coordinate-open (coordinate-from-integer (integer-from-input input)) board)))

(defn input-is-invalid-move [input board]
  (cond
    (not (integer-from-input input)) (not-an-integer-message)
    (not (input-on-board input)) (not-on-board-message)
    (not (is-coordinate-open (coordinate-from-integer (integer-from-input input)) board)) (not-open-message)))

(defn solicit-move [board]
  (move-solicitation-message)
  (let [input (read-line)]
    (if (is-input-valid-move input board)
      (coordinate-from-integer (integer-from-input input))
      (do
        (input-is-invalid-move input board)
        (solicit-move board)))))
