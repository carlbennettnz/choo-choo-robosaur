package entities.world;

import common.AABB;
import common.Collidable;
import common.Renderable;
import physics.PhysObject;

public abstract class Entity implements Renderable, Collidable {
    private PhysObject physObject;

    public Entity(PhysObject physObject) {
        setPhysObject(physObject);
    }

    public final void setPhysObject(PhysObject physObject){
        assert (physObject != null);
        this.physObject = physObject;
    }

    public final PhysObject getPhysObject(){
        return physObject;
    }

    public AABB getBoundingBox() {
        return physObject.getBoundingBox();
    }
}
