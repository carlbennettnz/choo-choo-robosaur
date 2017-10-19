package common;

public interface Physical extends Renderable {
    AABB getBoundingBox();
    Vector getVelocity();
    double getMass();
    Vector getAcceleration();

    void setBoundingBox(AABB box);
    void setVelocity(Vector velocity);
    void setMass(double mass);
    void setAcceleration(Vector acc);

    void collide(Physical o, Vector collision);

    default Vector setPosition(Vector pos) {
        return getBoundingBox().center = pos;
    }

    default void applyForce(Vector f) {
        if (getMass() <= 0) return;
        setAcceleration(getAcceleration().add(f.mult(1 / getMass())));
    }
}
