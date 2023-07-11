package game.entities.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.resources.Spritesheet;
import game.util.Sprite;

public class PlayerSprites extends Sprite {

	public PlayerSprites(int x, int y, int width, int height) throws IOException {
		super(5, 8);

		super.x = x;
		super.y = y;

		super.width = width;
		super.height = height;
	}

	public void setDirectionRight() {
		currentArraySprite = 0;
	}

	public void setDirectionLeft() {
		currentArraySprite = 1;
	}

	public void setDirectionDown() {
		currentArraySprite = 2;
	}

	public void setDirectionUp() {
		currentArraySprite = 3;
	}

	@Override
	protected void loadSpritesheet() throws IOException {
		Spritesheet spritesheet = new Spritesheet("/sprites/entities/player.png");

		sprites = new BufferedImage[4][maxIndex];

		for (int i = 0; i < sprites[0].length; i++) {
			sprites[0][i] = spritesheet.getSprite(0 + (i * 16), 0, 16, 16);
		}

		for (int i = 0; i < sprites[1].length; i++) {
			sprites[1][i] = spritesheet.getSprite(0 + (i * 16), 16, 16, 16);
		}

		for (int i = 0; i < sprites[2].length; i++) {
			sprites[2][i] = spritesheet.getSprite(0 + (i * 16), 32, 16, 16);
		}

		for (int i = 0; i < sprites[3].length; i++) {
			sprites[3][i] = spritesheet.getSprite(0 + (i * 16), 48, 16, 16);
		}

		currentArraySprite = 0;
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(sprites[currentArraySprite][index], x, y, width, height, null);
	}

}
