package physics;

import common.AABB;

public abstract class PhysObject {
	private AABB bounds;
	
	public PhysObject(AABB bounds, double mass) {
		this.bounds = bounds;
	}

	public abstract AABB getBoundingBox();

	public abstract Vector getPosition();

	public abstract void setPosition(Vector p);
	
	public void applyForce(Vector f) {
		bounds.applyForce(f);
	}

	
	/**
	 * dt is the time passed, in seconds, since advance was last called.
	 * @param dt change in time (seconds)
	 */
	public abstract void advance(double dt);
	
	public void resolveCollision(PhysObject o) {
		bounds.resolveCollision(o.bounds);
	}
}
