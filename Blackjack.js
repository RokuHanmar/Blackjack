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

// Gameplay loop. As long as the player has money, the game will play for 5 rounds
while (playerBust === false && currentRound <= 5) {
    console.log("_______________________________________________________________________________________________");
    console.log(`Round ${currentRound}`);
    console.log(`You currently have ${playerMoney} money`);
    let bet = prompt("Enter your bet: ");
    while (bet > playerMoney) {
        console.log("Error: bet cannot exceed total money");
        bet = prompt("Enter your bet: ");
    }
    while (bet <= 0) {
        console.log("Error: you must bet at least 1 money");
        bet = prompt("Enter your bet: ");
    }
    
// Reset variables
    roundEnded = false;
    playerCards = [];
    dealerCards = [];
    playerPoints = 0;
    dealerPoints = 0;
    drawnCards = [];  // Note: the drawn cards are returned to the deck at the end of each round
    winner = null;
    playerBlackjack = false;
    dealerBlackjack = false;
    playerOver = false;
    dealerOver = false;
    
// Player turn
    for (i = 0; i < 2; i++) {
        let card, points = draw();
        playerCards.push(card[0]);
        playerPoints += points;
    }
    console.log(`You have: ${playerCards}`);
    console.log(`You have: ${playerPoints} points`);
    if (playerPoints === 21) {
        console.log("Blackjack!");
        winner = "player";
        playerBlackjack = true;
    }
        
// Player decision once first 2 cards have been drawn - drawing and holding
    while (playerPoints < 21 && roundEnded === False) {
        choice = prompt("You can do the following: draw or hold. What do you choose? ");
        while (choice.toLowerCase() != "draw" && choice.toLowerCase() != "hold") {
            choice = prompt("You can do the following: draw or hold. What do you choose? ");
        }
        if (choice.toLowerCase() === "hold") {
            console.log(`You have chosen to hold. Your score is ${playerPoints}`);
            roundEnded = true;
        }
        while (choice.toLowerCase() === "draw" && playerPoints < 21) {
            card, points = draw();
            playerCards.push(card[0]);
            playerPoints += points;
            console.log(`You have: ${playerCards}`);
            console.log(`You have: ${playerPoints} points`);
        }

            // End player turn if they equal or exceed 21 points
            if (playerPoints > 21) {
                print("Exceeded 21 points; turn ending")
                playerOver = true
                playerPoints = 0  // This means the player loses the round
                choice = "hold";  // This line and the one below it means the player's turn ends
                roundEnded = true
            } else if (playerPoints === 21) {
                console.log("Equalled 21 points; turn ending");
                choice = "hold";
            } else {
                choice = prompt("You can do the following: draw or hold. What do you choose? ");
                if (choice.toLowerCase() === "hold") {
                    console.log(`You have chosen to hold. Your score is ${playerPoints}`);
                    roundEnded = true;
                }
            }
    }

// Dealer turn
    if (playerBlackjack === False){
        for (i = 0; i > 2; i++){
            card, points = dealerDraw();
            dealerCards.push(card[0]);
            dealerPoints += points;
        }
        console.log(`The dealer has: ${dealerCards}`);
        console.log(`The dealer has: ${dealerPoints} points`);
        if (dealerPoints === 21) {
            console.log("Blackjack!");
            winner = "dealer"
            dealerBlackjack = true;
        }
        
        while (dealerPoints < 17 and dealerPoints < 21)
            card, points = dealerDraw();
            dealerCards.push(card[0]);
            dealerPoints += points;
            console.log(`The dealer has: ${dealerCards});
            console.log(`The dealer has: ${dealerPoints} points);
       
        if (dealerPoints > 21){
            dealerOver = true;
        }
    }

// Determine winner - if one party earned a Blackjack earlier, they win automatically. Otherwise, points are compared
    if (winner === "player"){
        console.log("Player wins!");
    } else if (winner == "dealer"){
        console.log("Dealer wins!");
    } else {
        if ((playerPoints > dealerPoints || (dealerPoints > 21 && playerPoints <= 21)) && playerOver === false) {
            console.log("Player wins!");
            winner = "player";
        } else if ((dealerPoints > playerPoints || (playerPoints > 21 && dealerPoints <= 21)) && dealerOver === false) {
            console.log("Dealer wins!");
            winner = "dealer";
        } else {
            console.log("Round tied");
            winner = null;
        }
    }

//  TODO
// Give player their winnings
    if (winner === "player") {
        if (playerBlackjack === true) {
            bet = bet * 2;
        } else {
            bet = bet * 1.5;
        }
    } else if (winner === "dealer") {
        bet = bet * -1;
    } else if (winner === null) {
        bet = bet * 0;
    }
    playerMoney += bet;
    
    if (playerMoney <= 0) {
        console.log("Player has gone bust");
        playerBust = true;
    } else {
        currentRound += 1;
    }
}
