import java.util.ArrayList;
import java.util.Scanner;

public class Woo {

        //Main Deck
        ArrayList<Card> mainDeck;

        //Players
    ArrayList<Card> p1Hand = new ArrayList<Card>();
    ArrayList<Card> p2Hand = new ArrayList<Card>();

        //
        ArrayList<Card> field;
        Card trumpCard;

        public Woo(){
                newGame();
        }

        public void newGame(){
                field = new ArrayList<Card>();
                p1hand = new ArrayList<Card>();
                p2hand = new ArrayList<Card>();

                passCards();

                //draw trump card
                Card trumpCard = mainDeck.get(0);

                mainDeck = Deck.creator();
        }

        public void passCards(){
                //p1
                if( p1hand.size() < 6 ){
                        for( int i = p1hand.size(); i < 7; i++ ){
                                Deck.draw(mainDeck, p1hand);
                        }
                }
                //p2
                if( p1hand.size() < 6 ){
                        for( int i = p1hand.size(); i < 7; i++ ){
                                Deck.draw(mainDeck, p1hand);
                        }
                }
        }



public static void main (String[] args) {

          Woo game = new Woo();

          Card trumpCard = theDeck.get(0);

          System.out.println(trumpCard + "\n");
          theDeck.remove(0);
          theDeck.add(trumpCard);

          System.out.println(theDeck);

      Card.trump = trumpCard.getSuit();

          System.out.println( Card.trump );
  }

}
