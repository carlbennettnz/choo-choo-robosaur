package entities.world.characters.controllers;

import common.GameController;
import common.Vector;
import entities.world.characters.Character;
import entities.world.characters.CharacterController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements CharacterController, KeyListener {
    private int dir = 0;

    public PlayerController() {

    }

    public void update(Character character, double deltaTime, GameController game) {
        int movement = 500 * dir;

        character.setVelocity(new Vector(movement, character.getVelocity().y));
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 65: // left
                dir = -1;
                break;

            case 68: // right
                dir = 1;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 65:
                if (dir == -1) dir = 0;
                break;

            case 68:
                if (dir == 1) dir = 0;
                break;
        }
    }
}
