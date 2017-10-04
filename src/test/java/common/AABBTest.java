package common;

import org.junit.Test;
import physics.Vector;

import static org.junit.Assert.*;

public class AABBTest {
	private AABB makeAABB(int centerX, int centerY, int halfWidth, int halfHeight) {
		return new AABB(new Vector(centerX, centerY), new Vector(halfWidth, halfHeight));
	}

	private AABB makeAABB(int centerX, int centerY, int halfWidth, int halfHeight,
						  int velocityX, int velocityY, int accelerationX, int accelerationY) {
		return new AABB(new Vector(centerX, centerY), new Vector(halfWidth, halfHeight),
						new Vector(velocityX, velocityY), new Vector(accelerationX, accelerationY));
	}

	@Test
	public void min() throws Exception {
		assertEquals(makeAABB(0, 0, 1, 1).getMin(), new Vector(-1, -1));
		assertEquals(makeAABB(1, 1, 1, 1).getMin(), new Vector(0, 0));
		assertEquals(makeAABB(0, 0, 0, 0).getMin(), new Vector(0, 0));
	}

	@Test
	public void max() throws Exception {
		assertEquals(makeAABB( 0,  0, 1, 1).getMax(), new Vector(1, 1));
		assertEquals(makeAABB(-1, -1, 1, 1).getMax(), new Vector(0, 0));
		assertEquals(makeAABB( 0,  0, 0, 0).getMax(), new Vector(0, 0));
	}

	@Test
	public void size() throws Exception {
		assertEquals(makeAABB(0,  0, 1, 2).getSize(), new Vector(2, 4));
		assertEquals(makeAABB(5, -5, 1, 2).getSize(), new Vector(2, 4));
		assertEquals(makeAABB(5, -5, 1, 2).getSize(), new Vector(2, 4));
		assertEquals(makeAABB(0,  0, 0, 0).getSize(), new Vector(0, 0));
	}

	@Test
	public void center() throws Exception {
		assertEquals(makeAABB(0,  0, 1, 1).getCenter(), new Vector(0, 0));
		assertEquals(makeAABB(5, -5, 1, 1).getCenter(), new Vector(5, -5));
		assertEquals(makeAABB(0,  0, 0, 0).getCenter(), new Vector(0, 0));
	}

	@Test
	public void halfSize() throws Exception {
		assertEquals(makeAABB(0, 0, 1, 1).getHalfSize(), new Vector(1, 1));
		assertEquals(makeAABB(0, 0, 0, 0).getHalfSize(), new Vector(0, 0));
	}

	@Test
	public void velocity() throws Exception {
		assertEquals(makeAABB(0, 0, 0, 0).getVelocity(), new Vector(0, 0));
		assertEquals(makeAABB(0, 0, 0, 0, 0, 0, 0, 0).getVelocity(), new Vector(0, 0));
		assertEquals(makeAABB(0, 0, 0, 0, 5, -3, 0, 0).getVelocity(), new Vector(5, -3));
		assertEquals(makeAABB(1, 1, 1, 1, 5, -3, 1, 1).getVelocity(), new Vector(5, -3));
	}

	@Test
	public void acceleration() throws Exception {
		assertEquals(makeAABB(0, 0, 0, 0).getAcceleration(), new Vector(0, 0));
		assertEquals(makeAABB(0, 0, 0, 0, 0, 0, 0, 0).getAcceleration(), new Vector(0, 0));
		assertEquals(makeAABB(0, 0, 0, 0, 0, 0, 5, -3).getAcceleration(), new Vector(5, -3));
		assertEquals(makeAABB(1, 1, 1, 1, 1, 1, 5, -3).getAcceleration(), new Vector(5, -3));
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