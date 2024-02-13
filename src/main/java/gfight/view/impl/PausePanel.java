package gfight.view.impl;

import gfight.engine.api.Engine;
import gfight.engine.api.Engine.EngineStatus;

import java.awt.Image;
import java.awt.Font;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Box;

public class PausePanel extends JPanel {

    private final static String PATH_STRING = "src/main/resources/images/";
    private final Image backgroundImage;

    public PausePanel(final Engine engine) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        backgroundImage = new ImageIcon(PATH_STRING + "Pause.png").getImage();

        final JLabel titleLabel = new JLabel("Paused");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 55));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JButton continueButton = new JButton("  Continue   ");
        continueButton.setFont(new Font("Arial", Font.ITALIC, 22));
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.addActionListener(e -> engine.changeStatus(EngineStatus.GAME));

        final JButton goToMenuButton = new JButton("Go to Menu");
        goToMenuButton.setFont(new Font("Arial", Font.ITALIC, 22));
        goToMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        goToMenuButton.addActionListener(e -> engine.changeStatus(EngineStatus.MENU));

        this.add(Box.createVerticalGlue());
        this.add(titleLabel);
        this.add(Box.createVerticalStrut(5));
        this.add(continueButton);
        this.add(Box.createVerticalStrut(5));
        this.add(goToMenuButton);
        this.add(Box.createVerticalGlue());

    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
