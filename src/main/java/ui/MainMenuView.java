package ui;

import javax.swing.*;
import java.awt.*;

class MainMenuView extends JPanel {
	private final UI ui;

	MainMenuView(UI ui) {
		this.ui = ui;

		setLayout(new BorderLayout());

		JButton startBtn = new JButton("Start");

		startBtn.addActionListener(event -> {
			System.out.println("click");
			ui.start();
		});

		add(startBtn, BorderLayout.CENTER);
	}
}
