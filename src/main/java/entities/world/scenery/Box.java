package entities.world.scenery;

import common.AABB;

import java.awt.*;

public class Box extends Scenery {
	public Box(AABB boundingBox) {
		super(boundingBox);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, 100, 100);
	}
}
