(ns clojure_tic_tac_toe.console.player-spec
  (:require [speclj.core :refer :all]
            [clojure_tic_tac_toe.console.player :refer :all]
            [clojure_tic_tac_toe.core.board :refer :all]
            [clojure_tic_tac_toe.console.presenter :refer :all]))

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

(describe "input-is-invalid-move"
  (it "Invokes not-an-integer-message if the input is not an integer"
    (should-invoke not-an-integer-message {:times 1} (input-is-invalid-move "a" (create-board))))
  (it "Invokes not-on-board-message if the input is not an integer between 0 and 8"
    (should-invoke not-on-board-message {:times 1} (input-is-invalid-move "9" (create-board))))
  (it "Invokes not-open-message if the input is otherwise invalid"
    (should-invoke not-open-message {:times 1} (input-is-invalid-move "5" [[" " " " " "]
                                                                           [" " " " "X"]
                                                                           [" " " " " "]]))))

(describe "solicit-move"
  (with-out-str
    (it "Returns a coordinate once a input representing a valid move is passed"
      (should= [2 0] (with-in-str "9\n6" (solicit-move (create-board)))))
    (it "Invokes input-is-invalid-move with the input if input represeting an invalid move is passed"
      (should-invoke input-is-invalid-move {:with ["9"]} (with-in-str "9\n6" (solicit-move (create-board)))))))
