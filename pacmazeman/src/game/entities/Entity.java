package game.entities;

import java.awt.Graphics;

import game.util.Mask;
import game.util.Sprite;

public abstract class Entity {

	protected double x;
	protected double y;

	protected int width;
	protected int height;

	protected boolean up;
	protected boolean down;
	protected boolean right;
	protected boolean left;

	protected double speed;

	protected final Mask maskCollision;

	protected final Sprite sprites;

	public Entity(int x, int y, int width, int height, double speed, Mask maskCollision, Sprite sprite) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;

		this.speed = speed;

		this.maskCollision = maskCollision;

		this.sprites = sprite;
	}

	public void updatePosition(int x, int y) {
		this.x = x;
		this.y = y;

		sprites.updatePosition(x, y);
	}

	public Mask getMaskCollision() {
		return maskCollision;
	}

	public abstract void tick();

	public abstract void render(Graphics graphics);

	public void moveUp() {
		up = true;
	}

	public void stopUp() {
		up = false;
	}

	public void moveDown() {
		down = true;
	}

	public void stopDown() {
		down = false;
	}

	public void moveRight() {
		right = true;
	}

	public void stopRight() {
		right = false;
	}

	public void moveLeft() {
		left = true;
	}

	public void stopLeft() {
		left = false;
	}

}
