package game.itens.coin;

import java.awt.Graphics;
import java.io.IOException;

import game.itens.Item;
import game.resources.Sound;
import game.util.Mask;

public class Coin extends Item {

	private static Sound sound = null;

	public Coin(int x, int y) throws IOException {
		super(x, y, 16, 16, new Mask(x, y, 16, 16), new CoinSprites(x, y, 16, 16));

		if (Coin.sound == null) {
			Coin.sound = new Sound("/sounds/matrixxx/coin.wav");
			Coin.sound.start();
		}
	}

	public void coinDead() {
		Coin.sound.soundPlay();
	}

	private void updateMaskCollision() {
		maskCollision.update((int) x + 5, (int) y + 5, width - 10, height - 10);
	}

	@Override
	public void tick() {
		this.updateMaskCollision();

		sprites.tick();
	}

	@Override
	public void render(Graphics graphics) {
		sprites.render(graphics);
	}

}
