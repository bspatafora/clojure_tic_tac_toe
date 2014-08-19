(ns clojure_tic_tac_toe.core.move-strategies-spec
  (:require [speclj.core :refer :all]
            [clojure_tic_tac_toe.core.move-strategies :refer :all]
            [clojure_tic_tac_toe.core.board :refer :all]))

(describe "integer-from-input"
  (it "Returns a corresponding integer if the input is an integer-string"
    (should= 8 (integer-from-input "8")))
  (it "Returns nil if the input is not an integer-string"
    (should= nil (integer-from-input "?"))))

(describe "coordinate-from-integer"
  (it "Returns a coordinate corresponding to the integer (0-8)"
    (should= [1 2] (coordinate-from-integer 5))))

(describe "input-on-board"
  (it "Returns true if the input is an integer between 0 and 8"
    (should (input-on-board "4")))
  (it "Returns false if the input is an integer not between 0 and 8"
    (should-not (input-on-board "9"))))

(describe "is-input-valid-move"
  (it "Returns true if the input is an integer and untaken on the passed board"
    (should (is-input-valid-move "0" (create-board))))
  (it "Returns false if the input is an integer and not on the passed board"
    (should-not (is-input-valid-move "9" (create-board))))
  (it "Returns false if the input is an integer that is taken on the passed board"
    (should-not (is-input-valid-move "7" [[" " " " " "]
                                          [" " " " " "]
                                          [" " "X" " "]])))
  (it "Returns false if the input is not an integer"
    (should-not (is-input-valid-move "a" (create-board)))))

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
