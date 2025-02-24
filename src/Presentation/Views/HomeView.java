package Presentation.Views;

import Business.Music;
import Presentation.Try;
import Presentation.Url;
import Presentation.Views.CustomComponents.*;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Vista per el HOme view
 */
public class HomeView extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel();
    public JComboBox<String> sortLists;
    private JButton accountManagement;
    private JTextField filter;
    private JButton addSong;
    private ListSelectionModel rowSM;
    private JTable listGeneralSongs;
    private TableRowSorter<DefaultTableModel> tr;
    private Document document;

    /**
     * Inicialitza la vista
     */
    public HomeView() {
        runViewHome();
    }

    /**
     * Carrega els components de Swing.
     */
    private void runViewHome() {
        setLayout(new BorderLayout());
        centralView();
    }

    /**
     * Vista central
     */
    private void centralView() {
        setLayout(new BorderLayout());
        setBackground(new Color(20, 20, 20));

        accountManagement = new EJButton("Gestión de cuenta", new NoScalingIcon(new ImageIcon(Url.urlBotonGenerico)), true, false, true);
        accountManagement.setActionCommand(Try.tryGestionCuenta);

        JPanel norte = new JPanel(new GridLayout(1, 2));
        norte.setOpaque(false);
        norte.setPreferredSize(new Dimension(0, 200));

        JPanel gestionYbusqueda = new JPanel();
        gestionYbusqueda.setLayout(new BorderLayout());
        gestionYbusqueda.setOpaque(false);

        JPanel addCancionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addCancionPanel.setOpaque(false);
        addCancionPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));

        addSong = new EJButton("Añade una canción", new NoScalingIcon(new ImageIcon(Url.urlAdd)), false, false, false);
        addSong.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        addSong.setActionCommand(Try.tryViewAddSong);

        addCancionPanel.add(addSong);

        filter = new RoundJTextField(15);
        filter.setActionCommand(Try.tryFilter);

        JLabel lupa = new EJLabel("", 12, 20, 20, 20, 0, false);
        lupa.setIcon(new NoScalingIcon(new ImageIcon(Url.urlLupa)));
        lupa.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 10));

        JPanel busqueda = new JPanel();
        busqueda.setOpaque(false);
        busqueda.add(filter);
        busqueda.add(lupa);

        String[] opcionesOrden = {"Predeterminado", "Ascendente", "Descendente"};
        sortLists = new EJComboBox(opcionesOrden);
        sortLists.setActionCommand(Try.tryOrdenaListas);

        JPanel busquedaExhaustiva = new JPanel(new FlowLayout(FlowLayout.LEFT));
        busquedaExhaustiva.setOpaque(false);

        busquedaExhaustiva.add(busqueda);
        busquedaExhaustiva.add(new JLabel(" "));
        busquedaExhaustiva.add(sortLists);

        gestionYbusqueda.add(accountManagement, BorderLayout.NORTH);
        gestionYbusqueda.add(addCancionPanel, BorderLayout.CENTER);
        gestionYbusqueda.add(busquedaExhaustiva, BorderLayout.SOUTH);

        norte.add(new LogoAndTextView("Toda la música...", 1));
        norte.add(gestionYbusqueda);

        //Aqui le pasamos el parametro musica en verdad. Lo he puesto asi para ver el scrollbar. Cambiadlo luegoo
        listGeneralSongs = new EJTable(model, 6);
        tr = new TableRowSorter<>(model);
        listGeneralSongs.setRowSorter(tr);
        document = filter.getDocument();

        JScrollPane scrollPaneSongs = new EJScrollPane(listGeneralSongs, 20, 0, 0, 0);
        listGeneralSongs.setPreferredScrollableViewportSize(listGeneralSongs.getPreferredSize());
        listGeneralSongs.getTableHeader().setReorderingAllowed(false);
        listGeneralSongs.setCellSelectionEnabled(false);
        listGeneralSongs.setRowSelectionAllowed(true);
        listGeneralSongs.setFillsViewportHeight(true);
        listGeneralSongs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rowSM = listGeneralSongs.getSelectionModel();

        add(norte, BorderLayout.NORTH);
        add(scrollPaneSongs, BorderLayout.CENTER);
    }

    /**
     * Actualitzar el filtre
     */
    public void updateFilter() {
        tr.setRowFilter(new ContainsIgnoreCaseFilter(filter.getText()));
    }

    /**
     * Setejar les files JTable
     *
     * @param model model de la taula
     * @param music Musica que volem introduir.
     */
    private void setRows(DefaultTableModel model, List<Music> music) {
        // nos aseguramos que no haya ninguna ya
        model.setRowCount(0);
        // la información que se supone que está guardada en el Music
        // hay que merchear con la rama otra que he hecho para que funcione
        // hay que actualizar el DAO para las nuevas caracteristicas
        if (music != null) {
            for (int i = 0; i < music.size(); i++) {
                Object[] row = {i + 1, music.get(i).getName(), music.get(i).getAlbum(), music.get(i).getGender(), music.get(i).getSinger(), music.get(i).getOwner()};
                model.addRow(row);
            }
        }
    }

    /**
     * Actualitzar la musica
     *
     * @param listMusic Llista de la musica
     */
    public void updateMusic(List<Music> listMusic) {
        List<Music> musica = new ArrayList<>(listMusic);
        setRows(model, musica);
    }

    /**
     * Enllaçar amb el controller
     *
     * @param eventListener Evento
     */
    public void viewInitialListener(EventListener eventListener) {
        accountManagement.addActionListener((ActionListener) eventListener);
        addSong.addActionListener((ActionListener) eventListener);
        rowSM.addListSelectionListener((ListSelectionListener) eventListener);
        document.addDocumentListener((DocumentListener) eventListener);
        sortLists.addItemListener((ItemListener) eventListener);
    }

    /**
     * Agafar la taula
     *
     * @return Et retorna la JTable
     */
    public JTable getJtable() {
        return listGeneralSongs;
    }
}
