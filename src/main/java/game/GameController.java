package game;

import common.*;
import entities.world.Entity;
import entities.world.characters.Player;
import entities.world.characters.Robot;
import entities.world.characters.controllers.PlayerController;
import entities.world.characters.controllers.ShooterController;
import entities.world.scenery.Box;
import entities.world.scenery.Scenery;
import physics.PhysObject;
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
	private Set<PhysObject> physObjectsToAdd;

	public GameController(int width, int height) {
		this.status = GameStatus.PLAY;
		viewport = new AABB(new Vector(0, 0), new Vector(width / 2, height / 2));
		entitiesToAdd = new HashSet<>();
		physObjectsToAdd = new HashSet<>();

		keyListeners = new HashSet<>();
		world = new World();
		entities = new ArrayList<>();

		PhysObject po = new PhysObject(new AABB(new Vector(0, 0), new Vector(20, 50)), 1);
		PlayerController pc = new PlayerController();
		player = new Player(po, pc, 100);

		keyListeners.add(pc);
		addEntity(player, po);

		PhysObject boxPO1 = new PhysObject(new AABB(new Vector(100, 0), new Vector(50, 50)), 1);
		PhysObject boxPO2 = new PhysObject(new AABB(new Vector(500, 0), new Vector(50, 50)), 100);
		Box box1 = new Box(boxPO1);
		Box box2 = new Box(boxPO2);
		addEntity(box1, boxPO1);
		addEntity(box2, boxPO2);

		PhysObject robotPO = new PhysObject(new AABB(new Vector(-300, 0), new Vector(50, 50)), 1);
		ShooterController robotC = new ShooterController();
		Robot robot = new Robot(robotPO, robotC, 100);
		addEntity(robot, robotPO);
	}

	@Override
	public void bindKeyListeners(Component component) {
		for (KeyListener kl : keyListeners) {
			component.addKeyListener(kl);
		}
	}

	public Renderable getPlayer() {
		return player;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void addEntity(Entity entity, PhysObject physObject) {
		entitiesToAdd.add(entity);
		physObjectsToAdd.add(physObject);
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
	public void tick(double delta, common.GameController game) {
		List<Entity> entitiesForRemoval = entities.stream().filter(e -> e.removeOnNextTick).collect(Collectors.toList());

		for (Entity e : entitiesForRemoval) {
			world.removeObject((PhysObject) e.position);
			entities.remove(e);

			// todo: catch player death
		}

		entities.addAll(entitiesToAdd);
		entitiesToAdd.clear();

		for (PhysObject po : physObjectsToAdd) {
			world.addObject(po);
		}

		physObjectsToAdd.clear();

		for (Tickable entity : getEntities()) {
			entity.tick(delta, this);
		}

		world.advance(delta);

		viewport.center = player.getPosition();
	}

}