(ns clojure_tic_tac_toe.core.rules-spec
  (:require [speclj.core :refer :all]
            [clojure_tic_tac_toe.core.rules :refer :all]
            [clojure_tic_tac_toe.core.board :refer :all]))

(describe "winning-token"
  (it "Returns nil when no token has won"
    (should= nil (winning-token (create-board))))
  (it "Returns the winning token when a row is filled by that token"
    (should= "X" (winning-token [["O" "X" "O"]
                                 ["X" "X" "X"]
                                 [" " " " " "]])))
  (it "Returns the winning token when a column is filled by that token"
    (should= "X" (winning-token [["O" "X" " "]
                                 ["X" "X" " "]
                                 ["O" "X" " "]])))
  (it "Returns the winning token when a diagonal is filled by that token"
    (should= "X" (winning-token [["O" " " "X"]
                                 [" " "X" " "]
                                 ["X" " " "O"]]))))

(describe "is-draw"
  (it "Returns true when the board is full and there is no winner"
    (should= true (is-draw [["X" "X" "O"]
                            ["O" "O" "X"]
                            ["X" "O" "X"]])))
  (it "Returns false when the board is empty"
    (should= false (is-draw (create-board))))
  (it "Returns false when the board is not full"
    (should= false (is-draw [["X" " " " "]
                             [" " "X" " "]
                             [" " " " "X"]])))
  (it "Returns false when the board is full but there is a winner"
    (should= false (is-draw [["X" "O" "O"]
                             ["O" "X" "O"]
                             ["O" "O" "X"]]))))

(describe "is-game-over"
  (it "Returns false when the game is not over"
    (should= false (is-game-over (create-board))))
  (it "Returns true when there is a winning token"
    (should= true (is-game-over [["X" " " " "]
                                 [" " "X" " "]
                                 [" " " " "X"]])))
  (it "Returns true when there is a draw"
    (should= true (is-game-over [["X" "X" "O"]
                                 ["O" "O" "X"]
                                 ["X" "O" "X"]]))))
