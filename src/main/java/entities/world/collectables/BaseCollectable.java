package entities.world.collectables;

import common.AABB;
import common.GameController;
import common.Physical;
import common.Vector;
import entities.properties.Collectable;
import entities.properties.ItemCollector;
import entities.world.Entity;


abstract class BaseCollectable extends Entity implements Collectable {
    BaseCollectable(AABB box) {
        super(box, 0);
    }

    public void collide(Physical entity, Vector vector) {
        if (entity instanceof ItemCollector) {
			ItemCollector collector = (ItemCollector) entity;
			collector.collect(this);
            removeOnNextTick = true;
        }
    }

    @Override
    public void tick(double delta, GameController game) {

    }
}
