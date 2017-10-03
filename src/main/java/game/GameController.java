package game;

import common.Renderable;
import common.AABB;

import java.util.List;

public class GameController {
	private Renderable player;
	private List<Renderable> entities;
	private AABB viewport;
	private GameStatus status;
	private int level;

	GameController() {

	}

	public Renderable getPlayer() {
		return player;
	}

	public List<Renderable> getEntities() {
		return entities;
	}

	public AABB getViewport() {
		return viewport;
	}

	public GameStatus getStatus() {
		return status;
	}

	public int getLevel() {
		return level;
	}

	public enum GameStatus {
		GAME_TITLE,
		LEVEL_TITLE,
		PLAY,
		END_SCREEN
	}
}
