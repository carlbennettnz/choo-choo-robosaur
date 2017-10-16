package entities.properties;

import common.Physical;
import common.Vector;
import entities.inventory.InventoryItem;

/**
 * An entity that can be collected by ItemCollectors and provides an InventoyItem to be added to an Inventory.
 */
public interface Collectable extends Physical {
    default void collide(Physical o, Vector[] collision) {
        if (o instanceof ItemCollector) {
            ItemCollector collector = (ItemCollector) o;
            collector.collect(this);
        }
    }
}
