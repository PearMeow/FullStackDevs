public class Card implements Comparable {

    public static String[] suitArray = new String[] {"Diamonds", "Clubs", "Hearts", "Spades"};
    public static String[] numArray = new String [] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private int number;  // 6, 7, 8, 9, 10, 11 (J), 12 (Q), 13 (K), 14 (A)
    private int suit;    // 0 = diamonds, 1 = clubs, 2 = hearts, 3 = spades
    public static int trump; 

    public Card (int suit, int num) {

        this.suit = suit;
        number = num;
        trump = 0;

    }

    public int compareTo (Object perry) {
        return 0;
    }

    public String toString(){
        return numArray[number] + " of " + suitArray[suit];
    }
}
