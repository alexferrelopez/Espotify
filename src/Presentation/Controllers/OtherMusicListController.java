package Presentation.Controllers;

import Business.Music;
import Business.MusicManager;
import Business.QuickSort;
import Presentation.Try;
import Presentation.Views.FrameView;
import Presentation.Views.OtherMusicListView;

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
 * Aquest controller ens permet tenir un controller de les llistes dels altres usuaris, implementa diferents listeners com ActionListener, ListSelectionListener, DocumentListener, ItemListener
 */
public class OtherMusicListController implements ActionListener, ListSelectionListener, DocumentListener, ItemListener {

    private final FrameView frameView;
    private final OtherMusicListView otherMusicListView;
    private final MusicPlayerController musicPlayerController;
    private final MusicManager musicManager;
    private final OtherMusicDataController otherMusicDataController;
    private String nameMyPlaylist;

    /**
     * Aquest es el seu corresponent constructor.
     *
     * @param frameView                El necessita
     * @param otherMusicListView       El necessita
     * @param musicPlayerController    El necessita
     * @param musicManager             El necessita
     * @param otherMusicDataController El necessita
     */
    public OtherMusicListController(FrameView frameView, OtherMusicListView otherMusicListView,
                                    MusicPlayerController musicPlayerController, MusicManager musicManager,
                                    OtherMusicDataController otherMusicDataController) {
        this.frameView = frameView;
        this.otherMusicListView = otherMusicListView;
        this.musicPlayerController = musicPlayerController;
        this.musicManager = musicManager;
        this.otherMusicDataController = otherMusicDataController;
    }

    /**
     * Funcio que comença l'accio del controller.
     *
     * @param nameMyPlaylist Nom de la playlist escollida
     */
    public void start(String nameMyPlaylist) {
        this.nameMyPlaylist = nameMyPlaylist;
        otherMusicListView.getSlogan(nameMyPlaylist);
        getMyMusic(nameMyPlaylist);
    }

    /**
     * Listener de Accio per els diferents botons
     *
     * @param e Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Try.tryGestionCuenta -> {
                frameView.changePanel(FrameView.changeToGestionCuentaView);
                getMyMusic(nameMyPlaylist);
            }
            case Try.tryPlayMylist -> musicPlayerController.playPlaylist(musicManager.getMyMusic(nameMyPlaylist), 0);
        }
    }

    /**
     * Listeners per ordenar la llista.
     *
     * @param e event
     */
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == otherMusicListView.sortLists) {
            if (Objects.equals(otherMusicListView.sortLists.getSelectedItem(), "Ascendente")) {
                sortList(1);
            } else if (Objects.equals(otherMusicListView.sortLists.getSelectedItem(), "Descendente")) {
                sortList(0);
            } else {
                getMyMusic(nameMyPlaylist);
            }
        }
    }

    /**
     * Funcio que ordena una llista
     *
     * @param option preferencia
     */
    private void sortList(int option) {
        List<Music> sortList = musicManager.getMyMusic(nameMyPlaylist);
        //Descendente
        if (option == 0) {
            QuickSort.quickSort(sortList, 0, sortList.size() - 1, 0);
        }
        //Ascendente
        if (option == 1) {
            QuickSort.quickSort(sortList, 0, sortList.size() - 1, 1);
        }
        otherMusicListView.updateMusic(sortList);
    }

    /**
     * Funcio per obtenir la musica.
     *
     * @param nameMyPlaylist Nom de la playlist.
     */
    private void getMyMusic(String nameMyPlaylist) {
        otherMusicListView.updateMusic(musicManager.getMyMusic(nameMyPlaylist));
    }

    /**
     * Listener corresponent a la canço escollida de la JTable
     *
     * @param e event.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable listGeneralSongs = otherMusicListView.getJtable();
        List<String> musicDetails = new ArrayList<>();
        if (e.getValueIsAdjusting()) return;

        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        lsm.setValueIsAdjusting(true);
        if (!lsm.isSelectionEmpty()) {
            int selectedRow = lsm.getMinSelectionIndex();
            //musicDetails.s(listGeneralSongs.getValueAt(selectedRow,0));
            musicDetails.add((String) listGeneralSongs.getValueAt(selectedRow, 1));
            musicDetails.add((String) listGeneralSongs.getValueAt(selectedRow, 2));
            musicDetails.add((String) listGeneralSongs.getValueAt(selectedRow, 3));
            musicDetails.add((String) listGeneralSongs.getValueAt(selectedRow, 4));
            musicDetails.add((String) listGeneralSongs.getValueAt(selectedRow, 5));
            Music music = musicManager.searchSong(musicDetails);
            otherMusicDataController.start(music, musicManager.getMyMusic(nameMyPlaylist));
            getMyMusic(nameMyPlaylist);
            frameView.changePanel(FrameView.changeToOtherMusicDataView);

            lsm.clearSelection();
        }
    }

    /**
     * La usem per actualitzar el filtre si hi ha una actualitzacio.
     *
     * @param e event
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        otherMusicListView.updateFilter();
    }

    /**
     * La usem per eliminar la actualitzacio.
     *
     * @param e event
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        otherMusicListView.updateFilter();
    }

    /**
     * La usem com a listener per canviar l'estat
     *
     * @param e event
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        otherMusicListView.updateFilter();
    }
}

