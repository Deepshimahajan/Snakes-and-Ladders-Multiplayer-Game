package COEN275;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

@SuppressWarnings("rawtypes")
class BoardManager extends JPanel {
	private ArrayList<GameObjects> gameObjects;
	private JLabel title;
	private JLabel diceImage;
	private Thread t1;
	private Game game;

	Board board;
	LinkedList<Player> players;
	Player currPlayer;
	JButton diceButton;
	JButton saveButton;
	JButton quitButton;
	JLabel comment;



	BoardManager(int noOfPlayers, Game game) {
		this.game = game;
		players = new LinkedList<>();
		Color[] colors;
		colors = new Color[]{ Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK };
		for (int i = 1; i <= noOfPlayers; i++) {
			players.add(new Player("Player " + i, colors[i - 1], i));
		}

		gameObjects = new ArrayList<GameObjects>();
		currPlayer = players.peek();
		game.setContentPane(this);

		comment = new JLabel("Welcome! It's " + currPlayer.getName() + " turn");
		comment.setFont(new Font("Serif", Font.PLAIN, 20));

		title = new JLabel("Snakes and Ladders");
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		title.setHorizontalAlignment(SwingConstants.CENTER);


		JPanel titlePanel = new JPanel();
		titlePanel.add(title);

		setLayout(new BorderLayout(3, 3));
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
		
		add(comment);
		//comment.setBounds(230, 525, 300, 30);
		comment.setHorizontalAlignment(SwingConstants.CENTER);
		comment.setVerticalAlignment(SwingConstants.CENTER);
		comment.setBounds(500, 270, 300, 30);

		add(titlePanel, BorderLayout.NORTH);
		add(jPanel, BorderLayout.EAST);
		
		diceButton = new JButton("Roll Dice");
		add(diceButton);
		diceButton.setBounds(580, 110, 150, 50);
		diceButton.setBackground(new Color(200, 84, 0));
		diceButton.setContentAreaFilled(false);
		diceButton.setBorderPainted(false);
		diceButton.setOpaque(true);

		diceImage = new JLabel("");
		diceImage.setBounds(630, 180, 150, 50);
		add(diceImage);

		saveButton = new JButton("Save Game");
		add(saveButton);
		saveButton.setBounds(580,350,150,50);
		saveButton.setBackground(new Color(143, 214, 195));
		saveButton.setContentAreaFilled(false);
		saveButton.setBorderPainted(false);
		saveButton.setOpaque(true);
		
		quitButton = new JButton("Quit Game");
		quitButton.setBounds(580,460,150,50);
		add(quitButton);
		quitButton.setBackground(new Color(200, 84, 0));
		quitButton.setContentAreaFilled(false);
		quitButton.setBorderPainted(false);
		quitButton.setOpaque(true);

		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(null);
		board = new Board(players);
		board.setBounds(100, 50, 400, 400);
		boardPanel.add(board);
		add(boardPanel);

		Runnable r1 = new Blinky(diceButton);
		t1 = new Thread(r1);
		t1.start();

		new GameListener(this, diceImage, t1, game);

		getGameObjects();
	}

	void move(int steps) {
		diceButton.setEnabled(false);
		saveButton.setEnabled(false);
		int oldPosition = currPlayer.getPosition();
		int newPosition = currPlayer.getPosition() + steps;
		currPlayer.setPosition(newPosition);
		board.updatePositions();
		int jumpTo = checkGameObjects(newPosition);
		currPlayer.setPosition(newPosition);
		if(currPlayer.getPosition() > 100){
			currPlayer.setPosition(oldPosition);
			comment.setText(currPlayer.getName() + " cannot make this move.");
			super.update(this.getGraphics());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if(currPlayer.getPosition() == 100){
			currPlayer.setPosition(100);
			board.updatePositions();
			comment.setText(currPlayer.getName() + " is the winner.");
			super.update(this.getGraphics());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Winner Winner Chicken Dinner\n" +
					"Congrats " + currPlayer.getName() + ". You won the game.");
			try {
				Welcome welcome = new Welcome(game);
				game.setContentPane(welcome);
				game.setVisible(true);
			} catch (Exception exp) {
				exp.getStackTrace();
			}
		}
		else{
			board.updatePositions();
			if (jumpTo > newPosition) {
				comment.setText("Congrats, you reach a ladder!");
				super.update(this.getGraphics());
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				currPlayer.setPosition(jumpTo);
				board.updatePositions();
			}
			if (jumpTo < newPosition) {
				comment.setText("Oh, you are caught up by a snake!");
				super.update(this.getGraphics());
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				currPlayer.setPosition(jumpTo);
				board.updatePositions();
			}
		}
		Player temp = players.removeFirst();
		currPlayer = players.peek();
		if (currPlayer != null) {
			comment.setText("It's " + currPlayer.getName() + "'s turn.");
		}

		players.add(temp);
		diceButton.setEnabled(true);
		saveButton.setEnabled(true);
	}

	private int checkGameObjects(int nP) {
		for (GameObjects gObjects : gameObjects) {
			if (gObjects.getStart() == nP) {
				return gObjects.getEnd();
			}
		}
		return nP;
	}

	private void getGameObjects() {
		gameObjects = new ArrayList<GameObjects>();
		File file = new File(board.getImageName() + ".txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String move;
			while ((move = br.readLine()) != null) {
				if (move.split("-").length == 2) {
					if (move.split("-")[0].matches("[0-9]+") && move.split("-")[1].matches("[0-9]+")) {
						gameObjects.add(new GameObjects(Integer.parseInt(move.split("-")[0]),
								Integer.parseInt(move.split("-")[1])));
					} else {
						System.out.println("Error: Invalid File");
						System.exit(1);
					}
				} else {
					System.out.println("Error: Invalid File");
					System.exit(1);
				}
			}
		} catch (Exception e) {
			System.out.println("Error: Invalid File");
			System.exit(1);
		}
		for (GameObjects go : gameObjects) {
			System.out.print(go.getType() + ":" + go.getStart() + "-" + go.getEnd() + "\n");
		}
	}

	void saveGame() {
		PrintWriter writer;
		Object saveFileName = JOptionPane.showInputDialog(this, "Enter a File name:",
				"Save Game", JOptionPane.PLAIN_MESSAGE);
		if(saveFileName != null){
			if(saveFileName.equals("")){
				JOptionPane.showMessageDialog(this,
						"ERROR: Cannot save a game without a file name!");
			}
			else{
				try {
					writer = new PrintWriter("savegame/" + saveFileName + ".txt", "UTF-8");
					writer.println(board.getImageName());
					LinkedList<Player> temp = (LinkedList) players.clone();
					while(!temp.isEmpty()) {
						Player curr = temp.poll();
						if (curr != null) {
							writer.println(curr.getName() + ";" + curr.getPosition() + ";" + curr.getSequence());
						}
					}
					writer.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
