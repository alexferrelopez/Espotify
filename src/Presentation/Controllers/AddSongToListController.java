package Presentation.Controllers;

import Business.MusicManager;
import Business.PlaylistManager;
import Presentation.Try;
import Presentation.Views.AddSongToListView;
import Presentation.Views.FrameView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aquesta classe ens permet afegir una canço a una llista connectantnos amb la vista Listeners: ActionListener, ListSelectionListener
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class AddSongToListController implements ActionListener, ListSelectionListener {

    private final FrameView frameView;
    private final MusicManager musicManager;
    private final PlaylistManager playlistManager;
    private final AddSongToListView addSongToListView;
    private String nameMyPlaylist;

    /**
     * Controller de afegir canço a una llista
     *
     * @param frameView         controller general.
     * @param musicManager      Music manager.
     * @param playlistManager   Manager de les playlists.
     * @param addSongToListView Vista de afegir canço a una llista.
     */
    public AddSongToListController(FrameView frameView, MusicManager musicManager, PlaylistManager playlistManager, AddSongToListView addSongToListView) {
        this.frameView = frameView;
        this.musicManager = musicManager;
        this.playlistManager = playlistManager;
        this.addSongToListView = addSongToListView;
    }

    /**
     * Funcio que ens permet obtenir la llista de playlists
     *
     * @param nameMyPlaylist Nom de la playlist escollida
     */
    public void getPlaylistsList(String nameMyPlaylist) {
        this.nameMyPlaylist = nameMyPlaylist;
        addSongToListView.setNamePlaylist(nameMyPlaylist);
        addSongToListView.getListMusic(musicManager.getAllMusic());
    }

    /**
     * Funcio referent al listener de accions
     *
     * @param e Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Try.tryGestionCuenta.equals(e.getActionCommand())) {
            frameView.changePanel(FrameView.changeToGestionCuentaView);
        }
    }

    /**
     * Funcio que ens permet ordenar en una llista
     *
     * @param e Event.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            String[] dataMusic = addSongToListView.getJlist().split(" - ");
            playlistManager.addSongToPlaylist(frameView.getIdUser(), Integer.parseInt(dataMusic[0]), nameMyPlaylist);
        }
    }

}
