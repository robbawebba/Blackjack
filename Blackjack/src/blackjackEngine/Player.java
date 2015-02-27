package blackjackEngine;
import java.util.ArrayList;


/**
 * Class that controls a player
 * @author Michael Mulvaney & Robert  Weber
 *
 */
public class Player {

	/**
	 * Constructor for a new player
	 * @param model
	 */
	public Player(BlackjackModel model) {

		bm = model;
		hand = new ArrayList<Card>();
		count = 0;
		money = 200.00;
		ante = 5.00;
	}
	/**
	 * Method for dealing cards to the player's hand
	 * @param dealtCard
	 */
	public void dealtCard(Card dealtCard) {
		hand.add(dealtCard);
	}

	/**
	 * Method for determining whether a player is busted
	 * @return the cards in the hand
	 */
	public boolean isBusted() {
		return getValue() > 21;
	}

	/**
	 * Method for determining if a player has blackjack
	 * @return true or false
	 */
	public boolean hasBlackjack() {
		return (hand.size() == 2 && getValue() == 21);
	}
	/**
	 * Method for clearing the player's hand
	 */
	public void clearHand() {
		hand.removeAll(hand);		
	}

	/**
	 * Computes the value of the player's hand
	 * @return value of hand
	 */
	public int getValue() {
		int sum = 0;
		boolean hasAce = false;						// hasAce default is false

		for (Card card : hand){
			sum += card.getValue();
			if (card.getRank() == Rank.ACE){		// If the player has an Ace in their hand hasAce returns true	
				hasAce = true;
			}
		}
		if (hasAce && sum + 10 <= 21) {				// If hasAce returns true and the hand value(+10) is less than or equal
			sum += 10;								// to 21, then the hand value is increased by 10
		}
		return sum;
	}

	/**
	 * Method to determine specific cards in players' hands
	 * @param i
	 * @return a card in a player's hand
	 */
	public Card getCard(int i) {
		return hand.get(i);
	}
	
	public double getAnte() {
		return ante;
	}
	
	public void setAnte(double bet) {
		ante = bet;
	}
	
	public double getMoney() {
		return money;
	}
	public double addMoney(double cash) {
		money += cash;
		return money;
	}
	public double subMoney(double cash){
		money -=cash;
		return money;
	}

	/* Instance Variables */
	
	protected BlackjackModel bm;					// Blackjack Model
	protected ArrayList<Card> hand;					// Player's Hand
	int count;										// Player's Count
	double money;
	double ante;

}

