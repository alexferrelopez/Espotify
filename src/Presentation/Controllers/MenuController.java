package Presentation.Controllers;

import Presentation.Try;
import Presentation.Views.FrameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aquesta classe ens permet controllar el menu.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class MenuController implements ActionListener {

    private final FrameView frameView;
    private final HomeController homeController;
    private final AddSongToProjectController addSongToProjectController;
    private final MyPlaylistController myPlaylistController;
    private final OtherPlaylistController otherPlaylistController;
    private final StatisticsController statisticsController;


    /**
     * Constructor
     *
     * @param frameView                  Controlador general,
     * @param homeController             Constroller del home.
     * @param addSongToProjectController Controller de afegir canço.
     * @param myPlaylistController       Controller de la meva playlist.
     * @param otherPlaylistController    Controller de totes les playlist de los otros usuarios.
     * @param statisticsController       Controller de les estadistiques.
     */
    public MenuController(FrameView frameView, HomeController homeController, AddSongToProjectController addSongToProjectController,
                          MyPlaylistController myPlaylistController, OtherPlaylistController otherPlaylistController,
                          StatisticsController statisticsController) {
        this.frameView = frameView;
        this.homeController = homeController;
        this.addSongToProjectController = addSongToProjectController;
        this.myPlaylistController = myPlaylistController;
        this.otherPlaylistController = otherPlaylistController;
        this.statisticsController = statisticsController;
    }

    /**
     * Action listener
     *
     * @param e event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Try.tryInicio -> {
                homeController.getAllMusic();
                addSongToProjectController.updateFields();
                frameView.changePanel(FrameView.changeToMainGUIView);
            }
            case Try.tryEstadisticas -> {
                addSongToProjectController.updateFields();
                statisticsController.listGender();
                frameView.changePanel(FrameView.changeToEstadisticasView);
            }
            case Try.tryMisListas -> {
                myPlaylistController.getMyPlaylists();
                addSongToProjectController.updateFields();
                frameView.changePanel(FrameView.changeToMisListasView);
            }
            case Try.tryListasDeOtros -> {
                otherPlaylistController.getAllPlaylists();
                addSongToProjectController.updateFields();
                frameView.changePanel(FrameView.changeToListasDeOtrosUsuariosView);
            }
        }
    }
}
