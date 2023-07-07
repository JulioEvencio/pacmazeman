package game.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.main.Game;
import game.resources.Sound;

public abstract class Menu {
	
	private final int gameStateDefault;

	private final String[] options;
	
	private final int maxOption;

	protected int currentOption;
	protected int selectedOption;

	private boolean up;
	private boolean down;
	private boolean enter;
	
	private final Sound soundMenuLoop;
	private final Sound soundMenuChange;
	private final Sound soundMenuSelect;

	public Menu(int gameStateDefault, String[] options) {
		this.soundMenuLoop = new Sound("/sounds/wiphotos/menu-loop.wav");
		this.soundMenuLoop.start();
		
		this.soundMenuChange = new Sound("/sounds/victorium183/menu-change.wav");
		this.soundMenuChange.start();
		
		this.soundMenuSelect = new Sound("/sounds/inspectorj/menu-select.wav");
		this.soundMenuSelect.start();

		this.gameStateDefault = gameStateDefault;
		
		this.options = options;
		
		this.maxOption = this.options.length - 1;

		this.currentOption = 0;
		this.selectedOption = 0;

		up = false;
		down = false;
		enter = false;
	}
	
	public void menuUp() {
		this.setSound(soundMenuChange);
		up = true;
	}
	
	public void menuDown() {
		this.setSound(soundMenuChange);
		down = true;
	}
	
	public void menuEnter() {
		this.setSound(soundMenuSelect);
		enter = true;
	}
	
	public int getOption() {
		return selectedOption;
	}
	
	private void setSound(Sound sound) {
		if (Game.enableSound) {
			sound.soundPlay();
		} else {
			sound.soundStop();
		}
	}
	
	private void stopSound() {
		soundMenuLoop.soundStop();
	}
	
	protected abstract void enterLogic();
	
	public final void tick() {
		this.setSound(soundMenuLoop);
		
		selectedOption = gameStateDefault;
		
		if (up) {
			up = false;
			currentOption--;

			if (currentOption < 0) {
				currentOption = 0;
			}
		}

		if (down) {
			down = false;
			currentOption++;

			if (currentOption > maxOption) {
				currentOption = maxOption;
			}
		}

		if (enter) {
			enter = false;
			
			this.enterLogic();
			this.stopSound();
		}
	}

	public final void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);

		graphics.setColor(Color.YELLOW);
		graphics.setFont(new Font("arial", Font.BOLD, 36));
		graphics.drawString("Pacmazeman", (Game.WIDTH * Game.SCALE) / 2 - 110, (Game.HEIGHT * Game.SCALE) / 2 - 160);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.BOLD, 24));
		
		for (int i = 0; i < options.length; i++) {
			graphics.setColor(Color.WHITE);
			
			if (currentOption == i) {
				graphics.setColor(Color.YELLOW);
				graphics.drawString("-> ", Game.WIDTH * Game.SCALE / 2 - 90, 150 + (i * 50));
			}
			
			graphics.drawString(options[i], Game.WIDTH * Game.SCALE / 2 - 50, 150 + (i * 50));
		}
		
		graphics.setColor(Color.YELLOW);
		graphics.drawString("Use W and S keys to move and ENTER to select", Game.WIDTH * Game.SCALE / 2 - 305, 414);
	}

}