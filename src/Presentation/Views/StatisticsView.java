package Presentation.Views;

import Presentation.Try;
import Presentation.Url;
import Presentation.Views.CustomComponents.EJButton;
import Presentation.Views.CustomComponents.Histogram;
import Presentation.Views.CustomComponents.LogoAndTextView;
import Presentation.Views.CustomComponents.NoScalingIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Map;

/**
 * Classe de la vista de les estadistiques
 */
public class StatisticsView extends JPanel {
    private JButton gestionCuenta;

    private Histogram histogram;

    /**
     * El constructor inicialitza la vista
     */
    public StatisticsView() {
        runEstadisticas();
    }

    /**
     * Funcio que inicialitza la vista
     */
    private void runEstadisticas() {
        setLayout(new BorderLayout());
        centralView();
    }

    /**
     * Funcio corresponent a la zona del mig de la vista.
     */
    private void centralView() {
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(new Color(20, 20, 20));

        JPanel centro = new JPanel(new GridLayout(1, 3));
        centro.setOpaque(false);

        JPanel norte = new JPanel(new GridLayout(1, 2));
        norte.setOpaque(false);
        norte.setPreferredSize(new Dimension(0, 200));

        JPanel gestionYbusqueda = new JPanel();
        gestionYbusqueda.setLayout(new BorderLayout());
        gestionYbusqueda.setOpaque(false);


        gestionCuenta = new EJButton("Gestión de cuenta", new NoScalingIcon(new ImageIcon(Url.urlBotonGenerico)), true, false, true);
        gestionCuenta.setActionCommand(Try.tryGestionCuenta);

        gestionYbusqueda.add(gestionCuenta, BorderLayout.NORTH);


        norte.add(new LogoAndTextView("El género más escuchado...", 1));
        norte.add(gestionYbusqueda);
        JPanel centralViewCenter = centralViewCenter();

        centro.add(centralViewCenter);

        panelCentral.add(norte, BorderLayout.NORTH);
        panelCentral.add(centro, BorderLayout.CENTER);


        add(panelCentral, BorderLayout.CENTER);
    }

    /**
     * Centre del central view
     *
     * @return retorna el seu jpanel
     */
    private JPanel centralViewCenter() {
        histogram = new Histogram();
        return histogram;
    }

    /**
     * Actualitzar les estadistiques
     *
     * @param listGender Mapa de la grafica
     */
    public void updateStatistics(Map<String, Integer> listGender) {
        histogram.updateStatistics(listGender);
    }

    /**
     * Listener de les estadistiques que anira a registrarse al controller
     *
     * @param listener Retorna el listener.
     */
    public void statisticsListener(EventListener listener) {
        gestionCuenta.addActionListener((ActionListener) listener);
    }

}

