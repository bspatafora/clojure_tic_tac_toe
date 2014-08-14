(ns clojure_tic_tac_toe.core-spec
  (:require [speclj.core :refer :all]
            [clojure_tic_tac_toe.core :refer :all]))

(describe "create-board"
  (it "Returns an empty, 3x3 vector"
    (should= [[" " " " " "]
              [" " " " " "]
              [" " " " " "]] (create-board))))

(describe "string-from-board"
  (it "Returns a string representation of an empty 3x3 board vector"
    (should= "\n | | \n | | \n | | \n" (string-from-board (create-board))))
  (it "Returns a string representation of a filled 3x3 board vector"
    (should= "\nX|O|X\nO|X|O\nX|O|X\n" (string-from-board [["X" "O" "X"]
                                                           ["O" "X" "O"]
                                                           ["X" "O" "X"]]))))

(describe "place-token"
  (it "Returns a board based on the passed board with the passed token placed at the passed coordinates"
    (should= [["X" " " " "]
              [" " "O" " "]
              [" " " " " "]] (place-token "O" [1 1] [["X" " " " "]
                                                     [" " " " " "]
                                                     [" " " " " "]]))))

(describe "is-draw"
  (it "Returns true when the board is full"
    (should= true (is-draw [["X" "O" "X"]
                            ["O" "X" "O"]
                            ["X" "O" "X"]])))
  (it "Returns false when the board is empty"
    (should= false (is-draw (create-board))))
  (it "Returns false when the board is not full"
    (should= false (is-draw [["X" " " " "]
                             [" " "X" " "]
                             [" " " " "X"]]))))

(describe "full-slices"
  (it "Returns an empty vector when no slices are full of tokens"
    (should= [] (full-slices (create-board))))
  (it "Returns the slices that are full of tokens"
    (should= [["O" "X" "O"]] (full-slices [["X" " " "X"]
                                           ["O" "X" "O"]
                                           [" " " " " "]]))))

(describe "board-columns"
  (it "Returns column slices for the passed board"
    (should= [["X" "O" "X"]
              ["X" "O" "X"]
              ["X" "O" "X"]] (board-columns [["X" "X" "X"]
                                             ["O" "O" "O"]
                                             ["X" "X" "X"]]))))

(describe "board-diagonals"
  (it "Returns diagonal slices for the passed 3x3 board"
    (should= [["X" "X" "X"] ["O" "X" "O"]] (board-diagonals [["X" " " "O"]
                                                             [" " "X" " "]
                                                             ["O" " " "X"]]))))

(describe "board-full-slices"
  (it "Returns an empty vector when there are no full slices on the passed board"
    (should= [] (board-full-slices (create-board))))
  (it "Returns all full slices for the passed board"
    (should= [["O" "X" "O"] ["O" "X" "O"] ["X" "X" "X"]] (board-full-slices [[" " "O" "X"]
                                                                             ["O" "X" "O"]
                                                                             ["X" "O" " "]]))))

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

(describe "open-coordinates"
  (it "Returns the unfilled coordinates for the passed board"
    (should= [[0 1] [1 2] [2 0]] (open-coordinates [["X" " " "X"]
                                                    ["O" "X" " "]
                                                    [" " "X" "O"]]))))

(describe "random-move"
  (it "Returns a set of coordinates selected from a random open space on the passed board"
    (should= [1 1] (random-move [["X" "O" "X"]
                                 ["O" " " "O"]
                                 ["X" "O" "X"]]))))
