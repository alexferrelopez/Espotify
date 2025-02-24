package Presentation.Controllers;

import Business.Music;
import Business.MusicManager;
import Business.PlaylistManager;
import Presentation.Try;
import Presentation.Views.FrameView;
import Presentation.Views.OtherMusicDataView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Classe per veure les llistes de altres persones
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class OtherMusicDataController implements ActionListener, ListSelectionListener {
    private final MusicPlayerController musicPlayerController;
    private final MusicManager musicManager;
    private final FrameView frameView;
    private final OtherMusicDataView otherMusicDataView;
    private final PlaylistManager playlistManager;
    private List<Music> musicListData;
    private Music musicData;

    /**
     * Constructor de les dades de musica de altres usuaris.
     *
     * @param musicPlayerController Controller del music player
     * @param frameView             Vista del reproductor
     * @param otherMusicDataView    Vista asociada a les dades de altra musica.
     * @param playlistManager       Manager del playlist
     * @param musicManager          manager de la musica.
     */
    public OtherMusicDataController(MusicPlayerController musicPlayerController, FrameView frameView,
                                    OtherMusicDataView otherMusicDataView, PlaylistManager playlistManager, MusicManager musicManager) {
        this.musicPlayerController = musicPlayerController;
        this.frameView = frameView;
        this.otherMusicDataView = otherMusicDataView;
        this.playlistManager = playlistManager;
        this.musicManager = musicManager;
    }

    /**
     * Funcio per començar la accio del controller
     *
     * @param musicDetails Detalls de la musica
     * @param myMusic      La musica que li enviarem a la llista.
     */
    public void start(Music musicDetails, List<Music> myMusic) {
        musicData = musicDetails;
        musicListData = myMusic;
        musicManager.getSongLyrics(musicData.getSinger(), musicData.getName(), s -> otherMusicDataView.sendDataShow(musicData, s), musicData.getAlbum());
        otherMusicDataView.sendDataShow(musicDetails, "Buscando la letra de la canción...");
    }

    /**
     * Listener de accions per els diferents botons de la vista
     *
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Try.tryPlay -> {
                for (Music musicListDatum : musicListData) {
                    if (musicListDatum.getIdMusic() == musicData.getIdMusic()) {
                        musicPlayerController.playPlaylist(musicListData, musicListData.indexOf(musicListDatum));
                        break;
                    }
                }
            }
            case Try.tryGestionCuenta -> frameView.changePanel(FrameView.changeToGestionCuentaView);
        }
    }

    /**
     * Afegir canço a una playlist
     *
     * @param e event
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            playlistManager.addSongToPlaylist(frameView.getIdUser(), musicData.getIdMusic(), otherMusicDataView.getJlist());
        }
    }

}
