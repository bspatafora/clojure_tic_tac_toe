(ns clojure_tic_tac_toe.core)

(defn create-board []
  [[" " " " " "]
   [" " " " " "]
   [" " " " " "]])

(defn string-from-row [row]
  (clojure.string/join "|" row) )

(defn string-from-rows [rows]
  (clojure.string/join "\n" rows))

(defn string-from-board [board]
  (str "\n" (string-from-rows (map string-from-row board)) "\n"))

(defn place-token [token coordinates board]
  (assoc-in board coordinates token))

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

(defn winning-token [board]
  (let [winning-row (filterv #(every? #{(first %)} %) (board-full-slices board))]
    (when winning-row
      (get-in winning-row [0 0]))))

(defn is-draw [board]
  (if-not (= (winning-token board) nil)
    false
    (not-any? #{" "} (flatten board))))

(defn open-coordinates [board]
  (let [first-row-open-coordinates (vec (keep-indexed #(if (= " " %2) [0 %1]) (nth board 0)))
        second-row-open-coordinates (vec (keep-indexed #(if (= " " %2) [1 %1]) (nth board 1)))
        third-row-open-coordinates (vec (keep-indexed #(if (= " " %2) [2 %1]) (nth board 2)))]
    (concat first-row-open-coordinates second-row-open-coordinates third-row-open-coordinates)))

(defn random-move [board]
  (rand-nth (open-coordinates board)))

(defn solicit-move [board]
  (println "Pick a space (0-8):")
  (let [input (read-line)]
    (if (re-find #"^[0-8]$" input)
      (cond
        (< (read-string input) 3) [0 (mod (read-string input) 3)]
        (< (read-string input) 6) [1 (mod (read-string input) 3)]
        :else [2 (mod (read-string input) 3)])
      (solicit-move board))))

(defn -main[]
  (loop [board (create-board)
         token "X"
         move-strategy solicit-move]
    (do
      (println (string-from-board board))
      (if (or (winning-token board) (is-draw board))
        (cond
          (winning-token board) (println (str (winning-token board) " wins!"))
          (is-draw board) (println "Tie game."))
        (cond
          (= token "X") (recur (place-token token (move-strategy board) board) "O" random-move)
          (= token "O") (recur (place-token token (move-strategy board) board) "X" solicit-move))))))
