package common;

import physics.AABB;

import java.awt.*;

public interface Renderable {
	void draw(Graphics g);
	AABB getBoundingBox();
}
