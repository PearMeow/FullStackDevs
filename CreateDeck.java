public class CreateDeck {

    public ArrayList<Card> creator(){
        ArrayList<Card> deck = new ArrayList<Card>();
        for(int i = 1; i < 5; i++){
            for(int t = 6; t < 15; t++){
                deck.add(new Card(i, t);
            }
        }
    }

    public static void main (String[] args) {
        System.out.println(creator());
    }

}