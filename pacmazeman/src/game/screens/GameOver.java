package game.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.main.Game;

public class GameOver extends Screen {

	@Override
	protected void renderString(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("arial", Font.BOLD, 25));
		graphics.drawString("Game Over", Game.WIDTH * Game.SCALE / 2 - 60, 150);
	}

}
