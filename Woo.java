import java.util.ArrayList;
import java.util.Scanner;

public class Woo {
    //Main Deck
    private MainDeck mainDeck;      
    
    //Players *temporarily public*
    private Hand playerHand;
    private Hand computerHand;
    
    //For Each Round *temporarily public*
    private Deck field;
    private boolean playerTurn;

    //Scanner
    public static Scanner in = new Scanner( System.in );
    
    //constructor
    public Woo(){
            newGame();
    }

    //make new game
    public void newGame(){
            System.out.println("=====================================================\n" +
                               "                      New Game\n" +
                               "=====================================================\n"  );
            //initialize
            mainDeck = new MainDeck();
            playerHand = new Hand();
            computerHand = new Hand();
            
            Card.trumpCard = mainDeck.get(0);
            System.out.println("The trump card is " + Card.trumpCard + "\n");
            mainDeck.transfer(0, mainDeck);
            
            //hand out cards
            drawCards();

            //to see who goes first
            playerTurn = playerHand.trumpCount() >= computerHand.trumpCount();
            if( playerTurn ) System.out.println("You are going first!\n");
            else System.out.println("The computer is going first!\n");
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

    //make sure valid input is given by player 0->limit (exclusive)
    public int getValidInput(int limit){
        //mkae sure input is a number
        int input;
        try{
            input = Integer.parseInt( in.nextLine() );
        }
        catch( Exception e){
            System.out.println("\nNot valid input! Try Again!");
            return getValidInput(limit);
        }
        //make sure input is in range
        if( 0 <= input && input < limit ){
            return input;   
        }
        else{
            System.out.println("\nNot valid input! Try Again!");
            return getValidInput(limit);
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
                           "                 Computer Hand Size: " + Integer.toString(computerHand.size()) + "\n" +
                           "              Cards Left in Main Deck: " + Integer.toString(mainDeck.size()) + "\n" +
                           "=====================================================\n"  );
        //reset the field
        field = new Deck();
        //who attacks, who defends
        if( playerTurn ){
            while( attack() && defendAI() ){}
        }
        else {
            while( attackAI() && defend() ){}
        }
        //swap who goes first next round
        playerTurn = !playerTurn;
        //check if game as ended
        if( !hasEnded() ){
            drawCards();
            playRound();
        }
    }

    //ATTACK & DEFEND METHODS
    //future imp: make the defense methods return booleans

    //AI Attack
    public boolean attackAI(){
        //strategy: to play lowest value card
        //since computerHand is sorted, first card is lowest value card
        if (field.size() == 0 ){
            System.out.println("\nComputer has played " + computerHand.get(0) + "!");
            computerHand.transfer(0, field);
            return true;
        }
        for( int i = 0; i < computerHand.size(); i++ ){
            if (legalAttack( computerHand.get(i) ) ){
                System.out.println("\nComputer has played " + computerHand.get(i) + "!");
                computerHand.transfer(i, field);
                return true;
            }
        }
        System.out.println("\nComputer did not place a card to attack!");
        return false;
    }

    //AI Defend
    public boolean defendAI(){
        //strategy: play lowest value (playable) card that can beat card in field,
        //if n/a, then pick up the cards

        //loop through cards
        for(int a = 0; a < computerHand.size(); a++){
            //check if current card beats card in field
            if( legalDefense( computerHand.get(a) ) ){
                System.out.println("\nComputer uses the " + computerHand.get(a) + " to defeat the " + field.get(field.size() -1) + "!\n");
                computerHand.transfer(a, field);
                return true; //since you played card
            }
        }
        //if no card was found
        System.out.println(computerHand.toString() + "\nComputer is too weak to defeat your card. It takes all the cards on the field.");
        pickUp(computerHand);
        playerTurn = false; //since computer lost, will not go first next round
        return false;
    }       

    //Player Attack
    public boolean attack(){
        String xTra = "";
        int adder = 0;
        if ( field.size() > 0){
            xTra = Integer.toString(playerHand.size()) + ": Skip Attack\n";
            adder = 1;
        }
        String message =  "Cards in Field: " + field.toString() +
                          "\n\nChoose a card to play: \n" +
                          playerHand.toString() +
                          xTra +
                          "\nYour choice: ";
        System.out.print(message);
                          
        int input = getValidInput( playerHand.size() + adder );
        
        //check if inputted card is legal move, else restart
        while( input != playerHand.size() && !legalAttack( playerHand.get(input) ) ){
            System.out.print("\nNot a valid card to play nincompoop! Try again!\n" + message);
            input = getValidInput( playerHand.size() + adder );
        }
        if ( input == playerHand.size() ){
            System.out.println("\nYou chose not to attack!");
            return false;
        }
        //put card in field
        playerHand.transfer(input, field);
        return true;
    }
    
    //Player Defend
    public boolean defend(){
        String message =  "Cards in Field: " + field.toString() +
                          "\n\nChoose a card to play: \n" +
                          playerHand.toString() +
                          Integer.toString(playerHand.size()) + ": Take Cards" +
                          "\nYour choice: ";
        System.out.print(message);
        //input
        int input = getValidInput( playerHand.size()+1 );
        //keep on going until valid move is made
        while( input != playerHand.size() && !legalDefense( playerHand.get(input) ) ){
            System.out.print("\nNot a legal move! Try again!\n" + message);
            input = getValidInput(  playerHand.size() + 1 );
        }
        //if player chose to not defend
        if( input == playerHand.size() ){
            System.out.println("\nYou have accepted defeat you coward! You must take all the cards in retribution.");
            pickUp( playerHand );
            playerTurn = true;
            return false; //make return false if we make defend methods booleans
        }
        //place card
        playerHand.transfer(input, field);
        return true;
    }      
        
    public static void main (String[] args) {
        Woo game;
        boolean goAgain = true;
        while ( goAgain ){
            game = new Woo();
            game.start();

            System.out.println("Would you like to go again (yes/no): ");
            input = in.readLine();
            if( input.equals("yes") ){
                goAgain = true;
            }
            else{
                System.out.println("Okay BYE!");
                goAgain = false;
            }
        }

    }
}
