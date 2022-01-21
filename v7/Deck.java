import java.util.ArrayList;

public class Deck {

  //shuffle deck (taken from 51_bubbleSort)
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

  //take card from one ArrayList<Card> and transfer to another
  public static void transfer(ArrayList<Card> giver, int index, ArrayList<Card> receiver){
      receiver.add(giver.get(index));
      giver.remove(index);
  }

  //
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

  public static void swap(ArrayList<Card> data, int i, int j) {
    Card temp = data.get(j);
    data.set(j, data.get(i));
    data.set(i, temp);
  }

  public static void sort( ArrayList<Card> data ){
    for(int partition = 0; partition < data.size() - 1; partition++) {
      for(int i = partition + 1; i > 0; i--) {
        if ( data.get(i).compareTo(data.get(i - 1)) < 0 ) {
          swap(data, i, i - 1);
        }
        else
          break;
      }
    }
  }

  public static void main(String[] args){
    //test for sort
    Card.trumpCard = new Card(1, 10);
    ArrayList<Card> deck = createDeck();
    sort(deck);
    System.out.println( deck );
  }

}
