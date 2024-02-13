package gfight.view.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gfight.engine.api.Engine;
import gfight.engine.api.Engine.EngineStatus;

public class GameOver extends JPanel {
    private static final int TITLE_SIZE = 100;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    public GameOver(final Engine engine, final String text) {
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("GAME OVER!!");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(TITLE_SIZE / 4, 0, TITLE_SIZE, 0));
        title.setFont(new Font("Georgia", Font.BOLD, TITLE_SIZE));
        title.setForeground(Color.RED);
        this.setBackground(Color.WHITE);
        JPanel buttonContainer = new JPanel();
        buttonContainer.setBackground(Color.WHITE);
        JButton button = new JButton("Back to menu...");
        button.setFont(new Font("Arial", Font.BOLD, TITLE_SIZE / 4));
        button.setForeground(Color.BLUE);
        button.setSize(new Dimension(WIDTH / 5, HEIGHT / 5));
        button.addActionListener(al -> engine.changeStatus(EngineStatus.MENU));
        buttonContainer.add(button);
        add(title, BorderLayout.NORTH);
        add(buttonContainer, BorderLayout.CENTER);
    }
}
