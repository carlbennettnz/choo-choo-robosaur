package common;

import physics.Vector;

public class AABB {
	private final Vector center;
	private final Vector halfSize;
	private final Vector velocity;
	private final Vector acceleration;
	
	public AABB(Vector center, Vector halfSize) {
		this(center, halfSize, null, null);
	}
	
	public AABB(Vector center, Vector halfSize, Vector velocity, Vector acceleration) {
		this.center = center != null ? center : Vector.zero();
		this.halfSize = halfSize != null ? halfSize : Vector.zero();
		this.velocity = velocity != null ? velocity : Vector.zero();
		this.acceleration = acceleration != null ? acceleration : Vector.zero();
	}
	
	public Vector getMin() {
		return new Vector(center.x - halfSize.x, center.y - halfSize.y);
	}
	
	public Vector getMax() {
		return new Vector(center.x + halfSize.x, center.y + halfSize.y);
	}
	
	public Vector getSize() {
		return new Vector(halfSize.x * 2, halfSize.y * 2);
	}
	
	public Vector getCenter() {
		return center;
	}
	
	public Vector getHalfSize() {
		return halfSize;
	}
	
	public Vector getVelocity() {
		return velocity;
	}
	
	public Vector getAcceleration() {
		return acceleration;
	}

	public boolean intersects(AABB box) {
		if (box == null) return false;

		Vector a = getMin();
		Vector A = getMax();
		Vector b = box.getMin();
		Vector B = box.getMax();

		return a.x < B.x && A.x > b.x && a.y < B.y && A.y > b.y;
	}
}
