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
 * Vista per veure les altres playlists
 */
public class OtherPlaylistView extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel();
    private JButton gestionCuenta;
    private JTable listMyLists;
    private ListSelectionModel rowSM;

    /**
     * Comença la vista quan es crida
     */
    public OtherPlaylistView() {
        runListasdeOtrosUsuarios();
    }

    /**
     * Inicialitza els diversos components de Swing.
     */
    private void runListasdeOtrosUsuarios() {
        setLayout(new BorderLayout());
        centralView();
    }

    /**
     * Vista central de la vista.
     */
    private void centralView() {
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(new Color(20, 20, 20));

        gestionCuenta = new EJButton("Gestión de cuenta", new NoScalingIcon(new ImageIcon(Url.urlBotonGenerico)), true, false, true);
        gestionCuenta.setActionCommand(Try.tryGestionCuenta);


        JPanel norte = new JPanel(new GridLayout(1, 2));
        norte.setOpaque(false);
        norte.setPreferredSize(new Dimension(0, 200));

        JPanel gestionYbusqueda = new JPanel();
        gestionYbusqueda.setLayout(new BorderLayout());
        gestionYbusqueda.setOpaque(false);

        gestionYbusqueda.add(gestionCuenta, BorderLayout.NORTH);

        norte.add(new LogoAndTextView("Listas de otros usuarios...", 1));
        norte.add(gestionYbusqueda);

        listMyLists = new EJTable(model, 2);
        listMyLists.getColumnModel().getColumn(0).setPreferredWidth(1);
        JScrollPane scrollPaneLists = new EJScrollPane(listMyLists, 20, 0, 0, 0);
        listMyLists.setPreferredScrollableViewportSize(listMyLists.getPreferredSize());
        listMyLists.getTableHeader().setReorderingAllowed(false);
        listMyLists.setCellSelectionEnabled(false);
        listMyLists.setRowSelectionAllowed(true);
        listMyLists.setFillsViewportHeight(true);
        listMyLists.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rowSM = listMyLists.getSelectionModel();
        panelCentral.add(norte, BorderLayout.NORTH);
        panelCentral.add(scrollPaneLists, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

    }

    /**
     * Funcio per setejar les files
     *
     * @param model     Model de JTable
     * @param playlists Llista
     */

    private void setRows(DefaultTableModel model, List<Playlist> playlists) {
        // nos aseguramos que no haya ninguna ya
        model.setRowCount(0);
        if (playlists != null) {
            for (int id = 0; id < playlists.size(); id++) {
                Object[] row = {id + 1, playlists.get(id).getNamePlaylist()};
                model.addRow(row);
            }
        }
    }

    /**
     * Listener de aquesta JTable per enllaçarla amb el controller
     *
     * @param listener Listener
     */
    public void allPlaylistListener(EventListener listener) {
        gestionCuenta.addActionListener((ActionListener) listener);
        rowSM.addListSelectionListener((ListSelectionListener) listener);
    }

    /**
     * Actualitzar tota la playlist
     *
     * @param playlists Playlists.
     */
    public void updateListAllPlaylist(List<Playlist> playlists) {
        List<Playlist> listAllPlaylist = new ArrayList<>(playlists);
        setRows(model, listAllPlaylist);

    }

    /**
     * Aconseguir la JTable per retornala
     *
     * @return Retorna la JTable.
     */
    public JTable getJtable() {
        return listMyLists;
    }
}
