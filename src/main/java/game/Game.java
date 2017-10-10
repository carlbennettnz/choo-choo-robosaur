package game;

import ui.UI;

public class Game {
	private final UI ui;

	public Game() {
		this.ui = new UI();
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
