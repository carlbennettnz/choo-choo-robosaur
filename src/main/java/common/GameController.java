package common;

import entities.world.Entity;

import java.awt.*;
import java.util.List;

public interface GameController extends Tickable {
	List<Entity> getEntities();
	AABB getViewport();
	GameStatus getStatus();
	int getLevel();
	void addEntity(Entity entity);

	public enum GameStatus {
		GAME_TITLE,
		LEVEL_TITLE,
		PLAY,
		END_SCREEN
	}
}
