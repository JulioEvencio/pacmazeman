package game.tiles;

import java.awt.Color;
import java.awt.Graphics;

import game.util.Mask;

public class Block extends Tile {

	protected final Mask maskCollision;

	public Block(int x, int y) {
		super(x, y, 16, 16);

		maskCollision = new Mask(x, y, 16, 16);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(new Color(50, 50, 50));
		graphics.fillRect(x, y, width, height);
	}

}
