package Presentation.Views;

import Business.Music;
import Presentation.Try;
import Presentation.Url;
import Presentation.Views.CustomComponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 * Vista de les dades de una canço
 */
public class MyMusicDataView extends JPanel {
    private JButton gestionCuenta;
    private JButton eliminarCancion;
    private JButton playCancion;
    private JList<String> jlist;
    private JLabel autor;
    private JLabel genero;
    private JLabel tiempoCancion;
    private JLabel tituloCancion;
    private JLabel album;
    private JLabel propietario;
    private JTextArea letraCancion;

    /**
     * Inicialitza la vista
     */
    public MyMusicDataView() {
        runInfoCancion();
    }

    /**
     * Carrega els components de Swing
     */
    private void runInfoCancion() {
        setLayout(new BorderLayout());
        centralView();
    }

    /**
     * Vista central de les dades.
     */
    private void centralView() {
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(new Color(20, 20, 20));

        gestionCuenta = new EJButton("Gestión de cuenta", new NoScalingIcon(new ImageIcon(Url.urlBotonGenerico)), true, false, true);
        gestionCuenta.setActionCommand(Try.tryGestionCuenta);

        JPanel norte = new JPanel(new GridLayout(1, 2));
        norte.setOpaque(false);
        norte.setPreferredSize(new Dimension(0, 150));

        JPanel centrarTitleAndLogo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centrarTitleAndLogo.setOpaque(false);

        JPanel centrarTituloCancion = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centrarTituloCancion.setOpaque(false);
        tituloCancion = new EJLabel("Título", 20, 255, 255, 255, 0, true);


        JPanel marcaAgua = new JPanel();
        marcaAgua.setLayout(new BoxLayout(marcaAgua, BoxLayout.Y_AXIS));
        marcaAgua.setOpaque(false);

        JPanel gestionYbusqueda = new JPanel();
        gestionYbusqueda.setLayout(new BorderLayout());
        gestionYbusqueda.setOpaque(false);

        JPanel centro = new JPanel(new BorderLayout());

        JPanel gridNorte = northViewCenter();
        JPanel gridCentral = centralViewCenter();
        JPanel gridSouth = southViewCenter();

        gestionYbusqueda.add(gestionCuenta, BorderLayout.NORTH);

        centrarTitleAndLogo.add(new LogoAndTextView("", 0));

        marcaAgua.add(new JLabel(" "));
        marcaAgua.add(centrarTitleAndLogo);
        marcaAgua.add(centrarTituloCancion);

        centrarTituloCancion.add(tituloCancion);

        norte.add(marcaAgua);
        norte.add(gestionYbusqueda);

        centro.add(gridNorte, BorderLayout.NORTH);
        centro.add(gridCentral, BorderLayout.CENTER);
        centro.add(gridSouth, BorderLayout.SOUTH);

        panelCentral.add(norte, BorderLayout.NORTH);
        panelCentral.add(centro, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

    }

    /**
     * Vista nord centre
     *
     * @return Retorna el Jpanel
     */
    private JPanel northViewCenter() {
        JPanel gridNorte = new JPanel();
        gridNorte.setLayout(new GridLayout(1, 2));
        //gridNorte.setOpaque(false);
        gridNorte.setBackground(new Color(20, 20, 20));
        //norte.setPreferredSize(new Dimension(getWidth(), 150));

        //columna 1 de la gridNorte
        JPanel boxTitleReprod = new JPanel();
        boxTitleReprod.setLayout(new BoxLayout(boxTitleReprod, BoxLayout.Y_AXIS));
        boxTitleReprod.setOpaque(false);


        JPanel flowReprodTiempo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flowReprodTiempo.setOpaque(false);
        tiempoCancion = new EJLabel("00" + ":" + "00", 12, 255, 255, 255, 0, true);
        playCancion = new EJButton(false, false);
        playCancion.setIcon(new NoScalingIcon(new ImageIcon(Url.urlPlay)));
        playCancion.setActionCommand(Try.tryPlay);


        //columna 2 de la gridNorte
        JPanel boxInfoCancion = new JPanel();
        boxInfoCancion.setLayout(new BoxLayout(boxInfoCancion, BoxLayout.Y_AXIS));
        boxInfoCancion.setOpaque(false);
        boxInfoCancion.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));


        JPanel gridAlbumYGenAut = new JPanel();
        gridAlbumYGenAut.setLayout(new GridLayout(1, 2));
        gridAlbumYGenAut.setOpaque(false);

        JPanel boxAutorYGenero = new JPanel();
        boxAutorYGenero.setLayout(new BoxLayout(boxAutorYGenero, BoxLayout.Y_AXIS));
        boxAutorYGenero.setOpaque(false);

        JPanel flowAutorYgenero = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flowAutorYgenero.setOpaque(false);
        autor = new EJLabel("Autor", 15, 255, 255, 255, 0, true);
        genero = new EJLabel("Género", 15, 255, 255, 255, 0, true);


        JPanel flowAlbumYimage = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flowAlbumYimage.setOpaque(false);
        album = new EJLabel("Album", 15, 255, 255, 255, 0, true);
        album.setIcon(new NoScalingIcon(new ImageIcon(Url.urlAlbum)));
        album.setHorizontalTextPosition(SwingConstants.CENTER);
        album.setVerticalTextPosition(SwingConstants.BOTTOM);

        boxAutorYGenero.add(new JLabel(" "));
        boxAutorYGenero.add(autor);
        boxAutorYGenero.add(new JLabel(" "));
        boxAutorYGenero.add(genero);

        flowAlbumYimage.add(album);
        flowAutorYgenero.add(boxAutorYGenero);

        gridAlbumYGenAut.add(flowAlbumYimage);
        gridAlbumYGenAut.add(flowAutorYgenero);

        flowReprodTiempo.add(playCancion);
        flowReprodTiempo.add(tiempoCancion);

        gridNorte.add(gridAlbumYGenAut);
        gridNorte.add(flowReprodTiempo);


        return gridNorte;

    }

    /**
     * Vista central
     *
     * @return Retorna el Jpanel.
     */
    private JPanel centralViewCenter() {
        JPanel gridCentral = new JPanel();
        gridCentral.setLayout(new GridLayout(1, 2));
        gridCentral.setBackground(new Color(20, 20, 20));

        letraCancion = new EJTextArea(5, 10);
        JScrollPane letraCancionScroller = new EJScrollPane(letraCancion, 30, 100, 15, 100);

        gridCentral.add(letraCancionScroller);

        return gridCentral;
    }

    /**
     * Vista del sud centre
     *
     * @return Retorna el JPanel
     */
    private JPanel southViewCenter() {
        JPanel gridSouth = new JPanel();
        gridSouth.setLayout(new GridLayout(1, 2));
        gridSouth.setBackground(new Color(20, 20, 20));


        propietario = new EJLabel("Propietario", 15, 255, 255, 255, 0, true);
        propietario.setHorizontalAlignment(SwingConstants.RIGHT);
        propietario.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        eliminarCancion = new EJButton(false, false);
        eliminarCancion.setIcon(new NoScalingIcon(new ImageIcon(Url.urlBasura)));
        eliminarCancion.setHorizontalAlignment(SwingConstants.LEFT);
        eliminarCancion.setActionCommand(Try.tryEliminarCancion);
        eliminarCancion.setVisible(true);

        gridSouth.add(eliminarCancion);
        gridSouth.add(propietario);

        return gridSouth;
    }

    /**
     * Missatge de Pop UP
     *
     * @param errorMessage Missatge de error
     * @param option       Opcio
     */
    public void createPopUpMessage(String errorMessage, int option) {
        JPanel message = new JPanel();
        message.add(new EJLabel(errorMessage, 12, 255, 255, 255, 0, true));

        if (option == 1) {
            Try.optionDeleteSong = JOptionPane.showConfirmDialog(null, message, "Aviso!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, new NoScalingIcon(new ImageIcon(Url.urlAtencion)));
        } else {
            JOptionPane.showMessageDialog(null, message, "Aviso!", JOptionPane.WARNING_MESSAGE, new NoScalingIcon(new ImageIcon(Url.urlAtencion)));

        }
    }

    /**
     * Carregar els listeners al controller
     *
     * @param eventListener Evento
     */
    public void viewMyMusicDataListener(EventListener eventListener) {
        eliminarCancion.addActionListener((ActionListener) eventListener);
        playCancion.addActionListener((ActionListener) eventListener);
        gestionCuenta.addActionListener((ActionListener) eventListener);
    }

    /**
     * Canviar les dades enviades
     */
    public void sendDataShow(Music musicDetails, String lyrics) {
        tituloCancion.setText(musicDetails.getName());
        album.setText(musicDetails.getAlbum());
        genero.setText(musicDetails.getGender());
        autor.setText(musicDetails.getSinger());
        propietario.setText(musicDetails.getOwner());
        tiempoCancion.setText(musicDetails.getDuration());
        letraCancion.setText(lyrics);
    }

    /**
     * Retorna la llista
     *
     * @return El nom.
     */
    public String getJlist() {
        return jlist.getSelectedValue();
    }
}
