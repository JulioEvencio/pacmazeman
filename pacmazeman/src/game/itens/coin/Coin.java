package game.itens.coin;

import java.awt.Graphics;
import java.io.IOException;

import game.itens.Item;
import game.util.Mask;

public class Coin extends Item {

	public Coin(int x, int y) throws IOException {
		super(x, y, 16, 16, new Mask(x, y, 16, 16), new CoinSprites(x, y, 16, 16));
	}

	@Override
	public void tick() {
		sprites.tick();
	}

	@Override
	public void render(Graphics graphics) {
		sprites.render(graphics);
	}

}
