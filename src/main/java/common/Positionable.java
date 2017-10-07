package common;

public interface Positionable {
    public AABB getBoundingBox();
    public Vector getPosition();
    public Vector getVelocity();
    public void setPosition(Vector p);
    public void setVelocity(Vector p);
    public void applyForce(Vector f);
    public void setCollisionCallback(Collidable c);
}
