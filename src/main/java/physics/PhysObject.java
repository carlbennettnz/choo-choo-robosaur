package physics;

import common.AABB;
import common.Collidable;
import common.Positionable;
import common.Vector;

public class PhysObject implements Positionable {
    private AABB aabb;
    protected Collidable collisionCallback;
    private Vector velocity;
    private Vector acceleration;
    private double mass;

    public PhysObject(AABB aabb, double mass) {
        this(aabb, null, null, mass);
    }
    
    public PhysObject(AABB aabb, Vector velocity, Vector acceleration, double mass) {
        this.aabb = aabb;
        this.velocity = velocity != null ? velocity : Vector.zero();
        this.acceleration = acceleration != null ? acceleration : Vector.zero();
        this.mass = mass;
    }
    
    /**
     * dt is the time passed, in seconds, since advance was last called.
     * @param dt change in time (seconds)
     */
    public void advance(double dt) {
        velocity = velocity.add(acceleration.mult(dt));
        aabb.center = aabb.center.add(velocity.mult(dt));
        acceleration = Vector.zero();
    }
    
    public Vector[] collide(PhysObject o) {
        Vector[] collision = resolveCollision(o);
        if(collision != null) {
            if(collisionCallback != null) {
                collisionCallback.collide(o.collisionCallback, collision[0]);
            }
            if(o.collisionCallback != null) {
                o.collisionCallback.collide(collisionCallback, collision[1]);
            }
        }
        return collision;
    }
    
    /**
     * Resolves a collision with the other PhysObject, if there is one.
     * This means moving the two PhysObjects so that they are no longer 
     * colliding and also changing their velocities so that they are no longer
     * moving towards each other.
     * The return value is a vector array of the displacement that was caused due
     * to the collision, or null if there was no collision. At index 0 is this 
     * bounding box's displacement and index 1 is the other box's displacement.
     * @param o the other PhysObject
     * @return array of two displacement vectors, or null.
     */
    private Vector[] resolveCollision(PhysObject o) {
        AABB md = o.getBoundingBox().minkowskiDifference(getBoundingBox());
        
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
        
        getBoundingBox().center = getBoundingBox().center.add(A);
        o.getBoundingBox().center = o.getBoundingBox().center.add(B);
        
        return new Vector[] {A, B};
    }
    
    @Override
    public AABB getBoundingBox() {
        return aabb;
    }

    @Override
    public Vector getPosition() {
        return aabb.center;
    }

    @Override
    public Vector getVelocity() {
        return velocity;
    }

    @Override
    public void setPosition(Vector p) {
        aabb.center = p;
    }

    @Override
    public void setVelocity(Vector p) {
        velocity = p;
    }

    @Override
    public void applyForce(Vector f) {
        if(mass <= 0)
            return;
        acceleration = acceleration.add(f.mult(1/mass));
    }

    @Override
    public void setCollisionCallback(Collidable c) {
        this.collisionCallback = c;
    }
    
    @Override
    public String toString() {
        return "PhysObject(" + aabb + ", " + velocity + ", " + acceleration 
                + ", " + mass +")";
    }
}
