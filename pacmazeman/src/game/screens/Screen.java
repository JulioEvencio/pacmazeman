package game.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.main.Game;

public abstract class Screen {

	protected abstract void renderString(Graphics graphics);
	
	public final void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);

		graphics.setColor(Color.YELLOW);
		graphics.setFont(new Font("arial", Font.BOLD, 36));
		graphics.drawString("Pacmazeman", (Game.WIDTH * Game.SCALE) / 2 - 110, (Game.HEIGHT * Game.SCALE) / 2 - 160);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		this.renderString(graphics);
		
		graphics.setColor(Color.YELLOW);
		graphics.setFont(new Font("arial", Font.BOLD, 24));
		graphics.drawString("Press ENTER to go back to main menu", Game.WIDTH * Game.SCALE / 2 - 250, 414);
	}

}
