package game;

import entities.world.Entity;
import entities.world.EntityParser;
import ui.UI;

import java.awt.*;
import java.util.List;

public class Game {
	public Game() {
		new UI(this).start();
	}

	public common.GameController loadLevel(Component component, int level) {
		GameController controller = new GameController(component, level);

		List<Entity> entities = EntityParser.loadFile("src/main/res/levels/level" + level + ".txt");

		for (Entity entity : entities) {
			controller.addEntity(entity);
		}

		return controller;
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
