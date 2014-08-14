(ns clojure_tic_tac_toe.strings)

(defn- string-from-row [row]
  (clojure.string/join "|" row) )

(defn- string-from-rows [rows]
  (clojure.string/join "\n" rows))

(defn string-from-board [board]
  (str "\n" (string-from-rows (map string-from-row board)) "\n"))
