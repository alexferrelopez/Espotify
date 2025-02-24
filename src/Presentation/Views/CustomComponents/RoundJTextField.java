package Presentation.Views.CustomComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Aquesta custom class ens serveix per arrodonir els JTextField
 */
public class RoundJTextField extends JTextField {
    /**
     * Crida l'objectiu
     *
     * @param columns Columnes
     */
    public RoundJTextField(int columns) {
        super(columns);
    }

    /**
     * Posar color als components
     *
     * @param g Components.
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setPaint(getBackground());
            g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                    0, 0, getWidth() - 1, getHeight() - 1));
            g2.dispose();

        }
        super.paintComponent(g);
    }

    /**
     * Actualitzar la vista grafica.
     */
    @Override
    public void updateUI() {
        super.updateUI();
        setOpaque(false);
        setBackground(Color.white);
        setFont(new Font("Apple Casual", Font.BOLD, 12));
        setMaximumSize(new Dimension(80, 40));
        setBorder(new RoundedCornerBorder());
    }
}