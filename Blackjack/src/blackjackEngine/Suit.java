package blackjackEngine;
/** Suit.java:  An enumeration of playing card suits.
 * @author Robert Weber
 */

public enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES;
	
	public String toString() {
		return super.toString().toLowerCase();
	}
}
