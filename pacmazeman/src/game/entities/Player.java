package game.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.resources.Spritesheet;

public class Player extends Entity {

	private int frames;
	private final int maxFrames;

	private int index;
	private final int maxIndex;

	protected final BufferedImage[] sprites;

	public Player() throws IOException {
		super(50, 50, 16, 16, 1, 1);

		this.frames = 0;
		this.maxFrames = 5;

		this.index = 0;
		this.maxIndex = 8;

		Spritesheet spritesheet = new Spritesheet("/sprites/entities/player.png");

		this.sprites = new BufferedImage[this.maxIndex];

		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = spritesheet.getSprite(0 + (i * 16), 0, 16, 16);
		}
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
			x += speed;
		}

		if (left) {
			x -= speed;
		}
		
		frames++;

		if (frames == maxFrames) {
			frames = 0;
			index++;

			if (index == maxIndex) {
				index = 0;
			}
		}
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(sprites[index], (int) x, (int) y, width, height, null);
	}

}
