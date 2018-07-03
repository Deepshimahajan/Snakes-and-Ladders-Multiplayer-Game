package COEN275;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class Dice {
	
	private Random rollDice;
	private JLabel diceImage;
	private BoardManager boardManager;
	
	Dice(JLabel diceImage, BoardManager boardManager){
		this.boardManager = boardManager;
		this.diceImage = diceImage;
		rollDice = new Random(System.currentTimeMillis());
	}
	
	int rollDice(){
		return rollDice.nextInt(6) + 1;
	}

	void showAnimation(int number){
		Timer timer = new Timer(75, new ActionListener(){
			private int counter;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(counter<20){
					counter++;
					int i = (int) Math.floor(Math.random() * 6);
					diceImage.setIcon(new ImageIcon("images/dice" + i + ".jpg"));
				}
				else {
					((Timer)e.getSource()).stop();
					diceImage.setIcon(new ImageIcon("images/dice" + number + ".jpg"));
					boardManager.move(number);
				}
			}
		});
		timer.start();
	}
}
