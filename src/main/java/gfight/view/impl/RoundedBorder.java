package gfight.view.impl;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

/**
 * Utility class for rounder border.
 */
public final class RoundedBorder implements Border {

    private final int radius;

    /**
     * Creates rounder border.
     * @param radius 
     */
    RoundedBorder(final int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width,
            final int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(final Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

}
