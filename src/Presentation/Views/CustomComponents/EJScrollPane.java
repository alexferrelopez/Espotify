package Presentation.Views.CustomComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Aquesta custom class ens permet modificar el scroll pane
 **/
public class EJScrollPane extends JScrollPane {
    /**
     * Consequents modificacions del Scro
     *
     * @param c      Component
     * @param top    A dalt
     * @param left   Esquerra
     * @param bottom A baix
     * @param right  Dreta.
     */
    public EJScrollPane(Component c, int top, int left, int bottom, int right) {
        super(c);
        getViewport().setBackground(new Color(20, 20, 20));
        setBackground(new Color(20, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));

        JScrollBar scrollBarVertical = this.getVerticalScrollBar();
        JScrollBar scrollBarHorizontal = this.getHorizontalScrollBar();
        scrollBarVertical.setUI(new CustomScrollBarUI());
        scrollBarHorizontal.setUI(new CustomScrollBarUI());
    }
}
