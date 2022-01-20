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
    public ArrayList<Card> field;
    boolean playerAttack = true; //as of right now player will always be first

    //Scanner
    static Scanner in = new Scanner( System.in );
    
    //constructor
    public Woo(){
            newGame();
    }
    //should we combine constructor and newGame?
    //make new game
    public void newGame(){
            //initialize
            mainDeck = Deck.createDeck();
            Deck.shuffle(mainDeck);
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

        field = new ArrayList<Card>();

        //stub: 
        boolean playerTurn = true;

        //only works once right now, we need to make it loop later

        if( playerTurn ){
                attack(); //for player only for now
                defendAI(); //for computer only for now
        }

        else {
                attackAI();
                defend();
        }

        if( !hasEnded() ){
            passCards();
            playRound();
        }

    }


    //AI
    public void attackAI(){
        Deck.sort(computerHand);
        field.add(computerHand.get(0));
        computerHand.remove(0);
    }
    public void defendAI(){
        for(int a = 0; a < computerHand.size(); a++){
            if(field.get(field.size()-1).compareTo(computerHand.get(a)) < 0){
                System.out.println("Computer uses the " + computerHand.get(a) + "to defeat the " + field.get(field.size() -1));
                field.add(computerHand.get(a));
                computerHand.remove(a);
                return;
            }
        }
        System.out.println("Computer is too weak to defeat your card. It takes all the cards on the field");
        for(int e = field.size()-1; e >= 0; e--  ){
            computerHand.add(field.get(e));
            field.remove(e);
        }
    }       


    //Player
    public void attack(){
        System.out.println("Choose a card to play: ");
        System.out.println( Deck.printHand(playerHand) );
        
        String input = in.nextLine();
        field.add(playerHand.get(Integer.parseInt(input)));
        playerHand.remove(Integer.parseInt(input));    
    }

    public void defend(){

    }      
        


    //play turn
    public static void main (String[] args) {
        Woo game = new Woo();

        game.playRound();
        //while( !game.hasEnded() ){
        //       game.playRound();
        //}

    }
}
