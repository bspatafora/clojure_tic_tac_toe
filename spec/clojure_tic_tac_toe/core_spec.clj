(ns clojure_tic_tac_toe.core-spec
  (:require [speclj.core :refer :all]
            [clojure_tic_tac_toe.core :refer :all]))

(describe "create-board"
  (it "Returns an empty, 3x3 vector"
    (should= [[" ", " ", " "],
              [" ", " ", " "],
              [" ", " ", " "]] (create-board))))

(describe "string-from-board"
  (it "Returns a string representation of an empty 3x3 board vector"
    (should= "\n | | \n | | \n | | \n" (string-from-board (create-board))))
  (it "Returns a string representation of a filled 3x3 board vector"
    (should= "\nX|O|X\nO|X|O\nX|O|X\n" (string-from-board [["X", "O", "X"],
                                                           ["O", "X", "O"],
                                                           ["X", "O", "X"]]))))

(describe "place-move"
  (it "Returns a board based on the passed board with the passed move placed on it"
    (should= [["X", " ", " "],
              [" ", "O", " "],
              [" ", " ", " "]] (place-move "O" [1 1] [["X", " ", " "],
                                                      [" ", " ", " "],
                                                      [" ", " ", " "]]))))

(describe "is-draw"
  (it "Returns true when the board is full"
    (should= true (is-draw [["X", "O", "X"],
                            ["O", "X", "O"],
                            ["X", "O", "X"]])))
  (it "Returns false when the board is empty"
    (should= false (is-draw (create-board))))
  (it "Returns false when the board is not full"
    (should= false (is-draw [["X", " ", " "],
                             [" ", "X", " "],
                             [" ", " ", "X"]]))))
