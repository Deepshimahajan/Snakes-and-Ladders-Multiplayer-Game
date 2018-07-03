package COEN275;

import java.awt.*;
import javax.swing.JFrame;

class Game extends JFrame {

	Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		getContentPane().setBackground(new Color(45, 122, 67));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		startGame();
		setVisible(true);
	}

	void startGame() {
		Welcome newWelcome = new Welcome(this);
		add(newWelcome);
		setVisible(true);
	}
}
 