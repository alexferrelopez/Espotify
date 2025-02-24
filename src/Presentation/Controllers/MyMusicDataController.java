package Presentation.Controllers;

import Business.Music;
import Business.MusicManager;
import Business.PlaylistManager;
import Presentation.Try;
import Presentation.Views.FrameView;
import Presentation.Views.MyMusicDataView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Aquesta classe em permet controlar la vista de les dades de una canço.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class MyMusicDataController implements ActionListener, ListSelectionListener {
    private final MusicPlayerController musicPlayerController;
    private final FrameView frameView;
    private final MyMusicDataView myMusicDataView;
    private final PlaylistManager playlistManager;
    private final MusicManager musicManager;
    private List<Music> musicListData;
    private Music musicData;
    private MyMusicListController myMusicListController;
    private String namePlaylist;

    /**
     * Constructor de la classe
     *
     * @param musicPlayerController Controller del music player
     * @param frameView             Vista del reproductor.
     * @param myMusicDataView       Vista asociada.
     * @param playlistManager       Manager de llistes.
     */
    public MyMusicDataController(MusicPlayerController musicPlayerController, FrameView frameView,
                                 MyMusicDataView myMusicDataView, PlaylistManager playlistManager, MusicManager musicManager) {
        this.musicPlayerController = musicPlayerController;
        this.frameView = frameView;
        this.myMusicDataView = myMusicDataView;
        this.playlistManager = playlistManager;
        this.musicManager = musicManager;
    }

    /**
     * Setter de la classe
     *
     * @param myMusicListController el controller de la MusicList
     */
    public void setMyMusicListController(MyMusicListController myMusicListController) {
        this.myMusicListController = myMusicListController;
    }

    /**
     * Classe que em permet iniciar la classe desde la superior que en aquest cas es MyMusicListController
     *
     * @param musicDetails
     * @param namePlaylist
     * @param myMusic
     */
    public void start(Music musicDetails, String namePlaylist, List<Music> myMusic) {
        musicData = musicDetails;
        musicListData = myMusic;
        this.namePlaylist = namePlaylist;
        musicManager.getSongLyrics(musicData.getSinger(), musicData.getName(), s -> myMusicDataView.sendDataShow(musicData, s), musicData.getAlbum());
        myMusicDataView.sendDataShow(musicData, "Buscando la letra de la canción...");
    }

    /**
     * Funcio corresponent al action listener
     *
     * @param e event.
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
            case Try.tryEliminarCancion -> {
                myMusicDataView.createPopUpMessage("¿Quieres quitar la canción de tu lista?", 1);
                if (Try.optionDeleteSong == 0) {
                    playlistManager.deleteMusicPlayList(namePlaylist, musicData.getIdMusic());
                    myMusicDataView.createPopUpMessage("Canción quitada correctamente", 2);
                    myMusicListController.getMyMusic(namePlaylist);
                    frameView.changePanel(FrameView.changeToListMyMusicView);
                }
            }
            case Try.tryGestionCuenta -> frameView.changePanel(FrameView.changeToGestionCuentaView);
        }
    }

    /**
     * Funcio que ens serveix per a obtenir la informacio de afegir a una llista.
     *
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            playlistManager.addSongToPlaylist(frameView.getIdUser(), musicData.getIdMusic(), myMusicDataView.getJlist());
        }
    }
}