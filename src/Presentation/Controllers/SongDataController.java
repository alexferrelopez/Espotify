package Presentation.Controllers;

import Business.Music;
import Business.MusicManager;
import Business.PlaylistManager;
import Presentation.Try;
import Presentation.Views.FrameView;
import Presentation.Views.SongDataView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aquesta classe ens serveix com a controller per a la vista de detalls de una canço individual. Extendrem action listener per els botons i ListSelection per la fila de la JTable.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class SongDataController implements ActionListener, ListSelectionListener {
    private final MusicPlayerController musicPlayerController;
    private final FrameView frameView;
    private final SongDataView songDataView;
    private final MusicManager musicManager;
    private final PlaylistManager playlistManager;
    private Music musicData;
    private HomeController homeController;

    /**
     * Constructor de el controller
     *
     * @param musicPlayerController Li passarem el controller del music player per a reproduir una canço desde aquesta vista.
     * @param frameView             Li passarem el controller general per respectar el GRASP
     * @param songDataView          Li passarem la vista de la informacio de la canço
     * @param musicManager          El music manager
     * @param playlistManager       El playlistmanager
     */
    public SongDataController(MusicPlayerController musicPlayerController, FrameView frameView, SongDataView songDataView, MusicManager musicManager, PlaylistManager playlistManager) {
        this.musicPlayerController = musicPlayerController;
        this.frameView = frameView;
        this.songDataView = songDataView;
        this.musicManager = musicManager;
        this.playlistManager = playlistManager;
    }

    /**
     * Setejarem el home controller
     *
     * @param homeController
     */
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    /**
     * Funcio que ens permet començar amb aquesta vista i informacio quan toqui.
     *
     * @param musicDetails Li enviarem els detalls de una canço per a gestionar-ho.
     */
    public void start(Music musicDetails) {
        getPlaylistsList();
        musicData = musicDetails;
        songDataView.deleteSongIcon(false);
        if (musicData.getIdOwner() == frameView.getIdUser()) {
            songDataView.deleteSongIcon(true);
        }
        musicManager.getSongLyrics(musicData.getSinger(), musicData.getName(), s -> songDataView.sendDataShow(musicData, s), musicData.getAlbum());
        songDataView.sendDataShow(musicData, "Buscando la letra de la canción...");
    }

    /**
     * Funcio de l'action listener que em permetra canviar entre opcions com gestionar el compte, reproduir o eliminarla tot desde la vista.
     *
     * @param e Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Try.tryPlay -> musicPlayerController.playInSingleSongMode(musicData);
            case Try.tryEliminarCancion -> deleteMusic();
            case Try.tryGestionCuenta -> frameView.changePanel(FrameView.changeToGestionCuentaView);
        }
    }

    /**
     * Funcio que detecta si afegim una canço a una llista
     *
     * @param e ListSelectionEvent
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            playlistManager.addSongToPlaylist(frameView.getIdUser(), musicData.getIdMusic(), songDataView.getJlist());
        }
    }

    /**
     * Funcio que ens permet obtenir les diferents playlists.
     */
    private void getPlaylistsList() {
        songDataView.getListPlaylist(playlistManager.getMyPlaylists(frameView.getIdUser()));
    }

    /**
     * Funcio que ens permet gestionar la logica de eliminar una canço amb els seus errors i pop ups.
     */
    private void deleteMusic() {
        if (musicData.getIdMusic() != musicPlayerController.getIdMusicPlay()) {
            songDataView.createPopUpMessage("¿Seguro que quieres eliminar tu canción?", 1);
            if (Try.optionDeleteSong == 0) {
                boolean songSuccessfullyDeleted = musicManager.deleteSong(musicData.getIdMusic(), musicData.getUrl());
                if (songSuccessfullyDeleted) songDataView.createPopUpMessage("Canción eliminada correctamente", 2);
                else songDataView.createPopUpMessage("Ha habido un error en el borrado", 2);
                homeController.getAllMusic();
                frameView.changePanel(FrameView.changeToMainGUIView);
            }
        } else {
            songDataView.createPopUpMessage("La canción se esta reproduciendo no se puede eliminar!", 2);
        }
    }
}