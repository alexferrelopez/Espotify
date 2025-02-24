package Presentation.Controllers;

import Business.Music;
import Business.MusicManager;
import Business.QuickSort;
import Presentation.Try;
import Presentation.Views.FrameView;
import Presentation.Views.MyMusicListView;

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
 * Classe que em permet controlar la vista de una de les meves playlists, invocada per el MyPlayListController
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class MyMusicListController implements ActionListener, ListSelectionListener, DocumentListener, ItemListener {

    private final FrameView frameView;
    private final MyMusicListView myMusicListView;
    private final MusicPlayerController musicPlayerController;
    private final MusicManager musicManager;
    private final MyMusicDataController myMusicDataController;
    private final AddSongToListController addSongToListController;
    private String nameMyPlaylist;

    /**
     * Constructor de la classe
     *
     * @param frameView               .
     * @param myMusicListView         Vista de les musiques.
     * @param musicPlayerController   Controller del player de musica
     * @param musicManager            Music manager
     * @param myMusicDataController   La meva musica controller
     * @param addSongToListController Afegir canço controller.
     */
    public MyMusicListController(FrameView frameView, MyMusicListView myMusicListView,
                                 MusicPlayerController musicPlayerController, MusicManager musicManager,
                                 MyMusicDataController myMusicDataController, AddSongToListController addSongToListController) {
        this.frameView = frameView;
        this.myMusicListView = myMusicListView;
        this.musicPlayerController = musicPlayerController;
        this.musicManager = musicManager;
        this.myMusicDataController = myMusicDataController;
        this.addSongToListController = addSongToListController;
    }

    /**
     * Començament d'aquest controller per la classe superior MyPlaylistController
     *
     * @param nameMyPlaylist Nom de la llista
     */
    public void start(String nameMyPlaylist) {
        this.nameMyPlaylist = nameMyPlaylist;
        myMusicListView.setSlogan(nameMyPlaylist);
        getMyMusic(nameMyPlaylist);
    }

    /**
     * Action listener per els diferents botons
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
            case Try.tryViewCancionToPlaylist -> {
                addSongToListController.getPlaylistsList(nameMyPlaylist);
                frameView.changePanel(FrameView.changeToAddSongToListView);
                getMyMusic(nameMyPlaylist);
            }
            case Try.tryPlayMylist -> musicPlayerController.playPlaylist(musicManager.getMyMusic(nameMyPlaylist), 0);
        }
    }

    /**
     * Funcio per a ordenar les llistes que te en compte el ItemEvent
     *
     * @param e Event.
     */
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == myMusicListView.sortLists) {
            if (Objects.equals(myMusicListView.sortLists.getSelectedItem(), "Ascendente")) {
                sortList(1);
            } else if (Objects.equals(myMusicListView.sortLists.getSelectedItem(), "Descendente")) {
                sortList(0);
            } else {
                getMyMusic(nameMyPlaylist);
            }
        }
    }

    /**
     * Funcio que em permet ordenar la llista
     *
     * @param option opcio de ordenatge.
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
        myMusicListView.updateMusic(sortList);
    }

    /**
     * funcio que em permet obtenir la meva musica.
     *
     * @param nameMyPlaylist Nom de la meva llista
     */
    public void getMyMusic(String nameMyPlaylist) {
        myMusicListView.updateMusic(musicManager.getMyMusic(nameMyPlaylist));
    }

    /**
     * Funcio per el listener de la JTable
     *
     * @param e event.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable listGeneralSongs = myMusicListView.getJtable();
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
            myMusicDataController.start(music, nameMyPlaylist, musicManager.getMyMusic(nameMyPlaylist));
            getMyMusic(nameMyPlaylist);
            frameView.changePanel(FrameView.changeToMyMusicDataView);

            lsm.clearSelection();
        }
    }

    /**
     * Listener per actualitzar el filtre
     *
     * @param e Evento
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        myMusicListView.updateFilter();
    }

    /**
     * Listener per borrar la actualitzacio
     *
     * @param e Evento.
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        myMusicListView.updateFilter();
    }

    /**
     * Canviar la actualitzacio
     *
     * @param e Evento
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        myMusicListView.updateFilter();
    }
}
