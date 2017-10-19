package entities.world.characters.controllers;

import common.GameController;
import common.Vector;
import entities.world.characters.Character;
import entities.world.characters.CharacterController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements CharacterController, KeyListener {
    private int dir = 0;
    private boolean jump = false;

    public PlayerController() {

    }

    public void update(Character character, double deltaTime, GameController game) {
        double x = 800 * dir;
        double y = character.getVelocity().y;
        
        if(jump && character.onGround) {
            y -= 2000;
        }
        
        character.setVelocity(new Vector(x, y));
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 'A': // left
                dir = -1;
                break;

            case 'D': // right
                dir = 1;
                break;
            case ' ': // jump
                jump = true;
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
            case ' ': // jump
                jump = false;
                break;
        }
    }
}
