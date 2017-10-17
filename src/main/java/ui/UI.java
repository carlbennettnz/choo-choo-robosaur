package ui;

import common.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import canvas.Canvas;
import game.Game;

public class UI extends JFrame {
	private Game game;
	private GameController controller;
	private JPanel cards;
	private Canvas canvas;

	public UI(Game game) {
		super("Choo Choo Robosaur");

		this.game = game;

		cards = new JPanel(new CardLayout());

		setPreferredSize(new Dimension(1280, 720));
		add(cards, BorderLayout.CENTER);

		cards.add(new MainMenuView(this), Route.MAIN_MENU.toString());

		canvas = new Canvas();
		cards.add(canvas, Route.GAME.toString());

		pack();
		setVisible(true);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void goToRoute(Route route) {
		((CardLayout) cards.getLayout()).show(cards, route.toString());
	}

	public void start() {
		canvas.setController(game.loadLevel(this, 1));
		goToRoute(Route.GAME);
	}

	public void pause() {
		goToRoute(Route.PAUSE);
	}

	public void quit() {
		dispose();
	}

	public enum Route {
		MAIN_MENU,
		GAME,
		PAUSE
	}
}
