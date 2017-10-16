package entities;

import entities.events.combat.DamageEvent;
import entities.events.combat.HealEvent;
import entities.world.pawns.CharacterPawn;
import entities.world.pawns.pc.PlayerController;
import physics.AABB;
import physics.PhysObject;
import physics.Vector;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DamageableTest {

    private CharacterPawn testPawn(int health){
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

        return new CharacterPawn(renderer, physObject, PlayerController.CONTROLLER, health);
    }

    @Test
    public void damage() throws Exception {
        CharacterPawn character;

        character = testPawn(100);
        character.damage(50);
        assertTrue(character.getCurrentHealth() == 50);

        character = testPawn(100);
        character.damage(100);
        assertTrue(character.getCurrentHealth() == 0);

        character = testPawn(100);
        character.damage(150);
        assertTrue(character.getCurrentHealth() == 0);
    }

    @Test
    public void heal() throws Exception {
        CharacterPawn character;

        character = testPawn(100);
        character.damage(50);
        character.heal(25);
        assertTrue(character.getCurrentHealth() == 75);

        character = testPawn(100);
        character.damage(50);
        character.heal(50);
        assertTrue(character.getCurrentHealth() == 100);

        character = testPawn(100);
        character.damage(50);
        character.heal(100);
        assertTrue(character.getCurrentHealth() == 100);
    }

    @Test
    public void onDamage() throws Exception {
        CharacterPawn char1 = testPawn(100);
        CharacterPawn char2 = testPawn(100);

        new DamageEvent(char1, 50).post(); //must only affect target
        assertTrue(char1.getCurrentHealth() == 50);
        assertTrue(char2.getCurrentHealth() == 100);

        try {
            new DamageEvent(null, 50).post(); //must have a target
            fail();
        } catch (AssertionError e) { }

        try {
            new DamageEvent(char1, -50).post(); //dmg cant be -ve
            fail();
        } catch (AssertionError e) { }
    }

    @Test
    public void onHeal() throws Exception {
        CharacterPawn char1 = testPawn(100);
        CharacterPawn char2 = testPawn(100);
        char1.damage(50);
        char2.damage(50);

        new HealEvent(char1, 50).post(); //must only affect target
        assertTrue(char1.getCurrentHealth() == 100);
        assertTrue(char2.getCurrentHealth() == 50);

        try {
            new DamageEvent(null, 50).post(); //must have a target
            fail();
        } catch (AssertionError e) { }

        try {
            new DamageEvent(char1, -50).post(); //healing can't be -ve
            fail();
        } catch (AssertionError e) { }
    }

}