package physics;

import common.AABB;
import common.Physical;
import common.Vector;

import java.util.ArrayList;
import java.util.List;

public class World {
	private List<Physical> objects;
	private List<Physical> toAdd;
	private List<Physical> toRemove;

	public World() {
		objects = new ArrayList<>();
		toAdd = new ArrayList<>();
		toRemove = new ArrayList<>();
	}

	public void addObject(Physical o) {
		toAdd.add(o);
	}

	public void removeObject(Physical o) {
		toRemove.add(o);
	}
	
	/**
	 * dt is the time passed, in seconds, since advance was last called.
	 * @param dt change in time (seconds)
	 */
	public void advance(double dt) {
		/* remove objects that are to be removed */
		objects.removeAll(toRemove);
		toRemove.clear();
		
		/* add objects that are to be added */
		objects.addAll(toAdd);
		toAdd.clear();
		
		/* advance all objects */
		for (Physical o : objects) {
			advance(o, dt);
		}
		
		/* resolve collisions between objects */
		for (int i = 0; i < objects.size(); i++) {
			Physical a = objects.get(i);
			for(int j = i + 1; j < objects.size(); j++) {
				Physical b = objects.get(j);
				Vector[] collision = resolveCollision(a, b);
				if (collision != null) {
					a.collide(b, collision);
					b.collide(a, collision);
				}
			}
		}
	}

	/**
	 * dt is the time passed, in seconds, since advance was last called.
	 * @param dt change in time (seconds)
	 */
	public void advance(Physical o, double dt) {
		o.setVelocity(o.getVelocity().add(o.getAcceleration().mult(dt)));
		o.setPosition(o.getPosition().add(o.getVelocity().mult(dt)));
		o.setAcceleration(Vector.zero());
	}

	/**
	 * Resolves a collision with the other Physical, if there is one.
	 * This means moving the two Physicals so that they are no longer
	 * colliding and also changing their velocities so that they are no longer
	 * moving towards each other.
	 * The return value is a vector array of the displacement that was caused due
	 * to the collision, or null if there was no collision. At index 0 is this
	 * bounding box's displacement and index 1 is the other box's displacement.
	 * @param o the other Physical
	 * @return array of two displacement vectors, or null.
	 */
	Vector[] resolveCollision(Physical a, Physical b) {
		AABB md = b.getBoundingBox().minkowskiDifference(a.getBoundingBox());

        /* no collision if true */
		if (md.getMin().x >= 0 || md.getMax().x <= 0 || md.getMin().y >= 0 || md.getMax().y <= 0)
			return null;

        /* calculate the overlapping vector */
		Vector penetrationVector = md.closestPointOnBoundsToPoint(Vector.zero());

        /* adjust the velocities accordingly */
		Vector tangent = penetrationVector.normalized().tangent();

		a.setVelocity(tangent.mult(a.getVelocity().dot(tangent)));
		b.setVelocity(tangent.mult(b.getVelocity().dot(tangent)));

		if (a.getMass() == 0 && b.getMass() == 0)
			return new Vector[] {Vector.zero(), Vector.zero()};

        /* move the center based on masses */
		double m = a.getMass() + b.getMass();

		double i = 0;
		double j = 0;

		if (b.getMass() == 0) {
			i = 1;
		} else if (a.getMass () == 0) {
			j = 1;
		} else {
			i = b.getMass() / m;
			j = a.getMass() / m;
		}

		Vector A = penetrationVector.mult(i);
		Vector B = penetrationVector.mult(-j);

		a.setPosition(a.getPosition().add(A));
		b.setPosition(b.getPosition().add(B));

		return new Vector[] {A, B};
	}

	public List<Physical> getWorldState() {
		return objects;
	}
}
