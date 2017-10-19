package canvas;

import common.*;
import entities.Textures;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

public class Canvas extends JPanel {
	private GameController game = null;
	private Timer timer;

	public Canvas() {
		setBackground(Color.black);
		setPreferredSize(new Dimension(1280, 720));

		timer = new Timer(16, e -> {
			if (game != null) {
				game.tick(16.0 / 1000, game);
				repaint();
			}
		});
	}

	public void setController(GameController game) {
		this.game = game;
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
		drawBackground(g2);

		for (Renderable entity : getEntitiesInView()) {
			Vector pos = entity.getBoundingBox().getMin();
			int x = (int) Math.round(pos.x);
			int y = (int) Math.round(pos.y);
			g.translate(x, y);
			entity.draw(g);
			g.translate(-x, -y);
		}
	}
	
	private void drawBackground(Graphics2D g2) {
        Vector min = game.getViewport().getMin();
        Vector size = game.getViewport().getSize();
        BufferedImage img = Textures.BACKDROP;
        
        g2.translate(min.x, min.y);
        
        int width = img.getWidth();
        int height = img.getHeight();
        for(double x = -min.x%size.x-size.x; x < size.x; x+=width) {
            boolean yy = false;
            for(double y = -min.y%size.y-size.y; y < size.y; y+=height) {
                if(yy) {
                    g2.drawImage(img, (int) x, (int) y+height, width, -height, null);
                } else {
                    g2.drawImage(img, (int) x, (int) y, width, height, null);
                }
                yy = !yy;
            }
        }
        
        g2.translate(-min.x, -min.y);
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
