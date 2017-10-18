package entities.world.scenery;

import common.AABB;
import common.Vector;

import java.awt.*;

public class Crate extends Scenery {
	public Crate(AABB boundingBox) {
		super(boundingBox);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.green);
		Vector size = getBoundingBox().getSize();
		g.fillRect(0, 0, (int) size.x, (int) size.y);
	}
}
