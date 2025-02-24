package Presentation.Views.CustomComponents;


import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Classe customitzada per slider.
 */
public class CustomSliderUI extends BasicSliderUI {
    private static final int TRACK_HEIGHT = 5;
    private static final int TRACK_WIDTH = 5;
    private static final int TRACK_ARC = 6;
    private static final Dimension THUMB_SIZE = new Dimension(13, 13);
    private final RoundRectangle2D.Float trackShape = new RoundRectangle2D.Float();

    /**
     * Constructor
     *
     * @param jSlider Slider de canço.
     */
    public CustomSliderUI(JSlider jSlider) {
        super(jSlider);
    }

    /**
     * Calcula el track rect.
     */
    @Override
    protected void calculateTrackRect() {
        super.calculateTrackRect();
        if (isHorizontal()) {
            trackRect.y = trackRect.y + (trackRect.height - TRACK_HEIGHT) / 2;
            trackRect.height = TRACK_HEIGHT;
        } else {
            trackRect.x = trackRect.x + (trackRect.width - TRACK_WIDTH) / 2;
            trackRect.width = TRACK_WIDTH;
        }
        trackShape.setRoundRect(trackRect.x, trackRect.y, trackRect.width, trackRect.height, TRACK_ARC, TRACK_ARC);
    }

    /**
     * Calcula el thumb location
     */
    @Override
    protected void calculateThumbLocation() {
        super.calculateThumbLocation();
        if (isHorizontal()) {
            thumbRect.y = trackRect.y + (trackRect.height - thumbRect.height) / 2;
        } else {
            thumbRect.x = trackRect.x + (trackRect.width - thumbRect.width) / 2;
        }
    }

    /**
     * Agafa les mesures del thumb
     *
     * @return Retorna la dimensio
     */
    @Override
    protected Dimension getThumbSize() {
        return THUMB_SIZE;
    }

    /**
     * Funcio que determina si es horitzontal.
     *
     * @return Torna si ho es.
     */

    private boolean isHorizontal() {
        return slider.getOrientation() == JSlider.HORIZONTAL;
    }

    /**
     * Pinta el component
     *
     * @param g Grafics
     * @param c Component.
     */
    @Override
    public void paint(Graphics g, JComponent c) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(g, c);
    }

    /**
     * Paint track
     *
     * @param g Grafics.
     */
    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Shape clip = g2.getClip();

        boolean horizontal = isHorizontal();
        boolean inverted = slider.getInverted();

        // Paint shadow.
        g2.setColor(Color.gray);
        g2.fill(trackShape);

        // Paint track background.
        g2.setColor(Color.gray);
        g2.setClip(trackShape);
        trackShape.y += 1;
        g2.fill(trackShape);
        trackShape.y = trackRect.y;

        g2.setClip(clip);

        // Paint selected track.
        if (horizontal) {
            boolean ltr = slider.getComponentOrientation().isLeftToRight();
            if (ltr) inverted = !inverted;
            int thumbPos = thumbRect.x + thumbRect.width / 2;
            if (inverted) {
                g2.clipRect(0, 0, thumbPos, slider.getHeight());
            } else {
                g2.clipRect(thumbPos, 0, slider.getWidth() - thumbPos, slider.getHeight());
            }

        } else {
            int thumbPos = thumbRect.y + thumbRect.height / 2;
            if (inverted) {
                g2.clipRect(0, 0, slider.getHeight(), thumbPos);
            } else {
                g2.clipRect(0, thumbPos, slider.getWidth(), slider.getHeight() - thumbPos);
            }
        }
        g2.setColor(new Color(252, 194, 208));
        g2.fill(trackShape);
        g2.setClip(clip);
    }

    /**
     * Paint thumb
     *
     * @param g Grafics
     */
    @Override
    public void paintThumb(Graphics g) {
        g.setColor(new Color(255, 255, 255));
        g.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
    }

    /**
     * No la utilitzem pero es de herencia
     *
     * @param g
     */
    @Override
    public void paintFocus(Graphics g) {
    }
}
