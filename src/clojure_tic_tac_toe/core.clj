(ns clojure_tic_tac_toe.core)

(defn create-board []
  [[" ", " ", " "],
   [" ", " ", " "],
   [" ", " ", " "]])

(defn string-from-board [board]
  (loop [board board
         string "\n"]
    (if (empty? board)
      string
      (recur (rest board) (str string (clojure.string/join "|" (first board)) "\n")))))

(defn place-move [token, coordinates, board]
  (assoc-in board coordinates token))

(defn is-draw [board]
  (not (some #{" "} (flatten board))))

(defn -main[]
  )
