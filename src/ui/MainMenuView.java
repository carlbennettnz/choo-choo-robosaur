package ui;

import game.UIController;

import javax.swing.*;

class MainMenuView extends JComponent {
	private final UIController game;

	MainMenuView(UIController game) {
		this.game = game;

		// draw menu options here, hooking their events to
		// method calls on `game`
	}
}
