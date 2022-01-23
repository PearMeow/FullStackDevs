import java.util.ArrayList;

public class Deck {

  protected ArrayList<Card> cards;

  public Deck(){
    cards = new ArrayList<Card>();
  }

  //ArrayList Methods
  public Card get(int index){
    return cards.get(index);
  }
  public void add( Card c){
    cards.add(c);
  }
  public void set(int index, Card c){
    cards.set(index, c);
  }
  public void remove( int index ){
    cards.remove(index);
  }
  public int size(){
    return cards.size();
  }

  //take card from one deck and put in another
  public void transfer(int index, Deck receiver){
    receiver.add( cards.get(index) );
    this.remove(index);
  }

  //swap 2 vals
  public void swap(int i, int j) {
    Card temp = cards.get(j);
    cards.set(j, cards.get(i));
    cards.set(i, temp);
  }
  
  //sort
  public void sort(){
    for(int partition = 0; partition < cards.size() - 1; partition++) {
      for(int i = partition + 1; i > 0; i--) {
        if ( cards.get(i).compareTo(cards.get(i - 1)) < 0 ) {
          this.swap( i, i-1);
        }
        else{
          break;
        }//end else
      }//end for
    }//end for
  }//end sort
  
  //string rep
  public String toString(){
    return cards.toString();
  }

}//end class
