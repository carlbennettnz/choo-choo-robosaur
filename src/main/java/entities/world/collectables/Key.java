package entities.world.collectables;

import common.AABB;
import common.GameController;
import common.Physical;
import common.Vector;
import entities.inventory.InventoryItem;
import entities.properties.Collectable;
import entities.properties.ItemCollector;
import entities.world.Entity;
import entities.world.characters.Player;

import java.awt.*;

public class Key extends BaseCollectable {
	public Key(Vector pos) {
		super(new AABB(pos, new Vector(10, 10)));
	}

	@Override
	public void draw(Graphics g) {

	}
}
