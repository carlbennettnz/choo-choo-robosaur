package physics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import common.Physical;
import org.junit.Test;

import common.AABB;
import common.Vector;

import java.awt.*;

public class WorldTest {
    @Test
    public void horizontalCollisionNoVelocity() {
        Physical a = new P( 2,  0, 3, 3);
        Physical b = new P(-2,  0, 3, 3);
        World w = new World();
        Vector[] c = w.resolveCollision(a, b);

        assertEquals(c[0].x, 1.0, 0);
        assertEquals(c[0].y, 0.0, 0);
        assertEquals(c[1].x, -1.0, 0);
        assertEquals(c[1].y, 0.0, 0);

        c = w.resolveCollision(a, b);
        
        assertNull(c);
    }
    
    @Test
    public void horizontalCollisionDifferentMass() {
        Physical a = new P( 2,  0, 3, 3, 1.0);
        Physical b = new P(-2,  0, 3, 3, 2.0);
        World w = new World();
        Vector[] c = w.resolveCollision(a, b);
        
        assertEquals(c[0].x, 2.0 * 2.0/3.0, 0);
        assertEquals(c[0].y, 0.0, 0);
        assertEquals(c[1].x, -2.0 * 1.0/3.0, 0);
        assertEquals(c[1].y, 0.0, 0);

        c = w.resolveCollision(a, b);
        
        assertNull(c);
    }
    
    @Test
    public void horizontalCollisionImmovable() {
        Physical a = new P( 2,  0, 3, 3, 1.0);
        Physical b = new P(-2,  0, 3, 3, 0.0);
        World w = new World();
        Vector[] c = w.resolveCollision(a, b);
        
        assertEquals(c[0].x, 2.0, 0);
        assertEquals(c[0].y, 0.0, 0);
        assertEquals(c[1].x, 0.0, 0);
        assertEquals(c[1].y, 0.0, 0);

        c = w.resolveCollision(a, b);
        
        assertNull(c);
    }
    
    @Test
    public void horizontalCollisionVelocity() {
        Physical a = new P( 2,  0, 3, 3);
        Physical b = new P(-2,  0, 3, 3);

        a.setVelocity(new Vector(0.5, 1));
        b.setVelocity(new Vector(1, 0.5));
        
        World w = new World();
        Vector[] c = w.resolveCollision(a, b);

        assertEquals(a.getVelocity().x, 0.0, 0);
        assertEquals(a.getVelocity().y, 1.0, 0);
        assertEquals(b.getVelocity().x, 0.0, 0);
        assertEquals(b.getVelocity().y, 0.5, 0);
        
        assertEquals(c[0].x, 1.0, 0);
        assertEquals(c[0].y, 0.0, 0);
        assertEquals(c[1].x, -1.0, 0);
        assertEquals(c[1].y, 0.0, 0);

        c = w.resolveCollision(a, b);
        
        assertNull(c);
    }
    
    @Test
    public void verticalCollisionNoVelocity() {
        Physical a = new P( 0,  2, 3, 3);
        Physical b = new P( 0,  -2, 3, 3);
        World w = new World();
        Vector[] c = w.resolveCollision(a, b);

        assertEquals(c[0].x, 0.0, 0);
        assertEquals(c[0].y, 1.0, 0);
        assertEquals(c[1].x, 0.0, 0);
        assertEquals(c[1].y, -1.0, 0);

        c = w.resolveCollision(a, b);
        
        assertNull(c);
    }
    
    @Test
    public void diagonalCollisionNoVelocity() {
        Physical a = new P( 2,  2, 3, 3);
        Physical b = new P( -2,  -2, 3, 3);
        World w = new World();
        Vector[] c = w.resolveCollision(a, b);
        
        /* if x and y are equal length, move along x */
        assertEquals(c[0].x, 1.0, 0);
        assertEquals(c[0].y, 0.0, 0);
        assertEquals(c[1].x, -1.0, 0);
        assertEquals(c[1].y, 0.0, 0);

        c = w.resolveCollision(a, b);
        
        assertNull(c);
    }
}

class P implements Physical {
    private AABB b;
    private Vector v, a;
    private double m;
    P(int x, int y, int w, int h) { this(x, y, w, h, 1); }
    P(int x, int y, int w, int h, double m) { b = new AABB(new Vector(x, y), new Vector(w, h)); this.m = m; }
    public AABB getBoundingBox() { return b; }
    public void draw(Graphics g) {}
    public Vector getVelocity() { return v == null ? Vector.zero() : v; }
    public double getMass() { return m; }
    public Vector getAcceleration() { return a == null ? Vector.zero() : a; }
    public void setBoundingBox(AABB b) { this.b = b; }
    public void setVelocity(Vector v) { this.v = v; }
    public void setMass(double m) { this.m = m; }
    public void setAcceleration(Vector acc) { this.a = acc; }
    public void collide(Physical o, Vector collision) {}
}
