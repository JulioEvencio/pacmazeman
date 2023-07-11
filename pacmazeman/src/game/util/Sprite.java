package game.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Sprite {

	protected int x;
	protected int y;

	protected int width;
	protected int height;

	protected int frames;
	protected final int maxFrames;

	protected int index;
	protected final int maxIndex;

	protected BufferedImage[][] sprites;

	protected int currentArraySprite;

	public Sprite(int maxFrames, int maxIndex) throws IOException {
		this.maxFrames = maxFrames;
		this.maxIndex = maxIndex;

		this.currentArraySprite = 0;

		this.loadSpritesheet();
	}

	protected abstract void loadSpritesheet() throws IOException;

	public void updatePosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	protected void updateFramesSprites() {
		frames++;

		if (frames == maxFrames) {
			frames = 0;
			index++;

			if (index == maxIndex) {
				index = 0;
			}
		}
	}

	public void tick() {
		this.updateFramesSprites();
	}

	public abstract void render(Graphics graphics);

}
