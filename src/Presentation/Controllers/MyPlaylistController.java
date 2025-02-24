package Presentation.Controllers;

import Business.PlaylistManager;
import Presentation.Try;
import Presentation.Views.FrameView;
import Presentation.Views.MyPlaylistView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que em permet controlar les meves playlists implementa una action listener per els botons i un list selection per la JTable
 */
public class MyPlaylistController implements ActionListener, ListSelectionListener {
    private final FrameView frameView;
    private final MyMusicListController myMusicListController;
    private final PlaylistManager playlistManager;
    private final MyPlaylistView myPlaylistView;

    /**
     * Constructor de la classe
     *
     * @param frameView             Vista de el reproductor
     * @param myMusicListController Controller de la llista de musica
     * @param myPlaylistView        Vista de la meva llista.
     * @param playlistManager       Manager de la playlist
     */
    public MyPlaylistController(FrameView frameView, MyMusicListController myMusicListController, MyPlaylistView myPlaylistView, PlaylistManager playlistManager) {
        this.frameView = frameView;
        this.myMusicListController = myMusicListController;
        this.myPlaylistView = myPlaylistView;
        this.playlistManager = playlistManager;
    }

    /**
     * Action Listener per els botons, com crear una llista, gestionar el compte...
     *
     * @param e Event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Try.tryGestionCuenta -> {
                getMyPlaylists();
                frameView.changePanel(FrameView.changeToGestionCuentaView);
            }
            case Try.tryCreatePlaylist -> {
                myPlaylistView.showCreatPlaylist();
                if (MyPlaylistView.optionCreatePlaylist == 0) {
                    String name = myPlaylistView.getDataCreatePlaylist();
                    if (name.isEmpty()) {
                        myPlaylistView.createPopUpError("El campo esta vacío, prueba de nuevo!");
                    } else if (!playlistManager.createPlaylist(frameView.getIdUser(), name).equals("true")) {
                        myPlaylistView.createPopUpError("El nombre introducido para la lista ya está en uso!");
                    }
                    getMyPlaylists();
                }
            }
        }
    }

    /**
     * Funcio que em permet obtenir les meves llistes
     */
    public void getMyPlaylists() {
        myPlaylistView.updateListPlaylist(playlistManager.getMyPlaylists(frameView.getIdUser()));
    }

    /**
     * Funcio que em permet escoltar la taula i agafar un element
     *
     * @param e Event
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable listGeneralSongs = myPlaylistView.getJtable();
        if (e.getValueIsAdjusting()) return;

        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        lsm.setValueIsAdjusting(true);
        if (!lsm.isSelectionEmpty()) {
            int selectedRow = lsm.getMinSelectionIndex();
            String namePlaylist = (String) listGeneralSongs.getValueAt(selectedRow, 1);
            getMyPlaylists();
            myMusicListController.start(namePlaylist);
            frameView.changePanel(FrameView.changeToListMyMusicView);
            lsm.clearSelection();
        }
    }
}