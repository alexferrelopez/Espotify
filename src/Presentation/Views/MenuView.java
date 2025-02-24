package Presentation.Views;

import Presentation.Try;
import Presentation.Url;
import Presentation.Views.CustomComponents.EJButton;
import Presentation.Views.CustomComponents.NoScalingIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 * Vista per el menu
 */
public class MenuView extends JPanel {

    private static final int heightScreen = Toolkit.getDefaultToolkit().getScreenSize().height;       //Alto pantalla
    private JButton inicio;
    private JButton estadisticas;
    private JMenuItem misListas;
    private JMenuItem listasDeOtrosUsuarios;

    /**
     * Inicialitza la vista
     */
    public MenuView() {
        runMenu();
    }

    /**
     * Comença el menu.
     */
    private void runMenu() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(245, heightScreen));

        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setOpaque(false);


        JPanel inicioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inicioPanel.setOpaque(false);
        inicioPanel.setBorder(BorderFactory.createEmptyBorder(70, 30, 0, 0));

        JPanel estadisticasPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        estadisticasPanel.setOpaque(false);
        estadisticasPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));

        JPanel listasPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        listasPanel.setOpaque(false);
        listasPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        listasPanel.add(new JLabel(new NoScalingIcon(new ImageIcon(Url.urlListas))));

        inicio = new EJButton(" Inicio", new NoScalingIcon(new ImageIcon(Url.urlInicio)), false, true, false);
        inicio.setActionCommand(Try.tryInicio);

        estadisticas = new EJButton("Estadísticas", new NoScalingIcon(new ImageIcon(Url.urlEstadisticas)), false, true, false);
        estadisticas.setActionCommand(Try.tryEstadisticas);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(false);
        JMenu listas = new JMenu("Listas");
        listas.setForeground(Color.white);


        misListas = new JMenuItem("Mis listas");
        misListas.setBackground(Color.black);
        misListas.setForeground(Color.white);
        misListas.setActionCommand(Try.tryMisListas);

        listasDeOtrosUsuarios = new JMenuItem("Listas de otros");
        listasDeOtrosUsuarios.setBackground(Color.black);
        listasDeOtrosUsuarios.setForeground(Color.white);
        listasDeOtrosUsuarios.setActionCommand(Try.tryListasDeOtros);

        listas.add(misListas);
        listas.add(listasDeOtrosUsuarios);
        menuBar.add(listas);


        listasPanel.add(menuBar);
        inicioPanel.add(inicio);
        estadisticasPanel.add(estadisticas);


        menu.add(inicioPanel);
        menu.add(estadisticasPanel);
        menu.add(listasPanel);

        add(menu, BorderLayout.NORTH);
    }

    /**
     * Carregar listener al controller
     *
     * @param listener Evento.
     */
    public void menuListener(EventListener listener) {
        inicio.addActionListener((ActionListener) listener);
        estadisticas.addActionListener((ActionListener) listener);
        misListas.addActionListener((ActionListener) listener);
        listasDeOtrosUsuarios.addActionListener((ActionListener) listener);
    }
}
