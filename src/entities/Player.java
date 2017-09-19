package entities;

import physics.PhysObject;
import physics.PlayerPhysObject;

import java.awt.*;

public class Player {
	private final String name;
	private final PhysObject phys;

	private int health = 100;

	public Player(String name, Point pos) {
		this.name = name;
		this.phys = new PlayerPhysObject();

		this.phys.setPosition(pos);
	}

	public void move() {

	}

	public void attack() {

	}

	public void takeDamage(int amount) {
		health = Math.max(0, health - amount);

		if (health == 0) {
			die();
		}
	}

	public void heal(int amount) {
		health = Math.min(100, health + amount);
	}

	private void die() {

	}
}
