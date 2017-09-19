package gameview;

import game.GameController;

import javax.swing.*;
import java.awt.*;

public abstract class GameView extends JComponent {
	private final GameController state;

	public GameView(GameController state) {
		this.state = state;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
