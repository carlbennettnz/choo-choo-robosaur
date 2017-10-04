package common;

import java.awt.*;

public interface Renderable {
	void draw(Graphics g);
	AABB getBoundingBox();
}
