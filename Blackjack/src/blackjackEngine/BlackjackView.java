package blackjackEngine;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * BlackjackView manages the UI of the game
 * @author Robert Weber & Michael Mulvaney
 *
 */
public class BlackjackView {

	/**
	 * Constructor for blackjack view
	 * @param blackjack controller
	 */

	public BlackjackView(Blackjack blackjack) {

		bc = blackjack;
		bc.setSize(APPLICAITON_WIDTH,APPLICATION_HEIGHT);

		dealerHandSize = 0;
		playerHandSize = 0;

		bc.getGCanvas().setFocusable(true);
		bc.setBackground(Color.green.darker().darker());

		bc.setSize(APPLICAITON_WIDTH, APPLICATION_HEIGHT);

		// labels for status messages

		bc.add(status = new JLabel("Welcome to blackjack! Hit or Stay?"), bc.NORTH);
		status.setFont(LABEL_FONT);    

		// labels for win/loss

		win = new JLabel("W = " + wins);
		bc.add(win, bc.EAST);
		loss = new JLabel("L = " + losses);
		bc.add(loss, bc.EAST);

		// bet chooser
		bc.add(new JLabel("Bet"), bc.SOUTH );
		initBetChooser();
		
		
		// hit and stay buttons
		hitButton = new JButton("Hit");
		bc.add(hitButton, bc.SOUTH);
		stayButton = new JButton("Stay");
		bc.add(stayButton, bc.SOUTH);

		// button for restarting game

		bc.add(new JButton("Reset"), bc.SOUTH);

		// labels to display hand

		bc.add(yourcount = new JLabel("Your Count: " + intYourCount), bc.EAST);
		bc.add(dealercount = new JLabel("Dealer Count: " + intDealerCount ), bc.EAST);
		dealercount.setVisible(false);

		// labels to display statistics

		bc.add(percentWin = new JLabel("% Win = " + percentWin2 + "%"), bc.EAST);
		
		//bet labels
		bc.add(betLabel = new JLabel(String.format("bet = $%4.2f", bet)), bc.EAST);
		bc.add(moneyLabel = new JLabel(String.format("Money = $%4.2f", money)), bc.EAST);

		// labels to mark players

		bc.add(playerLabel = new JLabel("PLAYER"), 20, 125);
		bc.add(dealerLabel = new JLabel("DEALER"), 20, 300);
	}
	
	@SuppressWarnings("unchecked")
	private void initBetChooser() {
		betChooser = new JComboBox();
		betChooser.addItem("$2");
		betChooser.addItem("$5");
		betChooser.addItem("$10");
		betChooser.addItem("$15");
		betChooser.addItem("$20");
		betChooser.addItem("$25");
		betChooser.addItem("$50");
		betChooser.setEditable(false);
		betChooser.setSelectedItem("$5");
		bc.add(betChooser, bc.SOUTH);
	}

/* Returns the current color */
	public double getCurrentBet() {
		String name = (String) betChooser.getSelectedItem();
		if (name.equals("$2")) return 2.00;
		if (name.equals("$5")) return 5.00;
		if (name.equals("$10")) return 10.00;
		if (name.equals("$15")) return 15.00;
		if (name.equals("$20")) return 20.00;
		if (name.equals("$25")) return 25.00;
		if (name.equals("$50")) return 50.00;
		return 5.00;
	}
	
	/**
	 * notification that sets the status once the game is
	 * initialized 
	 * @param ante2 
	 * @param money2 
	 */
	public void gameInitializedNotification(double money2, double ante2) {
		status.setText("Welcome to Blackjack! Hit or stay?");
		bet = ante2;
		money = money2;
		betLabel.setText(String.format("Bet = $%4.2f", bet));
		moneyLabel.setText(String.format("Money = $%4.2f", money));
		
	}
	
	/**
	 * disables the "hit" button
	 */
	public void disableHit() {
		hitButton.setVisible(false);
	}

	/**
	 * sets certain aspects of the UI as visible/invisible 
	 * when it is the dealer's turn
	 */
	public void dealerTurn() {
		dealercount.setVisible(true);
		hitButton.setVisible(false);
		stayButton.setVisible(false);
	}
	
	/**
	 * resets the UI for a new game
	 */
	public void resetGame() {
		bc.removeAll();	
		intYourCount = intDealerCount = playerHandSize = dealerHandSize = 0;
		bc.add(playerLabel, 20, 125);
		bc.add(dealerLabel, 20, 300);
		hitButton.setVisible(true);
		stayButton.setVisible(true);
		dealercount.setVisible(false);
	}
	
	public void blackjackPlayerNotification(int wins2, double winPercent, double money2) {
		wins = wins2;
		percentWin2 = winPercent;
		status.setText("Player has blackjack! Click \"Reset\" to play again." );
		percentWin.setText(String.format("Win %%= %4.1f %%", percentWin2));
		dealercount.setVisible(true);
		win.setText( "W = " + wins);
		moneyLabel.setText(String.format("Money = $%4.2f", money));	
	}
	
	public void blackjackDealerNotification(int losses2, double winPercent, double money2) {
		losses = losses2;
		money = money2;
		status.setText("Dealer has blackjack! Click \"Reset\" to play again");
		percentWin2 = winPercent;
		percentWin.setText(String.format("Win %%= %4.1f %%", percentWin2));
		dealercount.setVisible(true);
		loss.setText( "L = " + losses2);
		moneyLabel.setText(String.format("Money = $%4.2f", money));
	}
	
	public void bothBlackjackNotification (double winPercent) {
		status.setText("You both have blackjack! Click \"Reset\" to play again");
		percentWin2 = winPercent;
		dealercount.setVisible(true);
		percentWin.setText(String.format("Win %%= %4.1f %%", percentWin2));
	}
	
	/**
	 * sets up the UI to show you lose
	 * @param money 
	 * @param losses
	 * @param Win percentage
	 */
	public void youLoseNotification(int losses2, double winPercent, double money2) {
		losses = losses2;
		money = money2;
		status.setText("Loser! Click \"Reset\" to play again");
		percentWin2 = winPercent;
		percentWin.setText(String.format("Win %%= %4.1f %%", percentWin2));
		loss.setText( "L = " + losses2);
		moneyLabel.setText("Money = $" + money);

	}
	
	/**
	 * sets up the UI to show you win
	 * @param wins
	 * @param Win percentage
	 */
	public void youWinNotification(int wins2, double winPercent, double money2) {
		wins = wins2;
		money = money2;
		status.setText("Winner! Click \"Reset\" to play again");
		percentWin2 = winPercent;
		percentWin.setText(String.format("Win %%= %4.1f %%", percentWin2));
		win.setText( "W = " + wins2);
		moneyLabel.setText("Money = $" + money);
	}
	
	/**
	 * sets up the UI to show you tied
	 * @param Win percentage
	 */
	public void tieNotification(double winPercent) {
		status.setText("You Tied! Click \"Reset\" to play again");
		percentWin2 = winPercent;
		percentWin.setText(String.format("Win %%= %4.1f %%", percentWin2));
	}
	
	/**
	 * sets up the UI to show everyone loses
	 * @param losses
	 * @param Win percentage
	 */
	public void everybodyLosesNotification(int loss2, double winPercent) {
		losses = loss2;
		status.setText("Both you and the dealer have busted. Click \"Reset\" to play again.");
		percentWin2 = winPercent;
		percentWin.setText(String.format("Win %%= %4.1f %%", percentWin2));
		loss.setText( "L = " + losses);
	}
	
	/**
	 * CHanges the UI when the player busts
	 */
	public void playerBusted() {
		status.setText("You are busted! Hit \"stay\" to continue."); 
		disableHit();
	}
	
	/**
	 * flips the dealer's first card face up
	 * @param card
	 */
	public void flipDealerCard(Card card2) {
		if(!card2.ifFaceUp()){
			card2.flipCard();
		}
		bc.add(card2.getImage(), DEALER_CARD_INITIAL_WIDTH+80, DEALER_CARD_INITIAL_HEIGHT);
	}
	
	/**
	 * sets the UI when a player hits
	 * @param player's count
	 * @param card to be added to canvas
	 */
	public void playerHitNotification(int count, Card card) {
		status.setText("Hit!");
		intYourCount = count;
		yourcount.setText("Your Count: " + count);
		playerHandSize++;
		bc.add(card.getImage(), PLAYER_CARD_INITIAL_WIDTH+(80*playerHandSize), PLAYER_CARD_INITIAL_HEIGHT);
	}
	
	/**
	 * sets the UI when the dealer hits
	 * @param dealer's count
	 * @param card to be added to canvas
	 */
	public void dealerHitNotification(int dealerCount, Card card) {
		intDealerCount = dealerCount;
		dealerHandSize++;
		status.setText("Dealer's turn...");
		dealercount.setText("Dealer Count: " + intDealerCount );
		bc.add(card.getImage(), DEALER_CARD_INITIAL_WIDTH+(80*dealerHandSize), DEALER_CARD_INITIAL_HEIGHT);
	}

	//   public void addCard(Card card) {
	//	   bc.add(card.getImage(), PLAYER_CARD_INITIAL_WIDTH+(50*playerHandSize), PLAYER_CARD_INITIAL_HEIGHT);
	//	   bc.add(card.getImage(), DEALER_CARD_INITIAL_WIDTH+(50*dealerHandSize), DEALER_CARD_INITIAL_HEIGHT);
	//   }

	// class constants
	private static final int APPLICAITON_WIDTH = 1000;// initial window width
	private static final int APPLICATION_HEIGHT = 750;// initial window height
	private static final Font LABEL_FONT = new Font("Helvetica", Font.BOLD, 24);

	//CONSTANTS FOR STARTING DIMENSIONS TO ADD CARDS

	private static final int PLAYER_CARD_INITIAL_WIDTH = 100;
	private static final int PLAYER_CARD_INITIAL_HEIGHT = 100;
	private static final int DEALER_CARD_INITIAL_WIDTH = 100;
	private static final int DEALER_CARD_INITIAL_HEIGHT = 250;

	// instance variables

	private Blackjack bc;		// blackjack controller
	private int wins;           // the number of wins
	private int losses;         // the number of losses  
	private int playerHandSize;	//player hand size
	private int dealerHandSize;	//dealer hand size
	private int intYourCount;	//player's count
	private int intDealerCount;	//dealer's count
	private double percentWin2;	// win percentage
	private double bet;
	private double money;

	// UI variables
	private JButton hitButton;	// hit button
	private JButton stayButton;	// stay button
	private JLabel playerLabel;	// "player" label
	private JLabel dealerLabel;	// "dealer" label
	private JLabel yourcount;	// player's count
	private JLabel dealercount;	// dealer's count

	private JLabel status;		// status message
	private JLabel win;			//wins label
	private JLabel loss;		//losses label
	private JLabel percentWin;	//win percentage label
	private JLabel moneyLabel;
	private JLabel betLabel;
	private JComboBox betChooser;
	


}