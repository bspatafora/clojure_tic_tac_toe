(ns clojure_tic_tac_toe.core
  (:require [clojure_tic_tac_toe.board :refer :all]
            [clojure_tic_tac_toe.presenter :refer :all]
            [clojure_tic_tac_toe.rules :refer :all]
            [clojure_tic_tac_toe.move-strategies :refer :all])
  (:gen-class))

(defn -main[]
  (loop [board (create-board)
         token "X"
         move-strategy solicit-move]
    (println (string-from-board board))
    (if (is-game-over board)
      (cond
        (winning-token board) (println (str (winning-token board) " wins!"))
        (is-draw board) (println "Tie game."))
      (cond
        (= token "X") (recur (place-token token (move-strategy board) board) "O" minimax-move)
        (= token "O") (recur (place-token token (move-strategy board) board) "X" solicit-move)))))
