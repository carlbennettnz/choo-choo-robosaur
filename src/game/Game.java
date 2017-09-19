package game;

import ui.UI;

public class Game {
	private final UIController controller;
	private final GameController state;
	private final UI ui;

	public Game() {
		this.controller = new UIController();
		this.state = new GameController();

		this.ui = new UI(this.controller, this.state);
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
