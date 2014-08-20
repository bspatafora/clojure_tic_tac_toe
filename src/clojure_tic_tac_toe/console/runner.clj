(ns clojure_tic_tac_toe.console.runner
  (:require [clojure_tic_tac_toe.core.board :refer :all]
            [clojure_tic_tac_toe.console.presenter :refer :all]
            [clojure_tic_tac_toe.core.rules :refer :all]
            [clojure_tic_tac_toe.core.unbeatable-ai :refer :all]
            [clojure_tic_tac_toe.console.player :refer :all]))

(defn- end-game [board]
  (cond
    (winning-token board) (winning-token-message (winning-token board))
    (is-draw board) (tie-game-message)))

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
    (display-board board)
    (if (is-game-over board)
      (end-game board)
      (recur (place-token token (move-strategy board) board) (next-token token) (next-move-strategy move-strategy)))))

(defn game-menu []
  (loop [play true]
    (if play
      (do
        (game-loop)
        (play-again-message)
        (recur (= (read-line) "y")))
      (thanks-for-playing-message))))
