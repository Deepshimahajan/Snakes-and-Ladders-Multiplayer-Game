package COEN275;

import java.awt.Color;

class Player {
	private String name;
	private int position;
	private int sequence;
	private Color color;

	Player(String name, Color color, int sequence){
		this.position = 0;
		this.name = name;
		this.color = color;
		this.sequence = sequence;
	}
	
	Player(String name, int position, int sequence){
		Color[] colors;
		colors = new Color[]{ Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA };
		colors = new Color[]{ Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK };
		this.position = position;
		this.name = name;
		this.color = colors[sequence - 1];
		this.sequence = sequence;
	}

	int getPosition() {
		return position;
	}

	void setPosition(int position) {
		this.position = position;
	}

	Color getColor() {
		return color;
	}

	int getSequence() {
		return sequence;
	}

	String getName() {
		return name;
	}
}
