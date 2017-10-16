package entities.world.collectables;

import common.AABB;
import common.Collidable;
import common.Vector;
import entities.inventory.InventoryItem;
import entities.properties.Collectable;
import entities.properties.ItemCollector;
import entities.world.Entity;


public abstract class BaseCollectable extends Entity implements Collectable {
    private InventoryItem inventoryItem;

    public BaseCollectable(AABB box, InventoryItem inventoryItem) {
        super(box, 0);

        assert (inventoryItem != null);

        this.inventoryItem = inventoryItem;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void collide(Collidable entity, Vector vector) {
        if (entity instanceof ItemCollector) {
			ItemCollector collector = (ItemCollector) entity;
			collector.collect(this);
        }
    }
}
