package game.entities.player;

import java.awt.Graphics;
import java.io.IOException;

import game.entities.Entity;
import game.util.Mask;

public class Player extends Entity {

	private double life;
	private double maxLife;

	public Player() throws IOException {
		super(50, 50, 16, 16, 1, new Mask(50, 50, 16, 16), new PlayerSprites());

		this.maxLife = 1;
		this.life = this.maxLife;
	}

	public boolean isDead() {
		return life <= 0;
	}

	private PlayerSprites getPlayerSprites() {
		return (PlayerSprites) sprites;
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
			this.getPlayerSprites().setDirectionRight();
			x += speed;
		}

		if (left) {
			this.getPlayerSprites().setDirectionLeft();
			x -= speed;
		}

		sprites.updatePosition((int) x, (int) y, width, height);
		sprites.tick();
	}

	@Override
	public void render(Graphics graphics) {
		sprites.render(graphics);
	}

}
