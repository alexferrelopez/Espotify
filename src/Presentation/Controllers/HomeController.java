package Presentation.Controllers;

import Business.Music;
import Business.MusicManager;
import Business.QuickSort;
import Presentation.Try;
import Presentation.Views.FrameView;
import Presentation.Views.HomeView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que ens permet controllar la HomeView
 */
public class HomeController implements ActionListener, ListSelectionListener, DocumentListener, ItemListener {

    private final FrameView frameView;
    private final HomeView homeView;
    private final MusicManager musicManager;
    private final SongDataController songDataController;

    /**
     * Constructor de la classe
     *
     * @param frameView          Vista del reproductor.
     * @param homeView           Vista de Home.
     * @param musicManager       Music manager.
     * @param songDataController Controller de dades de la musica
     */
    public HomeController(FrameView frameView, HomeView homeView, MusicManager musicManager,
                          SongDataController songDataController) {
        this.frameView = frameView;
        this.homeView = homeView;
        this.musicManager = musicManager;
        this.songDataController = songDataController;
    }

    /**
     * Funcio que escolta el listener de accions per els botons
     *
     * @param e Event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Try.tryGestionCuenta -> {
                frameView.changePanel(FrameView.changeToGestionCuentaView);
                getAllMusic();
            }
            case Try.tryViewAddSong -> {
                frameView.changePanel(FrameView.changeToAddSongToProjectView);
                getAllMusic();
            }
        }
    }

    /**
     * Funcio que escolta el desplegable de filtratge
     *
     * @param e Event.
     */
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == homeView.sortLists) {
            if (Objects.equals(homeView.sortLists.getSelectedItem(), "Ascendente")) {
                sortList(1);
            } else if (Objects.equals(homeView.sortLists.getSelectedItem(), "Descendente")) {
                sortList(0);
            } else {
                getAllMusic();
            }
        }
    }

    /**
     * Funcio que ens permet ordenar la llista.
     *
     * @param option tipus de ordenatge.
     */
    private void sortList(int option) {
        List<Music> sortList = musicManager.getAllMusic();
        //Descendente
        if (option == 0) {
            QuickSort.quickSort(sortList, 0, sortList.size() - 1, 0);
        }
        //Ascendente
        if (option == 1) {
            QuickSort.quickSort(sortList, 0, sortList.size() - 1, 1);
        }
        homeView.updateMusic(sortList);
    }

    /**
     * Funcio que agafa tota la musica de la llista
     */
    public void getAllMusic() {
        homeView.updateMusic(musicManager.getAllMusic());
    }

    /**
     * Listener de les files
     *
     * @param e Event.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable listGeneralSongs = homeView.getJtable();
        List<String> musicDetails = new ArrayList<>();
        if (e.getValueIsAdjusting()) return;

        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        lsm.setValueIsAdjusting(true);
        if (!lsm.isSelectionEmpty()) {
            int selectedRow = lsm.getMinSelectionIndex();
            musicDetails.add((String) listGeneralSongs.getValueAt(selectedRow, 1));
            musicDetails.add((String) listGeneralSongs.getValueAt(selectedRow, 2));
            musicDetails.add((String) listGeneralSongs.getValueAt(selectedRow, 3));
            musicDetails.add((String) listGeneralSongs.getValueAt(selectedRow, 4));
            musicDetails.add((String) listGeneralSongs.getValueAt(selectedRow, 5));
            Music music = musicManager.searchSong(musicDetails);
            songDataController.start(music);
            getAllMusic();
            frameView.changePanel(FrameView.changeToInfoCancionView);

            lsm.clearSelection();
        }
    }

    /**
     * Posar actualitzacio de filtre
     *
     * @param e Evento
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        homeView.updateFilter();
    }

    /**
     * Eliminar la actualitzacio del filtre
     *
     * @param e Evento.
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        homeView.updateFilter();
    }

    /**
     * Canviar la actualitzacio.
     *
     * @param e Evento.
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        homeView.updateFilter();
    }
}
