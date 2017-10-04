package entities.world.collectables;

import common.Collidable;
import entities.inventory.InventoryItem;
import entities.properties.Collectable;
import entities.properties.ItemCollector;
import entities.world.Entity;
import physics.PhysObject;
import physics.Vector;


public abstract class BaseCollectable extends Entity implements Collectable {
    private InventoryItem inventoryItem;

    public BaseCollectable(PhysObject physObject, InventoryItem inventoryItem) {
        super(physObject);

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
