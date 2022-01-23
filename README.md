# FullStackDevs
## Roster
* Ariel Fuchs 
* Perry Huang 
* Salaj Rijal 

## Project:
For our project we made the Russian card game, Durak, in Java. This game is about beating other peoples' cards and whoever runs out of cards first wins.
<br>

## Rules:
0. The deck consists of only cards from 6 through Ace.
1. The first person to run out of cards when the deck is empty wins.
2. The trump suit is selected by drawing the first card and seeing what suit it has. It gets put at the bottom of the deck after being drawn.
3. Whoever has the lowest trump card attacks first and if you have no trump card you don't go first. If both players have no trump cards, then the player goes first.
4. Each round, the player and the computer draw until they have 6 cards. If they already have 6 or more cards, they don't draw.  
5. Each round, there is an attacker and a defender. The attacker attacks th defender by playing any card from his hand. The defeder trys to beat the card.
6. Cards can only be beaten by a higher card of the same suit or a trump card.
7. Upon the defender beating the first card the attacker can add a card with the same number as a card that is already on the field. Repeat until the defender takes the cards or the attacker can't or won't play any additional cards.
8. If the defender fails to defend he takes cards. The attacker then gets another round as attacker.

<br>

## How to Launch: 
first compile (in root directory): <br>
`$javac Woo.java` <br>
to run: <br>
`$java Woo`