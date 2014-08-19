(ns clojure_tic_tac_toe.core
  (:require [clojure_tic_tac_toe.console.runner :refer :all])
  (:gen-class))

(defn -main []
  (game-menu))
