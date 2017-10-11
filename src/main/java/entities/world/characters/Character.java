package entities.world.characters;

import common.Collidable;
import common.GameController;
import common.Positionable;
import common.Vector;
import common.Tickable;
import entities.inventory.Inventory;
import entities.properties.Collectable;
import entities.properties.Damagable;
import entities.properties.ItemCollector;
import entities.world.Entity;

public abstract class Character extends Entity implements Damagable, Tickable, ItemCollector, Collidable {
    protected final Inventory inventory;
    protected final CharacterController controller;

    protected int health;
    protected int maxHealth;

    public Character(Positionable position, CharacterController controller, int maxHealth) {
        super(position);

        assert (maxHealth > 0);

        this.health = maxHealth;
        this.maxHealth = maxHealth;

        this.inventory = new Inventory();
        this.controller = controller;
    }

    public void tick(double deltaTime, GameController game){
        this.controller.update(this, deltaTime, game);
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

        if (health == 0) removeOnNextTick = true;
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

    public void collide(Collidable entity, Vector vector) {

    }
}
