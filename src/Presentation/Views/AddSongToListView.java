package Presentation.Views;

import Business.Music;
import Presentation.Try;
import Presentation.Url;
import Presentation.Views.CustomComponents.*;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;

/**
 * Vista per afegir una canço a una llista
 */
public class AddSongToListView extends JPanel {
    private final DefaultListModel<String> listasDisponibles = new DefaultListModel<>();
    private JButton gestionCuenta;
    private JList<String> jlist;
    private JLabel namePlaylist;

    /**
     * Inicialitza la vista
     */
    public AddSongToListView() {
        runAddSong();
    }

    /**
     * Components de Swing starting...
     */
    private void runAddSong() {
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

        JPanel marcaAgua = new JPanel();
        marcaAgua.setLayout(new BoxLayout(marcaAgua, BoxLayout.Y_AXIS));
        marcaAgua.setOpaque(false);


        JLabel titleAndLogo = new EJLabel("Espotyfai", 30, 255, 255, 255, 10, true);
        titleAndLogo.setIcon(new NoScalingIcon(new ImageIcon(Url.urlLogo)));

        gestionCuenta = new EJButton("Gestión de cuenta", new NoScalingIcon(new ImageIcon(Url.urlBotonGenerico)), true, false, true);
        gestionCuenta.setActionCommand(Try.tryGestionCuenta);

        JPanel norte = new JPanel(new GridLayout(1, 2));
        norte.setOpaque(false);
        norte.setPreferredSize(new Dimension(0, 200));

        JPanel gestionYbusqueda = new JPanel();
        gestionYbusqueda.setLayout(new BorderLayout());
        gestionYbusqueda.setOpaque(false);

        //centro
        JPanel centro = new JPanel(new GridLayout(1, 3));
        centro.setOpaque(false);

        jlist = new EJList(listasDisponibles);

        JScrollPane listScroller = new EJScrollPane(jlist, 0, 30, 100, 100);

        gestionYbusqueda.add(gestionCuenta, BorderLayout.NORTH);

        namePlaylist = new EJLabel("", 23, 255, 255, 255, 50, true);

        marcaAgua.add(new JLabel(" "));
        marcaAgua.add(titleAndLogo);
        marcaAgua.add(titleAndLogo);
        marcaAgua.add(namePlaylist);
        norte.add(marcaAgua);
        norte.add(gestionYbusqueda);


        centro.add(new JLabel(""));
        centro.add(listScroller);
        centro.add(new JLabel(""));

        panelCentral.add(norte, BorderLayout.NORTH);
        panelCentral.add(centro, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);
    }

    /**
     * Agafa la llista de la musica
     *
     * @param musicList Llista.
     */
    public void getListMusic(List<Music> musicList) {
        listasDisponibles.removeAllElements();
        for (Music music : musicList) {
            listasDisponibles.addElement(music.getIdMusic() + " - " + music.getName());
        }
        jlist.setModel(listasDisponibles);
    }

    /**
     * Retorna la taula
     *
     * @return El nom.
     */
    public String getJlist() {
        return jlist.getSelectedValue();
    }

    /**
     * Registrar controller a la vista
     *
     * @param listener Event
     */
    public void addSongToListListener(EventListener listener) {
        gestionCuenta.addActionListener((ActionListener) listener);
        jlist.addListSelectionListener((ListSelectionListener) listener);
    }

    /**
     * Posar el nom a les llistes
     *
     * @param namePlaylist Nom de la llista
     */
    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist.setText("Añade una canción a " + namePlaylist);
    }
}
