package blackjackEngine;
/** GCard.java:  The code for a playing card.
 * @author Dr. Mark A. Jones
 */

import acm.graphics.*;

@SuppressWarnings("serial")
public class GCard extends GCompound implements Comparable <GCard> {

	/** Create a playing card with a given rank and suit.
	 * @param r    rank
	 * @param s    suit
	 */
	public GCard(Rank r, Suit s) {
		super();
		rank = r;
		suit = s;
		image = new GImage(IMAGE_LOCATION + suit + "-" + rank + "-75.png");
		add(image, -image.getWidth()/2, -image.getHeight()/2);
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
	 * @return  the card as a string
	 */
	public int compareTo(GCard card) {
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
	
	public boolean equalTo(GCard card) {
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
	private final Rank rank;
	private final Suit suit;
	private final GImage image;




}
