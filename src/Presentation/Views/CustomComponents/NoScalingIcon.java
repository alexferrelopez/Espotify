package Presentation.Views.CustomComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Autoescalar els icones
 */
public class NoScalingIcon implements Icon {
    private final Icon icon;

    /**
     * Propietats
     *
     * @param icon Icone
     */
    public NoScalingIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * Amplada del Icone
     *
     * @return amplada
     */
    public int getIconWidth() {
        return icon.getIconWidth();
    }

    /**
     * Altura de licone
     *
     * @return altura
     */
    public int getIconHeight() {
        return icon.getIconHeight();
    }

    /**
     * Pintar l'icone
     *
     * @param c Component
     * @param g Grafics.
     * @param x Coordenada X.
     * @param y Coordenada Y.
     */
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

        AffineTransform at = g2d.getTransform();

        int scaleX = (int) (x * at.getScaleX());
        int scaleY = (int) (y * at.getScaleY());

        int offsetX = (int) (icon.getIconWidth() * (at.getScaleX() - 1) / 2);
        int offsetY = (int) (icon.getIconHeight() * (at.getScaleY() - 1) / 2);

        int locationX = scaleX + offsetX;
        int locationY = scaleY + offsetY;


        AffineTransform scaled = AffineTransform.getScaleInstance(1.0 / at.getScaleX(), 1.0 / at.getScaleY());
        at.concatenate(scaled);
        g2d.setTransform(at);

        icon.paintIcon(c, g2d, locationX, locationY);

        g2d.dispose();
    }


}
