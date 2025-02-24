package Presentation.Controllers;

import Business.UserManager;
import Presentation.ErrorMessage;
import Presentation.Try;
import Presentation.Views.FrameView;
import Presentation.Views.RegisterView;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Aquesta classe ens permet tenir un controller per a la vista de register inicial, implementara un action listener per els botons i un focus listener per a els camps i els seus errors.
 */
public class RegisterController implements ActionListener, FocusListener, MouseListener {

    private final FrameView frameView;
    private final HomeController homeController;
    private final RegisterView registerView;
    private final UserManager userManager;

    /**
     * Constructor de el register controller
     *
     * @param frameView      GeneralController
     * @param homeController HomeController
     * @param userManager    UserMaanger
     * @param registerView   Vista de registre.
     */
    public RegisterController(FrameView frameView, HomeController homeController, UserManager userManager, RegisterView registerView) {
        this.frameView = frameView;
        this.homeController = homeController;
        this.registerView = registerView;
        this.userManager = userManager;
    }

    /**
     * Action listener que ens permet tenir el control dels botons i realitzar accions
     *
     * @param e Event saltat.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Try.toLOGIN -> {
                logout();
                frameView.changePanel(FrameView.changeToLoginView);
            }
            case Try.tryREGISTER -> {
                if (checkFields()) {
                    String createUser = userManager.createUser(getRegisterData());
                    if (createUser.equals("true")) {
                        // Guardar los datos del usuario para poder usarlos
                        userManager.setUser(getRegisterData().get(0));
                        homeController.getAllMusic();
                        frameView.changePanel(FrameView.changeToMainGUIView);
                    }
                }
            }
        }
    }

    /**
     * Funcio que ens serveix per obtenir les dades de els JText
     *
     * @return Retorna una llista de Strings.
     */
    private List<String> getRegisterData() {
        return registerView.getData();
    }

    /**
     * Funcio que ens permet comprovar errors de camps
     *
     * @return Retorna si tots son correctes o si algun falla.
     */
    private boolean checkFields() {
        // Email
        if (getRegisterData().get(0) == null || getRegisterData().get(0).equals("Correo electrónico") ||
                (emailIsInvalid(getRegisterData().get(0))) || isExistUser(getRegisterData().get(0), "").equals("emailExist")) {
            return false;
        }

        //login
        if (getRegisterData().get(1) == null || getRegisterData().get(1).equals("¿Cómo deberíamos llamarte?") ||
                isExistUser("", getRegisterData().get(1)).equals("loginExist")) {
            return false;
        }

        //Contraseña
        if (getRegisterData().get(2) == null || getRegisterData().get(2).equals("¿Crear una contraseña?") ||
                (validPassword(getRegisterData().get(2)))) {
            return false;
        }

        // Confirm contraseña
        return getRegisterData().get(3) != null && !getRegisterData().get(3).equals("Comprobar contraseña") &&
                (isValidRepeatPassword(getRegisterData().get(2), getRegisterData().get(3)));
    }

    /**
     * Funcio que ens permet fer logout i que les dades que hi havien abans desapareguin.
     */
    public void logout() {
        registerView.focusChange(0, registerView.email, "Correo electrónico");
        registerView.focusChange(0, registerView.login, "¿Cómo deberíamos llamarte?");
        registerView.focusChange(0, registerView.login, "¿Cómo deberíamos llamarte?");
        registerView.focusChangePasswords(0, registerView.contrasenya, "Crear una contraseña");
        registerView.focusChangePasswords(0, registerView.contrasenyaConfirm, "Comprobar contraseña");
        registerView.setErrorMailText("");
        registerView.setErrorLoginText("");
        registerView.setErrorPasswordText("");
        registerView.setErrorConfirmPasswordText("");
    }

    /**
     * Funcio que comprova si el correu es valid sino mostra error
     *
     * @param email correu introduit per usuari
     * @return Retorna si es valid
     */
    private boolean emailIsInvalid(String email) {
        return !userManager.isAValidEmail(email);
    }

    /**
     * Funcio que comprova si l'usuari existeix
     *
     * @param email correu usuari
     * @param login nom usuari
     * @return retorna si existeix o no
     */
    private String isExistUser(String email, String login) {
        return userManager.isValidRegister(email, login);
    }

    /**
     * Funcio que ens permet comparar si la contrasenya introduida es igual  a la altre.
     *
     * @param password        Password inicial
     * @param passwordConfirm Confirmacio
     * @return Retorna si son iguals,.
     */
    private boolean isValidRepeatPassword(String password, String passwordConfirm) {
        return userManager.isValidRepeatPassword(password, passwordConfirm);
    }

    /**
     * Funcio que determina si el password es valid o no
     *
     * @param password Password introduit per l'usuari
     * @return Retorna si el password es valid.
     */
    private boolean validPassword(String password) {
        return !userManager.isValidPassword(password);
    }

    /**
     * Ens permet escoltar els listeners de focus i fer determinades coses en aquest cas fa desapareixer els textos si tenen el predeterminat i errors
     *
     * @param e Event
     */
    @Override
    public void focusGained(FocusEvent e) {
        List<JTextField> objectes = registerView.getProperties();
        List<String> data = registerView.getData();
        if (e.getSource() == objectes.get(0)) {
            if (data.get(0).isEmpty() || data.get(0).equals("Correo electrónico")) {
                registerView.focusChange(1, registerView.email, "Correo electrónico");
            }
        }

        if (e.getSource() == objectes.get(1)) {
            if (data.get(1).isEmpty() || data.get(1).equals("¿Cómo deberíamos llamarte?")) {
                registerView.focusChange(1, registerView.login, "¿Cómo deberíamos llamarte?");
            }
        }

        if (e.getSource() == objectes.get(2)) {
            if (data.get(2).isEmpty() || data.get(2).equals("Crear una contraseña")) {
                registerView.focusChangePasswords(1, registerView.contrasenya, "Crear una contraseña");
            }

        }

        if (e.getSource() == objectes.get(3)) {
            if (data.get(3).isEmpty() || data.get(3).equals("Comprobar contraseña")) {
                registerView.focusChangePasswords(1, registerView.contrasenyaConfirm, "Comprobar contraseña");
            }
        }
    }

    /**
     * Funcio similar a la de focus gained pero si perd el focus mostra errors o el missatge de abans.
     *
     * @param e Escolta.
     */
    @Override
    public void focusLost(FocusEvent e) {
        List<JTextField> objectes = registerView.getProperties();
        ArrayList<String> data = registerView.getData();
        if (e.getSource() == objectes.get(0)) { // Email
            if (data.get(0).isEmpty()) {
                registerView.focusChange(0, registerView.email, "Correo electrónico");
            }
            if (!data.get(0).isEmpty() && emailIsInvalid(data.get(0))) {
                //
                registerView.setErrorMailText(ErrorMessage.mailError);
            } else if (!data.get(0).isEmpty() && isExistUser(data.get(0), "").equals("emailExist")) {
                registerView.setErrorMailText(ErrorMessage.emailExist);
            } else {
                registerView.setErrorMailText("");
            }
        }

        if (e.getSource() == objectes.get(1)) { //login
            if (data.get(1).isEmpty()) {
                registerView.focusChange(0, registerView.login, "¿Cómo deberíamos llamarte?");
            }
            if (isExistUser("", data.get(1)).equals("loginExist") && !data.get(1).isEmpty()) {
                registerView.setErrorLoginText(ErrorMessage.loginError);
            } else {
                registerView.setErrorLoginText("");
            }
        }

        if (e.getSource() == objectes.get(2)) { //Contraseña
            if (data.get(2).isEmpty()) {
                registerView.focusChangePasswords(0, registerView.contrasenya, "Crear una contraseña");
            }
            if (validPassword(data.get(2)) && !data.get(2).isEmpty()) {
                registerView.setErrorPasswordText(ErrorMessage.contrasenyaError);
            } else {
                registerView.setErrorPasswordText("");
            }
        }

        if (e.getSource() == objectes.get(3)) { // Confirm contraseña
            if (data.get(3).isEmpty()) {
                registerView.focusChangePasswords(0, registerView.contrasenyaConfirm, "Comprobar contraseña");
            }
            if (!isValidRepeatPassword(data.get(2), data.get(3)) && !data.get(3).isEmpty()) {
                registerView.setErrorConfirmPasswordText(ErrorMessage.confirmContrasenyaError);
            } else {
                registerView.setErrorConfirmPasswordText("");
            }
        }
    }

    /**
     * No s'utilitza
     *
     * @param e .
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * No s'utilitza
     *
     * @param e .
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * No s'utilitza
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * S'utilitza per iluminar el boto de anar a login.
     *
     * @param e Evento de mouse.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        registerView.mouseEntra();
    }

    /**
     * S'utilitza per tornar al color original el boto.
     *
     * @param e Evento de mouse
     */
    @Override
    public void mouseExited(MouseEvent e) {
        registerView.mouseSurt();
    }

}
