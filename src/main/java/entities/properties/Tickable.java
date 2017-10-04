package entities.properties;

/**
 * An entity that changes over time.
 */
public interface Tickable {
    void tick(double deltaTime);
}
