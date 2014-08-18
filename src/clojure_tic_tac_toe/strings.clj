(ns clojure_tic_tac_toe.strings)

(defn- string-from-row [row row-number]
  (clojure.string/join "|" (map-indexed #(if (= %2 " ") (+ %1 (* row-number 3)) %2) row)))

(defn- string-from-rows [rows]
  (clojure.string/join "\n" rows))

(defn string-from-board [board]
  (str "\n" (string-from-rows (map string-from-row board [0 1 2])) "\n"))
