(ns clojure_tic_tac_toe.console.presenter-spec
  (:require [speclj.core :refer :all]
            [clojure_tic_tac_toe.console.presenter :refer :all]
            [clojure_tic_tac_toe.core.board :refer :all]))

(describe "string-from-board"
  (it "Returns a string representation (with numbered spaces) of an empty 3x3 board vector"
    (should= "\n0|1|2\n3|4|5\n6|7|8\n" (string-from-board (create-board))))
  (it "Returns a string representation (with colored tokens) of a 3x3 board vector with tokens on it"
    (should= "\n\033[32mO\033[0m|1|2\n3|4|5\n6|7|\033[31mX\033[0m\n" (string-from-board [["O" " " " "]
                                                                                         [" " " " " "]
                                                                                         [" " " " "X"]]))))
