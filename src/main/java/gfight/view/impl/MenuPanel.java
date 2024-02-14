package gfight.view.impl;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gfight.App;
import gfight.engine.api.Engine;
import gfight.engine.api.Engine.EngineStatus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class that represent the game menu.
 */
public class MenuPanel extends JPanel {

    /** Serializable UID. */
    private static final long serialVersionUID = -9101397258771046262L;

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int BUTTON_IMAGE_WIDTH = 100;
    private static final int BUTTON_IMAGE_HEIGHT = 75;
    private static final int DISTANCE = 50;
    private static final int TITLE_SIZE = 75;
    private static final int TEXT_SIZE = TITLE_SIZE / 4;
    private static final int SCALE_BUTTON = 6;
    private static final int RADIUS = 10;
    private static final String PATH_STRING = "src/main/resources/images/";
    private final ImageIcon background;
    private final JMenuBar menuBar = new JMenuBar();

    /**
     * Creates a new menu view.
     * 
     * @param engine the game engine
     */
    public MenuPanel(final Engine engine) {
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new BorderLayout());
        // BACKGROUND IMAGE
        background = new ImageIcon(PATH_STRING + "Background.png");
        // BUTTON IMAGES
        final ImageIcon playImage = new ImageIcon(PATH_STRING + "play.png");
        final ImageIcon statsImage = new ImageIcon(PATH_STRING + "stats.jpg");
        final ImageIcon resizedPlayImage = resizeImage(BUTTON_IMAGE_WIDTH, BUTTON_IMAGE_HEIGHT, playImage);
        final ImageIcon resizedStatsImage = resizeImage(BUTTON_IMAGE_WIDTH, BUTTON_IMAGE_HEIGHT, statsImage);

        // MAIN PANEL
        final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, DISTANCE, 0, 0));

        // TITLE
        final JLabel firstLabel = new JLabel("Geometry");
        final JLabel secondLabel = new JLabel("Fight");
        firstLabel.setFont(new Font("Helvetica", Font.BOLD, TITLE_SIZE));
        secondLabel.setFont(new Font("Helvetica", Font.BOLD, TITLE_SIZE));
        firstLabel.setAlignmentX(CENTER_ALIGNMENT);
        secondLabel.setAlignmentX(CENTER_ALIGNMENT);

        leftPanel.setOpaque(false);
        // BUTTONS
        final JButton playButton = new JButton("PLAY  ", resizedPlayImage);
        configurateButton(playButton);
        playButton.addActionListener(al -> engine.changeStatus(EngineStatus.GAME));
        playButton.setAlignmentX(CENTER_ALIGNMENT);

        final JButton statsButton = new JButton("STATS", resizedStatsImage);
        configurateButton(statsButton);
        statsButton.setAlignmentX(CENTER_ALIGNMENT);
        statsButton.addActionListener(e -> {
            try {
                JOptionPane.showMessageDialog(MenuPanel.this, Files.readString(Path.of(App.GAME_FOLDER + "stats.txt")),
                        "Stats", JOptionPane.PLAIN_MESSAGE);
            } catch (final IOException ex) {
                JOptionPane.showMessageDialog(MenuPanel.this, "No stats yet", "Stats", JOptionPane.PLAIN_MESSAGE);
            }
        });

        // MEUNU BAR
        final JMenu mapMenu = new JMenu("     Select a map     ");
        mapMenu.setBorder(new RoundedBorder(RADIUS));
        mapMenu.setBackground(Color.WHITE);
        mapMenu.setFont(new Font("Arial", Font.BOLD, TEXT_SIZE));
        final JMenuItem map1 = new JMenuItem("Map 1");
        final JMenuItem map2 = new JMenuItem("Map 2");
        final JMenuItem map3 = new JMenuItem("Map 3");
        map1.addActionListener(e -> {
            engine.selectLevel("map1");
            mapMenu.setText("     Map 1    ");
        });
        map2.addActionListener(e -> {
            engine.selectLevel("map2");
            mapMenu.setText("    Map 2    ");
        });
        map3.addActionListener(e -> {
            engine.selectLevel("map3");
            mapMenu.setText("    Map 3    ");
        });
        mapMenu.add(map1);
        mapMenu.add(map2);
        mapMenu.add(map3);
        menuBar.add(mapMenu);
        menuBar.setAlignmentX(CENTER_ALIGNMENT);

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(firstLabel);
        leftPanel.add(secondLabel);
        leftPanel.add(Box.createVerticalStrut(DISTANCE));
        leftPanel.add(playButton);
        leftPanel.add(statsButton);
        leftPanel.add(menuBar);
        leftPanel.add(Box.createVerticalGlue());

        add(leftPanel, BorderLayout.WEST);
    }

    private void configurateButton(final JButton button) {
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(WIDTH / SCALE_BUTTON, HEIGHT / SCALE_BUTTON));
        button.setHorizontalTextPosition(JButton.LEFT);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setBackground(Color.WHITE);
        button.setBorder(new RoundedBorder(RADIUS));
        button.setFont(new Font("Arial", Font.BOLD, TEXT_SIZE));
    }

    private ImageIcon resizeImage(final int width, final int height, final ImageIcon icon) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    @Override
    protected final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

}
