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

(defn place-move [token coordinates board]
  (assoc-in board coordinates token))

(defn is-draw [board]
  (not (some #{" "} (flatten board))))

(defn full-slices [slices]
 (filterv #(not-any? #{" "} %) slices))

(defn board-columns [board]
  (apply mapv vector board))

(defn board-diagonals [board]
  [[(get-in board [0 0]) (get-in board [1 1]) (get-in board [2 2])]
   [(get-in board [0 2]) (get-in board [1 1]) (get-in board [2 0])]])

(defn -main[]
  )
