package ui;

import game.GameController;

import javax.swing.*;
import java.awt.*;

class MainMenuView extends JComponent {
	private final GameController game;

	MainMenuView(GameController game) {
		this.game = game;

		// draw menu options here, hooking their events to
		// method calls on `game`
	}
}
