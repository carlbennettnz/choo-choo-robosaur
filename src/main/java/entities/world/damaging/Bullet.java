package entities.world.damaging;

import common.*;
import entities.properties.Damagable;
import entities.world.Entity;

import java.awt.*;

public class Bullet extends Entity {
	private int damage;

	public Bullet(Vector position, int damage, int direction) {
		super(new AABB(position, new Vector(10, 3)), 0);
		this.damage = damage;

		setVelocity(new Vector(400 * direction, 0));
	}

	public void collide(Physical entity, Vector[] collision) {
		if (entity instanceof Damagable) {
			Damagable d = (Damagable) entity;
			d.damage(damage);
		}

		removeOnNextTick = true;
	}

	@Override
	public void draw(Graphics g) {
		AABB box = getBoundingBox();
		g.setColor(Color.black);
		g.fillRect(0, 0, (int) Math.round(box.halfSize.x * 2), (int) Math.round(box.halfSize.y * 2));
	}

	@Override
	public void tick(double delta, GameController game) {

	}
}
