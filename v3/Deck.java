import java.util.ArrayList;

public class Deck {

    public static void shuffle( ArrayList al )
  {
    int randomIndex;
    //setup for traversal fr right to left
    for( int i = al.size()-1; i > 0; i-- ) {
      //pick an index at random
      randomIndex = (int)( (i+1) * Math.random() );
      //swap the values at position i and randomIndex
      al.set( i, al.set( randomIndex, al.get(i) ) );
    }
  }

    public static void draw(ArrayList<Card> decker, ArrayList<Card> handy){
        handy.add(decker.get(0));
        decker.remove(0);
    }

    public static ArrayList<Card> creator() {
        ArrayList<Card> deck = new ArrayList<Card>();
        for(int i = 0; i < 4; i++){
            for(int t = 6; t < 15; t++){
                deck.add(new Card(i, t));
            }
        }
        return deck;
    }

    public static void main (String[] args) {
        ArrayList<Card> theDeck = creator();
        shuffle(theDeck);
        
        
        System.out.println(theDeck);
        

        ArrayList<Card> p1Hand = new ArrayList<Card>();
        ArrayList<Card> p2Hand = new ArrayList<Card>();

        //code that hands out cards

        Card trumpCard = theDeck.get(0);
        Card.trump = trumpCard.getSuit();

    }

}