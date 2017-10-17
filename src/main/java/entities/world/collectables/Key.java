package entities.world.collectables;

import common.AABB;
import common.Vector;

import java.awt.*;

public class Key extends BaseCollectable {
	public Key(Vector pos) {
		super(new AABB(pos, new Vector(10, 10)));
	}

	@Override
	public void draw(Graphics g) {

	}
}
