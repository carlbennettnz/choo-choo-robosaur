package common;

public class AABB {
	public Vector center;
	public Vector halfSize;
	public Vector velocity;
	public Vector acceleration;
	public double mass;
	
	public AABB(Vector center, Vector halfSize, double mass) {
		this(center, halfSize, null, null, mass);
	}
	
	public AABB(Vector center, Vector halfSize, Vector velocity, Vector acceleration, double mass) {
		this.center = center != null ? center : Vector.zero();
		this.halfSize = halfSize != null ? halfSize : Vector.zero();
		this.velocity = velocity != null ? velocity : Vector.zero();
		this.acceleration = acceleration != null ? acceleration : Vector.zero();
		this.mass = mass;
	}
	
	public void applyForce(Vector f) {
		if(mass <= 0)
			return;
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
	 * Calculate the closest point from the given point that lies inside this AABB.
	 * @param point
	 * @return the closest point
	 */
	public Vector closestPointOnBoundsToPoint(Vector point) {
		Vector min = getMin();
		Vector max = getMax();
		
		double minDist = Math.abs(point.x - min.x);
		Vector boundsPoint = new Vector(min.x, point.y);
		
		if (Math.abs(max.x - point.x) < minDist) {
			minDist = Math.abs(max.x - point.x);
			boundsPoint = new Vector(max.x, point.y);
		}
		
		if (Math.abs(max.y - point.y) < minDist) {
			minDist = Math.abs(max.y - point.y);
			boundsPoint = new Vector(point.x, max.y);
		}
		
		if (Math.abs(min.y - point.y) < minDist) {
			minDist = Math.abs(min.y - point.y);
			boundsPoint = new Vector(point.x, min.y);
		}
		
		return boundsPoint;
	}
	
	/**
	 * Returns a bounding box of translations that would result in a collision
	 * with the other AABB. If (0, 0) is inside this bounding box, there is
	 * already a collision.
	 * @param the other AABB
	 * @return the minkowski difference
	 */
	public AABB minkowskiDifference(AABB o) {
		Vector topLeft = getMin().sub(o.getMax());
		Vector fullSize = getSize().add(o.getSize());
		return new AABB(topLeft.add(fullSize.mult(0.5)), fullSize.mult(0.5), null, null, 0);
	}
	
	/**
	 * Resolves a collision with the other bounding box, if there is one.
	 * This means moving the two bounding boxes so that they are no longer 
	 * colliding and also changing their velocities so that they are no longer
	 * moving towards each other.
	 * The return value is a vector array of the displacement that was caused due
	 * to the collision, or null if there was no collision. At index 0 is this 
	 * bounding box's displacement and index 1 is the other box's displacement.
	 * @param o the other AABB
	 * @return array of two displacement vectors, or null.
	 */
	public Vector[] resolveCollision(AABB o) {
		AABB md = o.minkowskiDifference(this);
		
		/* no collision if true */
		if (md.getMin().x >= 0 || md.getMax().x <= 0 || md.getMin().y >= 0 || md.getMax().y <= 0)
			return null;
		
		/* calculate the overlapping vector */
		Vector penetrationVector = md.closestPointOnBoundsToPoint(Vector.zero());

		/* adjust the velocities accordingly */
        Vector tangent = penetrationVector.normalized().tangent();
        
        velocity = tangent.mult(velocity.dot(tangent));
        o.velocity = tangent.mult(o.velocity.dot(tangent));

        if (mass == 0 && o.mass == 0)
			return new Vector[] {Vector.zero(), Vector.zero()};
        
		/* move the center based on masses */
		double m = mass + o.mass;
		
		double a = 0;
		double b = 0;
		
		if(o.mass == 0) {
			a = 1;
		} else if(mass == 0) {
			b = 1;
		} else {
			a = o.mass / m;
			b = mass / m;
		}
		
		Vector A = penetrationVector.mult(a);
		Vector B = penetrationVector.mult(-b);
		
		center = center.add(A);
		o.center = o.center.add(B);
		
        return new Vector[] {A, B};
	}
}
