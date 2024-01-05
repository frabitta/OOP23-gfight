package oop23.gfight.view;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Canvas extends JPanel{

    private final int centerX;
	private final int centerY;

    Canvas(int width, int height) {
        setSize(width,height);

        this.centerX = width/2;
		this.centerY = height/2;
		//this.addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		requestFocusInWindow(); 
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2.clearRect(0,0,this.getWidth(),this.getHeight());

        g2.setColor(Color.BLUE);
		g2.setStroke(new BasicStroke(4f));
        g2.drawOval(centerX-20, centerY-20, 40, 40);
    }
}
