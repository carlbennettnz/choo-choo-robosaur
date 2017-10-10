package ui;

import game.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import canvas.Canvas;

public class UI extends JFrame {
	private GameController controller;
	private JPanel cards;

	public UI() {
		super("Choo Choo Robosaur");

		cards = new JPanel(new CardLayout());

		setPreferredSize(new Dimension(1280, 720));
		add(cards, BorderLayout.CENTER);

		GameController gc = new GameController(1280, 720);
		gc.bindKeyListeners(this);

		cards.add(new MainMenuView(this), Route.MAIN_MENU.toString());
		cards.add(new Canvas(gc), Route.GAME.toString());

		goToRoute(Route.GAME);

		pack();
		setVisible(true);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void goToRoute(Route route) {
		((CardLayout) cards.getLayout()).show(cards, route.toString());
	}

	public void start() {
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
