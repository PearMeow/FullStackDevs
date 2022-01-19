public class Card implements Comparable {

    private int number;  // 6, 7,8,9,10,11 (J), 12(Q), 13(K), 14(A)
    private int suit;    // 1 = diamond, 2 = clubs, 3 = hearts, 4 = spades
    public static int trump; 

    public Card (int symbol, int num) {

        suit = symbol;
        number = num;
        trump = 0;

    }

    public int compareTo (Comparable perry) {
        
    }

    public String toString(){
        return  Integer.toString(suit) + " " + Integer.toString(number);
    }
}