package gameview;

import game.GameStateManager;

import javax.swing.*;
import java.awt.*;

public abstract class GameView extends JComponent {
	private final GameStateManager state;

	public GameView(GameStateManager state) {
		this.state = state;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
