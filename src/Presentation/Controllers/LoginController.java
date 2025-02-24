package Presentation.Controllers;

import Business.UserManager;
import Presentation.Try;
import Presentation.Views.FrameView;
import Presentation.Views.LoginView;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

/**
 * Aquesta classe ens permet tenir un controller de el Login, implementa diferents listeners com el Action,Focus,Mouse.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class LoginController implements ActionListener, FocusListener, MouseListener {
    private final FrameView frameView;
    private final HomeController homeController;
    private final LoginView loginView;
    private final UserManager userManager;

    /**
     * Constructor de la classe
     *
     * @param frameView      Vista del reproductor.
     * @param homeController Home controller.
     * @param loginView      Vista del login.
     * @param userManager    Manager del usuari.
     */
    public LoginController(FrameView frameView, HomeController homeController, LoginView loginView, UserManager userManager) {
        this.frameView = frameView;
        this.homeController = homeController;
        this.loginView = loginView;
        this.userManager = userManager;
    }

    /**
     * Escolta els action listeners de la vista de login que seran els botons.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Try.toREGISTER -> {
                logout();
                frameView.changePanel(FrameView.changeToRegisterView);
            }
            case Try.tryLOGIN -> {
                List<String> datos = getLoginData();
                String isValidLogin = userManager.isValidLogin(datos);
                if (isValidLogin.equals("true")) {
                    // Guardar los datos del usuario para poder usarlos
                    userManager.setUser(datos.get(0));
                    if (userManager.getUser() != null) {
                        homeController.getAllMusic();
                        frameView.changePanel(FrameView.changeToMainGUIView);
                    }
                } else if (isValidLogin.equals("loginNotExist")) {
                    loginView.createPopUp("Tu usuario o correo electronic son incorrectas:(");
                } else if (isValidLogin.equals("passwordNotExisit")) {
                    loginView.createPopUp("Tu contraseña es incorrecta:(");
                } else {
                    loginView.createPopUp("No has introducido ningún dato:(");
                }
            }
        }
    }

    /**
     * Funcio que ens permet fer logout borrant la informacio anterior.
     */
    public void logout() {
        loginView.modifyFocusLogin("Usuario/Correo electrónico");
        loginView.modifyFocusPassword("Contraseña", 1);
    }

    /**
     * Funcio que ens permet tenir la informacio del login
     *
     * @return Retorna una llista de Strings.
     */
    private List<String> getLoginData() {
        return loginView.getData();
    }

    /**
     * Funcio que ens permet escoltar els listeners de focus per els camps de text
     *
     * @param e Listener Focus Event.
     */
    @Override
    public void focusGained(FocusEvent e) {
        List<JTextField> objectes = loginView.getProperties();
        List<String> info = loginView.getData();

        if (e.getSource() == objectes.get(1) && (info.get(1).isEmpty() || info.get(1).equals("Contraseña"))) {
            loginView.modifyFocusPassword("", 0);
        }
        if ((e.getSource() == objectes.get(0)) && (info.get(0).isEmpty() || info.get(0).equals("Usuario/Correo electrónico"))) {
            loginView.modifyFocusLogin("");
        }
    }

    /**
     * Funcio que ens permet detectar la perdua de focus de algun element. En aquest cas ens deixarà posar textos si esta buit.
     *
     * @param e Listener Focus Event.
     */
    @Override
    public void focusLost(FocusEvent e) {
        List<JTextField> objectes = loginView.getProperties();
        List<String> info = loginView.getData();
        if (e.getSource() == objectes.get(1)) {
            if (info.get(1).isEmpty()) {
                loginView.modifyFocusPassword("Contraseña", 1);
            }
        }
        if (e.getSource() == objectes.get(0)) {
            if (info.get(0).isEmpty()) {
                loginView.modifyFocusLogin("Usuario/Correo electrónico");
            }
        }
    }

    /**
     * No s'utilitza
     *
     * @param e Listener Mouse Event.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        loginView.modifyMouseLogin(0);
    }

    /**
     * No s'utilitza
     *
     * @param e Listener Mouse Event.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        loginView.modifyMouseLogin(1);
    }

    /**
     * No s'utilitza
     *
     * @param e Listener Mouse Event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * No s'utilitza
     *
     * @param e Listener Mouse Event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * No s'utilitza.
     *
     * @param e Listener Mouse Event.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

}

