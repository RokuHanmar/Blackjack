import random

cards = [["Hearts", "Clubs", "Diamonds", "Spades"], ["Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"]]
drawnCards = []

playerMoney = 100
rounds = 5
playerPoints = 0

playerBust = False
roundEnded = False

def draw():  # Get a suit and a value, combine them, and check to see if it's already drawn. If so, draw a new card. If
    suit = random.randint(0, 3)  # not, add to the list of drawn cards and return
    suit = cards[0][suit]
    value = random.randint(0, 12)
    value = cards[1][value]
    
    points = 0
    if value == "Jack" or value == "Queen" or value == "King":
        points = 10
    elif value == "Ace":
        highOrLow = input("High Ace, or Low Ace? Answer will default to low ")
        if highOrLow.lower() == "high":
            points = 11
        else:
            points = 1
    else:
        points = int(value)
        
    card = (str(value) + " of " + str(suit))
    while card in drawnCards:
        suit = random.randint(0, 3)
        suit = cards[0][suit]
        value = random.randint(0, 12)
        value = cards[1][value]
        card = (str(value) + " of " + str(suit))
    drawnCards.append(card)        
    return card, points

# Gameplay loop. As long as the player has money, the game will play for 5 rounds
while playerBust == False and rounds > 0:
    bet = int(input("Enter your bet: "))
    while bet > playerMoney:
        print("Error: bet cannot exceed total money")
        bet = int(input("Enter your bet: "))
    #draw()
#    print(drawnCards)

# Reset variables
    roundEnded = False
    playerCards = []
    dealerCards = []
    playerPoints = 0
    dealerPoints = 0
    drawnCards = []  # Note: the drawn cards are returned to the deck at the end of each round
    
# Player turn
    for i in range(2):
        card, points = draw()
        playerCards.append(card)
        playerPoints += points
        print("You have: " + str(playerCards))
        print("You have: " + str(playerPoints) + " points")
        
# Player decision once first 2 cards have been drawn - drawing and folding
    while playerPoints < 21 and roundEnded == False:
            choice = input("You can do the following: draw or fold. What do you choose? ")
            while choice.lower() != "draw" and choice.lower() != "fold":
                choice = input("You can do the following: draw or fold. What do you choose? ")
            if choice.lower() == "fold":
                print("You have folded. Your score is " + str(playerPoints))
                roundEnded = True
            while choice.lower() == "draw":
                card, points = draw()
                playerCards.append(card)
                playerPoints += points
                print("You have: " + str(playerCards))
                print("You have: " + str(playerPoints) + " points")
                choice = input("You can do the following: draw or fold. What do you choose? ")
                while choice.lower() != "draw" and choice.lower() != "fold":
                    choice = input("You can do the following: draw or fold. What do you choose? ")
                
        # BUGS: round doesn't end when points exceed 21. 7 is worth 3 points. 9 of Spades is worth 10 points. 8, 3, 4, 10, King, Queen, 5, 6 all unaffected
        # TODO: check they don't exceed 21, implement dealer functionality, expand on betting functionality
