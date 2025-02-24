package Presentation.Views.CustomComponents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Carrega una imatge al fons.
 */
public class JImagePanel extends JPanel {
    private BufferedImage image;

    /**
     * Constructor
     *
     * @param path lloc
     */
    public JImagePanel(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Size determinat
     *
     * @return Dimensio.
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension preferred = super.getPreferredSize();

        float width = image.getWidth();
        float height = image.getHeight();

        preferred.height = Math.round(getWidth() * height / width);

        return preferred;
    }

    /**
     * pintar el component
     *
     * @param g Grafics.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
