package entities.world;

import common.*;

public abstract class Entity implements Renderable, Physical, Tickable {
    public boolean removeOnNextTick = false;
    private AABB boundingBox;
    private Vector velocity;
    private Vector acceleration;
    private double mass;

    public Entity(AABB boundingBox, double mass) {
        this.boundingBox = boundingBox;
        this.mass = mass;
        velocity = Vector.zero();
        acceleration = Vector.zero();
    }
    
    @Override
    public AABB getBoundingBox() {
        return boundingBox;
    }

    @Override
    public Vector getVelocity() {
        return velocity;
    }

    @Override
    public Vector getAcceleration() {
        return acceleration;
    }

    @Override
    public void setBoundingBox(AABB box) {
        boundingBox = box;
    }

    @Override
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public void setMass(double mass) {
        this.mass = mass;
    }

    @Override
    public void setAcceleration(Vector acc) {
        acceleration = acc;
    }
}
