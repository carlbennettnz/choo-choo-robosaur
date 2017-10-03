package physics;

public class PlayerPhysObject extends PhysObject {
	public PlayerPhysObject(AABB bounds, double mass) {
		super(bounds, mass);
	}

	@Override
	public AABB getBoundingBox() {
		return null;
	}

	@Override
	public Vector getPosition() {
		return null;
	}

	@Override
	public void setPosition(Vector p) {

	}

	@Override
	public void resolveCollision(PhysObject o) {

	}

	@Override
	public void applyForce(Vector f) {
		
	}

	@Override
	public void advance(double dt) {
		
	}
}
