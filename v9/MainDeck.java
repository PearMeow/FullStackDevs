import java.util.ArrayList;

public class MainDeck extends Deck{
    
    //adds card to deck and shuffles
    public MainDeck(){
        super();
        this.cards = createDeck();
        this.shuffle();
    }

    //create the deck
    public static ArrayList<Card> createDeck(){
      ArrayList<Card> deck = new ArrayList<Card>();
      for(int i = 0; i < 4; i++){
          for(int t = 6; t < 15; t++){
              deck.add(new Card(i, t));
          }
      }
      return deck;
    }

    //shuffle the deck
    public void shuffle(){
        int randomIndex;
        for( int i = cards.size()-1; i > 0; i-- ) {
          randomIndex = (int)( (i+1) * Math.random() );
          cards.set( i, cards.set( randomIndex, cards.get(i) ) );
        }
    }

}
