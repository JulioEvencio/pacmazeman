package game.screens;

import game.main.Game;

public class MainMenu extends Menu {

	public MainMenu() {
		super(Game.GAME_MENU, new String[] { "New Game", "Tutorial", "Credits", "Exit" });
	}

	@Override
	protected void enterLogic() {
		if (currentOption == 0) {
			selectedOption = Game.GAME_RUN;
		} else if (currentOption == 1) {
			selectedOption = Game.GAME_TUTORIAL;
		} else if (currentOption == 2) {
			selectedOption = Game.GAME_CREDITS;
		} else if (currentOption == 3) {
			selectedOption = Game.GAME_EXIT;
		}
	}

}