package game.scenarios;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.entities.enemy.Enemy;
import game.entities.player.Player;

public class Scenario {

	private final Player player;

	private final List<Enemy> enemies;

	public Scenario(Player player) throws IOException {
		this.player = player;

		this.enemies = new ArrayList<>();

		this.enemies.add(new Enemy(10, 10));
		this.enemies.add(new Enemy(80, 50));
		this.enemies.add(new Enemy(300, 200));
	}

	public void tick() {
		for (Enemy enemy : enemies) {
			enemy.tick();
		}

		player.tick();
	}

	public void render(Graphics graphics) {
		for (Enemy enemy : enemies) {
			enemy.render(graphics);
		}

		player.render(graphics);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player.moveUp();
		}

		if (e.getKeyCode() == KeyEvent.VK_S) {
			player.moveDown();
		}

		if (e.getKeyCode() == KeyEvent.VK_D) {
			player.moveRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_A) {
			player.moveLeft();
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player.stopUp();
		}

		if (e.getKeyCode() == KeyEvent.VK_S) {
			player.stopDown();
		}

		if (e.getKeyCode() == KeyEvent.VK_D) {
			player.stopRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_A) {
			player.stopLeft();
		}
	}

}
