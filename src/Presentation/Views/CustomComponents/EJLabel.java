package Presentation.Views.CustomComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Es tracta de una classe customitzada per els JLabels.
 */
public class EJLabel extends JLabel {
    /**
     * Constructor de el component customitzat
     *
     * @param label      Nom del component
     * @param sizeFont   Tamany de la lletra.
     * @param colorR     Color Vermell.
     * @param colorG     Color Verd.
     * @param colorB     Color Blau.
     * @param leftBorder Marge esquerra.
     * @param border     Borde.
     */
    public EJLabel(String label, int sizeFont, int colorR, int colorG, int colorB, int leftBorder, boolean border) {
        super(label);
        setFont(new Font("Apple Casual", Font.BOLD, sizeFont));
        setForeground(new Color(colorR, colorG, colorB));
        if (border) setBorder(BorderFactory.createEmptyBorder(0, leftBorder, 0, 0));
    }
}
