package Presentation.Views.CustomComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Aquesta custom class ems permet modificar les arees de text
 */
public class EJTextArea extends JTextArea {
    /**
     * Constructor de la classe que seteja com han de ser les Jtext Areas.
     *
     * @param rows    Files
     * @param columns Columnes
     */
    public EJTextArea(int rows, int columns) {
        super(rows, columns);
        setBackground(new Color(20, 20, 20));
        setForeground(Color.white);
        setLineWrap(true);
        setWrapStyleWord(true);
        setFont(new Font("Apple Casual", Font.BOLD, 20));
        setEditable(false);
    }
}
