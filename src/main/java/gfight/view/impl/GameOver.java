package gfight.view.impl;

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

/**
 * Class that represent the GameOver panel
 */
public class GameOver extends JPanel {
    private static final int TITLE_SIZE = 100;

    public GameOver(final Engine engine) {
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
        button.addActionListener(al -> engine.changeStatus(EngineStatus.MENU));
        buttonContainer.add(button);
        add(title, BorderLayout.NORTH);
        add(buttonContainer, BorderLayout.CENTER);
    }
}
