package game.screens;

import game.main.Game;

public class Pause extends Menu {

	public Pause() {
		super(Game.GAME_PAUSE, new String[] { "Resume", "New Game", "Menu", "Exit" });
	}

	@Override
	protected void enterLogic() {
		if (currentOption == 0) {
			selectedOption = Game.GAME_RUN;
		} else if (currentOption == 1) {
			selectedOption = Game.GAME_RESTART;
		} else if (currentOption == 2) {
			selectedOption = Game.GAME_MENU;
		} else if (currentOption == 3) {
			selectedOption = Game.GAME_EXIT;
		}
	}

}