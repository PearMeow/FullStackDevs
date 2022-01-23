
//will always be sorted. behaves like OAL
public class Hand extends Deck{

    //overwritten add
    @Override
    public void add(Card newVal)
    {
      //check if there are any elements in data
      if(size() == 0){
          cards.add(newVal);
        return;
      }
      //loop through data until current val (val at index i) is greater than or equal to newVal
      for(int i = 0; i < size(); i++){
        if( get(i).compareTo(newVal) >= 0){
            cards.add(i,newVal);
            return;
          }
      }
      //add to the end of the array if no spot found in the middle of the array
      cards.add(newVal);
    }

    public int trumpCount(){
        int count = 0;
        for(Card card: cards){
            if ( card.isTrump() ){
                count++;
            }
        }
        return count;
    }

    public String toString(){
        int count = 0;
        String result = "";
        //loop through each card in cards and add it to its own row
        for( Card card: cards){
          result += Integer.toString(count) + ": " + card.toString() + "\n";
          count++;
        }
        return result;
    }
}


