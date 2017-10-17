package entities.world;

import common.AABB;
import common.Vector;
import entities.world.characters.CharacterController;
import entities.world.characters.Player;
import entities.world.characters.Robot;
import entities.world.characters.controllers.PlayerController;
import entities.world.characters.controllers.ShooterController;
import entities.world.collectables.Key;
import entities.world.scenery.Crate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EntityParser {
	public static List<Entity> loadFile(String path) {
		File f = new File(path);
		Scanner s;
		List<Entity> entities = new ArrayList<>();

		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			throw new Error("Level file not found");
		}

		while (s.hasNext()) {
			String type = s.next();
			Entity e = null;

			switch (type) {
				case "Player":
					e = parsePlayer(s);
					break;

				case "Robot":
					e = parseRobot(s);
					break;

				case "Crate":
					e = parseCrate(s);
					break;

				case "Key":
					e = parseKey(s);
					break;
			}

			if (e != null) {
				entities.add(e);
			} else {
				throw new Error("Unrecognised entity in level file");
			}
		}

		return entities;
	}

	private static Entity parsePlayer(Scanner s) {
		Vector pos = new Vector(
			s.nextDouble(),
			s.nextDouble()
		);

		CharacterController controller = new PlayerController();

		return new Player(pos, controller, 100);
	}

	private static Entity parseRobot(Scanner s) {
		Vector pos = new Vector(
			s.nextDouble(),
			s.nextDouble()
		);

		CharacterController controller = new ShooterController();

		return new Robot(pos, controller, 100);
	}

	private static Entity parseCrate(Scanner s) {
		Vector pos = new Vector(
			s.nextDouble(),
			s.nextDouble()
		);

		Vector size = new Vector(
			s.nextDouble(),
			s.nextDouble()
		);

		AABB box = new AABB(pos, size);

		return new Crate(box);
	}

	private static Entity parseKey(Scanner s) {
		Vector pos = new Vector(
			s.nextDouble(),
			s.nextDouble()
		);

		return new Key(pos);
	}
}
