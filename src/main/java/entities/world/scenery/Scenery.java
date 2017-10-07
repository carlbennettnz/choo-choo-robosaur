package entities.world.scenery;

import common.Positionable;
import entities.world.Entity;
import physics.PhysObject;

public abstract class Scenery extends Entity {
    protected PhysObject physObject;

    public Scenery(Positionable position){
        super(position);
    }

    // would probably have a second constructor that creates an unmovable PhysObject
}
