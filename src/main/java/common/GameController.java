package common;

import entities.world.Entity;
import physics.PhysObject;

import java.awt.*;
import java.util.List;

public interface GameController extends Tickable {
	public Renderable getPlayer();
	public List<Entity> getEntities();
	public AABB getViewport();
	public GameStatus getStatus();
	public int getLevel();
	public void bindKeyListeners(Component component);
	public void addEntity(Entity entity, PhysObject physObject);

	public enum GameStatus {
		GAME_TITLE,
		LEVEL_TITLE,
		PLAY,
		END_SCREEN
	}
}
