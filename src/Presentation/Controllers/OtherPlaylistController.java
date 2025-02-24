package Presentation.Controllers;

import Business.PlaylistManager;
import Presentation.Try;
import Presentation.Views.FrameView;
import Presentation.Views.OtherPlaylistView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class OtherPlaylistController implements ActionListener, ListSelectionListener {
    private final FrameView frameView;
    private final OtherPlaylistView otherPlaylistView;
    private final PlaylistManager playlistManager;
    private final OtherMusicListController otherMusicListController;

    /**
     * Constructor del controller de altres playlists
     *
     * @param frameView                Vista dels frames del reproductor
     * @param otherPlaylistView        Vista de altres playlists
     * @param playlistManager          Manager de les playlists
     * @param otherMusicListController Controller de altre musica.
     */
    public OtherPlaylistController(FrameView frameView, OtherPlaylistView otherPlaylistView, PlaylistManager playlistManager, OtherMusicListController otherMusicListController) {
        this.frameView = frameView;
        this.otherPlaylistView = otherPlaylistView;
        this.playlistManager = playlistManager;
        this.otherMusicListController = otherMusicListController;
    }

    /**
     * Listener de accions
     *
     * @param e event
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Try.tryGestionCuenta.equals(e.getActionCommand())) {
            getAllPlaylists();
            frameView.changePanel(FrameView.changeToGestionCuentaView);
        }
    }

    /**
     * Funcio per els listeners de la JTable
     *
     * @param e event
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable listGeneralSongs = otherPlaylistView.getJtable();
        if (e.getValueIsAdjusting()) return;

        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        lsm.setValueIsAdjusting(true);
        if (!lsm.isSelectionEmpty()) {
            int selectedRow = lsm.getMinSelectionIndex();
            String namePlaylist = (String) listGeneralSongs.getValueAt(selectedRow, 1);

            getAllPlaylists();
            otherMusicListController.start(namePlaylist);
            frameView.changePanel(FrameView.changeToListOtherMusicView);
            lsm.clearSelection();
        }
    }

    /**
     * Funcio que ens permet obtenir totes les llistes
     */
    public void getAllPlaylists() {
        otherPlaylistView.updateListAllPlaylist(playlistManager.getAllPlaylists(frameView.getIdUser()));
    }
}
