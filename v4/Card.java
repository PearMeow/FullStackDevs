public class Card implements Comparable {

    private int number;  // 6, 7, 8, 9, 10, 11 (J), 12 (Q), 13 (K), 14 (A)
    private int suit;    // 0 = diamonds, 1 = clubs, 2 = hearts, 3 = spades

    public static String[] suitArray = new String[] {"Diamonds", "Clubs", "Hearts", "Spades"};
    public static String[] numArray = new String [] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    public static Card trumpCard;


    public Card (int suit, int num) {

        this.suit = suit;
        number = num;
        trump = 0;

    }

    public boolean isTrump(){
        return suit == trumpCard.getSuit();
    }

    public int getSuit(){
        return suit;
    }

    public String toString(){
        return numArray[number] + " of " + suitArray[suit];
    }

    public int compareTo (Object c) {
            //check if perry is Card
        if( ! (c instanceof Card) ){
            //raise error
            return 1;
        }
        Card perry = (Card) c;
            //base cases
            if (!perry.isTrump() && this.isTrump() ){
                return 1;
            }

            else if (perry.isTrump() && !this.isTrump() ){
                return -1;
            }

            else{
                int difference = this.number - perry.number;
                return (difference)/Math.abs(difference);
            }
    }
}
