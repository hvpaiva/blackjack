(ns blackjack.engine)

(defn- ace
  "Return value of ace."
  {:added "1.0.0"}
  [card]
  (if (= card 1) "A" card))

(defn- king
  "Return value of king."
  {:added "1.0.0"}
  [card]
  (if (= card 13) "K" card))

(defn- queen
  "Return value of queen."
  {:added "1.0.0"}
  [card]
  (if (= card 12) "Q" card))

(defn- jack
  "Return value of jack."
  {:added "1.0.0"}
  [card]
  (if (= card 11) "J" card))

(defn- new-card
  "Creates a randomic new card between 1 and 13."
  {:added "1.0.0"}
  []
  (let [card (inc (rand-int 13))]
    (case card
      1 (ace card)
      13 (king card)
      12 (queen card)
      11 (jack card)
      card)))

(defn- initial-hand
  "Creates a hand with two cards."
  {:added "1.0.0"}
  []
  [(new-card) (new-card)])

(defn- face-card?
  "Returns true if card is a face card."
  {:added "1.0.0"}
  [card]
  (or (= card "K") (= card "Q") (= card "J")))

(defn- ace-card?
  "Returns true if card is a acce card."
  {:added "1.0.0"}
  [card]
  (= card "A"))

(defn- face-card-as-ten
  "Normalize a face card to always be 10."
  {:added "1.0.0"}
  [card]
  (if (face-card? card) 10 card))

(defn- ace-cards-as-eleven
  "Normalize an ace card to be 11."
  {:added "1.0.0"}
  [card]
  (if (ace-card? card) 11 card))

(defn- ace-cards-as-one
  "Normalize an ace card to be 1."
  {:added "1.0.0"}
  [card]
  (if (ace-card? card) 1 card))

(defn- hand-as-numbers
  "Formats a hand to consider the cards as numbers."
  {:added "1.0.0"}
  [hand, ace-as-one?]
  (->>
    (map face-card-as-ten hand)
    (map (if ace-as-one? ace-cards-as-one ace-cards-as-eleven))))

(defn- total-hand
  "Calculates the total of a hand."
  {:added "1.0.0"}
  [hand, ace-as-one?]
  (reduce + (hand-as-numbers hand ace-as-one?)))

(defn score
  "Define the score of a player. By summing the value of the hand."
  {:added "1.0.0"}
  [hand]
  (let [total-with-ace-eleven (total-hand hand false)
        total-with-ace-one (total-hand hand true)]
    (if (> total-with-ace-eleven 21) total-with-ace-one total-with-ace-eleven)))

(defn new-player
  "Creates a new player with a hand and a score."
  {:added "1.0.0"}
  [name]
  (let [hand (initial-hand)
        score (score hand)]
    {:name  name
     :hand  hand
     :score score
     :decision true}))

(defn deal-card
  "Adds a new card to a player's hand."
  {:added "1.0.0"}
  [player]
  (let [card (new-card)
        new-cards (conj (:hand player) card)]
    (assoc player :hand new-cards :score (score new-cards))))