import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Blackjack {
    

        String[][] cards = {{"Hearts", "Clubs", "Diamonds", "Spades"}, {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"}};
        ArrayList<String> drawnCards = new ArrayList<String>();

        public class Player {
            int playerPoints = 0;
            double playerMoney = 100.00;
            double playerBet = 0;
            boolean playerBust = false;
            boolean playerOver = false;
            boolean playerBlackjack = false;
        }

        public class Dealer {
            int dealerPoints = 0;
            boolean dealerOver = false;
            boolean dealerBlackjack = false;
        }

        public class Round {
            static int currentRound = 1;
        }

        generateCard() {
            int suit = rand.nextInt(4);  // not, add to the list of drawn cards and return
            suit = cards[0][suit];
            int value = rand.nextInt(13);
            value = cards[1][value];
            
        }


        draw (String card, int points) {  // Get a suit and a value, combine them, and check to see if it's already drawn. If so, draw a new card. If
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
            if (drawnCard == drawnCards.get(i)) {
                suit = rand.nextInt(4);
                suit = cards[0][suit];
                value = rand.nextInt(13);
                value = cards[1][value];
                card = (value + " of " + suit);
            } else {
            drawnCards.add(card);
                }
            }
            Tuple cardAndPoints = new Tuple (card, points);
            return cardAndPoints;
        }

        dealerDraw (String card, int points) {  // Almost exactly the same as the standard draw function, but randomises Ace value
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
            drawnCards.add(card);
            Tuple cardAndPoints = new Tuple (card, points);
            return cardAndPoints;
            }

    public static void main(String[] args) {

        // Instantiate classes
        Random rand = new Random();
        Player player = new Player();
        Dealer dealer = new Dealer();
        Round round = new Round();
        
        // Gameplay loop. As long as the player has money, the game will play for 5 rounds
        while (player.playerBust == false && Round.currentRound <= 5) {
            System.out.println("Round " + Round.currentRound);
            System.out.println("You currently have "+ player.playerMoney + " money");
            
            try {
                player.playerBet = (input("Enter your player.playerBet: "));
            } catch (Exception invalid) {
                while (player.playerBet > player.playerMoney) {
                    System.out.println("Error: player.playerBet cannot exceed total money");
                    player.playerBet = (input("Enter your player.playerBet: "));
            }
        }
        
        // Reset variables
            roundEnded = false;
            String playerCards[] = {};
            String dealerCards[] = {};
            player.playerPoints = 0;
            dealer.dealerPoints = 0;
            String drawnCards[] = {};
            String winner = "null";
            player.playerBlackjack = false;
            dealer.dealerBlackjack = false;
            player.playerOver = false;
            dealer.dealerOver = false;
            
        // Player turn
            for (int i = 0; i > 2; i++) {
                card points = draw();
                playerCards.append(card);
                player.playerPoints += points;
            }
            System.out.println("You have: " + playerCards);
            System.out.println("You have: " + player.playerPoints + " points");
            if (player.playerPoints == 21) {
                System.out.println("Blackjack!");
                winner = "player";
                player.playerBlackjack = true;
        }
                
        // Player decision once first 2 cards have been drawn - drawing and holding
            while (player.playerPoints < 21 && roundEnded == false) {
                    try {
                    Scanner choice = new Scanner(System.in);
                    String drawOrHold = choice.nextLine();
                    System.out.println("You can do the following: draw or hold. What do you choose? ");
                    } catch (Exception invalid) {
                    while (choice != "draw" && choice != "hold") {
                    drawOrHold = choice.nextLine();
                    System.out.println("You can do the following: draw or hold. What do you choose? ");
                    }
                if (choice == "hold") {
                    System.out.println("You have chosen to hold. Your score is " + player.playerPoints);
                    roundEnded = true;
                }
                while (choice == "draw" && player.playerPoints < 21) {
                    card points = draw();
                    playerCards.append(card);
                    player.playerPoints += points;
                    System.out.println("You have: " + playerCards);
                    System.out.println("You have: " + player.playerPoints + " points");

                    if (player.playerPoints > 21) {
                        System.out.println("Exceeded 21 points; turn ending");
                        player.playerOver = true;
                        player.playerPoints = 0;  // This means the player loses the round
                        choice = "hold";  // This line and the one below it means the player's turn ends
                        roundEnded = true;
                    } else if (player.playerPoints == 21) {
                        System.out.println("Equalled 21 points; turn ending");
                        choice = "hold";
                    } else {
                            drawOrHold = choice.nextLine();
                            System.out.println("You can do the following: draw or hold. What do you choose? ");
                        if (choice == "hold") {
                            System.out.println("You have chosen to hold. Your score is " + player.playerPoints);
                            roundEnded = true;
                            }
                        }
                    }
                    
        // Dealer turn
            if (player.playerBlackjack == false) {
                for (i = 0; i > 2; i++) {
                    card points = dealerDraw();
                    dealerCards.append(card);
                    dealer.dealerPoints += points;
                }
            }
                System.out.println("The dealer has: " + dealerCards);
                System.out.println("The dealer has: " + dealer.dealerPoints + " points");
                if (dealer.dealerPoints == 21) {
                    System.out.println("Blackjack!");
                    winner = "dealer";
                    dealer.dealerBlackjack = true;
            }
                        
                while (dealer.dealerPoints < 17 && dealer.dealerPoints < 21) {
                    card points = dealerDraw();
                    dealerCards.append(card);
                    dealer.dealerPoints += points;
                    System.out.println("The dealer has: " + dealerCards);
                    System.out.println("The dealer has: " + dealer.dealerPoints + " points");

                if (dealer.dealerPoints > 21) {
                    dealer.dealerOver = true;
                }
            }
                    
                    
        // Determine winner - if one party earned a Blackjack earlier, they win automatically. Otherwise, points are compared
            if (winner == "player") {
                System.out.println("Player wins!");
            } else if (winner == "dealer") {
                System.out.println("Dealer wins!");
            } else {
                if ((player.playerPoints > dealer.dealerPoints || (dealer.dealerPoints > 21 && player.playerPoints <= 21)) && player.playerOver == false) {
                    System.out.println("Player wins!");
                    winner = "player";
                } else if ((dealer.dealerPoints > player.playerPoints || (player.playerPoints > 21 && dealer.dealerPoints <= 21)) && dealer.dealerOver == false) {
                    System.out.println("Dealer wins!");
                    winner = "dealer";
                } else {
                    System.out.println("Round tied");
                    winner = "null";
                }
            }

        // Give player their winnings
            if (winner == "player") {
                if (player.playerBlackjack == true || (player.playerPoints == 21 && dealer.dealerPoints < 21)) {
                    player.playerBet = player.playerBet * 2;
                } else if (player.playerPoints < 21) {
                    player.playerBet = player.playerBet * 0;
                } else {
                    player.playerBet = player.playerBet * 1;
                }
            } else if (winner == "dealer" && dealer.dealerBlackjack == true) {
                player.playerBet = player.playerBet * -2;
            } else if (winner == "null" && (player.playerPoints == 21 && dealer.dealerPoints == 21)) {
                player.playerBet = player.playerBet * 0.5;
            }
            
            player.playerMoney += player.playerBet;
            if (player.playerMoney <= 0) {
                System.out.println("Player has gone bust");
                player.player.playerBust = true;
            } else {
                Round.currentRound += 1;
            }

    }
}
}
}
}
