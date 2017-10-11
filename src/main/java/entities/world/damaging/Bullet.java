package entities.world.damaging;

import common.*;
import entities.properties.Damagable;
import entities.world.Entity;

import java.awt.*;

public class Bullet extends Entity {
	private int damage;

	public Bullet(Positionable position, int damage) {
		super(position);
		this.damage = damage;
	}

	@Override
	public void collide(Collidable entity, Vector vector) {
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
