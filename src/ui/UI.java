package ui;

import game.UIController;
import game.GameController;

import javax.swing.*;
import java.awt.*;

public class UI extends JComponent {
	private final UIController game;
	private final GameController state;

	public UI(UIController game, GameController state) {
		this.game = game;
		this.state = state;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
