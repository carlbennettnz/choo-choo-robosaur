package entities.world.characters;

import common.GameController;

public interface CharacterController {
	void update(Character character, double deltaTime, GameController game);
}
