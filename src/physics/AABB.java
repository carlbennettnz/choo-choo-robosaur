package physics;

public class AABB {
	private Vector center;
	private Vector halfSize;
	private Vector velocity;
	private Vector acceleration;
	
	public AABB(Vector center, Vector halfSize) {
		this(center, halfSize, null, null);
	}
	
	public AABB(Vector center, Vector halfSize, Vector velocity, Vector acceleration) {
		this.center = center != null ? center : Vector.zero();
		this.center = halfSize != null ? halfSize : Vector.zero();
		this.center = velocity != null ? velocity : Vector.zero();
		this.center = acceleration != null ? acceleration : Vector.zero();
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
}
