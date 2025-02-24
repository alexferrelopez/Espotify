package Presentation.Views.CustomComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Aquesta custom class ens permet modificar el JSlider
 */
public class EJSlider extends JSlider {
    /**
     * Constructor determinat per la slider
     *
     * @param i  Propietats de Slider
     * @param i1 Propietats de Slider 2
     */
    public EJSlider(int i, int i1) {
        super(i, i1);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Point p = e.getPoint();
                double percent = p.x / ((double) getWidth());
                int range = getMaximum() - getMinimum();
                double newVal = range * percent;
                int result = (int) (getMinimum() + newVal);
                setValue(result);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Point p = e.getPoint();
                double percent = p.x / ((double) getWidth());
                int range = getMaximum() - getMinimum();
                double newVal = range * percent;
                int result = (int) (getMinimum() + newVal);
                setValue(result);
            }
        });
    }
}