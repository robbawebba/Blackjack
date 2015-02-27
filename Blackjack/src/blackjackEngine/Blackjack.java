package blackjackEngine;
import java.awt.event.ActionEvent;
import acm.program.*;

/**
* Main Blackjack program and controller
* @author Robert Weber & Michael Mulvaney
*/
@SuppressWarnings("serial")
public class Blackjack extends GraphicsProgram {

    /**
     * @param args
     */
    public static void main(String[] args) {
        (new Blackjack()).start();
    }

    /**
     * constructor for the blackjack controller
     */
    public Blackjack() {
    	bv = new BlackjackView(this);   
        bm = new BlackjackModel(bv);
    }
    
    /**
     * initializes the canvas
     */
    public void init () {
    	addActionListeners();
		addKeyListeners();
    }
    
    /**
     * runs the game
     */
    public void run() {
        bm.initializeGame(bv.getCurrentBet());
    }

    /**
     * handles the actions performed on the UI
     * @param event that occurred in the UI
     */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Hit")) {
            bm.hitMe();
        } if (command.equals("Stay")) {
        	bv.dealerTurn();
            bm.playDealer();
            }
        if (command.equals("Reset")) {
        	bv.resetGame();
        	bm.resetGame();
        	bm.initializeGame(bv.getCurrentBet());
        }
        }

    private BlackjackModel bm; // blackjack model
    private BlackjackView bv;  //blackjack view

}