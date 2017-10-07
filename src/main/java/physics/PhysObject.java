package physics;

import common.AABB;
import common.Collidable;
import common.Positionable;
import common.Vector;

public abstract class PhysObject implements Positionable {
    private AABB aabb;
    protected Collidable collisionCallback;

    public PhysObject(AABB aabb) {
        this.aabb = aabb;
    }
    
    /**
     * dt is the time passed, in seconds, since advance was last called.
     * @param dt change in time (seconds)
     */
    public abstract void advance(double dt);
    
    public void resolveCollision(PhysObject o) {
        Vector[] collision = aabb.resolveCollision(o.aabb);
        if(collision != null) {
            if(collisionCallback != null) {
                collisionCallback.collide(o.collisionCallback, collision[0]);
            }
            if(o.collisionCallback != null) {
                o.collisionCallback.collide(collisionCallback, collision[1]);
            }
        }
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
        return aabb.velocity;
    }

    @Override
    public void setPosition(Vector p) {
        aabb.center = p;
    }

    @Override
    public void setVelocity(Vector p) {
        aabb.velocity = p;
    }

    @Override
    public void applyForce(Vector f) {
        aabb.applyForce(f);
    }

    @Override
    public void setCollisionCallback(Collidable c) {
        this.collisionCallback = c;
    }
}
