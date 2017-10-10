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
		g.setColor(Color.red);
		((Graphics2D) g).fill(new Rectangle2D.Double(0, 0, size.x, size.y));

	}
}
