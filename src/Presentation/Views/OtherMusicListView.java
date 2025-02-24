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
 * Vista de les llistes dels altres per dins.
 */
public class OtherMusicListView extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel();
    public JComboBox<String> sortLists;
    private JButton accountManagement;
    private JTextField filter;
    private JButton playCancion;
    private JLabel slogan;
    private ListSelectionModel rowSM;
    private JTable listGeneralSongs;
    private TableRowSorter<DefaultTableModel> tr;
    private Document document;

    /**
     * Inicialitza la vista
     */
    public OtherMusicListView() {
        runViewHome();
    }

    /**
     * Carrega els components de Swing
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

        JLabel titleAndLogo = new EJLabel("Espotyfai", 30, 255, 255, 255, 10, true);
        titleAndLogo.setIcon(new NoScalingIcon(new ImageIcon(Url.urlLogo)));

        playCancion = new EJButton(false, false);
        playCancion.setIcon(new NoScalingIcon(new ImageIcon(Url.urlPlayPlayList)));
        playCancion.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        playCancion.setActionCommand(Try.tryPlayMylist);


        slogan = new EJLabel("", 23, 255, 255, 255, 50, true);

        JPanel marcaAgua = new JPanel();
        marcaAgua.setLayout(new BoxLayout(marcaAgua, BoxLayout.Y_AXIS));
        marcaAgua.setOpaque(false);

        JPanel gestionYbusqueda = new JPanel();
        gestionYbusqueda.setLayout(new BorderLayout());
        gestionYbusqueda.setOpaque(false);

        JPanel addCancionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addCancionPanel.setOpaque(false);
        addCancionPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));


        filter = new RoundJTextField(15);
        filter.setActionCommand(Try.tryFilter);

        JLabel lupa = new EJLabel("", 0, 0, 0, 0, 0, false);
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

        marcaAgua.add(new JLabel(" "));
        marcaAgua.add(titleAndLogo);
        marcaAgua.add(slogan);
        marcaAgua.add(playCancion);

        norte.add(marcaAgua);
        norte.add(gestionYbusqueda);

        //aqui le pasamos el parametro musica en verdad. Lo he puesto asi para ver el scrollbar. Cambiadlo luegoo
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
     * Setejar les files
     *
     * @param model Model de la taula
     * @param music Musica
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
     * Actualitzar tota la musica
     *
     * @param listMusic Llista de la musica
     */
    public void updateMusic(List<Music> listMusic) {
        List<Music> music = new ArrayList<>(listMusic);
        setRows(model, music);
    }

    /**
     * Listener de la vista que afegirem al controller
     *
     * @param eventListener Evento
     */
    public void viewOtherMusicListListener(EventListener eventListener) {
        accountManagement.addActionListener((ActionListener) eventListener);
        playCancion.addActionListener((ActionListener) eventListener);
        rowSM.addListSelectionListener((ListSelectionListener) eventListener);
        document.addDocumentListener((DocumentListener) eventListener);
        sortLists.addItemListener((ItemListener) eventListener);
    }

    /**
     * Agafar la Jtable
     *
     * @return Retorna la Jtable
     */
    public JTable getJtable() {
        return listGeneralSongs;
    }

    /**
     * Aconsegueixes el slogan
     *
     * @param namePlaylist Nom de la llista.
     */
    public void getSlogan(String namePlaylist) {
        slogan.setText(namePlaylist);
    }
}
