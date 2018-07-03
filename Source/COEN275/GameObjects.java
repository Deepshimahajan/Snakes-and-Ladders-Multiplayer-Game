package COEN275;

class GameObjects {
	private int type; // 0 for snakes; 1 for ladders
	private int start;
	private int end;
	GameObjects(int start, int end) {
		this.start = start;
		this.end = end;
		this.type = start>end ? 0 : 1;
	}
	int getType() {
		return type;
	}
	int getStart() {
		return start;
	}
	int getEnd() {
		return end;
	}
}
