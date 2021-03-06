(ns blackjack.game
  (:require [blackjack.engine :as b.engine]
            [blackjack.ui :as b.ui]))

(def win-score-limiar 21)
(def dealer-name "Dealer")

(defn- dealer-decision?
  "Returns an output if the Dealer chose to continue or not."
  {:added "1.0.0"}
  [dealer-score player-score]
  (not (or (> dealer-score player-score) (> dealer-score win-score-limiar))))

(defn- win
  "Returns the winner of the game."
  {:added "1.0.0"}
  [players]
  (let [player (:player players)
        dealer (:dealer players)
        player-score (b.engine/score (:hand player))
        dealer-score (b.engine/score (:hand dealer))]
    (cond
      (= player-score dealer-score) dealer
      (and (> player-score win-score-limiar) (> dealer-score win-score-limiar)) (if (< player-score dealer-score) player dealer)
      (and (> player-score win-score-limiar) (<= dealer-score win-score-limiar)) dealer
      (and (<= player-score win-score-limiar) (> dealer-score win-score-limiar)) player
      (and (<= player-score win-score-limiar) (< dealer-score win-score-limiar)) (if (> player-score dealer-score) player dealer)
      :else dealer)))


(defn- dealer-continues?
  "Returns true if dealer should continue."
  {:added "1.0.0"}
  [players dealer-decision?]
  (let [player-score (b.engine/score (:hand (:player players)))
        dealer-score (b.engine/score (:hand (:dealer players)))]
    (let [continues? (dealer-decision? dealer-score player-score)]
      (b.ui/dealer-decision-output continues?)
      continues?)))

(defn- player-continues?
  "Returns true if player should continue."
  {:added "1.0.0"}
  [players]
  (and (< (b.engine/score (:hand (:player players))) win-score-limiar) (b.ui/player-decision?)))

(defn- deal
  "Deals cards to player."
  {:added "1.0.0"}
  [player]
  (b.engine/deal-card player))

(defn- check-score
  "Checks the score of the players."
  {:added "1.0.0"}
  [players]
  (b.ui/wins-output (:name (win players))))

(defn- end-game
  "Ends the game."
  {:added "1.0.0"}
  [players]
  (b.ui/print-players players false)
  (check-score players))

(defn- game-loop
  "Starts the game loop."
  {:added "1.0.0"}
  [players masked?]
  (b.ui/print-players players masked?)
  (let [player-decision? (and (:decision (:player players)) (player-continues? players))
        dealer-decision? (and (:decision (:dealer players)) (dealer-continues? players dealer-decision?))
        player (assoc (:player players) :decision player-decision?)
        dealer (assoc (:dealer players) :decision dealer-decision?)]
    (if
      (or player-decision? dealer-decision?)
      (game-loop (assoc players
                   :player (if player-decision? (deal player) player)
                   :dealer (if dealer-decision? (deal dealer) dealer)) masked?)
      (end-game players))))

(defn start-game
  "Starts a new game."
  {:added "1.0.0"}
  ([] (start-game true))
  ([masked?]
   (let [player (b.engine/new-player (b.ui/player-name))
         dealer (b.engine/new-player dealer-name)
         players {:dealer dealer :player player}]
     (game-loop players masked?))))


