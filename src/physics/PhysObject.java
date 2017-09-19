package physics;

import javafx.geometry.BoundingBox;

import java.awt.*;

public abstract class PhysObject {
	public PhysObject() {

	}

	public abstract BoundingBox getBoudingBox();

	public abstract Point getPosition();

	public abstract void setPosition(Point p);

	public abstract void handleCollision(PhysObject o);
}
