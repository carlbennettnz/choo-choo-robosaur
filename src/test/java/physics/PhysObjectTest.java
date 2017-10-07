package physics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import common.AABB;
import common.Vector;

public class PhysObjectTest {
    private PhysObject makePhysObject(int centerX, int centerY, int halfWidth, int halfHeight) {
        return new PhysObject(new AABB(new Vector(centerX, centerY), new Vector(halfWidth, halfHeight)), 1.0);
    }
    
    private PhysObject makePhysObject(int centerX, int centerY, int halfWidth, int halfHeight, double mass) {
        return new PhysObject(new AABB(new Vector(centerX, centerY), new Vector(halfWidth, halfHeight)), mass);
    }


    @Test
    public void horizontalCollisionNoVelocity() {
        PhysObject a = makePhysObject( 2,  0, 3, 3);
        PhysObject b = makePhysObject(-2,  0, 3, 3);
        Vector[] c = a.collide(b);

        assertEquals(c[0].x, 1.0, 0);
        assertEquals(c[0].y, 0.0, 0);
        assertEquals(c[1].x, -1.0, 0);
        assertEquals(c[1].y, 0.0, 0);
        
        c = a.collide(b);
        
        assertNull(c);
    }
    
    @Test
    public void horizontalCollisionDifferentMass() {
        PhysObject a = makePhysObject( 2,  0, 3, 3, 1.0);
        PhysObject b = makePhysObject(-2,  0, 3, 3, 2.0);
        Vector[] c = a.collide(b);
        
        assertEquals(c[0].x, 2.0 * 2.0/3.0, 0);
        assertEquals(c[0].y, 0.0, 0);
        assertEquals(c[1].x, -2.0 * 1.0/3.0, 0);
        assertEquals(c[1].y, 0.0, 0);
        
        c = a.collide(b);
        
        assertNull(c);
    }
    
    @Test
    public void horizontalCollisionImmovable() {
        PhysObject a = makePhysObject( 2,  0, 3, 3, 1.0);
        PhysObject b = makePhysObject(-2,  0, 3, 3, 0.0);
        Vector[] c = a.collide(b);
        
        assertEquals(c[0].x, 2.0, 0);
        assertEquals(c[0].y, 0.0, 0);
        assertEquals(c[1].x, 0.0, 0);
        assertEquals(c[1].y, 0.0, 0);
        
        c = a.collide(b);
        
        assertNull(c);
    }
    
    @Test
    public void horizontalCollisionVelocity() {
        PhysObject a = makePhysObject( 2,  0, 3, 3);
        PhysObject b = makePhysObject(-2,  0, 3, 3);

        a.setVelocity(new Vector(0.5, 1));
        b.setVelocity(new Vector(1, 0.5));
        
        Vector[] c = a.collide(b);

        assertEquals(a.getVelocity().x, 0.0, 0);
        assertEquals(a.getVelocity().y, 1.0, 0);
        assertEquals(b.getVelocity().x, 0.0, 0);
        assertEquals(b.getVelocity().y, 0.5, 0);
        
        assertEquals(c[0].x, 1.0, 0);
        assertEquals(c[0].y, 0.0, 0);
        assertEquals(c[1].x, -1.0, 0);
        assertEquals(c[1].y, 0.0, 0);
        
        c = a.collide(b);
        
        assertNull(c);
    }
    
    @Test
    public void verticalCollisionNoVelocity() {
        PhysObject a = makePhysObject( 0,  2, 3, 3);
        PhysObject b = makePhysObject( 0,  -2, 3, 3);
        Vector[] c = a.collide(b);

        assertEquals(c[0].x, 0.0, 0);
        assertEquals(c[0].y, 1.0, 0);
        assertEquals(c[1].x, 0.0, 0);
        assertEquals(c[1].y, -1.0, 0);
        
        c = a.collide(b);
        
        assertNull(c);
    }
    
    @Test
    public void diagonalCollisionNoVelocity() {
        PhysObject a = makePhysObject( 2,  2, 3, 3);
        PhysObject b = makePhysObject( -2,  -2, 3, 3);
        Vector[] c = a.collide(b);
        
        /* if x and y are equal length, move along x */
        assertEquals(c[0].x, 1.0, 0);
        assertEquals(c[0].y, 0.0, 0);
        assertEquals(c[1].x, -1.0, 0);
        assertEquals(c[1].y, 0.0, 0);
        
        c = a.collide(b);
        
        assertNull(c);
    }
}
