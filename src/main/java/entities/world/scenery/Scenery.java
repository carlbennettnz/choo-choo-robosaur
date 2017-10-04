package entities.world.scenery;

import entities.world.Entity;
import physics.PhysObject;

public abstract class Scenery extends Entity {
    protected PhysObject physObject;

    public Scenery(PhysObject physObject){
        super(physObject);
    }

    // would probably have a second constructor that creates an unmovable PhysObject
}
