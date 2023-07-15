package game.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.main.Game;

public class FinalScreen extends Screen {

	@Override
	protected void renderString(Graphics graphics) {
		graphics.setColor(Color.GREEN);
		graphics.setFont(new Font("arial", Font.BOLD, 25));
		graphics.drawString("Wow! You're a game master!", Game.WIDTH * Game.SCALE / 2 - 175, 150);
		graphics.drawString("Congratulations!", Game.WIDTH * Game.SCALE / 2 - 100, 200);
	}

}
