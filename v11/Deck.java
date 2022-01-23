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
  
  //string rep
  public String toString(){
    return cards.toString();
  }

}//end class
