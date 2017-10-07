package common;

public class AABB {
	public Vector center;
	public Vector halfSize;
	
	public AABB(Vector center, Vector halfSize) {
		this.center = center != null ? center : Vector.zero();
		this.halfSize = halfSize != null ? halfSize : Vector.zero();
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
		return new AABB(topLeft.add(fullSize.mult(0.5)), fullSize.mult(0.5));
	}
}
