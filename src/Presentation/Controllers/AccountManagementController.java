package Presentation.Controllers;

import Business.Music;
import Business.MusicManager;
import Business.UserManager;
import Presentation.Try;
import Presentation.Views.AccountManagementView;
import Presentation.Views.FrameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que ens permet manegar el nostre compte Listeners: ActionListener
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class AccountManagementController implements ActionListener {
    private final FrameView frameView;
    private final LoginController loginController;
    private final RegisterController registerController;
    private final MusicPlayerController musicPlayerController;
    private final AccountManagementView accountManagementView;
    private final UserManager userManager;
    private final MusicManager musicManager;

    /**
     * Constructor de aquest controller
     *
     * @param frameView             General controller
     * @param loginController       Controller del login
     * @param registerController    Controller del registre.
     * @param musicPlayerController Controller del music Player.
     * @param accountManagementView Vista de management account
     * @param userManager           Manager usuaris
     * @param musicManager          Music manager
     */
    public AccountManagementController(FrameView frameView, LoginController loginController, RegisterController registerController,
                                       MusicPlayerController musicPlayerController, AccountManagementView accountManagementView, UserManager userManager, MusicManager musicManager) {
        this.frameView = frameView;
        this.loginController = loginController;
        this.registerController = registerController;
        this.musicPlayerController = musicPlayerController;
        this.accountManagementView = accountManagementView;
        this.userManager = userManager;
        this.musicManager = musicManager;

    }

    /**
     * Listener de accio per canviar entre pantalles
     *
     * @param e Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Try.tryCLOSE -> {
                logout();
                frameView.changePanel(FrameView.changeToLoginView);
            }
            case Try.tryREMOVE -> {
                accountManagementView.showRequestRemoveAccount();
                if (Try.optionDeleteUser == 0) {
                    ArrayList<String> datos = accountManagementView.getDataDelete();
                    if (delete(datos.get(0), datos.get(1))) {
                        logout();
                        frameView.changePanel(FrameView.changeToLoginView);
                    }
                }
            }
        }
    }

    /**
     * Funcio que ens permet fer logout
     */
    private void logout() {
        loginController.logout();
        registerController.logout();
        userManager.logout();
        musicPlayerController.stop();
    }

    /**
     * Funcio que ens permet eliminar el nostre compte
     *
     * @param userData Dades del usuari
     * @param password Password del usuari
     * @return retorna si l'ha pogut borrar
     */
    private boolean delete(String userData, String password) {
        List<String> musicListPath = musicListPath();
        if (userManager.deleteUser(userData, password)) {
            musicManager.deleteAllSongFile(musicListPath);
            accountManagementView.createPopUpMessage("Cuenta eliminada correctamente");
            return true;
        } else {
            accountManagementView.createPopUpMessage("Error al introducir los datos");
            return false;
        }
    }

    /**
     * Path de la llista de musica
     *
     * @return Retorna la llista
     */
    private List<String> musicListPath() {
        List<String> listPath = new ArrayList<>();
        for (Music music : musicManager.getAllMusic()) {
            if (music.getIdOwner() == userManager.getIdUser()) {
                listPath.add(music.getUrl());
            }
        }
        return listPath;
    }
}


