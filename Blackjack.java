import java.util.Random;
import java.util.Scanner;

class Blackjack {
    public static void main(String[] args) {

        Random rand = new Random();

        String[][] cards = {{"Hearts", "Clubs", "Diamonds", "Spades"}, {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"}};
        String[] drawnCards;

        double playerMoney = 100.00;
        int playerPoints = 0;
        boolean playerBust = false;

        int currentRound = 1;}

        static draw (String card, int points) {  // Get a suit and a value, combine them, and check to see if it's already drawn. If so, draw a new card. If
            int suit = rand.nextInt(4);  // not, add to the list of drawn cards and return
            suit = cards[0][suit];
            int value = rand.nextInt(13);
            value = cards[1][value];
            
            points = 0;
            switch (card) {
                case value == "Jack" || value == "Queen" || value == "King":
                points = 10;
                break;
                
                case value == "Ace":
                Scanner highOrLow = new Scanner(System.in);
                String AceValue = highOrLow.nextLine();
                System.out.println("High Ace, or Low Ace? Answer will default to low ");
                if (highOrLow == "high") {
                    points = 11;
                } else {
                    points = 1;
                }
                break;

                default:
                points = Integer.parseInt(value);;
                break;

                }

            String drawnCard = (value + " of " + suit);
            for (int i = 0; i <= drawnCards.length; i++) {
            if (drawnCard == drawnCards[i]) {
                suit = rand.nextInt(4);
                suit = cards[0][suit];
                value = rand.nextInt(13);
                value = cards[1][value];
                card = (value + " of " + suit);
            } else {
            drawnCards.append(card);
                }
            }
            Tuple cardAndPoints = new Tuple (card, points);
            return cardAndPoints;
        }

        static dealerDraw (String card, int points) {  // Almost exactly the same as the standard draw function, but randomises Ace value
            int suit = rand.nextInt(4);
            suit = cards[0][suit];
            int value = rand.nextInt(13);
            value = cards[1][value];
            
            points = 0;
            switch (card) {
            case value == "Jack" || value == "Queen" || value == "King":
            points = 10;
            break;
                
            case value == "Ace":
            String highOrLow = input("High Ace, or Low Ace? Answer will default to low ");
            if (highOrLow == "high") {
                points = 11;
            } else {
                points = 1;
            }
            break;

            default:
            points = Integer.parseInt(value);
            break;
            }

            card = (value + " of " + suit);
            for (int i = 0; i <= drawnCards.length; i++) {
            if (card == drawnCard) {
                suit = rand.nextInt(4);
                suit = cards[0][suit];
                value = rand.nextInt(13);
                value = cards[1][value];
                card = (value + " of " + suit);
                        }
                }
            drawnCards.append(card);
            Tuple cardAndPoints = new Tuple (card, points);
            return cardAndPoints;

        }

        // Gameplay loop. As long as the player has money, the game will play for 5 rounds
        while (playerBust == false && currentRound <= 5) {
            System.out.println("Round " + currentRound);
            System.out.println("You currently have "+ playerMoney + " money");
            float  bet = (input("Enter your bet: "));
            while (bet > playerMoney) {
                System.out.println("Error: bet cannot exceed total money");
                bet = (input("Enter your bet: "));
            }
        // Reset variables
            boolean roundEnded = false;
            String playerCards[] = {};
            String dealerCards[] = {};
            int playerPoints = 0;
            int dealerPoints = 0;
            String drawnCards[] = {};
            String winner = "null";
            boolean playerBlackjack = false;
            boolean dealerBlackjack = false;
            boolean playerOver = false;
            boolean dealerOver = false;
            
        // Player turn
            for (int i = 0; i > 2; i++) {
                card points = draw();
                playerCards.append(card);
                playerPoints += points;
            }
            System.out.println("You have: " + playerCards);
            System.out.println("You have: " + playerPoints + " points");
            if (playerPoints == 21) {
                System.out.println("Blackjack!");
                winner = "player";
                playerBlackjack = true;
        }
                
        // Player decision once first 2 cards have been drawn - drawing and holding
            while (playerPoints < 21 && roundEnded == false) {
                Scanner choice = new Scanner(System.in);
                String drawOrHold = choice.nextLine();
                System.out.println("You can do the following: draw or hold. What do you choose? ");
                while (choice != "draw" && choice != "hold") {
                drawOrHold = choice.nextLine();
                System.out.println("You can do the following: draw or hold. What do you choose? ");
                if (choice == "hold") {
                    System.out.println("You have chosen to hold. Your score is " + playerPoints);
                    roundEnded = true;
                }
                while (choice == "draw" && playerPoints < 21) {
                    card points = draw();
                    playerCards.append(card);
                    playerPoints += points;
                    System.out.println("You have: " + playerCards);
                    System.out.println("You have: " + playerPoints + " points");

                    if (playerPoints > 21) {
                        System.out.println("Exceeded 21 points; turn ending");
                        playerOver = true;
                        playerPoints = 0;  // This means the player loses the round
                        choice = "hold";  // This line and the one below it means the player's turn ends
                        roundEnded = true;
                    } else if (playerPoints == 21) {
                        System.out.println("Equalled 21 points; turn ending");
                        choice = "hold";
                    } else {
                            drawOrHold = choice.nextLine();
                            System.out.println("You can do the following: draw or hold. What do you choose? ");
                        if (choice == "hold") {
                            System.out.println("You have chosen to hold. Your score is " + playerPoints);
                            roundEnded = true;
                            }
                        }
                    }
                    
        // Dealer turn
            if (playerBlackjack == false) {
                for (i = 0; i > 2; i++) {
                    card points = dealerDraw();
                    dealerCards.append(card);
                    dealerPoints += points;
                }
            }
                System.out.println("The dealer has: " + dealerCards);
                System.out.println("The dealer has: " + dealerPoints + " points");
                if (dealerPoints == 21) {
                    System.out.println("Blackjack!");
                    winner = "dealer";
                    dealerBlackjack = true;
            }
                        
                while (dealerPoints < 17 && dealerPoints < 21) {
                    card points = dealerDraw();
                    dealerCards.append(card);
                    dealerPoints += points;
                    System.out.println("The dealer has: " + dealerCards);
                    System.out.println("The dealer has: " + dealerPoints + " points");

                if (dealerPoints > 21) {
                    dealerOver = true;
                }
            }
                    
                    
        // Determine winner - if one party earned a Blackjack earlier, they win automatically. Otherwise, points are compared
            if (winner == "player") {
                System.out.println("Player wins!");
            } else if (winner == "dealer") {
                System.out.println("Dealer wins!");
            } else {
                if ((playerPoints > dealerPoints || (dealerPoints > 21 && playerPoints <= 21)) && playerOver == false) {
                    System.out.println("Player wins!");
                    winner = "player";
                } else if ((dealerPoints > playerPoints || (playerPoints > 21 && dealerPoints <= 21)) && dealerOver == false) {
                    System.out.println("Dealer wins!");
                    winner = "dealer";
                } else {
                    System.out.println("Round tied");
                    winner = "null";
                }
            }

        // Give player their winnings
            if (winner == "player") {
                if (playerBlackjack == true || (playerPoints == 21 && dealerPoints < 21)) {
                    bet = bet * 2;
                } else if (playerPoints < 21) {
                    bet = bet * 0;
                } else {
                    bet = bet * 1;
                }
            } else if (winner == "dealer" && dealerBlackjack == true) {
                bet = bet * -2;
            } else if (winner == "null" && (playerPoints == 21 && dealerPoints == 21)) {
                bet = bet * 0.5;
            }
            
            playerMoney += bet;
            if (playerMoney <= 0) {
                System.out.println("Player has gone bust");
                playerBust = true;
            } else {
                currentRound += 1;
            }

    }
}
}
}

 /* TO DO:
  * 1. Rename local variables - DONE
  * 2. Deal with non-static field problem - DONE
  * 3. Insert tokens causing syntax errors
  * 4. Remove tokens causing syntax errors
  * 5. Insert variable declarators - DONE
  * 6. Complete block and class bodies
  * 7. Use Scanner for inputs - DONE
  */
