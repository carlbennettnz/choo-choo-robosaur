package canvas;

import common.Renderable;
import common.GameController;
import common.AABB;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class Canvas extends java.awt.Canvas {
	private final GameController game;

	public Canvas(GameController game) {
		this.game = game;
		setBackground(Color.black);
	}

	public void paint(Graphics g) {
		switch (game.getStatus()) {
			case GAME_TITLE:
				drawGameTitle(g);
				break;

			case LEVEL_TITLE:
				drawLevelTitle(g);
				break;

			case PLAY:
				drawGame(g);
				break;

			case END_SCREEN:
				drawEndScreen(g);
				break;
		}
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
		g.setColor(Color.white);
		g.drawChars(title.toCharArray(), 0, title.length(), 20, size.height / 2);
	}

	void drawGame(Graphics g) {
		for (Renderable entity: getEntitiesInView()) {
			entity.draw(g);
		}
	}

	List<Renderable> getEntitiesInView() {
		List<Renderable> entities = game.getEntities();
		AABB viewport = game.getViewport();

		return entities
			.stream()
			.filter(r -> r.getBoundingBox().intersects(viewport))
			.collect(Collectors.toList());
	}

	public void update(Graphics g) {
		Image offscreen = getOffscreenImage();
		Graphics og = offscreen.getGraphics();
		paint(og);
		g.drawImage(offscreen, 0, 0, this);
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
