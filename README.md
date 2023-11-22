# Blackjack
An attempt at making blackjack in Python, Java and C

Inspired by a COMP228 assignment, which asked me to do this in Swift (not uploaded at this point in time to avoid breaching Academic Integrity). This is Pontoon style blackjack, as opposed to Crazy Eights

Here are the rules:
1. The player starts with a betting pool of 100. The game lasts for 5 rounds, or until the player's betting pool reaches 0 or lower. The game uses a standard 52 card deck
2. The aim of the game is to reach as close to the number 21 as possible without exceeding it. Whoever does so wins the round
3. Each number card is worth the number on the card. All picture cards are worth 10 points, apart from the Ace. The Ace is worth 1 point or 11, determined by user input
4. The player goes first. They place a bet and are then dealt two cards, and then choose whether to draw more cards or stay with what they have. If the former, another card is given and the player is asked again until the latter is chosen. Choosing to fold ends the player's turn
5. The dealer is dealt two cards, and must draw to a minimum of 17 before being allowed to fold
6. If the player wins, they receive 150% of their stake. If they draw a Blackjack (picture card/10 + Ace), they receive 200% of their stake
7. If the dealer wins, the player loses their stake.
8. If the round is tied, the player's stake is returned to them and the next round begins (assuming the tied round was not the last)

This is the same game made in three languages as a way to test myself on the three. I am starting with Python because it's the simplest, and the language I have the most experience with. I plan to implement the game in Java next
