package game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.entities.player.Player;
import game.scenarios.Scenario;
import game.screens.Credits;
import game.screens.FinalScreen;
import game.screens.GameOver;
import game.screens.MainMenu;
import game.screens.Menu;
import game.screens.Screen;
import game.screens.Tutorial;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	private Thread thread;
	private boolean isRunning;

	public static final int WIDTH = 720;
	public static final int HEIGHT = 480;
	public static final int SCALE = 1;

	private int gameState;

	public static final int GAME_MENU = 1;
	public static final int GAME_RUN = 2;
	public static final int GAME_TUTORIAL = 3;
	public static final int GAME_CREDITS = 4;
	public static final int GAME_GAME_OVER = 5;
	public static final int GAME_FINAL_SCREEN = 6;
	public static final int GAME_EXIT = 7;

	private int fps;
	private boolean showFPS;

	private final BufferedImage renderer;

	private final Menu mainMenu;

	private final Screen tutorial;
	private final Screen credits;
	private final Screen gameOver;
	private final Screen finalScreen;

	public static boolean enableSound;

	private Player player;
	private Scenario scenario;

	public Game() throws IOException {
		this.addKeyListener(this);

		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame();

		frame.setTitle("Pacmazeman");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		this.renderer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		this.gameState = Game.GAME_MENU;

		this.mainMenu = new MainMenu();

		this.tutorial = new Tutorial();
		this.credits = new Credits();
		this.gameOver = new GameOver();
		this.finalScreen = new FinalScreen();

		Game.enableSound = true;

		this.player = new Player(0, 0);
		this.scenario = new Scenario(player);
	}

	private synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	private synchronized void stop() {
		isRunning = false;

		try {
			thread.join();
		} catch (Exception e) {
			Game.exitWithError("An error has occurred. The program will be terminated.");
		}
	}

	private void updateGameState(int gameState) {
		this.gameState = gameState;
	}

	private void tick() {
		if (gameState == Game.GAME_RUN) {
			scenario.tick();
		} else if (gameState == Game.GAME_MENU) {
			mainMenu.tick();

			this.updateGameState(mainMenu.getOption());
		} else if (gameState == Game.GAME_EXIT) {
			Game.exitGame();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics render = renderer.getGraphics();

		render.setColor(Color.BLACK);
		render.fillRect(0, 0, WIDTH, HEIGHT);

		if (gameState == Game.GAME_RUN) {
			scenario.render(render);
		}

		render.dispose();

		Graphics graphics = bs.getDrawGraphics();
		graphics.drawImage(renderer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

		switch (gameState) {
			case Game.GAME_MENU:
				mainMenu.render(graphics);
				break;
			case Game.GAME_TUTORIAL:
				tutorial.render(graphics);
				break;
			case Game.GAME_CREDITS:
				credits.render(graphics);
				break;
			case Game.GAME_GAME_OVER:
				gameOver.render(graphics);
				break;
			case Game.GAME_FINAL_SCREEN:
				finalScreen.render(graphics);
				break;
		}

		if (showFPS) {
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("arial", Font.BOLD, 20));
			graphics.drawString("FPS: " + fps, (Game.WIDTH * Game.SCALE) - 100, 32);
		}

		bs.show();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (gameState == Game.GAME_RUN) {
			scenario.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (gameState == Game.GAME_RUN) {
			scenario.keyReleased(e);
		} else if (gameState == Game.GAME_MENU) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				mainMenu.menuUp();
			}

			if (e.getKeyCode() == KeyEvent.VK_S) {
				mainMenu.menuDown();
			}

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				mainMenu.menuEnter();
			}
		} else if (gameState == Game.GAME_TUTORIAL || gameState == Game.GAME_CREDITS || gameState == Game.GAME_GAME_OVER || gameState == Game.GAME_FINAL_SCREEN) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				this.updateGameState(Game.GAME_MENU);
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_F3) {
			showFPS = !showFPS;
		}

		if (e.getKeyCode() == KeyEvent.VK_F4) {
			enableSound = !enableSound;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Code
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0.0;

		int frames = 0;
		double timer = System.currentTimeMillis();

		this.requestFocus();

		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				this.tick();
				this.render();
				delta--;

				frames++;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				fps = frames;
				frames = 0;
				timer = System.currentTimeMillis();
			}
		}

		this.stop();
	}

	public static void main(String[] args) {
		try {
			new Game().start();
		} catch (Exception e) {
			Game.exitWithError("An error has occurred. The program will be terminated.");
		}
	}

	public static void exitGame() {
		System.exit(0);
	}

	public static void exitWithError(String error) {
		JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
		Game.exitGame();
	}

}
