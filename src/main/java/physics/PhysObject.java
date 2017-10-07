package physics;

import common.AABB;
import common.Collidable;

public abstract class PhysObject {
	private AABB bounds;
	private Collidable collisionCallback;
	
	public PhysObject(AABB bounds, double mass) {
		this.bounds = bounds;
	}
	
	public void setCollisionCallback(Collidable c) {
		collisionCallback = c;
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
		Vector[] collision = bounds.resolveCollision(o.bounds);
		
		if(collision != null) {
			if(collisionCallback != null) {
				collisionCallback.collide(o.collisionCallback, collision[0]);
			}
			if(o.collisionCallback != null) {
				o.collisionCallback.collide(collisionCallback, collision[1]);
			}
		}
	}
}
