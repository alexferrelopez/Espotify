package Presentation.Views.CustomComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Modificacio per la combo BOx
 */
public class EJComboBox extends JComboBox<String> {
    /**
     * Constructor de la classe
     *
     * @param items Conte els items de la combo box.
     */
    public EJComboBox(String[] items) {
        super(items);
        setSelectedIndex(0);
        setForeground(Color.BLACK);
        setBackground(Color.WHITE);
    }

}


