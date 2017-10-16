package entities.world.scenery;

import common.*;
import entities.world.Entity;

public abstract class Scenery extends Entity {
    public Scenery(AABB box){
        super(box, 0);
    }

    @Override
    public void tick(double delta, GameController game) {

    }

    @Override
    public void collide(Physical o, Vector[] collision) {

    }
}
