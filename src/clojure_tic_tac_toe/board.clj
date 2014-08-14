(ns clojure_tic_tac_toe.board)

(defn create-board []
  [[" " " " " "]
   [" " " " " "]
   [" " " " " "]])

(defn place-token [token coordinate board]
  (assoc-in board coordinate token))

(defn full-slices [slices]
  (filter #(not-any? #{" "} %) slices))

(defn board-columns [board]
  (apply map vector board))

(defn board-diagonals [board]
  [[(get-in board [0 0]) (get-in board [1 1]) (get-in board [2 2])]
   [(get-in board [0 2]) (get-in board [1 1]) (get-in board [2 0])]])

(defn board-full-slices [board]
  (let [full-row-slices (full-slices board)
        full-column-slices (full-slices (board-columns board))
        full-diagonal-slices (full-slices (board-diagonals board))]
    (concat full-row-slices full-column-slices full-diagonal-slices)))

(defn open-coordinates [board]
  (let [first-row-open-coordinates (vec (keep-indexed #(if (= " " %2) [0 %1]) (nth board 0)))
        second-row-open-coordinates (vec (keep-indexed #(if (= " " %2) [1 %1]) (nth board 1)))
        third-row-open-coordinates (vec (keep-indexed #(if (= " " %2) [2 %1]) (nth board 2)))]
    (concat first-row-open-coordinates second-row-open-coordinates third-row-open-coordinates)))
