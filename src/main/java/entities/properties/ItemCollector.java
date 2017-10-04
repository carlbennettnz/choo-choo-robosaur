package entities.properties;

/**
 * An entity that can collect Collectables.
 */
public interface ItemCollector {
    boolean collect(Collectable collectable);
}
