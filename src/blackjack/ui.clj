(ns blackjack.ui
  (:require [blackjack.graphics :as graphics]))

(defn player-name
  "Returns the name of the player."
  {:added "1.0.0"}
  []
  (println "What is your name?")
  (read-line))

(defn player-decision?
  "Returns true if player wants a new card."
  {:added "1.0.0"}
  []
  (println "Do you want a new card? (y/n)")
  (let [answer (read-line)]
    (if (= answer (or "y" "Y")) true false)))

(defn print-players
  "Prints the players."
  {:added "1.0.0"}
  [players, masked-dealer?]
  (if masked-dealer?
    (graphics/print-masked-player (:dealer players))
    (graphics/print-player (:dealer players)))
  (graphics/print-player (:player players)))

(defn dealer-decision-output
  "Returns an output if the Dealer chose to continue or not."
  {:added "1.0.0"}
  [continues?]
  (if continues?
    (println "> Dealer continues.")
    (println "> Dealer stops."))
  (println "--------------"))

(defn wins-output
  "Prints the player wins."
  {:added "1.0.0"}
  [name]
  (println name "wins!"))