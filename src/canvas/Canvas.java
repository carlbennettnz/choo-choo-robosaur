package canvas;

import game.GameController;

import javax.swing.*;
import java.awt.*;

public abstract class Canvas extends JComponent {
	private final GameController state;

	public Canvas(GameController state) {
		this.state = state;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
