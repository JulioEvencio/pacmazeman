package game.tiles;

import java.awt.Graphics;

public abstract class Tile {

	protected final int x;
	protected final int y;

	protected final int width;
	protected final int height;

	public Tile(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;
	}

	public abstract void render(Graphics graphics);

}
