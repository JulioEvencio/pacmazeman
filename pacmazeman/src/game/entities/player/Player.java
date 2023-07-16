package game.entities.player;

import java.awt.Graphics;
import java.io.IOException;

import game.entities.Entity;
import game.scenarios.Scenario;
import game.util.Mask;

public class Player extends Entity {

	private double life;
	private double maxLife;

	public Player(int x, int y) throws IOException {
		super(x, y, 16, 16, 1, new Mask(x, y, 16, 16), new PlayerSprites(x, y, 16, 16));

		this.maxLife = 1;
		this.life = this.maxLife;
	}
	
	public void takeDamage(double damage) {
		life -= damage;
	}

	public boolean isDead() {
		return life <= 0;
	}

	private PlayerSprites getPlayerSprites() {
		return (PlayerSprites) sprites;
	}

	private void updateMaskCollision() {
		maskCollision.update((int) x + 3, (int) y + 3, width - 5, height - 5);
	}

	@Override
	public void tick(Scenario scenario) {
		if (up && scenario.isFree(this, scenario.UP)) {
			this.getPlayerSprites().setDirectionUp();
			y -= speed;
		} else if (down && scenario.isFree(this, scenario.DOWN)) {
			this.getPlayerSprites().setDirectionDown();
			y += speed;
		} else if (right && scenario.isFree(this, scenario.RIGHT)) {
			this.getPlayerSprites().setDirectionRight();
			x += speed;
		} else if (left && scenario.isFree(this, scenario.LEFT)) {
			this.getPlayerSprites().setDirectionLeft();
			x -= speed;
		}

		this.updateMaskCollision();

		sprites.updatePosition((int) x, (int) y);
		sprites.tick();
	}

	@Override
	public void render(Graphics graphics) {
		sprites.render(graphics);
	}

}
