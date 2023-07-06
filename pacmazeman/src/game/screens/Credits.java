package game.screens;

import java.awt.Graphics;

import game.main.Game;

public class Credits extends Screen {

	@Override
	protected void renderString(Graphics graphics) {
		graphics.drawString("> Programmer: Júlio Igreja", Game.WIDTH * Game.SCALE / 2 - 300, 150);
		graphics.drawString("> Access: https://github.com/JulioEvencio", Game.WIDTH * Game.SCALE / 2 - 300, 180);
		
		graphics.drawString("> Sound - menu select: InspectorJ", Game.WIDTH * Game.SCALE / 2 - 300, 210);
		graphics.drawString("> Access: https://freesound.org/people/InspectorJ", Game.WIDTH * Game.SCALE / 2 - 300, 240);
		
		graphics.drawString("> Sound - menu change: victorium183", Game.WIDTH * Game.SCALE / 2 - 300, 270);
		graphics.drawString("> Access: https://freesound.org/people/victorium183", Game.WIDTH * Game.SCALE / 2 - 300, 300);
		
		graphics.drawString("> Sound - menu loop: wi-photos", Game.WIDTH * Game.SCALE / 2 - 300, 330);
		graphics.drawString("> Access: https://freesound.org/people/wi-photos", Game.WIDTH * Game.SCALE / 2 - 300, 360);
	}

}
