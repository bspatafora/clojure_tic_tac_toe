(ns clojure_tic_tac_toe.strings-spec
  (:require [speclj.core :refer :all]
            [clojure_tic_tac_toe.strings :refer :all]
            [clojure_tic_tac_toe.board :refer :all]))

(describe "string-from-board"
  (it "Returns a string representation of an empty 3x3 board vector"
    (should= "\n | | \n | | \n | | \n" (string-from-board (create-board))))
  (it "Returns a string representation of a filled 3x3 board vector"
    (should= "\nX|O|X\nO|X|O\nX|O|X\n" (string-from-board [["X" "O" "X"]
                                                           ["O" "X" "O"]
                                                           ["X" "O" "X"]]))))
