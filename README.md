# blackjack

A Blackjack clojure console game.

## Game Rules

[consultar regras](/doc/intro.md)

## Game structure

The overall game structure is as follows:

- **core:** entry point for the game, runs the game logic.
- **game:** mantains the game logic.
- **engine:** logic for card dealing and player creation.
- **ui:** user interface.
- **graphics:** graphics for cards.

# Game

![game-image](/images/game.png)

The game is played in a single player mode. You are against the Dealer.
Every turn you can choose to deal a card or to pass, and the dealer chooses to himself as well.

In the end, the player with the score closest to 21 wins.

## License

Copyright © 2022

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
