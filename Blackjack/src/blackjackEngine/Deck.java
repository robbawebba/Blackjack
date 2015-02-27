package blackjackEngine;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	/**
	 * Creates an array deck of cards
	 */
	public Deck() {
		deck = new ArrayList<Card>();
		for (Rank r : Rank.values()) {
			for (Suit s : Suit.values()) {
				deck.add(new Card(r, s));
			}
		}
	}

	/** Deal a card.
	 * @return  the bottom card from the deck
	 */

	public Card deal() {
		if (deck.size() > 0 ) {
			Card card = deck.get(deck.size()-1);  
			deck.remove(card);
			return card;  
		} return null;
	}

	/** Shuffle the remaining cards in the deck. */

	public void shuffle() {
		Collections.shuffle(deck);
	}

	/** The deck as a String.
	 * @return  the deck as a string
	 */

	public String toString() {
		return deck.toString();
	}

	/** The number of cards left in the deck.
	 * @return   the number of cards left
	 */

	public int numberLeft() {
		return deck.size();
	}

	/* private instance variables */

	private ArrayList<Card> deck;		// Deck Array
}
