package Presentation.Views.CustomComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Llista customitzada.
 */
public class EJList extends JList<String> {
    /**
     * Constructor de la JList
     *
     * @param listasDisponibles Llistes disponibles que té.
     */
    public EJList(DefaultListModel<String> listasDisponibles) {
        super(listasDisponibles);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setLayoutOrientation(JList.VERTICAL);
        setVisibleRowCount(5);
        setBackground(new Color(20, 20, 20));
        setForeground(Color.white);
        setSelectionBackground(new Color(252, 194, 208));
        setBorder(BorderFactory.createEmptyBorder(0, 52, 0, 0));
    }
}
