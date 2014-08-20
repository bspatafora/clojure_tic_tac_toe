(ns clojure_tic_tac_toe.console.presenter)

(defn- colorize [text color-code]
  (str "\033[" color-code "m" text "\033[0m"))

(defn- red [text]
  (colorize text 31))

(defn- colorize-token [token]
  (if (= token "X")
    (red token)
    (colorize token 32)))

(defn- string-from-row [row row-number]
  (clojure.string/join "|" (map-indexed #(if (= %2 " ") (+ %1 (* row-number 3)) (colorize-token %2)) row)))

(defn- string-from-rows [rows]
  (clojure.string/join "\n" rows))

(defn string-from-board [board]
  (str "\n" (string-from-rows (map string-from-row board [0 1 2])) "\n"))

(defn display-board [board]
  (println (string-from-board board)))

(defn move-solicitation-message []
  (println "Pick a space"))

(defn not-an-integer-message []
  (println (red "Input must be an integer")))

(defn not-on-board-message []
  (println (red "Input must be on the board")))

(defn not-open-message []
  (println (red "Space must be open")))

(defn winning-token-message [token]
  (println (str token " wins!")))

(defn tie-game-message []
  (println "Tie game."))

(defn play-again-message []
  (println "Play again? (y/n)"))

(defn thanks-for-playing-message []
  (println "Thanks for playing!"))
