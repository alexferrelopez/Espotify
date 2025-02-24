package Presentation.Views;

import Presentation.Try;
import Presentation.Url;
import Presentation.Views.CustomComponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Aquesta es la vista corresponent al proces de registrar-se.
 */
public class RegisterView extends JPanel {
    private static final int widthScreen = Toolkit.getDefaultToolkit().getScreenSize().width;         //Ancho pantalla
    private static final int heightScreen = Toolkit.getDefaultToolkit().getScreenSize().height;       //Alto pantalla
    public JTextField email;                //campo de texto para escribir el correo electrónico
    public JPasswordField contrasenya;          //campo de texto para escribir la contraseña
    public JPasswordField contrasenyaConfirm;   //campo de texto para escribir la confirmación de contraseña
    public JTextField login;                //campo de texto para escribir el login de usuario
    private JPanel rectangle;                //panel central de la pantalla dividido en (3filas x 1columna) negro para organizar los componentes
    private JButton jbToInicioSesion;        //boton para cambiar a la pantalla de inicio de sesion
    private JButton jbRegistro;              //boton para registrarse
    private JLabel errorEmail;              //texto error de email
    private JLabel errorLogin;              //texto error de login
    private JLabel errorContrasenya;        //texto error de contraseña
    private JLabel errorContrasenyaConfirm; //texto error de confirmación de contraseña

    public RegisterView() {
        runRegistro();
    }

    /**
     * Aquesta funcio inicia la vista, on va cridant les diferents funcions que corresponen a cada zona de la pantalla
     */
    private void runRegistro() {
        //Creamos un fondo de pantalla (imagen dividida en 3 columnas y jPanel para el centro de la pantalla)
        JImagePanel viewImagePanel = creaFondoVista();

        //Dividimos los componentes del jPanel negro central en 3 regiones (casillas 1-3)
        upperView();
        centralView();
        bottomView();

        //añadimos el jpanel con fondo negro en medio
        viewImagePanel.add(new JLabel(""));
        viewImagePanel.add(rectangle);
        viewImagePanel.add(new JLabel(""));

        //definimos el layout de la propia clase y añadimos la imagen al panel
        setLayout(new BorderLayout());
        add(viewImagePanel);
    }

    /**
     * Funcion que crea un JImagePanel con la imagen de fondo del tamaño de la pantalla del ordenador y la divide en 3 columnas (regiones)
     * Ademas crea el jPanel que situaremos en el centro de la pantalla y lo dividimos en 3 filas para añadir los componentes facilmente
     *
     * @return viewImagePanel
     */
    private JImagePanel creaFondoVista() {
        JImagePanel viewImagePanel = new JImagePanel(Url.urlBackground);
        viewImagePanel.setSize(widthScreen, heightScreen);
        viewImagePanel.setLayout(new GridLayout(1, 3));

        rectangle = new JPanel();
        rectangle.setBackground(Color.black);
        rectangle.setLayout(new GridLayout(3, 1));

        //viewImagePanel.add(rectangle);

        return viewImagePanel;
    }

    /**
     * Parte superior de la vista donde se añade el logo, nombre de la app y una frase informativa
     */
    private void upperView() {
        JPanel arriba = createJPanel("Box Layout", 0, 0, 0);

        //titulo
        JLabel title = new EJLabel("Espotyfai", 30, 255, 255, 255, 60, false);
        //frase informativa
        JLabel slogan = new EJLabel("<html><p>Regístrate para conseguir<br>una cuenta gratuita de<BR>Espotyfai.</p><html>", 25, 255, 255, 255, 60, true);

        //logo
        //title.setIcon(setIcon(urlLogo, 55, 55));
        title.setIcon(new NoScalingIcon(new ImageIcon(Url.urlLogo)));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        slogan.setAlignmentX(Component.CENTER_ALIGNMENT);

        arriba.add(title);
        arriba.add(slogan);
        rectangle.add(arriba);
    }

    /**
     * Parte de la vista donde se añaden los JTextField para los campos del registro
     */
    private void centralView() {
        JPanel camposRegistro = createJPanel("Box Layout", 0, 0, 0);

        email = new EJTextField(100, "Correo electrónico", false, false, true, false);
        // email.addFocusListener(this);

        contrasenya = new EJPasswordField("Crear una contraseña", 100, 10);
        //LISTENER
        //  contrasenya.addFocusListener(this);
        contrasenya.setEchoChar((char) 0);

        contrasenyaConfirm = new EJPasswordField("Comprobar contraseña", 100, 10);
        //LISTENER
        // contrasenyaConfirm.addFocusListener(this);
        contrasenyaConfirm.setEchoChar((char) 0);

        login = new EJTextField(100, "¿Cómo deberíamos llamarte?", false, false, true, false);
        //LISTENER

        //errores
        errorEmail = new EJLabel("", 12, 255, 255, 255, 60, false);
        errorLogin = new EJLabel("", 12, 255, 255, 255, 60, false);
        errorContrasenya = new EJLabel("", 12, 255, 255, 255, 60, false);
        errorContrasenyaConfirm = new EJLabel("", 12, 255, 255, 255, 60, false);

        //Paneles donde introducimos los errores
        JPanel panelErrorEmail = createJPanel("Flow Layout", 0, 0, 15);
        JPanel panelErrorContrasenya = createJPanel("Flow Layout", 0, 0, 0);
        JPanel panelErrorContrasenyaConfirm = createJPanel("Flow Layout", 0, 0, 120);
        JPanel panelErrorLogin = createJPanel("Flow Layout", 0, 0, 20);

        //añadimos los errores
        errorEmail.setText("");
        errorLogin.setText("");
        errorContrasenya.setText("");
        errorContrasenyaConfirm.setText("");

        panelErrorLogin.add(errorLogin);
        panelErrorEmail.add(errorEmail);
        panelErrorContrasenya.add(errorContrasenya);
        panelErrorContrasenyaConfirm.add(errorContrasenyaConfirm);

        //añadimos los campos
        camposRegistro.add(email);
        camposRegistro.add(panelErrorEmail);

        camposRegistro.add(contrasenya);
        camposRegistro.add(panelErrorContrasenya);

        camposRegistro.add(contrasenyaConfirm);
        camposRegistro.add(panelErrorContrasenyaConfirm);

        camposRegistro.add(login);
        camposRegistro.add(panelErrorLogin);

        rectangle.add(camposRegistro);
    }

    /**
     * Parte inferior del rectangulo donde se añade el boton para registrarse y se da la posibilidad de cambiar a la ventana de inicio de sesion
     */
    private void bottomView() {
        JPanel abajo = createJPanel("Box Layout", 0, 0, 0);

        //boton de registrarse
        JPanel panelContinuar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelContinuar.setOpaque(false);
        jbRegistro = new EJButton("CONTINUAR", new NoScalingIcon(new ImageIcon(Url.urlButton)), true, false, false);
        jbRegistro.setActionCommand(Try.tryREGISTER);


        JPanel inicio = createJPanel("Flow Layout", 50, 100, 0);

        //Frase para preguntar si quiere cambiar a la pantalla de inicio de sesion
        JLabel jlabelToInicioSesion = new EJLabel("¿Ya tienes Espotyfai? ", 15, 155, 155, 155, 60, false);
        //Boton para cambiar a la pantalla de inicio de sesion
        jbToInicioSesion = new EJButton("Inicia sesión", false, false);
        jbToInicioSesion.setActionCommand(Try.toLOGIN);


        panelContinuar.add(jbRegistro);

        inicio.add(jlabelToInicioSesion);
        inicio.add(jbToInicioSesion);
        abajo.add(panelContinuar);
        abajo.add(inicio);
        rectangle.add(abajo);
    }

    /**
     * Aquesta funcio ens permet crear JPanels customitzats amb diferents opcions
     *
     * @param layout       Tipus
     * @param topBorder    .
     * @param bottomBorder .
     * @param rightBorder  .
     * @return
     */
    private JPanel createJPanel(String layout, int topBorder, int bottomBorder, int rightBorder) {
        JPanel jPanel = new JPanel();
        jPanel.setOpaque(false);
        if (layout.equals("Flow Layout")) {
            jPanel.setBorder(BorderFactory.createEmptyBorder(topBorder, 0, bottomBorder, rightBorder));
        } else if (layout.equals("Box Layout")) {
            jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        }
        return jPanel;
    }

    /**
     * Funcio que ens permet canviar el boto de passar a registre que es posa de color rosa quan es  passsa per sobre.
     */
    public void mouseEntra() {
        jbToInicioSesion.setForeground(new Color(252, 194, 208));
    }

    /**
     * Funcio similar a mouseEntra() que torna a posar el color original al sortir de la opcio.
     */
    public void mouseSurt() {
        jbToInicioSesion.setForeground(new Color(255, 255, 255));
    }

    /**
     * Funcio que ens permet registrar els diferents listeners als controllers.
     *
     * @param listener Listeners que volem registrar
     */
    public void registerListener(EventListener listener) {
        login.addFocusListener((FocusListener) listener);
        email.addFocusListener((FocusListener) listener);
        contrasenya.addFocusListener((FocusListener) listener);
        contrasenyaConfirm.addFocusListener((FocusListener) listener);
        jbToInicioSesion.addActionListener((ActionListener) listener);
        jbRegistro.addActionListener((ActionListener) listener);
        jbToInicioSesion.addMouseListener((MouseListener) listener);
    }

    /**
     * Ens permet obtenir els camps de text emplenats per l'usuari.
     *
     * @return Retorna un arraylist de JTextField.
     */
    public List<JTextField> getProperties() {
        ArrayList<JTextField> objects = new ArrayList<>();
        objects.add(email);
        objects.add(login);
        objects.add(contrasenya);
        objects.add(contrasenyaConfirm);

        return objects;
    }

    /**
     * Ens permet obtenir els camps de text emplenats per l'usuari.
     *
     * @return Retorna un arraylist de JTextField.
     */
    public ArrayList<String> getData() {
        ArrayList<String> datos = new ArrayList<>();
        datos.add(email.getText().trim());
        datos.add(login.getText().trim());
        datos.add(String.valueOf(contrasenya.getPassword()).trim());
        datos.add(String.valueOf(contrasenyaConfirm.getPassword()).trim());

        return datos;
    }

    /**
     * Funcio referenciant al focus enviat desde el controller que em permet veure els errors
     *
     * @param i          TIpus de canvi
     * @param jTextField Quin text volem modificar
     * @param string     Frase que volem posar.
     */
    public void focusChange(int i, JTextField jTextField, String string) {
        if (i == 1) {
            jTextField.setText("");
        } else {
            jTextField.setText(string);
        }
    }

    /**
     * Un focus change per els passwords de la mateixa manera que el usuari.
     *
     * @param i              tipus
     * @param jPasswordField Camp de password
     * @param string         Frase que volem introduir.
     */
    public void focusChangePasswords(int i, JPasswordField jPasswordField, String string) {
        if (i == 1) {
            jPasswordField.setText("");
            jPasswordField.setEchoChar('*');
        } else {
            jPasswordField.setText(string);
            jPasswordField.setEchoChar((char) 0);
        }
    }

    /**
     * Setter del text de contrasenya error
     *
     * @param contrasenyaError String error de la contrasenya
     */
    public void setErrorPasswordText(String contrasenyaError) {
        errorContrasenya.setText(contrasenyaError);
    }

    /**
     * Setter del text de login
     *
     * @param loginError String error del login.
     */
    public void setErrorLoginText(String loginError) {
        errorLogin.setText(loginError);
    }

    /**
     * Setter de confirmar contrasenya per l'error
     *
     * @param loginError Tipus de error
     */
    public void setErrorConfirmPasswordText(String loginError) {
        errorContrasenyaConfirm.setText(loginError);
    }

    /**
     * Setter per ensenyar l'error de email
     *
     * @param mailError Missatge de error
     */
    public void setErrorMailText(String mailError) {
        errorEmail.setText(mailError);
    }

}
