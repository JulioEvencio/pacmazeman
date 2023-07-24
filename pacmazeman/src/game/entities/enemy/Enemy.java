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

	public Enemy(int x, int y) throws IOException {
		super(x, y, 16, 16, 0.5, new Mask(x, y, 16, 16), new EnemySprites(x, y, 16, 16));
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

		if (maskThis.x < maskPlayer.x && scenario.isFree(this, scenario.RIGHT)) {
			x += speed;
			this.getEnemySprites().setDirectionRight();
		} else if (maskThis.x > maskPlayer.x && scenario.isFree(this, scenario.LEFT)) {
			x -= speed;
			this.getEnemySprites().setDirectionLeft();
		} else if (maskThis.y < maskPlayer.y && scenario.isFree(this, scenario.DOWN)) {
			y += speed;
		} else if (maskThis.y > maskPlayer.y && scenario.isFree(this, scenario.UP)) {
			y -= speed;
		}
	}
	
	public void followPath(List<Node> path) {
		if (path != null) {
			if (path.size() > 0) {
				Vector2i target = path.get(path.size() - 1).tile;
				
				// xprev = x;
				// yprev = y;
				
				if (x < target.x * 16) {
					// x += speed;
					x++;
				} else if (x > target.x * 16) {
					// x -= speed;
					x--;
				} else if (y < target.y * 16) {
					// y += speed;
					y++;
				} else if (y > target.y * 16) {
					// y -= speed;
					y--;
				}
				
				if (x == target.x * 16 && y == target.y * 16) {
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

		// this.algorithmFindPlayer(scenario);
		if (path == null || path.size() == 0) {
			Vector2i start = new Vector2i((int) x / 16, (int) y / 16);
			Vector2i end = new Vector2i((int) scenario.player.getMaskCollision().getRectangle().x / 16, (int) scenario.player.getMaskCollision().getRectangle().y / 16);
			
			path = AStar.findPath(scenario, start, end);
		}
		
		this.followPath(path);

		this.updateMaskCollision();

		sprites.updatePosition((int) x, (int) y);
		sprites.tick();
	}

	@Override
	public void render(Graphics graphics) {
		sprites.render(graphics);
	}

}
