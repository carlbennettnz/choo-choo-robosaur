package canvas;

import physics.AABB;

import java.awt.*;

public interface Renderable {
	AABB getBoundingBox();
	void draw(Graphics g);
}
