package game.entities;

import java.awt.Graphics;

import game.util.Mask;

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

	protected double life;
	protected double maxLife;

	protected final Mask maskCollision;

	public Entity(int x, int y, int width, int height, double speed, double maxLife) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.speed = speed;

		this.maxLife = maxLife;
		this.life = this.maxLife;

		maskCollision = new Mask(x, y, width, height);
	}

	public Mask getMaskCollision() {
		return maskCollision;
	}

	public abstract void tick();

	public abstract void render(Graphics graphics);

}
