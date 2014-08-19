(ns clojure_tic_tac_toe.core.board-spec
  (:require [speclj.core :refer :all]
            [clojure_tic_tac_toe.core.board :refer :all]))

(describe "create-board"
  (it "Returns an empty, 3x3 vector"
    (should= [[" " " " " "]
              [" " " " " "]
              [" " " " " "]] (create-board))))

(describe "place-token"
  (it "Returns a board based on the passed board with the passed token placed at the passed coordinate"
    (should= [["X" " " " "]
              [" " "O" " "]
              [" " " " " "]] (place-token "O" [1 1] [["X" " " " "]
                                                     [" " " " " "]
                                                     [" " " " " "]]))))

(describe "full-slices"
  (it "Returns an empty list when no slices are full of tokens"
    (should= '() (full-slices (create-board))))
  (it "Returns the slices that are full of tokens"
    (should= '(["O" "X" "O"]) (full-slices [["X" " " "X"]
                                            ["O" "X" "O"]
                                            [" " " " " "]]))))

(describe "board-columns"
  (it "Returns column slices for the passed board"
    (should= '(["X" "O" "X"]
               ["X" "O" "X"]
               ["X" "O" "X"]) (board-columns [["X" "X" "X"]
                                              ["O" "O" "O"]
                                              ["X" "X" "X"]]))))

(describe "board-diagonals"
  (it "Returns diagonal slices for the passed 3x3 board"
    (should= [["X" "X" "X"] ["O" "X" "O"]] (board-diagonals [["X" " " "O"]
                                                             [" " "X" " "]
                                                             ["O" " " "X"]]))))

(describe "board-full-slices"
  (it "Returns an empty list when there are no full slices on the passed board"
    (should= '() (board-full-slices (create-board))))
  (it "Returns all full slices for the passed board"
    (should= '(["O" "X" "O"] ["O" "X" "O"] ["X" "X" "X"]) (board-full-slices [[" " "O" "X"]
                                                                              ["O" "X" "O"]
                                                                              ["X" "O" " "]]))))

(describe "open-coordinates"
  (it "Returns the unfilled coordinates for the passed board"
    (should= '([0 1] [1 2] [2 0]) (open-coordinates [["X" " " "X"]
                                                     ["O" "X" " "]
                                                     [" " "X" "O"]]))))

(describe "is-coordinate-open"
  (it "Returns false if there is a token at the passed coordinate on the passed board"
    (should= false (is-coordinate-open [0 0] [["X" " " " "]
                                              [" " " " " "]
                                              [" " " " " "]])))
  (it "Returns true if there is no token at the passed coordinate on the passed board"
    (should= true (is-coordinate-open [0 1] [["X" " " " "]
                                             [" " " " " "]
                                             [" " " " " "]]))))
