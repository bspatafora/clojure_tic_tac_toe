(ns clojure_tic_tac_toe.core.unbeatable-ai-spec
  (:require [speclj.core :refer :all]
            [clojure_tic_tac_toe.core.unbeatable-ai :refer :all]
            [clojure_tic_tac_toe.core.board :refer :all]))

(describe "minimax"
  (it "Returns 0 when the passed board represents a draw"
    (should= 0 (minimax [["X" "X" "O"]
                         ["O" "O" "X"]
                         ["X" "O" "X"]] true)))
  (it "Returns 1 when the passed board represents a win for O"
    (should= 1 (minimax [["O" " " " "]
                         [" " "O" " "]
                         [" " " " "O"]] true)))
  (it "Returns -1 when the passed board represents a win for X"
    (should= -1 (minimax [["X" " " " "]
                          [" " "X" " "]
                          [" " " " "X"]] true)))
  (it "Returns the correct score when the passed a board that isn’t in an end-game state"
    (should= 0 (minimax [["X" "X" "O"]
                         [" " "O" " "]
                         ["X" " " " "]] true))))

(describe "minimax-move"
  (it "Returns the coordinate of the candidate move with the best minimax score"
    (should= [1 0] (minimax-move [["X" "X" "O"]
                                  [" " "O" " "]
                                  ["X" " " " "]]))))
