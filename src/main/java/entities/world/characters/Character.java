package entities.world.characters;

import common.Collidable;
import entities.inventory.Inventory;
import entities.properties.Collectable;
import entities.properties.Damageable;
import entities.properties.ItemCollector;
import entities.properties.Tickable;
import entities.world.Entity;
import physics.PhysObject;

public abstract class Character extends Entity implements Damageable, Tickable, ItemCollector, Collidable {
    private final Inventory inventory;
    private final CharacterController controller;

    private int health;
    private int maxHealth;

    public Character(PhysObject physObject, CharacterController controller, int maxHealth) {
        super(physObject);

        assert (maxHealth > 0);

        this.health = maxHealth;
        this.maxHealth = maxHealth;

        this.inventory = new Inventory();
        this.controller = controller;
    }

    public final void tick(double deltaTime){
        this.controller.update(this, deltaTime);
    }

    public int getHealth() {
        return health;
    }

    public int getRemainingHealth() {
        return maxHealth - health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void damage(int damage) {
        assert(damage >= 0);
        health = Math.max(0, health - damage);
    }

    public void heal(int healing) {
        assert(healing >= 0);
        health = Math.min(health + healing, maxHealth);
    }

    public Inventory getInventory(){
        return inventory;
    }

    public boolean collect(Collectable collectable){
        return inventory.add(collectable.getInventoryItem());
    }
}
