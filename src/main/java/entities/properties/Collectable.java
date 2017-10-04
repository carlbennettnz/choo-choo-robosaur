package entities.properties;

import common.Collidable;
import entities.inventory.InventoryItem;

/**
 * An entity that can be collected by ItemCollectors and provides an InventoyItem to be added to an Inventory.
 */
public interface Collectable extends Collidable {
    InventoryItem getInventoryItem();
}
