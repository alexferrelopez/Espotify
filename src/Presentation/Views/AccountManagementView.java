package Presentation.Views;

import Presentation.Try;
import Presentation.Url;
import Presentation.Views.CustomComponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;

/**
 * Vista de account management
 */
public class AccountManagementView extends JPanel {
    private JButton cierraSesion;
    private JButton eliminaCuenta;
    private JTextField loginConfirmRemove;
    private JTextField passwordConfirmRemove;

    /**
     * Inicialitza la vista
     */
    public AccountManagementView() {
        runGestionCuenta();
    }

    /**
     * Comença els componens de Swing
     */
    private void runGestionCuenta() {
        setLayout(new BorderLayout());
        centralView();
    }

    /**
     * crea un pop up
     */
    private void createPopUp() {
        loginConfirmRemove = new EJTextField(20, true, false, false, false);
        passwordConfirmRemove = new EJPasswordField(20, 10);

        JPanel fields = new JPanel();
        fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));

        JPanel loginConfirm = new JPanel();
        loginConfirm.add(new EJLabel("Usuario:        ", 12, 255, 255, 255, 0, true));
        loginConfirm.add(loginConfirmRemove);

        JPanel passwordConfirm = new JPanel();
        passwordConfirm.add(new EJLabel("Contraseña: ", 12, 255, 255, 255, 0, true));
        passwordConfirm.add(passwordConfirmRemove);

        fields.add(loginConfirm);
        fields.add(passwordConfirm);
        Try.optionDeleteUser = JOptionPane.showConfirmDialog(null, fields, "Para eliminar cuenta introduce tus credenciales:(", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, new NoScalingIcon(new ImageIcon(Url.urlAtencion)));
    }

    /**
     * Crea un altre pop up de error
     *
     * @param errorMessage missatge de error
     */
    public void createPopUpMessage(String errorMessage) {
        JPanel fields = new JPanel();
        fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));

        JPanel message = new JPanel();
        message.add(new EJLabel(errorMessage, 12, 255, 255, 255, 0, true));

        fields.add(message);
        JOptionPane.showConfirmDialog(null, fields, "Aviso!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, new NoScalingIcon(new ImageIcon(Url.urlAtencion)));
    }

    /**
     * Vista central.
     */
    private void centralView() {
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(new Color(20, 20, 20));

        JPanel gestionaCuenta = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gestionaCuenta.setOpaque(false);

        cierraSesion = new EJButton("Cerrar sesión", new NoScalingIcon(new ImageIcon(Url.urlBotonGenerico)), true, false, false);
        cierraSesion.setActionCommand(Try.tryCLOSE);
        eliminaCuenta = new EJButton("Eliminar cuenta", new NoScalingIcon(new ImageIcon(Url.urlBotonGenerico)), true, false, false);
        eliminaCuenta.setActionCommand(Try.tryREMOVE);

        gestionaCuenta.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 100));
        gestionaCuenta.add(cierraSesion);
        gestionaCuenta.add(eliminaCuenta);

        panelCentral.add(new LogoAndTextView("Gestiona tu cuenta...", 1), BorderLayout.NORTH);
        panelCentral.add(gestionaCuenta, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);
    }

    /**
     * Mostrar borrar un compte amb el pop up
     */
    public void showRequestRemoveAccount() {
        createPopUp();
    }

    /**
     * Registra vista al controller per els listeners.
     *
     * @param listener Event.
     */
    public void AccountManagementListener(EventListener listener) {
        cierraSesion.addActionListener((ActionListener) listener);
        eliminaCuenta.addActionListener((ActionListener) listener);
    }

    /**
     * Dades al borrar
     *
     * @return Retorna les dddes
     */
    public ArrayList<String> getDataDelete() {
        ArrayList<String> datos = new ArrayList<>();
        datos.add(loginConfirmRemove.getText().trim());
        datos.add(passwordConfirmRemove.getText().trim());
        return datos;
    }

}
