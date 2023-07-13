package game.scenarios;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import game.entities.enemy.Enemy;
import game.entities.player.Player;
import game.main.Game;
import game.resources.Sound;

public class Scenario {

	private int WIDTH;
	private int HEIGHT;

	private final Player player;

	private final List<Enemy> enemies;
	
	private final Sound soundMenuLoop;

	public Scenario(Player player) throws IOException {
		this.soundMenuLoop = new Sound("/sounds/igorchagas/scenario.wav");
		this.soundMenuLoop.start();
		
		this.player = player;

		this.enemies = new ArrayList<>();

		this.loadScenario();
	}

	private void loadScenario() throws IOException {
		BufferedImage map = ImageIO.read(this.getClass().getResource("/scenarios/scenario.png"));

		int[] pixels = new int[map.getWidth() * map.getHeight()];

		this.WIDTH = map.getWidth();
		this.HEIGHT = map.getHeight();

		map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());

		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				int currentPixel = pixels[x + (y * map.getWidth())];

				switch (currentPixel) {
					case 0xFF000000:
						// Code
						break;
					case 0xFFFFFFFF:
						// Code
						break;
					case 0xFF0000FF:
						this.enemies.add(new Enemy(x * 16, y * 16));
						break;
					case 0xFFFFFF00:
						player.updatePosition(x * 16, y * 16);
						break;
				}
			}
		}
	}
	
	private void setSound(Sound sound) {
		if (Game.enableSound) {
			sound.soundPlay();
		} else {
			sound.soundStop();
		}
	}
	
	private void stopSound() {
		soundMenuLoop.soundStop();
	}
	
	public boolean gameOver() {
		this.stopSound();
		
		return player.isDead();
	}

	public void tick() {
		this.setSound(soundMenuLoop);
		
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
