package game.entities.enemy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.List;

import game.astar.AStar;
import game.astar.Node;
import game.astar.Vector2i;
import game.entities.Entity;
import game.scenarios.Scenario;
import game.util.Mask;

public class Enemy extends Entity {

	private List<Node> path;

	public Enemy(int x, int y, double speed) throws IOException {
		super(x, y, 16, 16, speed, new Mask(x, y, 16, 16), new EnemySprites(x, y, 16, 16));
	}

	private double dealDamage() {
		return 1;
	}

	private EnemySprites getEnemySprites() {
		return (EnemySprites) sprites;
	}

	private void updateMaskCollision() {
		maskCollision.update((int) x + 3, (int) y + 3, width - 5, height - 5);
	}

	private void algorithmFindPlayer(Scenario scenario) {
		Rectangle maskThis = maskCollision.getRectangle();
		Rectangle maskPlayer = scenario.player.getMaskCollision().getRectangle();

		if (path == null || path.size() == 0) {
			Vector2i start = new Vector2i((int) maskThis.x / 16, (int) maskThis.y / 16);
			Vector2i end = new Vector2i((int) maskPlayer.x / 16, (int) maskPlayer.y / 16);

			path = AStar.findPath(scenario, start, end);
		}

		this.followPath(path);
	}

	private void followPath(List<Node> path) {
		Rectangle maskThis = maskCollision.getRectangle();
		
		if (path != null) {
			if (path.size() > 0) {
				Vector2i target = path.get(path.size() - 1).tile;

				if (maskThis.x < target.x * 16) {
					x += speed;
					this.getEnemySprites().setDirectionRight();
				} else if (maskThis.x > target.x * 16) {
					x -= speed;
					this.getEnemySprites().setDirectionLeft();
				} else if (maskThis.y < target.y * 16) {
					y += speed;
				} else if (maskThis.y > target.y * 16) {
					y -= speed;
				}

				if (maskThis.x == target.x * 16 && maskThis.y == target.y * 16) {
					path.remove(path.size() - 1);
				}
			}
		}
	}

	@Override
	public void tick(Scenario scenario) {
		if (scenario.player.getMaskCollision().getRectangle().intersects(this.getMaskCollision().getRectangle())) {
			scenario.player.takeDamage(this.dealDamage());
		}

		this.algorithmFindPlayer(scenario);

		this.updateMaskCollision();

		sprites.updatePosition((int) x, (int) y);
		sprites.tick();
	}

	@Override
	public void render(Graphics graphics) {
		sprites.render(graphics);
	}

}
