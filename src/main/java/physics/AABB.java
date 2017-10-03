package physics;

public class AABB {
	public Vector center;
	public Vector halfSize;
	public Vector velocity;
	public Vector acceleration;
	public double mass, imass;
	
	public AABB(Vector center, Vector halfSize, double mass) {
		this(center, halfSize, null, null, mass);
	}
	
	public AABB(Vector center, Vector halfSize, Vector velocity, Vector acceleration, double mass) {
		this.center = center != null ? center : Vector.zero();
		this.halfSize = halfSize != null ? halfSize : Vector.zero();
		this.velocity = velocity != null ? velocity : Vector.zero();
		this.acceleration = acceleration != null ? acceleration : Vector.zero();
		this.mass = mass;
		this.imass = mass > 0 ? 1 / mass : 0;
	}
	
	public void applyForce(Vector f) {
		acceleration = acceleration.add(f.mult(1/mass));
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
	
	/**
	 * Returns true if the two AABBs intersect, false otherwise.
	 * @param box
	 * @return true if intersection
	 */
	public boolean intersects(AABB box) {
		if (box == null) return false;

		Vector a = getMin();
		Vector A = getMax();
		Vector b = box.getMin();
		Vector B = box.getMax();

		return a.x < B.x && A.x > b.x && a.y < B.y && A.y > b.y;
	}
	
	/**
	 * Calculate the closest point from the given point that lines inside this AABB.
	 * @param point
	 * @return the closest point
	 */
	public Vector closestPointOnBoundsToPoint(Vector point) {
		double minDist = Math.abs(point.x - getMin().x);
		Vector boundsPoint = new Vector(getMin().x, point.y);
		
		if (Math.abs(getMax().x - point.x) < minDist) {
			minDist = Math.abs(getMax().x - point.x);
			boundsPoint = new Vector(getMax().x, point.y);
		}
		
		if (Math.abs(getMax().y - point.y) < minDist) {
			minDist = Math.abs(getMax().y - point.y);
			boundsPoint = new Vector(point.x, getMax().y);
		}
		
		if (Math.abs(getMin().y - point.y) < minDist) {
			minDist = Math.abs(getMin().y - point.y);
			boundsPoint = new Vector(point.x, getMin().y);
		}
		
		return boundsPoint;
	}
	
	public AABB minkowskiDifference(AABB o) {
		Vector topLeft = getMin().sub(o.getMax());
		Vector fullSize = getSize().add(o.getSize());
		return new AABB(topLeft.add(fullSize.mult(0.5)), fullSize.mult(0.5), null, null, 0);
	}
	
	public Vector[] resolveCollision(AABB o) {
		AABB md = o.minkowskiDifference(this);
		if (md.getMin().x <= 0 && md.getMax().x >= 0 && md.getMin().y <= 0 && md.getMax().y >= 0) {
			
			/* calculate the overlapping vector */
			Vector penetrationVector = md.closestPointOnBoundsToPoint(Vector.zero());

			/* adjust the velocities accordingly */
	        Vector tangent = penetrationVector.normalized().tangent();
	        velocity = tangent.mult(velocity.dot(tangent));
	        o.velocity = tangent.mult(o.velocity.dot(tangent));

			/* move the center based on masses */
			if(mass != 0 || o.mass != 0) {
				double m = mass + o.mass;
				
				double a = (o.mass != 0 ? o.mass : mass) / m;
				double b = (mass != 0 ? mass : o.mass) / m;
				
				Vector A = penetrationVector.mult(+a);
				Vector B = penetrationVector.mult(-b);
				
				center = center.add(A);
				o.center = center.add(B);
				
		        return new Vector[] {A, B};
			}
		}
		
		return null;
	}
}
