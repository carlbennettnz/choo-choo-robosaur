package canvas;

import common.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.stream.Collectors;

public class Canvas extends JPanel {
	private final GameController game;
	private Timer timer;

	public Canvas(GameController game) {
		this.game = game;
		setBackground(Color.black);
		setPreferredSize(new Dimension(1280, 720));

		timer = new Timer(16, e -> {
			game.tick(16.0/1000, game);
			repaint();
		});

		timer.start();
	}

	public void paintComponent(Graphics g) {
		Image offscreen = getOffscreenImage();
		Graphics og = offscreen.getGraphics();

		switch (game.getStatus()) {
			case GAME_TITLE:
				drawGameTitle(og);
				break;

			case LEVEL_TITLE:
				drawLevelTitle(og);
				break;

			case PLAY:
				drawGame(og);
				break;

			case END_SCREEN:
				drawEndScreen(og);
				break;
		}

		g.drawImage(offscreen, 0, 0, this);
	}

	void drawGameTitle(Graphics g) {
		drawTitle(g, "Choo Choo Robosaur");
	}

	void drawLevelTitle(Graphics g) {
		drawTitle(g, "Level " + game.getLevel());
	}

	void drawEndScreen(Graphics g) {
		drawTitle(g, "End");
	}

	void drawTitle(Graphics g, String title) {
		Dimension size = getSize();
		int fontSize = 40;
		Font font = new Font("Arial", Font.BOLD, fontSize);
		FontMetrics metrics = getFontMetrics(font);
		int titleWidth = metrics.stringWidth(title);

		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(title, (size.width - titleWidth) / 2, (size.height + metrics.getHeight()) / 2);
	}

	void drawGame(Graphics g) {
		Vector topLeft = game.getViewport().getMin();
		g.translate((int) Math.round(-topLeft.x), (int) Math.round(-topLeft.y));

		Graphics2D g2 = (Graphics2D) g;

		g2.setPaint(new GradientPaint(-5000, 0, new Color(0, 20, 50), 5000, 0, new Color(20, 60, 230)));
		g2.fill(new Rectangle2D.Double(-5000, topLeft.y, 10000, getHeight()));

		for (Renderable entity : getEntitiesInView()) {
			Vector pos = entity.getBoundingBox().getMin();
			int x = (int) Math.round(pos.x);
			int y = (int) Math.round(pos.y);
			g.translate(x, y);
			entity.draw(g);
			g.translate(-x, -y);
		}
	}

	List<Renderable> getEntitiesInView() {
		List<? extends Renderable> entities = game.getEntities();
		AABB viewport = game.getViewport();

		return entities
			.stream()
			.filter(r -> r.getBoundingBox().intersects(viewport))
			.collect(Collectors.toList());
	}

	Image getOffscreenImage() {
		Dimension d = getSize();
		Image offscreen = createImage(d.width, d.height);
		Graphics og = offscreen.getGraphics();
		og.setColor(getBackground());
		og.fillRect(0, 0, d.width, d.height);

		return offscreen;
	}
}
