(ns clojure_tic_tac_toe.move-strategies-spec
  (:require [speclj.core :refer :all]
            [clojure_tic_tac_toe.move-strategies :refer :all]
            [clojure_tic_tac_toe.board :refer :all]))

(describe "random-move"
  (it "Returns a coordinate selected from a random open space on the passed board"
    (should= [1 1] (random-move [["X" "O" "X"]
                                 ["O" " " "O"]
                                 ["X" "O" "X"]]))))

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
    (should= [2 0] (with-in-str "6" (solicit-move (create-board))))))
