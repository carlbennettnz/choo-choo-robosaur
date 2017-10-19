package entities.world.characters;

import common.AABB;
import common.GameController;
import common.Physical;
import common.Vector;
import entities.properties.Shooter;
import entities.world.damaging.Bullet;

import java.awt.*;

public class Robot extends Character implements Shooter {
	public Robot(Vector position, CharacterController controller, int maxHealth) {
		super(position, controller, maxHealth);
	}

	@Override
	public void draw(Graphics g) {
		AABB box = getBoundingBox();
		g.setColor(Color.gray);
		g.fillRect(0, 0, (int) Math.round(box.halfSize.x * 2), (int) Math.round(box.halfSize.y * 2));
	}

	@Override
	public void shoot(int direction, GameController game) {
		direction = direction < 0 ? -1 : 1;
		double halfWidth = getBoundingBox().halfSize.x;
		Vector robotPos = getPosition();

		Vector bulletPos = robotPos.add(new Vector(halfWidth * direction + 10, 0));

		Bullet b = new Bullet(bulletPos, 10, direction);
		game.addEntity(b);
	}

	@Override
	public void collide(Physical o, Vector collision) {

	}
}
