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
import game.itens.coin.Coin;
import game.main.Game;
import game.resources.Sound;
import game.tiles.Block;
import game.tiles.Floor;

public class Scenario {

	private int WIDTH;
	private int HEIGHT;

	private final Player player;

	private final List<Floor> floors;
	private final List<Block> blocks;

	private final List<Coin> coins;

	private final List<Enemy> enemies;

	private final Sound soundMenuLoop;

	public Scenario(Player player) throws IOException {
		this.soundMenuLoop = new Sound("/sounds/igorchagas/scenario.wav");
		this.soundMenuLoop.start();

		this.player = player;
		
		this.floors = new ArrayList<>();
		this.blocks = new ArrayList<>();

		this.enemies = new ArrayList<>();

		this.coins = new ArrayList<>();

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
						this.blocks.add(new Block(x * 16, y * 16));
						break;
					case 0xFF00FF00:
						this.floors.add(new Floor(x * 16, y * 16));
						this.coins.add(new Coin(x * 16, y * 16));
						break;
					case 0xFF0000FF:
						this.floors.add(new Floor(x * 16, y * 16));
						this.enemies.add(new Enemy(x * 16, y * 16));
						break;
					case 0xFFFFFF00:
						this.floors.add(new Floor(x * 16, y * 16));
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

		for (Coin coin : coins) {
			coin.tick();
		}

		for (Enemy enemy : enemies) {
			enemy.tick();
		}

		player.tick();
	}

	public void render(Graphics graphics) {
		for (Floor floor : floors) {
			floor.render(graphics);
		}
		
		for (Block block : blocks) {
			block.render(graphics);
		}
		
		for (Coin coin : coins) {
			coin.render(graphics);
		}

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
