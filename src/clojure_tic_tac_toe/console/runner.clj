(ns clojure_tic_tac_toe.console.runner
  (:require [clojure_tic_tac_toe.core.board :refer :all]
            [clojure_tic_tac_toe.console.presenter :refer :all]
            [clojure_tic_tac_toe.core.rules :refer :all]
            [clojure_tic_tac_toe.core.move-strategies :refer :all]))

(defn- end-game [board]
  (cond
    (winning-token board) (println (str (winning-token board) " wins!"))
    (is-draw board) (println "Tie game.")))

(defn- next-token [token]
  (cond
    (= token "X") "O"
    (= token "O") "X"))

(defn- next-move-strategy [move-strategy]
  (cond
    (= move-strategy solicit-move) minimax-move
    (= move-strategy minimax-move) solicit-move))

(defn- game-loop []
  (loop [board (create-board)
         token "X"
         move-strategy solicit-move]
    (println (string-from-board board))
    (if (is-game-over board)
      (end-game board)
      (recur (place-token token (move-strategy board) board) (next-token token) (next-move-strategy move-strategy)))))

(defn game-menu []
  (loop [play true]
    (if play
      (do
        (game-loop)
        (println "Play again? (y/n)")
        (recur (= (read-line) "y")))
      (println "Thanks for playing!"))))
