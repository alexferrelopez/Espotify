package Presentation.Views.CustomComponents;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Aquesta custom class ens serveix per arrodonir cantonades
 */
class RoundedCornerBorder extends AbstractBorder {
    private static final Color ALPHA_ZERO = new Color(0x0, true);

    /**
     * Pinta els bordes
     *
     * @param c      Component
     * @param g      Grafics
     * @param x      Component X
     * @param y      Component Y
     * @param width  Ample
     * @param height Alt.
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape border = getBorderShape(x, y, width - 1, height - 1);
        g2.setPaint(ALPHA_ZERO);
        Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
        corner.subtract(new Area(border));
        g2.fill(corner);
        g2.setPaint(Color.GRAY);
        g2.draw(border);
        g2.dispose();
    }

    /**
     * Forma dels bordes
     *
     * @param x Posicio X
     * @param y Posicio Y.
     * @param w Amplada.
     * @param h Altura
     * @return Retorna la forma
     */
    public Shape getBorderShape(int x, int y, int w, int h) {
        return new RoundRectangle2D.Double(x, y, w, h, h, h);
    }

    /**
     * Insets del bordes
     */
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(4, 8, 4, 8);
    }

    /**
     * Recuperar els insets de els botdes.
     *
     * @param c
     * @param insets
     * @return
     */
    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.set(4, 8, 4, 8);
        return insets;
    }
}