package COEN275;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel {
	private BufferedImage image;
	private LinkedList<Player> players;
	private String imageName;

	Board(LinkedList<Player> players) {
		Random rand = new Random();
		int  n = rand.nextInt(7) + 1;
		//n = 7;
		imageName = "docs/SNL" + String.valueOf(n);
		this.players = players;
		try {
			image = ImageIO.read(new File(imageName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
	@Override
	protected void paintComponent(Graphics g) {
		try {
			image = ImageIO.read(new File(imageName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);

		int[] position;
		int[] offsetX = new int[] {1, 20, 1, 20};
		int[] offsetY = new int[] {1, 1, 20, 20};
		for(Player player: players) {
			System.out.println(player.getName());

			position = calculatePosition(player.getPosition());

			g.setColor(player.getColor());
			g.fillOval(position[0]+offsetX[player.getSequence()-1],
					position[1]+offsetY[player.getSequence()-1],
					19, 19); //create oval
			g.setColor(Color.BLACK);
			g.drawOval(position[0]+offsetX[player.getSequence()-1],
					position[1]+offsetY[player.getSequence()-1],
					19, 19);
			if(player.getName().equals("Player 5") )
				g.setColor(Color.BLACK);
			else
				g.setColor(Color.WHITE);
			g.drawString(player.getName().substring((player.getName().length() - 1)),
					position[0]+offsetX[player.getSequence()-1] + 6,
					position[1]+offsetY[player.getSequence()-1] + 14);
		}
	}
	
	void updatePositions () {
		repaint();
	}
	
	private int[] calculatePosition(int pos) {
		int x;
		int y;
		
		int row = 9 - (pos - 1)/10; //0 to 9
		int col = pos - ((pos - 1)/10)*10 -1  ; //0 - 9
		if(row%2 == 0) {
			col = 9 - col;
		}
		x = col * 40;
		y = row * 40;
		
		return  new int[] {x, y};
	}
	
	String getImageName() {
		return imageName;
	}
	
	void setImageName(String imageName) {
		this.imageName = imageName;
	}

	void setPlayers(LinkedList<Player> players) {
		this.players= players;
	}
	
}
