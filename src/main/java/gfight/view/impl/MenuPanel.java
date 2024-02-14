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
import javax.swing.JPanel;

import gfight.engine.api.Engine;
import gfight.engine.api.Engine.EngineStatus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

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
    private static final int TITLE_SIZE = 75;
    private static final String PATH_STRING = "src/main/resources/images/";
    private final ImageIcon background;
    private final ImageIcon playImage;
    private final ImageIcon statsImage;
    private final JMenuBar menuBar = new JMenuBar();

    public MenuPanel(final Engine engine) {
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new BorderLayout());
        // BACKGROUND IMAGE
        background = new ImageIcon(PATH_STRING + "Background.png");
        // BUTTON IMAGES
        playImage = new ImageIcon(PATH_STRING + "play.png");
        statsImage = new ImageIcon(PATH_STRING + "stats.jpg");
        ImageIcon resizedPlayImage = resizeImage(BUTTON_IMAGE_WIDTH, BUTTON_IMAGE_HEIGHT, playImage);
        ImageIcon resizedStatsImage = resizeImage(BUTTON_IMAGE_WIDTH, BUTTON_IMAGE_HEIGHT, statsImage);

        // MAIN PANEL
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

        // TITLE
        JLabel firstLabel = new JLabel("Geometry");
        JLabel secondLabel = new JLabel("Fight");
        firstLabel.setFont(new Font("Helvetica", Font.BOLD, TITLE_SIZE));
        secondLabel.setFont(new Font("Helvetica", Font.BOLD, TITLE_SIZE));
        firstLabel.setAlignmentX(CENTER_ALIGNMENT);
        secondLabel.setAlignmentX(CENTER_ALIGNMENT);

        leftPanel.setOpaque(false);
        // BUTTONS
        JButton playButton = new JButton("PLAY  ", resizedPlayImage);
        configurateButton(playButton);
        playButton.addActionListener(al -> engine.changeStatus(EngineStatus.GAME));
        playButton.setAlignmentX(CENTER_ALIGNMENT);

        JButton statsButton = new JButton("STATS", resizedStatsImage);
        configurateButton(statsButton);
        statsButton.setAlignmentX(CENTER_ALIGNMENT);

        // MEUNU BAR
        JMenu mapMenu = new JMenu("     Select a map     ");
        mapMenu.setBorder(BorderFactory.createEtchedBorder());
        mapMenu.setFont(new Font("Arial", Font.BOLD, TITLE_SIZE / 4));
        JMenuItem map1 = new JMenuItem("Map 1");
        JMenuItem map2 = new JMenuItem("Map 2");
        JMenuItem map3 = new JMenuItem("Map 3");
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
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(playButton);
        leftPanel.add(statsButton);
        leftPanel.add(menuBar);
        leftPanel.add(Box.createVerticalGlue());

        add(leftPanel, BorderLayout.WEST);
    }

    private void configurateButton(final JButton button) {
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(WIDTH / 6, HEIGHT / 6));
        button.setHorizontalTextPosition(JButton.LEFT);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setFont(new Font("Arial", Font.BOLD, TITLE_SIZE / 4));
    }

    private ImageIcon resizeImage(final int width, final int height, final ImageIcon icon) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

}
