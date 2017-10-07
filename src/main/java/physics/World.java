package physics;

import java.util.ArrayList;
import java.util.List;

public class World {
	List<PhysObject> objects;
	List<PhysObject> toAdd;
	List<PhysObject> toRemove;
	public World() {
		objects = new ArrayList<>();
		toAdd = new ArrayList<>();
		toRemove = new ArrayList<>();
	}

	public void addObject(PhysObject o) {
		toAdd.add(o);
	}

	public void removeObject(PhysObject o) {
		toRemove.add(o);
	}
	
	/**
	 * dt is the time passed, in seconds, since advance was last called.
	 * @param dt change in time (seconds)
	 */
	public void advance(double dt) {
		/* remove objects that are to be removed */
		objects.removeAll(toRemove);
		toRemove.clear();
		
		/* add objects that are to be added */
		objects.addAll(toAdd);
		toAdd.clear();
		
		/* advance all objects */
		for(PhysObject o : objects) {
			o.advance(dt);
		}
		
		/* resolve collisions between objects */
		for(int i = 0; i < objects.size(); i++) {
			PhysObject a = objects.get(i);
			for(int j = i + 1; j < objects.size(); j++) {
				PhysObject b = objects.get(j);
				a.resolveCollision(b);
			}
		}
	}

	public List<PhysObject> getWorldState() {
		return objects;
	}
}
