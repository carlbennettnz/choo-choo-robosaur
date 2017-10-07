package common;

import org.junit.Test;

import static org.junit.Assert.*;

public class AABBTest {
	private AABB makeAABB(int centerX, int centerY, int halfWidth, int halfHeight) {
		return new AABB(new Vector(centerX, centerY), new Vector(halfWidth, halfHeight));
	}

	@Test
	public void intersectsEdgesTouch() throws Exception {
		AABB a = makeAABB( 0,  0, 1, 1);
		AABB l = makeAABB(-2,  0, 1, 1);
		AABB r = makeAABB( 2,  0, 1, 1);
		AABB t = makeAABB( 0, -2, 1, 1);
		AABB b = makeAABB( 0,  2, 1, 1);

		assertFalse(a.intersects(l));
		assertFalse(a.intersects(r));
		assertFalse(a.intersects(t));
		assertFalse(a.intersects(b));
	}

	@Test
	public void intersectsCovered() throws Exception {
		AABB a       = makeAABB(0, 0, 2, 2);
		AABB outside = makeAABB(0, 0, 3, 3);
		AABB inside  = makeAABB(0, 0, 1, 1);

		assertTrue(a.intersects(outside));
		assertTrue(a.intersects(inside));
	}

	@Test
	public void intersectsPartial() throws Exception {
		AABB a  = makeAABB( 0,  0, 1, 1);
		AABB l  = makeAABB(-1,  0, 1, 1);
		AABB r  = makeAABB( 1,  0, 1, 1);
		AABB t  = makeAABB( 0, -1, 1, 1);
		AABB b  = makeAABB( 0,  1, 1, 1);
		AABB tl = makeAABB(-1, -1, 1, 1);
		AABB tr = makeAABB( 1, -1, 1, 1);
		AABB bl = makeAABB(-1,  1, 1, 1);
		AABB br = makeAABB( 1,  1, 1, 1);

		assertTrue(a.intersects(l));
		assertTrue(a.intersects(r));
		assertTrue(a.intersects(t));
		assertTrue(a.intersects(b));
		assertTrue(a.intersects(tl));
		assertTrue(a.intersects(tr));
		assertTrue(a.intersects(bl));
		assertTrue(a.intersects(br));
	}

	@Test
	public void intersectsSelf() throws Exception {
		AABB a = makeAABB(0, 0, 1, 1);

		assertTrue(a.intersects(a));
	}

	@Test
	public void intersectsNull() throws Exception {
		AABB a = makeAABB(0, 0, 1, 1);

		assertFalse(a.intersects(null));
	}

}