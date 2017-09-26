package physics;

public abstract class PhysObject {
	public PhysObject() {
	}

	public abstract AABB getBoundingBox();

	public abstract Vector getPosition();

	public abstract void setPosition(Vector p);
	
	public abstract void applyForce(Vector f);

	public abstract void handleCollision(PhysObject o);
}
