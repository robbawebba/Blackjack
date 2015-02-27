package blackjackEngine;
/**
 * model for the game Blackjack
 * @author Robert Weber & Michael Mulvaney
 */
public class BlackjackModel {

    /**
     * constructor for the model
     * @param blackjack view
     */
	public BlackjackModel(BlackjackView view) {
        bv = view;
        wins = losses = totalGames = 0;
        winPercent = 0.0;
        player = new Player(this);
        dealer = new Dealer(this);
        
    }
	
	/**
	 * initializes the game and deals cards
	 * to player and dealer
	 */
    public void initializeGame(double bet){

        deck = new Deck();
        deck.shuffle();
        
        player.setAnte(bet);
        
        //deal player's first card
        Card card1 = deck.deal();
        player.dealtCard(card1);
        bv.playerHitNotification(player.getValue(), card1);
        
        //deal dealer's first card
        Card card2 = deck.deal();
        firstCard = card2;
        card2.flipCard();
        dealer.dealtCard(card2);
        bv.dealerHitNotification(dealer.getValue(), card2);
        
        //deal player's second card
        Card card3 = deck.deal();
        player.dealtCard(card3);
        bv.playerHitNotification(player.getValue(), card3);
        
        //deal dealer's second card
        Card card4 = deck.deal();
        dealer.dealtCard(card4);
        bv.dealerHitNotification(dealer.getValue(), card4);
        
        totalGames++;
        bv.gameInitializedNotification(player.getMoney(), player.getAnte());
        
        if (player.hasBlackjack() && !dealer.hasBlackjack()) {
        	wins++;
        	winPercent = (((double)wins/totalGames)*100.0);
        	bv.flipDealerCard(firstCard);
        	bv.blackjackPlayerNotification(wins, winPercent, player.addMoney((player.getAnte()*(3/2))));
        }
        if (!player.hasBlackjack() && dealer.hasBlackjack()) {
        	losses++;
        	winPercent = (((double)wins/totalGames)*100.0);
        	bv.flipDealerCard(firstCard);
        	bv.blackjackDealerNotification(losses, winPercent,player.subMoney((player.getAnte())));
        	
        }
        if (player.hasBlackjack() && dealer.hasBlackjack()) {
        	winPercent = (((double)wins/totalGames)*100.0);
        	bv.flipDealerCard(firstCard);
        	bv.bothBlackjackNotification(winPercent);
        }
        
    }
    
    /**
     * resets the game by clearing the 
     * dealer's and player's hands
     */
    public void resetGame() {
		player.clearHand();
		dealer.clearHand();		
	}
    
    /**
     * adds card t player's hand and 
     * sends the card to the view
     */
    public void hitMe() {
        Card card =  deck.deal();
        player.dealtCard(card);
        bv.playerHitNotification(player.getValue(), card);
        if (player.isBusted()) {
        	bv.playerBusted();
        }
    }
    
    /**
     * adds cards to dealer's hand as long as his
     * count is less than 16, sends them
     * to the view, and determines who wins
     * or loses
     */
    public void playDealer() {    		
    	bv.flipDealerCard(firstCard); //flips dealer's first card
        while (dealer.getValue() <= 16) { //hit dealer until he is over 17
        	Card card = deck.deal();
           dealer.dealtCard(card);
           bv.dealerHitNotification(dealer.getValue(), card);
        }  
        if(dealer.isBusted() && player.isBusted()) { //everybody loses
        	losses++;
        	winPercent = (((double)wins/totalGames)*100.0);
        	bv.everybodyLosesNotification(losses, winPercent);
        } else if(!dealer.isBusted() && player.isBusted()) { // dealer wins
        	losses++;
        	winPercent = (((double)wins/totalGames)*100.0);
        	bv.youLoseNotification(losses, winPercent, player.subMoney(player.getAnte()));
        } else if( dealer.isBusted() && !player.isBusted()) { //player wins
        	wins++;
        	winPercent = (((double)wins/totalGames)*100.0);
        	bv.youWinNotification(wins, winPercent, player.addMoney(player.getAnte()));
        } else if( dealer.getValue() > player.getValue()) { //dealer wins
        	losses++;
        	winPercent = (((double)wins/totalGames)*100.0);
        	bv.youLoseNotification(losses, winPercent, player.subMoney(player.getAnte()));
        } else if( dealer.getValue() < player.getValue()) { //player wins
        	wins++;
        	winPercent = (((double)wins/totalGames)*100.0);
        	bv.youWinNotification(wins, winPercent, player.addMoney(player.getAnte()));
        } else if (dealer.hasBlackjack() && !player.hasBlackjack()) { //dealer wins
        	losses++;
        	winPercent = (((double)wins/totalGames)*100.0);
        	bv.youLoseNotification(losses, winPercent, player.subMoney(player.getAnte()));
        }else if (!dealer.hasBlackjack() && player.hasBlackjack()) { //player wins
        	wins++;
        	winPercent = ((double)wins/totalGames)*100.0;
        	bv.youWinNotification(wins, winPercent, player.addMoney(player.getAnte()));
        } else { //tie
        	winPercent = (((double)(wins)/totalGames)*100.0);
        	bv.tieNotification(winPercent);
        }
    }
 
    private double winPercent;	//win percentage
    private Card firstCard;		// dealer's first card
    private Deck deck;			//deck of cards
    private Dealer dealer;		//dealer
    private Player player;		//player
    private BlackjackView bv;	//blackjack view
    private int wins;			//wins
    private int losses;			//losses
    private int totalGames;		//total number of games
	

}