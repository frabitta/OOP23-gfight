package gfight.view.impl;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gfight.engine.api.Engine;
import gfight.engine.api.Engine.EngineStatus;

/**
 * Class that represents the GameOver panel.
 */
public class GameOver extends JPanel {
    private static final long serialVersionUID = 4003161718422635256L;

    private static final String PATH_STRING = "src/main/resources/images/";
    private static final int TITLE_FONT_DIM = 70;
    private static final int BUTTON_FONT_DIM = 22;
    private static final int TILE_SPACING = 5;
    private static final int RADIUS = 10;

    private final Image backgroundImage;

    /**
     * Creates a GameOver view.
     * 
     * @param engine the game engine
     */
    public GameOver(final Engine engine) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        backgroundImage = new ImageIcon(PATH_STRING + "game_over2.png").getImage();

        final JLabel titleLabel = new JLabel("GAME OVER!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_DIM));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JButton backToMenu = new JButton("Go to Menu");
        backToMenu.setFont(new Font("Arial", Font.ITALIC, BUTTON_FONT_DIM));
        backToMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        backToMenu.setBorder(new RoundedBorder(RADIUS));
        backToMenu.addActionListener(e -> engine.changeStatus(EngineStatus.MENU));

        this.add(Box.createVerticalGlue());
        this.add(titleLabel);
        this.add(Box.createVerticalStrut(TILE_SPACING));
        this.add(backToMenu);
        this.add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
