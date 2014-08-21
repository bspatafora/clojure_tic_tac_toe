(ns clojure_tic_tac_toe.core.board)

(defn create-board []
  [[" " " " " "]
   [" " " " " "]
   [" " " " " "]])

(defn place-token [token coordinate board]
  (assoc-in board coordinate token))

(defn board-columns [board]
  (apply map vector board))

(defn board-diagonals [board]
  [[(get-in board [0 0]) (get-in board [1 1]) (get-in board [2 2])]
   [(get-in board [0 2]) (get-in board [1 1]) (get-in board [2 0])]])

(defn full-slices [slices]
  (filter #(not-any? #{" "} %) slices))

(defn board-full-slices [board]
  (mapcat full-slices [board (board-columns board) (board-diagonals board)]))

(defn open-coordinates [slice row]
  (keep-indexed #(if (= " " %2) [row %1]) slice))

(defn board-open-coordinates [board]
  (mapcat open-coordinates board [0 1 2]))

(defn is-coordinate-open [coordinate board]
  (if (some #{coordinate} (board-open-coordinates board))
    true
    false))
