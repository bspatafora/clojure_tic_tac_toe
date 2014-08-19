(ns clojure_tic_tac_toe.core.move-strategies-spec
  (:require [speclj.core :refer :all]
            [clojure_tic_tac_toe.core.move-strategies :refer :all]
            [clojure_tic_tac_toe.core.board :refer :all]))

(describe "is-coordinate-open"
  (it "Returns false if there is a token at the passed coordinate on the passed board"
    (should= false (is-coordinate-open [0 0] [["X" " " " "]
                                              [" " " " " "]
                                              [" " " " " "]])))
  (it "Returns true if there is no token at the passed coordinate on the passed board"
    (should= true (is-coordinate-open [0 1] [["X" " " " "]
                                             [" " " " " "]
                                             [" " " " " "]]))))

(describe "coordinate-from-integer"
  (it "Returns a coordinate corresponding to the integer (0-8)"
    (should= [1 2] (coordinate-from-integer 5))))

(describe "solicit-move"
  (it "Returns a coordinate once a move corresponding to an open space on the passed board is input"
    (with-out-str 
      (should= [2 0] (with-in-str "6" (solicit-move (create-board)))))))

(describe "random-move"
  (it "Returns a coordinate selected from a random open space on the passed board"
    (should= [1 1] (random-move [["X" "O" "X"]
                                 ["O" " " "O"]
                                 ["X" "O" "X"]]))))

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
