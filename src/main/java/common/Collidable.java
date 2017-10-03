package common;

import physics.Vector;

public interface Collidable {
	void collide(Collidable entity, Vector vector);
}
