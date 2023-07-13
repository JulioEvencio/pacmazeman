package game.tiles;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class Floor extends Tile {

	public Floor(int x, int y) throws IOException {
		super(x, y, 16, 16);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(new Color(100, 100, 100));
		graphics.fillRect(x, y, width, height);
	}

}
