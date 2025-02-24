package Presentation.Views.CustomComponents;


import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Es tracta de una classe que ens permet tenir una custom scroll bar
 */
class CustomScrollBarUI extends BasicScrollBarUI {
    private static final int THUMB_ARC = 20;
    private final RoundRectangle2D.Float thumbShape = new RoundRectangle2D.Float();

    /**
     * Constructor corresponent.
     */
    public CustomScrollBarUI() {
        super();
    }

    /**
     * Crear un boto de zero
     *
     * @return Retorna el boto
     */
    private JButton createZeroButton() {
        JButton button = new JButton();
        Dimension zeroDim = new Dimension(0, 0);
        button.setPreferredSize(zeroDim);
        button.setMinimumSize(zeroDim);
        button.setMaximumSize(zeroDim);
        return button;
    }

    /**
     * Crea un boto per decrementar
     *
     * @param orientation Orientacio
     * @return Retorna el boto
     */
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    /**
     * Crea un boto per incrementar
     *
     * @param orientation Orientacio
     * @return Retorna el boto
     */
    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    /**
     * Serveix per pintar el track
     *
     * @param g           Grafics
     * @param c           Component
     * @param trackBounds Rectangle
     */
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(20, 20, 20));
        g2d.fill(trackBounds);
        g2d.draw(trackBounds);
    }

    /**
     * Ens pinta els thumbs
     *
     * @param g           Grafics
     * @param c           Component
     * @param thumbBounds Rectangle
     */
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(252, 194, 208));

        thumbShape.setRoundRect(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height, THUMB_ARC, THUMB_ARC);

        g2d.fill(thumbShape);
        g2d.draw(thumbShape);
    }

    /**
     * Aconsegueix la preferred size
     *
     * @param c Component
     * @return Retorna la dimensio.
     */
    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(10, super.getPreferredSize(c).height);
    }

    /**
     * Customitza un component
     *
     * @param g Quins grafics
     * @param c El component
     */
    @Override
    public void paint(Graphics g, JComponent c) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(g, c);
    }
}
