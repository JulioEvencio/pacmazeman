package game.screens;

import java.awt.Graphics;

import game.main.Game;

public class Credits extends Screen {

	@Override
	protected void renderString(Graphics graphics) {
		graphics.drawString("> Programmer: Júlio Igreja", Game.WIDTH * Game.SCALE / 2 - 300, 150);
		graphics.drawString("> Access: https://github.com/JulioEvencio", Game.WIDTH * Game.SCALE / 2 - 300, 170);
	}

}
