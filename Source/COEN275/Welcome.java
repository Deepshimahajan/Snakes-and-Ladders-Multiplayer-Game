package COEN275;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class Welcome extends JPanel {
	JButton newGame;
	JButton loadGame;
	JButton instructions;
	JButton quitGame;

	private Game game;
	private JLabel title;
	private JLabel lblNewLabel;

	Welcome(Game game) {
		this.game = game;
		setLayout(null);
		
		title = new JLabel("Snakes and Ladders");
		title.setBounds(250,50,300,100);
		title.setFont(new Font("Comic Sans MS", Font.CENTER_BASELINE, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.darkGray);

		newGame = new JButton("New Game");
		newGame.setBounds(250,130,300,50);
		newGame.setBackground(new Color(200, 84, 0));
		newGame.setContentAreaFilled(false);
		newGame.setBorderPainted(false);
		newGame.setOpaque(true);

		loadGame = new JButton("Load Game");
		loadGame.setBounds(250,220,300,50);
		loadGame.setBackground(new Color(143, 214, 195));
		loadGame.setContentAreaFilled(false);
		loadGame.setBorderPainted(false);
		loadGame.setOpaque(true);
		
		instructions = new JButton("Instructions");
		instructions.setBounds(250,310,300,50);
		instructions.setBackground(new Color(200, 84, 0));
		instructions.setContentAreaFilled(false);
		instructions.setBorderPainted(false);
		instructions.setOpaque(true);
	
		quitGame = new JButton("Quit Game");
		quitGame.setBounds(250,400,300,50);
		quitGame.setBackground(new Color(143, 214, 195));
		quitGame.setContentAreaFilled(false);
		quitGame.setBorderPainted(false);
		quitGame.setOpaque(true);

		add(title);
		
		lblNewLabel = new JLabel("New label");
		add(lblNewLabel);
		add(newGame);
		add(loadGame);
		add(instructions);
		add(quitGame);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("images/snake.jpg"));
		lblNewLabel_1.setBounds(0, -8, 800, 600);
		add(lblNewLabel_1);

		new WelcomePageListener(this);
	}

	void showInstructions() {
		new Instructions(this.game);
	}

	void quitGame() {
		System.exit(1);
	}
	 
	
	void selectPlayers() {
		Object[] options = { "4 Players", "3 Players", "2 Players" };
	
		ImageIcon icon = new ImageIcon("images/icon.jpeg");
		int n = JOptionPane.showOptionDialog(this, "Select the Number of Players", "Player Options",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon, options, options[2]);
		int noOfPlayers = 0;
		if (n == JOptionPane.YES_OPTION) {
			noOfPlayers = 4;
		} else if (n == JOptionPane.NO_OPTION) {
			noOfPlayers = 3;
		} else if (n == JOptionPane.CANCEL_OPTION){
			noOfPlayers = 2;
		}
		if (noOfPlayers != 0) {
			new BoardManager(noOfPlayers, game);
		}
	}

	void getFile() {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String line;
			String boardName = "docs/SNL1";
			LinkedList<Player> players = new LinkedList<>();

			File file = fc.getSelectedFile();
			System.out.println(file.getName());
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(file));
				try {
					boardName = br.readLine();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					while ((line = br.readLine()) != null) {
						if(line.split(";").length == 3) {
							players.add(new Player(line.split(";")[0],
									Integer.valueOf(line.split(";")[1]),
									Integer.valueOf(line.split(";")[2])));
							System.out.println(line.split(";")[0]+ " " +
									(line.split(";")[1])+ " " + (line.split(";")[2]));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			BoardManager boardManager = new BoardManager(3, game);
			boardManager.players = players;
			boardManager.board.setImageName(boardName);
			boardManager.board.setPlayers(players);
			boardManager.currPlayer = players.peek();
			if (players.peek() != null) {
				boardManager.comment.setText("Welcome Back! It's "+ players.peek().getName() + "'s turn.");
			}
			boardManager.board.repaint();
		}
	}
}
