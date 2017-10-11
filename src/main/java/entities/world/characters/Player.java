package entities.world.characters;

import common.AABB;
import common.Positionable;
import common.Vector;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player extends Character {
	public Player(Positionable position, CharacterController controller, int maxHealth) {
		super(position, controller, maxHealth);
	}

	@Override
	public void draw(Graphics g) {
		Vector size = getBoundingBox().getSize();
		g.setColor(new Color((int) ((double) health / maxHealth * 255), 0, 0));
		((Graphics2D) g).fill(new Rectangle2D.Double(0, 0, size.x, size.y));
		g.setColor(Color.white);
		g.drawString(String.valueOf(health), 0, 120);
	}
}
