package entities;

import entities.events.collision.CollisionEvent;
import entities.inventory.IInventoryItem;
import entities.inventory.Inventory;
import entities.properties.ICollectable;
import entities.properties.ICollider;
import entities.properties.IItemCollector;
import entities.world.pawns.CharacterPawn;
import entities.world.pawns.pc.PlayerController;
import physics.AABB;
import physics.PhysObject;
import physics.Vector;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CollectableTest {

    private CharacterPawn collector;
    private ICollectable collectable;
    boolean collision;

    private void init(){
        collector = newCollector();
        collectable = newCollectable();
        collision = false;

        CollisionEvent.Dispatcher.addListener(collector);
        CollisionEvent.Dispatcher.addListener(collectable);
    }

    private CharacterPawn newCollector(){
        IRenderer renderer = new IRenderer() {
            public void draw() { }
        };

        PhysObject physObject = new PhysObject() {
            public AABB getBoundingBox() { return null; }
            public Vector getPosition() { return null; }
            public void setPosition(Vector p) { }
            public void handleCollision(PhysObject o) { }
            public void applyForce(Vector v) { }
        };

        return new CharacterPawn(renderer, physObject, PlayerController.CONTROLLER, 100);
    }

    private ICollectable newCollectable(){
        ICollectable collectable = new ICollectable(){
            public PhysObject getPhysObject() { return null; }
            public void setPhysObject(PhysObject physObject) { }
            public void onCollision(CollisionEvent event) {
                collision = true;

                assert (event != null);
                assert (event.getCollider1() != null);
                assert (event.getCollider2() != null);

                ICollider collider = null;

                if (event.getCollider1() == this)
                    collider = event.getCollider2();
                else if (event.getCollider2() == this)
                    collider = event.getCollider1();

                if (collider == null)
                    return;
                if (!(collider instanceof IItemCollector))
                    return;

                IItemCollector collector = (IItemCollector)collider;
                collector.collect(this);
            }
            public IInventoryItem getInventoryItem() { return newInventoryItem(); }
        };

        return collectable;
    }

    private IInventoryItem newInventoryItem(){
        IInventoryItem inventoryItem = new IInventoryItem(){
            public IRenderer getRenderer() { return null; }
            public void setRenderer(IRenderer renderer) { }
        };

        return inventoryItem;
    }

    @Test
    public void collectableTest(){
        init();
        assertTrue(collector.getInventory().size() == 0);

        new CollisionEvent(collectable, collector).post();

        assertTrue(collision);
        assertTrue(collector.getInventory().size() == 1);
    }

    @Test
    public void inventoryTest(){
        IInventoryItem item = newInventoryItem();
        Inventory inventory = new Inventory();

        assertTrue(inventory.size() == 0);
        assertTrue(inventory.add(item));
        assertTrue(inventory.size() == 1);

        assertTrue(inventory.remove(item));
        assertTrue(inventory.size() == 0);

        assertTrue(!inventory.remove(item));
        assertTrue(inventory.size() == 0);
    }
}