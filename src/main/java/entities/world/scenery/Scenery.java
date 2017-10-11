package entities.world.scenery;

import common.Collidable;
import common.GameController;
import common.Positionable;
import common.Vector;
import entities.world.Entity;
import physics.PhysObject;

public abstract class Scenery extends Entity {
    protected PhysObject physObject;

    public Scenery(Positionable position){
        super(position);
    }

    // would probably have a second constructor that creates an unmovable PhysObject

    @Override
    public void tick(double delta, GameController game) {

    }

    @Override
    public void collide(Collidable entity, Vector vector) {

    }
}
