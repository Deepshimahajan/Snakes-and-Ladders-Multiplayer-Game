package COEN275;

public class Main {
	public static void main (String[] args) {
		try {
			new Game();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
