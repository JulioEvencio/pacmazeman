package game.itens;

import java.awt.Graphics;

import game.util.Mask;
import game.util.Sprite;

public abstract class Item {

	protected int x;
	protected int y;

	protected int width;
	protected int height;

	protected final Mask maskCollision;

	protected final Sprite sprites;

	public Item(int x, int y, int width, int height, Mask maskCollision, Sprite sprite) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;

		this.maskCollision = maskCollision;

		this.sprites = sprite;
	}

	public Mask getMaskCollision() {
		return maskCollision;
	}

	public abstract void tick();

	public abstract void render(Graphics graphics);

}
