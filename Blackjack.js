const cards = [["Hearts", "Clubs", "Diamonds", "Spades"], ["Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"]];
let drawnCards = [];

let playerMoney = 100.00;
let playerPoints = 0;
let playerBust = false;

let currentRound = 1;

generateCard = () => { // Draw cards
    let suit = Math.floor(Math.random() * 4);
    let value = Math.floor(Math.random() * 13);
    let card = `${cards[value]} of ${suits[suit]}`;
    return card;

draw = () => {  // Draw a card, check if it's unique, total its points, then output it
    card = generateCard();
            
    while (drawnCards.includes(card)) {
        card = generateCard();
    }
    drawnCards.push(card);
    
    let points = 0;
    if (card.includes("Jack") || card.includes("Queen") || card.includes("King")) {
        points = 10;
    } else if (card.includes("Ace")) {
        highOrLow = prompt("High Ace, or Low Ace? Answer will default to low ")
        if (highOrLow.lower() === "high") {
            points = 11;
        } else {
            points = 1;
        }
    } else {
        points = card[1];
    }
    return card, points;
}
 
dealerDraw = () => {  // Almost exactly the same as the original draw function, but randomises Ace value
    card = generateCard();
            
    while (drawnCards.includes(card)) {
        card = generateCard();
    }
    drawnCards.push(card);
    
    let points = 0;
    if (card.includes("Jack") || card.includes("Queen") || card.includes("King")) {
        points = 10;
    else if (card.includes("Ace")) {
        let highOrLow = Math.floor(Math.random() * 2);
        if (highOrLow === 1) {
            points = 1;
        } else {
            points = 2;
        }
    } else {
        points = card[1];
    }
    return card, points;
}

// TODO
// Gameplay loop. As long as the player has money, the game will play for 5 rounds
while playerBust == False and currentRound <= 5:
    print("_______________________________________________________________________________________________")
    print("Round " + str(currentRound))
    print("You currently have "+ str(playerMoney) + " money")
    bet = float(input("Enter your bet: "))
    while bet > playerMoney:
        print("Error: bet cannot exceed total money")
        bet = float(input("Enter your bet: "))
    while bet <= 0:
        print("Error: you must bet at least 1 money")
        bet = float(input("Enter your bet: "))
   
// Reset variables
    roundEnded = False
    playerCards = []
    dealerCards = []
    playerPoints = 0
    dealerPoints = 0
    drawnCards = []  # Note: the drawn cards are returned to the deck at the end of each round
    winner = "null"
    playerBlackjack = False
    dealerBlackjack = False
    playerOver = False
    dealerOver = False
    
// Player turn
    for i in range(2):
        card, points = draw()
        playerCards.append(card[0])
        playerPoints += int(points)
    print("You have: " + str(playerCards))
    print("You have: " + str(playerPoints) + " points")
    if playerPoints == 21:
        print("Blackjack!")
        winner = "player"
        playerBlackjack = True
        
// Player decision once first 2 cards have been drawn - drawing and holding
    while playerPoints < 21 and roundEnded == False:
        choice = input("You can do the following: draw or hold. What do you choose? ")
        while choice.lower() != "draw" and choice.lower() != "hold":
            choice = input("You can do the following: draw or hold. What do you choose? ")
        if choice.lower() == "hold":
            print("You have chosen to hold. Your score is " + str(playerPoints))
            roundEnded = True
        while choice.lower() == "draw" and playerPoints < 21:
            card, points = draw()
            playerCards.append(card[0])
            playerPoints += int(points)
            print("You have: " + str(playerCards))
            print("You have: " + str(playerPoints) + " points")

            # End player turn if they equal or exceed 21 points
            if playerPoints > 21:
                print("Exceeded 21 points; turn ending")
                playerOver = True
                playerPoints = 0  # This means the player loses the round
                choice = "hold"  # This line and the one below it means the player's turn ends
                roundEnded = True
            elif playerPoints == 21:
                print("Equalled 21 points; turn ending")
                choice = "hold"
            else:
                choice = input("You can do the following: draw or hold. What do you choose? ")
                if choice.lower() == "hold":
                    print("You have chosen to hold. Your score is " + str(playerPoints))
                    roundEnded = True

// Dealer turn
    if playerBlackjack == False:
        for i in range(2):
            card, points = dealerDraw()
            dealerCards.append(card[0])
            dealerPoints += int(points)
        print("The dealer has: " + str(dealerCards))
        print("The dealer has: " + str(dealerPoints) + " points")
        if dealerPoints == 21:
            print("Blackjack!")
            winner = "dealer"
            dealerBlackjack = True
                
        while dealerPoints < 17 and dealerPoints < 21:
            card, points = dealerDraw()
            dealerCards.append(card[0])
            dealerPoints += int(points)
            print("The dealer has: " + str(dealerCards))
            print("The dealer has: " + str(dealerPoints) + " points")
       
        if dealerPoints > 21:
            dealerOver = True
            
            
// Determine winner - if one party earned a Blackjack earlier, they win automatically. Otherwise, points are compared
    if winner == "player":
        print("Player wins!")
    elif winner == "dealer":
        print("Dealer wins!")
    else:
        if (playerPoints > dealerPoints or (dealerPoints > 21 and playerPoints <= 21)) and playerOver == False:
            print("Player wins!")
            winner = "player"
        elif (dealerPoints > playerPoints or (playerPoints > 21 and dealerPoints <= 21)) and dealerOver == False:
            print("Dealer wins!")
            winner = "dealer"
        else:
            print("Round tied")
            winner = "null"

// Give player their winnings
    if winner == "player":
        if playerBlackjack == True:
            bet = bet * 2
        else:
            bet = bet * 1.5
    elif winner == "dealer":
        bet = bet * -1
    elif winner == "null":
        bet = bet * 0
    playerMoney += bet
    
    if playerMoney <= 0:
        print("Player has gone bust")
        playerBust = True
    else:
        currentRound += 1
