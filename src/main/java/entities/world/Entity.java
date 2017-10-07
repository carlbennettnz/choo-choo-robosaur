package entities.world;

import common.AABB;
import common.Collidable;
import common.Positionable;
import common.Renderable;
import common.Vector;

public abstract class Entity implements Renderable, Collidable, Positionable {
    private Positionable position;
    protected Collidable collisionCallback;

    public Entity(Positionable position) {
        setPositionable(position);
    }

    private final void setPositionable(Positionable position) {
        if(position == null)
            throw new RuntimeException("entity must have a positionable.");
        this.position = position;
        position.setCollisionCallback(this);
    }
    
    @Override
    public AABB getBoundingBox() {
        return position.getBoundingBox();
    }

    @Override
    public Vector getPosition() {
        return position.getPosition();
    }

    @Override
    public Vector getVelocity() {
        return position.getVelocity();
    }

    @Override
    public void setPosition(Vector p) {
        position.setPosition(p);
    }

    @Override
    public void setVelocity(Vector p) {
        position.setVelocity(p);
    }

    @Override
    public void applyForce(Vector f) {
        position.applyForce(f);
    }

    @Override
    public void setCollisionCallback(Collidable c) {
        this.collisionCallback = c;
    }
}
