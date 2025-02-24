package Presentation.Views.CustomComponents;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Aquesta custom class Ens permet veure l'histograma de les estadístiques.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class Histogram extends JPanel {

    private final JPanel statisticsPanel;
    private final JPanel labelPanel;

    private final List<Statistics> statistics = new ArrayList<>();

    /**
     * crear el panel
     */
    public Histogram() {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());
        setBackground(new Color(20, 20, 20));

        int statisticsGap = 100;
        statisticsPanel = new JPanel(new GridLayout(0, 1, statisticsGap, 0));
        labelPanel = new JPanel(new GridLayout(0, 1, statisticsGap, 0));

        Border inner = new EmptyBorder(10, 10, 0, 10);
        statisticsPanel.setBorder(inner);
        statisticsPanel.setBackground(new Color(20, 20, 20));

        labelPanel.setBorder(new EmptyBorder(15, 10, 0, 10));
        labelPanel.setBackground(new Color(20, 20, 20));

        add(statisticsPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.WEST);
    }

    /**
     * Datos a mostrar
     */
    public void updateStatistics(Map<String, Integer> listGender) {
        statistics.clear();
        int contador = 0;
        for (String key : listGender.keySet()) {
            if (contador < 10) {
                addHistogramColumn(key, listGender.get(key), new Color(252, 194, 208));
            }
            contador++;
        }
        layoutHistogram();
    }

    /**
     * Datos a poner en la barra.
     *
     * @param label Nombre a mostar.
     * @param value El numero, por ejemplo suma de cada genero.
     * @param color El color a mostrar.
     */
    private void addHistogramColumn(String label, int value, Color color) {
        Statistics bar = new Statistics(label, value, color);
        statistics.add(bar);
    }

    /**
     * Los layouts a mostrar dentro del panel.
     */
    private void layoutHistogram() {
        statisticsPanel.removeAll();
        labelPanel.removeAll();

        // Valor más alto de la estadística
        int maxValue = 0;
        for (Statistics stat : statistics) {
            maxValue = Math.max(maxValue, stat.value());
        }

        for (Statistics stat : statistics) {
            JLabel label = new JLabel(stat.value() + "");
            label.setHorizontalTextPosition(JLabel.RIGHT); //establecer la posición del texto horizontal
            label.setHorizontalAlignment(JLabel.LEFT); //establecer la alineación horizontal
            label.setVerticalTextPosition(JLabel.CENTER); //establecer la posición vertical del texto
            label.setVerticalAlignment(JLabel.CENTER); //establecer alineación vertical
            label.setForeground(Color.white);

            int histogramWidth = 800;
            int statisticWidth = ((stat.value() * histogramWidth) / maxValue) + 2;
            int histogramHeight = 90;
            int statisticHeight = histogramHeight / statistics.size();
            Icon icon = new ColorIcon(stat.color(), statisticWidth, statisticHeight);

            label.setIcon(icon);
            statisticsPanel.add(label);

            JLabel statLabel = new JLabel(stat.day());
            statLabel.setForeground(Color.white);
            statLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add(statLabel);
        }
    }

    /**
     * Crear una record Statistics para poder gestionar el contendo de la barra.
     */
    private record Statistics(String day, int value, Color color) {
    }

    /**
     * Crear record ColorIcon para poder controlar los el color y el tamaño de la barra.
     */
    private record ColorIcon(Color color, int width, int height) implements Icon {
        public int getIconWidth() {
            return width;
        }

        public int getIconHeight() {
            return height;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }
}
