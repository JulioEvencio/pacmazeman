package game.entities.enemy;

import java.awt.Graphics;
import java.io.IOException;

import game.entities.Entity;
import game.util.Mask;

public class Enemy extends Entity {

	public Enemy(int x, int y) throws IOException {
		super(x, y, 16, 16, 0.5, new Mask(x, y, 16, 16), new EnemySprites(x, y, 16, 16));
	}

	private EnemySprites getEnemySprites() {
		return (EnemySprites) sprites;
	}

	@Override
	public void tick() {
		sprites.updatePosition((int) x, (int) y);
		sprites.tick();
	}

	@Override
	public void render(Graphics graphics) {
		sprites.render(graphics);
	}

}
