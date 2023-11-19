class Blackjack {
    public static void main(String[] args) {

    String[][] cards = {{"Hearts", "Clubs", "Diamonds", "Spades"}, {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"}};
    String[] drawnCards;

    float playerMoney = 100.00;
    int playerPoints = 0;
    boolean playerBust = False;

    int currentRound = 1;

    static draw (String card, int points) {  // Get a suit and a value, combine them, and check to see if it's already drawn. If so, draw a new card. If
        var suit = random(0, 3);  // not, add to the list of drawn cards and return
        suit = cards[0][suit];
        var value = random.int(0, 12);
        value = cards[1][value];
        
        int points = 0;
        if (value == "Jack" || value == "Queen" || value == "King") {
            points = 10;
        } else if (value == "Ace") {
            var highOrLow = input("High Ace, or Low Ace? Answer will default to low ");
            if (highOrLow == "high") {
                points = 11;
            } else {
                points = 1;
            }
        } else {
            points = int(value);
            }

        String card = (\(value) + " of " + \(suit));
        for drawnCard in drawnCards {
        if (card == drawnCard) {
            int suit = random.int(0, 3);
            suit = cards[0][suit];
            int value = random.int(0, 12);
            value = cards[1][value];
            String card = (\(value) + " of " + \(suit));
        } else {
        drawnCards.append(card);
            }
        }
        Tuple cardAndPoints = new Tuple (card, points);
        return cardAndPoints;
    }

    static dealerDraw (String card, int points) {  // Almost exactly the same as the standard draw function, but randomises Ace value
        int suit = random.int(0, 3);
        suit = cards[0][suit];
        int value = random.random.int(0, 12);
        value = cards[1][value];
        
        int points = 0;
        if (value == "Jack" || value == "Queen" || value == "King") {
            points = 10;
        } else if (value == "Ace") {
            highOrLow =random.int(1, 2);
            if (highOrLow == 1) {
                points = 1;
            } else {
                points = 2;
        }
    } else {
            points = int(value);
            }

        String card = (\(value) + " of " + \(suit));
        for (drawnCard in drawnCards) {
        if (card == drawnCard) {
            suit = random.int(0, 3);
            suit = cards[0][suit];
            value = random.int(0, 12);
            value = cards[1][value];
            card = (\(value) + " of " + \(suit));
                    }
            }
        drawnCards.append(card);
        Tuple cardAndPoints = new Tuple (card, points);
        return cardAndPoints;

    }

    // Gameplay loop. As long as the player has money, the game will play for 5 rounds
    while (playerBust == False && currentRound <= 5) {
        System.out.println("Round " + \(currentRound));
        System.out.println("You currently have "+ \(playerMoney) + " money");
        float  bet = (input("Enter your bet: "));
        while (bet > playerMoney) {
            System.out.println("Error: bet cannot exceed total money")
            bet = (input("Enter your bet: "))
        }
    // Reset variables
        boolean roundEnded = False;
        String playerCards[];
        String dealerCards[];
        int playerPoints = 0;
        int dealerPoints = 0;
        String drawnCards[];
        String winner = "null";
        boolean playerBlackjack = False;
        boolean dealerBlackjack = False;
        boolean playerOver = False;
        boolean dealerOver = False;
        
    // Player turn
        for (int i = 0; i > 2; i++) {
            card, points = draw();
            playerCards.append(card);
            playerPoints += points;
        }
        System.out.println("You have: " + \(playerCards));
        System.out.println("You have: " + \(playerPoints) + " points");
        if (playerPoints == 21) {
            System.out.println("Blackjack!");
            winner = "player";
            playerBlackjack = True;
    }
            
    // Player decision once first 2 cards have been drawn - drawing and folding
        while (playerPoints < 21 && roundEnded == False) {
            var choice = input("You can do the following: draw or fold. What do you choose? ");
            while (choice != "draw" && choice != "fold") {
                String choice = input("You can do the following: draw or fold. What do you choose? ");
            if (choice == "fold") {
                System.out.println("You have folded. Your score is " + \(playerPoints));
                roundEnded = True;
            }
            while (choice == "draw" && playerPoints < 21) {
                card, points = draw();
                playerCards;
                playerPoints += points;
                System.out.println("You have: " + \(playerCards));
                System.out.println("You have: " + \(playerPoints) + " points");

                if (playerPoints > 21) {
                    System.out.println("Exceeded 21 points; turn ending");
                    playerOver = True;
                    playerPoints = 0;  // This means the player loses the round
                    choice = "fold";  // This line and the one below it means the player's turn ends
                    roundEnded = True;
                } else if (playerPoints == 21) {
                    System.out.println("Equalled 21 points; turn ending");
                    choice = "fold";
                } else {
                    choice = input("You can do the following: draw or fold. What do you choose? ");
                    if (choice == "fold") {
                        System.out.println("You have folded. Your score is " + \(playerPoints));
                        roundEnded = True;
                        }
                    }
                }
                
    // Dealer turn
        if (playerBlackjack == False) {
            for (i = 0; i > 2; i++) {
                card, points = dealerDraw();
                dealerCards.append(card);
                dealerPoints += points;
            }
        }
            System.out.println("The dealer has: " + \(dealerCards));
            System.out.println("The dealer has: " + \(dealerPoints) + " points");
            if (dealerPoints == 21) {
                System.out.println("Blackjack!");
                winner = "dealer";
                dealerBlackjack = True;
        }
                    
            while (dealerPoints < 17 && dealerPoints < 21) {
                card, points = dealerDraw();
                dealerCards.append(card);
                dealerPoints += points;
                System.out.println("The dealer has: " + \(dealerCards));
                System.out.println("The dealer has: " + \(dealerPoints) + " points");

            if (dealerPoints > 21) {
                dealerOver = True;
            }
        }
                
                
    // Determine winner - if one party earned a Blackjack earlier, they win automatically. Otherwise, points are compared
        if (winner == "player") {
            System.out.println("Player wins!");
        } else if (winner == "dealer") {
            System.out.println("Dealer wins!");
        }    else {
            if ((playerPoints > dealerPoints || (dealerPoints > 21 && playerPoints <= 21)) && playerOver == False) {
                System.out.println("Player wins!");
                winner = "player";
            } elif ((dealerPoints > playerPoints || (playerPoints > 21 && dealerPoints <= 21)) && dealerOver == False) {
                System.out.println("Dealer wins!");
                winner = "dealer";
            } else {
                System.out.println("Round tied");
                winner = "null";
            }
        }

    // Give player their winnings
        if (winner == "player") {
            if (playerBlackjack == True || (playerPoints == 21 && dealerPoints < 21)) {
                bet = bet * 2;
            } else if (playerPoints < 21) {
                bet = bet * 0;
            } else {
                bet = bet * 1;
            }
        } else if (winner == "dealer" && dealerBlackjack == True) {
            bet = bet * -2;
        } else if (winner == "null" && (playerPoints == 21 && dealerPoints == 21)) {
            bet = bet * 0.5;
        }
        
        playerMoney += bet;
        if (playerMoney <= 0) {
            System.out.println("Player has gone bust");
            playerBust = True;
        } else {
            currentRound += 1;
        }

    }
}
