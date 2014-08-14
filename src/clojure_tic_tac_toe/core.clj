(ns clojure_tic_tac_toe.core)

(defn create-board []
  [[" " " " " "]
   [" " " " " "]
   [" " " " " "]])

(defn string-from-board [board]
  (loop [board board
         string "\n"]
    (if (empty? board)
      string
      (recur (rest board) (str string (clojure.string/join "|" (first board)) "\n")))))

(defn place-token [token coordinates board]
  (assoc-in board coordinates token))

(defn is-draw [board]
  (not-any? #{" "} (flatten board)))

(defn full-slices [slices]
  (filterv #(not-any? #{" "} %) slices))

(defn board-columns [board]
  (apply mapv vector board))

(defn board-diagonals [board]
  [[(get-in board [0 0]) (get-in board [1 1]) (get-in board [2 2])]
   [(get-in board [0 2]) (get-in board [1 1]) (get-in board [2 0])]])

(defn board-full-slices [board]
  (let [full-row-slices (full-slices board)
        full-column-slices (full-slices (board-columns board))
        full-diagonal-slices (full-slices (board-diagonals board))]
    (into full-row-slices (into full-column-slices full-diagonal-slices))))

(defn winning-token [board]
  (let [winning-row (filterv #(every? #{(first %)} %) (board-full-slices board))]
    (if winning-row
      (get-in winning-row [0 0])
      nil)))

(defn open-coordinates [board]
  (let [first-row-open-coordinates (vec (keep-indexed #(if (= " " %2) [0 %1]) (nth board 0)))
        second-row-open-coordinates (vec (keep-indexed #(if (= " " %2) [1 %1]) (nth board 1)))
        third-row-open-coordinates (vec (keep-indexed #(if (= " " %2) [2 %1]) (nth board 2)))]
  (into first-row-open-coordinates (into second-row-open-coordinates third-row-open-coordinates))))

(defn -main[]
  )
