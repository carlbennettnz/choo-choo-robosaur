package game;

import common.*;
import entities.world.Entity;
import entities.world.characters.*;
import entities.world.characters.Character;
import entities.world.characters.Robot;
import entities.world.characters.controllers.PlayerController;
import entities.world.characters.controllers.ShooterController;
import entities.world.scenery.Box;
import physics.World;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameController implements common.GameController {
	private List<Entity> entities;
	private AABB viewport;
	private GameStatus status;
	private int level;
	private Set<KeyListener> keyListeners;
	private World world;
	private Player player;
	private Set<Entity> entitiesToAdd;
	private Component component;

	public GameController(Component component) {
		this.status = GameStatus.PLAY;
		Dimension size = component.getPreferredSize();
		viewport = new AABB(new Vector(0, 0), new Vector(size.getWidth() / 2, size.getHeight() / 2));
		entitiesToAdd = new HashSet<>();
		level = 1;

		this.component = component;
		world = new World();
		entities = new ArrayList<>();
		player = new Player(new Vector(0, 0), new PlayerController(), 100);

		addEntity(player);
		addEntity(new Robot(new Vector(-300, 0), new ShooterController(), 100));
		addEntity(new Box(new AABB(new Vector(100, 0), new Vector(50, 50))));
		addEntity(new Box(new AABB(new Vector(500, 0), new Vector(50, 50))));
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void addEntity(Entity entity) {
		entitiesToAdd.add(entity);
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

	public void tick(double delta, common.GameController game) {
		List<Entity> entitiesForRemoval = entities.stream()
			.filter(e -> e.removeOnNextTick)
			.collect(Collectors.toList());

		for (Entity e : entitiesForRemoval) {
			world.removeObject(e);
			entities.remove(e);

			// todo: catch player death

			// lol
			if (e instanceof Character && ((Character) e).getController() instanceof KeyListener) {
				component.removeKeyListener((KeyListener) ((Character) e).getController());
			}
		}

		for (Entity e : entitiesToAdd) {
			entities.add(e);
			world.addObject(e);

			// lol
			if (e instanceof Character && ((Character) e).getController() instanceof KeyListener) {
				component.addKeyListener((KeyListener) ((Character) e).getController());
			}
		}

		entitiesToAdd.clear();

		for (Tickable entity : getEntities()) {
			entity.tick(delta, this);
		}

		world.advance(delta);

		viewport.center = player.getPosition();
	}

}