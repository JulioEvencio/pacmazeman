package game.util;

import java.awt.Rectangle;

public class Mask {

	private Rectangle rectangle;

	public Mask(int x, int y, int width, int height) {
		rectangle = new Rectangle(x, y, width, height);
	}

	public void update(int x, int y, int width, int height) {
		rectangle = new Rectangle(x, y, width, height);
	}

	private Rectangle getRectangle() {
		return rectangle;
	}

	public boolean isColliding(Mask mask) {
		return rectangle.intersects(mask.getRectangle());
	}

}
