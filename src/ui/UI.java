package ui;

import game.GameController;
import game.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class UI extends JComponent {
	private final GameController game;
	private final GameStateManager state;

	public UI(GameController game, GameStateManager state) {
		this.game = game;
		this.state = state;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
