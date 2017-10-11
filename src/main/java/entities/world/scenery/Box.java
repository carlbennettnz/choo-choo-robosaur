package entities.world.scenery;

import common.Collidable;
import common.GameController;
import common.Positionable;
import common.Vector;

import java.awt.*;

public class Box extends Scenery {
	public Box(Positionable position) {
		super(position);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, 100, 100);
	}
}
