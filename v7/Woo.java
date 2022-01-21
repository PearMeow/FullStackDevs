import java.util.ArrayList;
import java.util.Scanner;

public class Woo {
    //Main Deck
    ArrayList<Card> mainDeck;      
    
    //Players *temporarily public*
    public ArrayList<Card> playerHand = new ArrayList<Card>();
    public ArrayList<Card> computerHand = new ArrayList<Card>();
    
    //For Each Round
    public ArrayList<Card> field;
    boolean playerTurn = true; //as of right now player will always be first

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
            
            drawCards();   
            //set up trump
            Card.trumpCard = mainDeck.get(0);
            mainDeck.remove(0); mainDeck.add(Card.trumpCard);//remove trump card and add it to back of deck
            //to see who goes first
            
            System.out.println("The trump card is " + Card.trumpCard + "\n");
    }      
    
    //future imp:
    //we want to create player class so takeCards() will be a method for that calss
    public void drawCards(){
            //player one
        if (mainDeck.size() > 0) {
            if( playerHand.size() < 6 ){
                for( int i = playerHand.size(); i < 6; i++ ){
                    Deck.transfer(mainDeck, 0, playerHand);
                }
            }
            //player two
            if( computerHand.size() < 6 ){
                for( int i = computerHand.size(); i < 6; i++ ){
                    Deck.transfer(mainDeck, 0, computerHand);
                }
            }
        }
        return;
    }
    
    //to check if game has ended yet
    public boolean hasEnded(){
            if( playerHand.size() < 1 ) {
                    System.out.println("You win... this time.");
                    return true;
            }
            else if (computerHand.size() < 1 ) {
                    System.out.println("Hahaha I win.");
                    return true;
            }
            return false;
    }

    //play round
    public void playRound(){
        //reset the field
        field = new ArrayList<Card>();
        Deck.sort(computerHand); 
        Deck.sort(playerHand); 
        //future: 
        //check to see who goes first
        if( playerTurn ){
            attack(); //for player only for now
            defendAI(); //for computer only for now
        }
        else {
            attackAI();
            defend();
        }
        playerTurn = !playerTurn;

        if( !hasEnded() ){
            drawCards();
            playRound();
        }
        
    }

    //AI Attack
    public void attackAI(){
        field.add(computerHand.get(0));
        computerHand.remove(0);
    }

    //AI Defend
    public void defendAI(){
        //loops through the cards 
        for(int a = 0; a < computerHand.size(); a++){
            //check if current card beats card in field
            if( field.get(field.size()-1).compareTo(computerHand.get(a)) < 0 && field.get(field.size()-1).getSuit() == computerHand.get(a).getSuit() ){
                System.out.println("\nComputer uses the " + computerHand.get(a) + " to defeat the " + field.get(field.size() -1) + "\n");
                
                field.add(computerHand.get(a));
                computerHand.remove(a);
                return; //might wanna remove this
            }
            // ADD 
            // if successful defend, check player's hand for things that they can add
            // if addable cards, attack again
        }
        System.out.println("\nComputer is too weak to defeat your card. It takes all the cards on the field.");
        for(int e = field.size()-1; e >= 0; e--  ){
            computerHand.add(field.get(e));
            field.remove(e);
        }
        playerTurn = false;
    }       

    //PlayerAttack
    public void attack(){
        //output
        String output = "Cards in Field: " + field.toString() +
                        "\n\nChoose a card to attack with: \n" +
                        Deck.printHand(playerHand) +
                        "Your choice: ";
        System.out.print(output);
        //input
        int input = Integer.parseInt(in.nextLine());
        /*
        while (input > playerHand.size() || input < 0) {
            System.out.println("Play a card bruv");
            input = Integer.parseInt(in.nextLine());
        }
        */
        //take card for your deck and put in field
        field.add(playerHand.get(input));
        playerHand.remove(input);    
    }
    
    //PlayerDefend
    public void defend(){
        //keep on going until valid move is made
        boolean madeMove = false;
        while( !madeMove ){
            //output
            String output = "\nCards in Field: " + field.toString() +
                            "\n\nChoose a card to Defend with: \n" +
                            Deck.printHand(playerHand) + 
                            Integer.toString( playerHand.size() ) + ": Take Cards\n" +
                            "Your choice: ";
            System.out.print(output);
            //input
            int input = Integer.parseInt(in.nextLine());
            //if chose to skip, take the kids *ahem* i mean cards
            /*
            while (input > playerHand.size() || input < 0) {
            System.out.println("Play a card bruv");
            input = Integer.parseInt(in.nextLine());
        }
            */
            if ( input == playerHand.size() ){
                System.out.println("\nYou have accepted defeat you coward! You must take all the cards in retribution.");
                for(int e = field.size()-1; e >= 0; e--  ){
                    playerHand.add(field.get(e));
                    field.remove(e);
                }
                madeMove = true;
                playerTurn = true;
            }
            //check if card chosen is valid
            else if( playerHand.get(input).compareTo(field.get(field.size()-1)) > 0  && playerHand.get(input).getSuit() == field.get(field.size()-1).getSuit()){
                //if valid: place it
                field.add(playerHand.get(input));
                playerHand.remove(input);
                madeMove = true;
            }
            else{
                //if not valid chastise the imbecile
                System.out.println("That card isn't strong enough you doof. Try again.");
            }
        }
    }      
        
    public static void main (String[] args) {
        Woo game = new Woo();
        game.playRound();
    }
}
