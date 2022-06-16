(ns blackjack.ui
  (:require [blackjack.graphics :as b.graphics]))


(def language "en")

(defn- localize
  "Returns the localized string."
  {:added "1.0.0"}
  []
  (if (= language "en")
    {:player-name      "What is your name?"
     :player-decision  "Do you want a new card? (y/n)"
     :wins             "wins!"
     :dealer-continues "Dealer continues."
     :dealer-stops     "Dealer stops."
     :score            "points."}
    {:player-name      "Qual o seu nome?"
     :player-decision  "Você deseja outra carta? (s/n)"
     :wins             "ganhou!"
     :dealer-continues "O dealer decidiu continuar."
     :dealer-stops     "O dealer decidiu parar."
     :score            "pontos."}))

(defn player-name
  "Returns the name of the player."
  {:added "1.0.0"}
  []
  (println (:player-name (localize)))
  (read-line))

(defn player-decision?
  "Returns true if player wants a new card."
  {:added "1.0.0"}
  []
  (println (:player-decision (localize)))
  (let [answer (read-line)]
    (or (= answer "y")
        (= answer "Y")
        (= answer "s")
        (= answer "S"))))

(defn print-players
  "Prints the players."
  {:added "1.0.0"}
  [players, masked-dealer?]
  (println "--------------")
  (b.graphics/print-player (:dealer players) masked-dealer?)
  (println (if masked-dealer? "X" (:score (:dealer players))) (:score (localize)))
  (println "--------------")
  (b.graphics/print-player (:player players) false)
  (println (:score (:player players)) (:score (localize)))
  (println "--------------"))


(defn dealer-decision-output
  "Returns an output if the Dealer chose to continue or not."
  {:added "1.0.0"}
  [continues?]
  (if continues?
    (println (:dealer-continues (localize)))
    (println (:dealer-stops (localize)))))

(defn wins-output
  "Prints the player wins."
  {:added "1.0.0"}
  [name]
  (println name (:wins (localize))))