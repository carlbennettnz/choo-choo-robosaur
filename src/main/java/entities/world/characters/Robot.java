package entities.world.characters;

import common.AABB;
import common.GameController;
import common.Positionable;
import common.Vector;
import entities.properties.Shooter;
import entities.world.damaging.Bullet;
import physics.PhysObject;

import java.awt.*;

public class Robot extends Character implements Shooter {
	public Robot(Positionable position, CharacterController controller, int maxHealth) {
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
		AABB box = getBoundingBox();
		PhysObject bpo = new PhysObject(new AABB(box.center.add(new Vector(box.halfSize.x * direction + 10, 0)), new Vector(10, 4)), 0);
		bpo.setVelocity(new Vector(400 * direction, 0));
		Bullet b = new Bullet(bpo, 10);
		game.addEntity(b, bpo);
	}
}
