package Presentation.Views;


import Business.Playlist;
import Presentation.Try;
import Presentation.Url;
import Presentation.Views.CustomComponents.*;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Vista de la meva playlist
 */
public class MyPlaylistView extends JPanel {
    public static int optionCreatePlaylist;
    private final DefaultTableModel model = new DefaultTableModel();
    private JButton accountManagement;
    private JButton createList;
    private JTextField namePlaylist;
    private JTable playlistTable;
    private ListSelectionModel rowSM;

    /**
     * Inicialitza la vista
     */
    public MyPlaylistView() {
        runMainGUI();
    }

    /**
     * Carrega els components de Swing
     */
    private void runMainGUI() {
        setLayout(new BorderLayout());
        centralView();
    }

    /**
     * Vista central
     */
    private void centralView() {
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(new Color(20, 20, 20));

        accountManagement = new EJButton("Gestión de cuenta", new NoScalingIcon(new ImageIcon(Url.urlBotonGenerico)), true, false, true);
        accountManagement.setActionCommand(Try.tryGestionCuenta);

        JPanel norte = new JPanel(new GridLayout(1, 2));
        norte.setOpaque(false);
        norte.setPreferredSize(new Dimension(0, 200));

        JPanel gestionYCreacion = new JPanel();
        gestionYCreacion.setLayout(new BorderLayout());
        gestionYCreacion.setOpaque(false);


        JPanel crearListaPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        crearListaPanel.setOpaque(false);
        crearListaPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));

        createList = new EJButton("Crea una lista", new NoScalingIcon(new ImageIcon(Url.urlAdd)), false, false, false);
        createList.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        createList.setActionCommand(Try.tryCreatePlaylist);

        crearListaPanel.add(createList);

        gestionYCreacion.add(accountManagement, BorderLayout.NORTH);
        gestionYCreacion.add(crearListaPanel, BorderLayout.CENTER);

        norte.add(new LogoAndTextView("Mis listas...", 1));
        norte.add(gestionYCreacion);

        playlistTable = new EJTable(model, 2);
        playlistTable.getColumnModel().getColumn(0).setPreferredWidth(1);
        JScrollPane scrollPaneLists = new EJScrollPane(playlistTable, 20, 0, 0, 0);
        playlistTable.setPreferredScrollableViewportSize(playlistTable.getPreferredSize());
        playlistTable.getTableHeader().setReorderingAllowed(false);
        playlistTable.setCellSelectionEnabled(false);
        playlistTable.setRowSelectionAllowed(true);
        playlistTable.setFillsViewportHeight(true);
        playlistTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rowSM = playlistTable.getSelectionModel();

        panelCentral.add(norte, BorderLayout.NORTH);
        panelCentral.add(scrollPaneLists, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);
    }

    /**
     * Seteja les files
     *
     * @param model     model de la taula
     * @param playlists Llistes
     */
    private void setRows(DefaultTableModel model, List<Playlist> playlists) {
        // nos aseguramos que no haya ninguna ya
        model.setRowCount(0);
        // la información que se supone que está guardada en el playlsit
        // hay que merchear con la rama otra que he hecho para que funcione
        // hay que actualizar el DAO para las nuevas caracteristicas

        if (playlists != null) {
            for (int id = 0; id < playlists.size(); id++) {
                Object[] row = {id + 1, playlists.get(id).getNamePlaylist()};
                model.addRow(row);
            }
        }
    }

    /**
     * Crea un pop up
     */
    private void createPopUp() {
        namePlaylist = new EJTextField(20, true, false, false, false);

        JPanel playlistConfirm = new JPanel();
        playlistConfirm.add(new EJLabel("Nombre:       ", 12, 255, 255, 255, 0, true));
        playlistConfirm.add(namePlaylist);

        optionCreatePlaylist = JOptionPane.showConfirmDialog(null, playlistConfirm, "Crea tu Lista", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Error del pop up
     *
     * @param message Missatge de error
     */
    public void createPopUpError(String message) {
        JOptionPane.showMessageDialog(null, message, "Atención: " + "Error en creación de lista", JOptionPane.ERROR_MESSAGE, new NoScalingIcon(new ImageIcon(Url.urlAtencion)));
    }

    /**
     * Ensenyar que has creat la llista
     */
    public void showCreatPlaylist() {
        createPopUp();
    }

    /**
     * Registrar vista al controller
     *
     * @param listener Evento.
     */
    public void playlistListener(EventListener listener) {
        accountManagement.addActionListener((ActionListener) listener);
        createList.addActionListener((ActionListener) listener);
        rowSM.addListSelectionListener((ListSelectionListener) listener);
    }

    /**
     * Aconseguir les dades de Crear una llista
     *
     * @return Retorna el nom
     */
    public String getDataCreatePlaylist() {
        return namePlaylist.getText().trim();
    }

    /**
     * Actualitza una llista
     *
     * @param playlists Retorna les llistes
     */
    public void updateListPlaylist(List<Playlist> playlists) {
        List<Playlist> listPlaylist = new ArrayList<>(playlists);
        setRows(model, listPlaylist);
    }

    /**
     * Aconseguir la JTable
     *
     * @return Retorna la taula.
     */
    public JTable getJtable() {
        return playlistTable;
    }
}
