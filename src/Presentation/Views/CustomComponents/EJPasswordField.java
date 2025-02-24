package Presentation.Views.CustomComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que em permet modificar el camp de text de password
 */
public class EJPasswordField extends JPasswordField {
    /**
     * Constructor del password field
     *
     * @param columns Li passem les columnes
     * @param bottom  Li passem el bottom
     */
    public EJPasswordField(int columns, int bottom) {
        super(columns);
        passwordField(bottom);

    }

    /**
     * Constructor 2 del password field
     *
     * @param text    Text que volem posar
     * @param columns Columnes.
     * @param bottom  Part de sota.
     */
    public EJPasswordField(String text, int columns, int bottom) {
        super(text, columns);
        passwordField(bottom);

    }

    /**
     * Camp de password en si.
     *
     * @param bottom Li passem el bottom
     */
    private void passwordField(int bottom) {
        setFont(new Font("Apple Casual", Font.BOLD, 12));
        setMaximumSize(new Dimension(300, 40));
        setForeground(new Color(175, 175, 175));
        setBackground(Color.darkGray);
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(8, 25, bottom, 0));
    }
}
