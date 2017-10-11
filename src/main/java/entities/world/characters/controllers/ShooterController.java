package entities.world.characters.controllers;

import common.GameController;
import entities.properties.Shooter;
import entities.world.characters.Character;
import entities.world.characters.CharacterController;
import entities.world.characters.Robot;

public class ShooterController implements CharacterController {
	private double accTime = 0;
	private int calls = 0;

	@Override
	public void update(Character character, double deltaTime, GameController game) {
		accTime += deltaTime;
		calls++;

		Shooter shooter = (Shooter) character;

		while (accTime > 3) {
			accTime -= 3;

			shooter.shoot(1, game);
		}
	}
}
