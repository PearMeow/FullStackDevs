import java.util.ArrayList;
import java.util.Scanner;

public class Woo {
    //Main Deck
    ArrayList<Card> mainDeck;      
    Card trumpCard;
    
    //Players
    public ArrayList<Card> playerHand = new ArrayList<Card>();
    public ArrayList<Card> computerHand = new ArrayList<Card>();
    
    //For Each Round
    ArrayList<Card> field;
    
    //constructor
    public Woo(){
            newGame();
    }
    //should we combine constructor and newGame?
    //make new game
    public void newGame(){
            //initialize
            mainDeck = Deck.createDeck();
            playerHand = new ArrayList<Card>();
            computerHand = new ArrayList<Card>();
            //hand out cards
            passCards();   
            //set up trump
            Card trumpCard = mainDeck.get(0);
            mainDeck.remove(0); mainDeck.add(trumpCard);//remove trump card and add it to back of deck
            System.out.println("The trump card is " + trumpCard + "\n");
    }      
    
    //future imp:
    //we want to create player class so takeCards() will be a method for that calss
    public void passCards(){
            //player one
            if( playerHand.size() < 6 ){
                for( int i = playerHand.size(); i < 6; i++ ){
                    Deck.draw(mainDeck, playerHand);
                }
            }
            //player two
            if( computerHand.size() < 6 ){
                for( int i = computerHand.size(); i < 6; i++ ){
                    Deck.draw(mainDeck, computerHand);
                }
            }
    }
    
    //to check if game has ended yet
    public boolean hasEnded(){
            if( playerHand.size() < 1 || computerHand.size() < 1 ){
                    return true;
            }
            return false;
    }

    //play round
    public void playRound(){

            //playerAttack();
            //aiDefend();

    }


    //attack


    //defend
        


    //play turn
    public static void main (String[] args) {
        Woo game = new Woo();
        
        //while( !game.hasEnded() ){
        //       game.playRound();
        //}
    }
}
