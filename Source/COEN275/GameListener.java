package COEN275;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameListener implements ActionListener {
	private Dice dice;
	private BoardManager bManager;
	private Thread t;
	private Game game;
	
	
	GameListener(BoardManager bManager, JLabel diceImage, Thread t, Game game) {
		this.t = t;
		this.bManager = bManager;
		this.game = game;
		bManager.diceButton.addActionListener(this);
		bManager.saveButton.addActionListener(this);
		bManager.quitButton.addActionListener(this);
		dice = new Dice(diceImage, bManager);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
        if (src == bManager.diceButton) {
        	bManager.diceButton.setEnabled(false);
			int number = dice.rollDice();
			dice.showAnimation(number);
			bManager.diceButton.setEnabled(true);
        } else if (src == bManager.saveButton) {
        	bManager.saveGame();
        } else if (src == bManager.quitButton) {
			try {
				Welcome welcome = new Welcome(game);
				game.setContentPane(welcome);
				game.setVisible(true);
			} catch (Exception exp) {
				exp.getStackTrace();
			}
        }
	}
}
