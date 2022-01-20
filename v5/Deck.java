import java.util.ArrayList;

public class Deck {

  public static void shuffle( ArrayList al ){
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

  public static ArrayList<Card> createDeck() {
      ArrayList<Card> deck = new ArrayList<Card>();
      for(int i = 0; i < 4; i++){
          for(int t = 6; t < 15; t++){
              deck.add(new Card(i, t));
          }
      }
      return deck;
  }

  public static String printHand(ArrayList<Card> hand){
    int count = 0;
    String result = "";
    
    //loop through printing each card in a row
    for( Card perry: hand){
      result += Integer.toString(count) + ": " + perry.toString() + "\n";
      count++;
    }
    return result;

  }

  public static void main(String[] args) {
    //test for printhand
    System.out.println( printHand( shuffle(createDeck()) ) );
  }

}
