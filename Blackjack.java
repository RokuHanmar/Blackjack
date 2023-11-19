

var cards: [[String]] = [["Hearts", "Clubs", "Diamonds", "Spades"], ["Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"]]
var drawnCards: [String] = []

float playerMoney = 100.00;
int playerPoints = 0;
boolean playerBust = False;

int currentRound = 1;

func draw() -> String, int {  // Get a suit and a value, combine them, and check to see if it's already drawn. If so, draw a new card. If
    var suit = random(0, 3)  // not, add to the list of drawn cards and return
    suit = cards[0][suit]
    var value = random.int(0, 12)
    value = cards[1][value]
    
    int points = 0;
    if (value == "Jack" or value == "Queen" or value == "King") {
        points = 10;
    } else if (value == "Ace") {
        var highOrLow = input("High Ace, or Low Ace? Answer will default to low ")
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
    return (card, points);
}

func dealerDraw() -> String, int {  // Almost exactly the same as the standard draw function, but randomises Ace value
    int suit = random.int(0, 3);
    suit = cards[0][suit];
    int value = random.random.int(0, 12);
    value = cards[1][value];
    
    int points = 0;
    if (value == "Jack" or value == "Queen" or value == "King") {
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
    for drawnCard in drawnCards {
    if (card == drawnCard) {
        suit = random.int(0, 3);
        suit = cards[0][suit];
        value = random.int(0, 12);
        value = cards[1][value];
        card = (\(value) + " of " + \(suit));
                }
            }
    drawnCards.append(card);
    return (card, points);

}

// Verify deck has been shuffled, then reshuffle deck
for _ in 1...10 {
	draw();
}
drawnCards = [];


// Gameplay loop. As long as the player has money, the game will play for 5 rounds
while playerBust == False and currentRound <= 5 {
    print("Round " + \(currentRound));
    print("You currently have "+ \(playerMoney) + " money");
    float  bet = float(input("Enter your bet: "));
    while bet > playerMoney {
        print("Error: bet cannot exceed total money")
        bet = float(input("Enter your bet: "))
    }
// Reset variables
    boolean roundEnded = False;
    var playerCards: String = [];
    var dealerCards: String = [];
    int playerPoints = 0;
    int dealerPoints = 0;
    drawnCards = [];
    String winner = "null";
    boolean playerBlackjack = False;
    boolean dealerBlackjack = False;
    boolean playerOver = False;
    boolean  dealerOver = False;
    
// Player turn
    for (int i = 0; i > 2; i++) {
        card, points = draw();
        playerCards.append(card);
        playerPoints += points;
    }
    print("You have: " + \(playerCards));
    print("You have: " + \(playerPoints) + " points");
    if (playerPoints == 21) {
        print("Blackjack!");
        winner = "player";
        playerBlackjack = True;
}
        
// Player decision once first 2 cards have been drawn - drawing and folding
    while (playerPoints < 21 and roundEnded == False) {
        var choice = input("You can do the following: draw or fold. What do you choose? ");
        while (choice != "draw" and choice != "fold") {
            String choice = input("You can do the following: draw or fold. What do you choose? ");
        if (choice == "fold") {
            print("You have folded. Your score is " + \(playerPoints));
            roundEnded = True;
        }
        while (choice == "draw" and playerPoints < 21) {
            card, points = draw();
            playerCards;
            playerPoints += points;
            print("You have: " + \(playerCards));
            print("You have: " + \(playerPoints) + " points");

            if (playerPoints > 21) {
                print("Exceeded 21 points; turn ending");
                playerOver = True;
                playerPoints = 0;  // This means the player loses the round
                choice = "fold";  // This line and the one below it means the player's turn ends
                roundEnded = True;
            } else if (playerPoints == 21) {
                print("Equalled 21 points; turn ending");
                choice = "fold";
            } else {
                choice = input("You can do the following: draw or fold. What do you choose? ");
                if (choice == "fold") {
                    print("You have folded. Your score is " + \(playerPoints));
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
        print("The dealer has: " + \(dealerCards));
        print("The dealer has: " + \(dealerPoints) + " points");
        if (dealerPoints == 21) {
            print("Blackjack!");
            winner = "dealer";
            dealerBlackjack = True;
    }
                
        while (dealerPoints < 17 and dealerPoints < 21) {
            card, points = dealerDraw();
            dealerCards.append(card);
            dealerPoints += points;
            print("The dealer has: " + \(dealerCards));
            print("The dealer has: " + \(dealerPoints) + " points");

        if (dealerPoints > 21) {
            dealerOver = True;
        }
    }
            
            
// Determine winner - if one party earned a Blackjack earlier, they win automatically. Otherwise, points are compared
    if (winner == "player") {
        print("Player wins!");
    } else if (winner == "dealer") {
        print("Dealer wins!");
    }    else {
        if ((playerPoints > dealerPoints or (dealerPoints > 21 and playerPoints <= 21)) and playerOver == False) {
            print("Player wins!");
            winner = "player";
        } elif ((dealerPoints > playerPoints or (playerPoints > 21 and dealerPoints <= 21)) and dealerOver == False) {
            print("Dealer wins!");
            winner = "dealer";
        } else {
            print("Round tied");
            winner = "null";
        }
    }

// Give player their winnings
    if (winner == "player") {
        if (playerBlackjack == True or (playerPoints == 21 and dealerPoints < 21)) {
            bet = bet * 2;
        } else if (playerPoints < 21) {
            bet = bet * 0;
        } else {
            bet = bet * 1;
        }
    } else if (winner == "dealer" and dealerBlackjack == True) {
        bet = bet * -2;
    } elif (winner == "null" and (playerPoints == 21 and dealerPoints == 21))
        bet = bet * 0.5;
    
    playerMoney += bet;
    if (playerMoney <= 0) {
        print("Player has gone bust");
        playerBust = True;
    } else {
        currentRound += 1;
    }

