import java.util.ArrayList;
import java.util.Scanner;

public class Woo {
    //Main Deck
    MainDeck mainDeck;      
    
    //Players *temporarily public*
    public Hand playerHand;
    public Hand computerHand;
    
    //For Each Round *temporarily public*
    public Deck field;
    boolean playerTurn;

    //Scanner
    static Scanner in = new Scanner( System.in );
    
    //constructor
    public Woo(){
            newGame();
    }

    //should we combine constructor and newGame?
    //make new game
    public void newGame(){
            System.out.println("=====================================================\n" +
                               "                      New Game\n" +
                               "=====================================================\n"  );
            //initialize
            mainDeck = new MainDeck();
            playerHand = new Hand();
            computerHand = new Hand();
            
            //set up trump (select, and put to back of deck)
            Card.trumpCard = mainDeck.get(0);
            mainDeck.transfer(0, mainDeck); //mainDeck.remove(0); mainDeck.add(Card.trumpCard); //remove trump card and add it to back of deck
            System.out.println("The trump card is " + Card.trumpCard + "\n");
            
            //hand out cards
            drawCards();

            //to see who goes first
            playerTurn = playerHand.trumpCount() >= computerHand.trumpCount();
            if( playerTurn ) System.out.println("You are going first!");
            else System.out.println("The computer is going first!");
    }      
    
    //draw cards until all hands have at least 6 cards or deck is empty
    public void drawCards(){
        //player
        while (mainDeck.size() > 0 && playerHand.size() < 6){
            mainDeck.transfer(0, playerHand);
        }
        //computer 
        while (mainDeck.size() > 0 && computerHand.size() < 6){
            mainDeck.transfer(0, computerHand);            
        }
    }
    
    //to check if game has ended yet
    public boolean hasEnded(){
        if( playerHand.size() < 1 ) {
            System.out.println("Player wins");
            return true;
        }
        else if (computerHand.size() < 1 ) {
            System.out.println("Computer (me) wins. You suck :(.");
            return true;
        }
        return false;
    }

    //pick up: pick up all cards in field
    public void pickUp( Deck taker ){
        while( field.size() > 0 ){
            field.transfer(0, taker);
        }
    }

    //legal move checkers (check if card can be played legally in field)
    public boolean legalDefense( Card c ){
        /*
        Legal If:
        1: Same suit as attacking card or trump card
        AND
        2: Beats attacking card
        */
        Card attackingCard = field.get( field.size() -1 );
        boolean winner = c.compareTo(attackingCard) > 0;
        boolean validSuit = (c.getSuit() == attackingCard.getSuit()) || c.isTrump();
        return winner && validSuit;
    }
    public boolean legalAttack( Card c){
        /*
        Legal If:
        1: No cards in field (can play anything)
        OR
        2: If cards in field, only legal if number was played before
        */
        //check if field is empty
        if (field.size() == 0){
            return true;
        } 
        //check if number was played before
        for( int i = 0; i < field.size(); i++){
            if( c.getNumber() == field.get(i).getNumber() ){
                return true;
            }
        }
        return false;
    }

    //make sure valid input is given by player
    public int getValidInput(boolean defense){
        //code to diffrentiate for defense and attack
        String skipTurn = "";
        int adder = 0;
        if ( defense ){
            skipTurn = Integer.toString(playerHand.size()) + ": Take Cards\n";
            adder = 1;
        } 
        //print message
        System.out.print("Cards in Field: " + field.toString() +
                           "\n\nChoose a card to play: \n" +
                           playerHand.toString() +
                           skipTurn +
                           "\nYour choice: ");
        int input;
        //mkae sure input is a number
        try{
            input = Integer.parseInt( in.nextLine() );
        }
        catch( Exception e){
            System.out.println("\nNot valid input! Try Again!");
            return getValidInput(defense);
        }
        //make sure input is in range
        if( 0 <= input && input < playerHand.size()+adder ){
            return input;   
        }
        else{
            System.out.println("\nNot valid input! Try Again!");
            return getValidInput(defense);
        }
    }

    //wrapper for play round (not sure if needed)
    public void start(){
        playRound();
    }

    //play round
    public void playRound(){
        System.out.println("=====================================================\n" +
                           "                      New Round\n" +
                           "=====================================================\n"  );
        //reset the field
        field = new Deck();
        //who attacks, who defends
        if( playerTurn ){
            attack();
            defendAI();
        }
        else {
            attackAI();
            defend();
        }
        //swap who goes first next round
        playerTurn = !playerTurn;
        //check if game as ended
        if( !hasEnded() ){
            //if not ended, draw cards and start another round
            drawCards();
            playRound();
        }
    }

    //ATTACK & DEFEND METHODS
    //future imp: make the defense methods return booleans

    //AI Attack
    public void attackAI(){
        //strategy: to play lowest value card
        //since computerHand is sorted, first card is lowest value card
        System.out.println("Computer has played " + computerHand.get(0) + "!");
        computerHand.transfer(0, field);
    }

    //AI Defend
    public void defendAI(){
        //strategy: play lowest value (playable) card that can beat card in field,
        //if n/a, then pick up the cards

        //loop through cards
        for(int a = 0; a < computerHand.size(); a++){
            //check if current card beats card in field
            if( legalDefense( computerHand.get(a) ) ){
                System.out.println("\nComputer uses the " + computerHand.get(a) + " to defeat the " + field.get(field.size() -1) + "!\n");
                computerHand.transfer(a, field);
                return; //since you played card
            }
            // ADD 
            // if successful defend, check player's hand for things that they can add
            // if addable cards, attack again
        }
        //if no card was found
        System.out.println(computerHand.toString() + "\nComputer is too weak to defeat your card. It takes all the cards on the field.");
        pickUp(computerHand);
        playerTurn = false; //since computer lost, will not go first next round
    }       

    //Player Attack
    public void attack(){
        //input
        int input = getValidInput( false );
        //check if inputted card is legal move, else restart
        while( !legalAttack( playerHand.get(input) ) ){
            System.out.println("Not a valid card to play nincompoop! Try again!");
            input = getValidInput( false );
        }
        //put card in field
        playerHand.transfer(input, field);
    }
    
    //Player Defend
    public void defend(){
        //input
        int input = getValidInput( true );
        //keep on going until valid move is made
        while( input != playerHand.size() && !legalDefense( playerHand.get(input) ) ){
            System.out.println("Not a legal move! Try again!");
            input = getValidInput(  true );
        }
        //if player chose to not defend
        if( input == playerHand.size() ){
            System.out.println("\nYou have accepted defeat you coward! You must take all the cards in retribution.");
            pickUp( playerHand );
            playerTurn = true;
            return; //make return false if we make defend methods booleans
        }
        //place card
        playerHand.transfer(input, field);
    }      
        
    public static void main (String[] args) {
        Woo game = new Woo();
        game.start();
    }
}
