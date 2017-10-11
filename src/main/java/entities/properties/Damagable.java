package entities.properties;

/**
 * An entity that can be damaged and healed.
 */
public interface Damagable {
    void damage(int amountOfDamage);
    void heal(int amountOfHealing);
}
