package game;

import ui.UI;

public class Game {
	private final GameController controller;
	private final GameStateManager state;
	private final UI ui;

	public Game() {
		this.controller = new GameController();
		this.state = new GameStateManager();

		this.ui = new UI(this.controller, this.state);
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
