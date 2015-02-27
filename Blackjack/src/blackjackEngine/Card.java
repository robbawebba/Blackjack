package blackjackEngine;
import acm.graphics.GImage;

/** GCard.java:  The code for a playing card.
* @author Dr. Mark A. Jones
*/

public class Card implements Comparable <Card> {

    /** Create a playing card with a given rank and suit.
     * @param r    rank
     * @param s    suit
     */

    public Card(Rank r, Suit s) {

        super();
        rank = r;
        suit = s;
        faceUp = true;
        image = new GImage(IMAGE_LOCATION + suit + "-" + rank + "-75.png");

    }

    /** Get the rank of the card.
     * @return   the rank
     */

    public Rank getRank() {
        return rank;
    }

    /** Get the suit of the card.
     * @return   the suit
     */

    public Suit getSuit() {
        return suit;
    }

    /**
     * Gets the Images used for card faces
     * @return Image
     */
    public GImage getImage(){
        return image;
    }

    /**
     * Computes a cards value based on its rank
     * @return integer
     */
    public int getValue() {
        if (rank.toString().equals("2")) return 2;
        if (rank.toString().equals("3")) return 3;
        if (rank.toString().equals("4")) return 4;
        if (rank.toString().equals("5")) return 5;
        if (rank.toString().equals("6")) return 6;
        if (rank.toString().equals("7")) return 7;
        if (rank.toString().equals("8")) return 8;
        if (rank.toString().equals("9")) return 9;
        if (rank.toString().equals("10"))return 10;
        if (rank.toString().equals("j")) return 10;
        if (rank.toString().equals("q")) return 10;
        if (rank.toString().equals("k")) return 10;
        if (rank.toString().equals("a")) return 1;
        return 0;
    }

    /**
     * Determines whether a card is face up
     * @return true or false
     */
    
    public boolean ifFaceUp(){
        return faceUp;
    }

   
    /**
     * Flips a card face on the canvas
     * @return  -1, 0, or 1
     */
    public int flipCard() {

        if (!faceUp){
            faceUp = true;
            image.setImage (IMAGE_LOCATION + suit + "-" + rank + "-75.png");
            return 1;
        }

        if (faceUp){

            faceUp = false;
            image.setImage(IMAGE_LOCATION + "back-red-75-1.png");
            return -1;
        }
        return 0;
    }

    /**
     * @return  the card as a string
     */

    public int compareTo(Card card) {

        if (suit.compareTo(card.getSuit()) < 0) {
            return -1;
        }
        if (suit.compareTo(card.getSuit()) > 0) {
            return 1;
        }
        if (rank.compareTo(card.getRank()) < 0) {
            return -1;
        }
        if (rank.compareTo(card.getRank()) > 0) {
            return 1;
        }       
        return 0;
    }

    /**
     * Compares two cards
     * @param card
     * @return true or false
     */
    public boolean equalTo(Card card) {
        return card.compareTo(card) == 0;
    }

    /** A card as a string.
     * @return  the card as a string
     */

    public String toString() {
        return rank + " of " + suit;
    }

    /* private class variables */

    private static final String IMAGE_LOCATION = "images/";

    /* private instance variables */

    private boolean faceUp;						// Card face position
    private final Rank rank;					// Rank of Card
    private final Suit suit;					// Suit of Card
    private GImage image;						// Images used for cards

}