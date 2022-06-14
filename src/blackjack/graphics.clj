(ns blackjack.graphics)

(defn- random-symbol
  "Returns a random symbol from the list of symbols."
  []
  (get ["♠" "♦" "♥" "♣"] (rand-int 4)))

(defn- break-line
  "Returns a string with a line break."
  []
  (println ""))

(defn- top
  "Returns a string with the top of the card."
  []
  (print "┌───────┐"))

(defn- bottom
  "Returns a string with the bottom of the card."
  []
  (print "└───────┘"))

(defn- number-part-top
  "Returns a string with the top of the number part of the card."
  [card]
  (cond
    (= card "X") (print "│░░░░░░░│")
    (= card (or 1 "A")) (print "│A      │")
    (= card 10) (print "│10     │")
    (= card (or 11 "J")) (print "│J      │")
    (= card (or 12 "Q")) (print "│Q      │")
    (= card (or 13 "K")) (print "│K      │")
    :else (print (format "│%s      │" card))))

(defn- number-part-bottom
  "Returns a string with the bottom of the number part of the card."
  [card]
  (cond
    (= card "X") (print "│░░░░░░░│")
    (= card (or 1 "A")) (print "│      A│")
    (= card 10) (print "│     10│")
    (= card (or 11 "J")) (print "│      J│")
    (= card (or 12 "Q")) (print "│      Q│")
    (= card (or 13 "K")) (print "│      K│")
    :else (print (format "│      %s│" card))))

(defn- symbol-part
  "Returns a string with the symbol part of the card."
  [card]
  (case card
    "X" (print "│░░░░░░░│")
    (print (format "│   %s   │" (random-symbol)))))

(defn- mask-first-card
  "Masks the first card of the vector"
  [cards]
  (apply vector (-> list
                    (apply (subvec cards 1))
                    (conj "X"))))

(defn- show-masked
  "Returns player masked"
  [player]
  (-> player
      (update :hand mask-first-card)
      (assoc :score "X")))

(defn print-cards
  "Prints the cards of the player."
  [cards]
  (let [quantity-cards (count cards)]
    (dotimes [n quantity-cards]
      (top))
    (break-line)
    (dotimes [n quantity-cards]
      (number-part-top (get cards n)))
    (break-line)
    (dotimes [n quantity-cards]
      (symbol-part (get cards n)))
    (break-line)
    (dotimes [n quantity-cards]
      (number-part-bottom (get cards n)))
    (break-line)
    (dotimes [n quantity-cards]
      (bottom))
    (break-line)))

(defn print-card
  "Prints the card of the player."
  [card]
  (print-cards [card]))

(defn print-player
  "Prints the player."
  [player masked?]
  (let [masked-player (if masked? (show-masked player) player)
        player-name (:name masked-player)
        cards (:hand masked-player)]
    (println player-name)
    (print-cards cards)))