package game.screens;

import java.awt.Graphics;

import game.main.Game;

public class Credits extends Screen {

	@Override
	protected void renderString(Graphics graphics) {
		graphics.drawString("Programmer:", Game.WIDTH * Game.SCALE / 2 - 300, 150);
		graphics.drawString("    > Júlio Igreja", Game.WIDTH * Game.SCALE / 2 - 300, 180);
		
		graphics.drawString("Sounds:", Game.WIDTH * Game.SCALE / 2 - 300, 210);
		graphics.drawString("    > InspectorJ (freesound.org)     > victorium183 (freesound.org)", Game.WIDTH * Game.SCALE / 2 - 300, 240);
		graphics.drawString("    > wi-photos (freesound.org)      > MATRIXXX_ (freesound.org)", Game.WIDTH * Game.SCALE / 2 - 300, 270);
		graphics.drawString("    > IgorChagas (freesound.org)", Game.WIDTH * Game.SCALE / 2 - 300, 300);
		
		graphics.drawString("Sprites:", Game.WIDTH * Game.SCALE / 2 - 300, 330);
		graphics.drawString("    > VladPenn (itch.io)", Game.WIDTH * Game.SCALE / 2 - 300, 360);
	}

}
