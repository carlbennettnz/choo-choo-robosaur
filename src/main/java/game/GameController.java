package game;

import common.Renderable;
import common.AABB;
import common.Tickable;
import common.Vector;
import entities.world.Entity;
import entities.world.characters.Player;
import entities.world.characters.controllers.PlayerController;
import physics.PhysObject;
import physics.World;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameController implements common.GameController {
	private Renderable player;
	private List<Entity> entities;
	private AABB viewport;
	private GameStatus status;
	private int level;
	private Set<KeyListener> keyListeners;
	private World world;

	public GameController(int width, int height) {
		this.status = GameStatus.PLAY;
		viewport = new AABB(new Vector(0, 0), new Vector(width / 2, height / 2));

		keyListeners = new HashSet<>();
		world = new World();
		entities = new ArrayList<>();

		PhysObject po = new PhysObject(new AABB(new Vector(0, 0), new Vector(20, 50)), 1);
		PlayerController pc = new PlayerController();
		Player player = new Player(po, pc, 100);

		keyListeners.add(pc);
		world.addObject(po);
		entities.add(player);
	}

	@Override
	public void bindKeyListeners(Component component) {
		for (KeyListener kl : keyListeners) {
			System.out.println("ok");
			component.addKeyListener(kl);
		}
	}

	public Renderable getPlayer() {
		return player;
	}

	public List<Entity> getEntities() {
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

	@Override
	public void tick(double delta) {
		for (Tickable entity : getEntities()) {
			entity.tick(delta);
		}

		world.advance	(delta);
	}
}
