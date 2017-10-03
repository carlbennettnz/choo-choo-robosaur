package common;

import java.util.List;

public interface GameController {
	public Renderable getPlayer();
	public List<Renderable> getEntities();
	public AABB getViewport();
	public GameStatus getStatus();
	public int getLevel();

	public enum GameStatus {
		GAME_TITLE,
		LEVEL_TITLE,
		PLAY,
		END_SCREEN
	}
}
