import random

cards = [["Hearts", "Clubs", "Diamonds", "Spades"], ["Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"]]
drawnCards = []

playerMoney = 100
rounds = 5

playerBust = False

def draw():  # Get a suit and a value, combine them, and check to see if it's already drawn. If so, draw a new card. If
    suit = random.randint(0, 3)  # not, add to the list of drawn cards and return
    suit = cards[0][suit]
    value = random.randint(0, 12)
    value = cards[1][value]
    card = (str(value) + " of " + str(suit))
    while card in drawnCards:
        suit = random.randint(0, 3)
        suit = cards[0][suit]
        value = random.randint(0, 12)
        value = cards[1][value]
        card = (str(value) + " of " + str(suit))
    drawnCards.append(card)        
    return card

# Gameplay loop. As long as the player has money, the game will play for 5 rounds
while playerBust == False and rounds > 0:
    bet = int(input("Enter your bet: "))
    while bet > playerMoney:
        print("Error: bet cannot exceed total money")
        bet = int(input("Enter your bet: "))
    #draw()
#    print(drawnCards)
    
    playerCards = []
    dealerCards = []
    for i in range(2):
        card = draw()
        playerCards.append(card)
        #print(playerCards)
        
        # TODO: total cards, check they don't exceed 21, implement dealer functionality, expand on betting functionality
