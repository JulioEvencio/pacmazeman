package game.entities;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Entity {

	public Player() {
		super(50, 50, 16, 16, 1, 1);
	}

	@Override
	public void tick() {
		if (up) {
			y -= speed;
		}

		if (down) {
			y += speed;
		}

		if (right) {
			x += speed;
		}

		if (left) {
			x -= speed;
		}
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.YELLOW);
		graphics.fillRect((int) x, (int) y, width, height);
	}

}
