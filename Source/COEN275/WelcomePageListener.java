package COEN275;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePageListener implements ActionListener{
	private Welcome welcome;

	WelcomePageListener(Welcome welcome) {
		this.welcome = welcome;
		welcome.newGame.addActionListener(this);
		welcome.loadGame.addActionListener(this);
		welcome.instructions.addActionListener(this);
		welcome.quitGame.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
        if (src == welcome.newGame) {
        	welcome.selectPlayers();
        } else if (src == welcome.loadGame) {
        	welcome.getFile();
        } else if (src == welcome.instructions) {
        	welcome.showInstructions();
        } else if (src == welcome.quitGame) {
        	welcome.quitGame();
        }
	}
} 
