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
 * Vista per el login de l'usuari.
 */
public class LoginView extends JPanel {
    private static final int widthScreen = Toolkit.getDefaultToolkit().getScreenSize().width;          //Ancho pantalla
    private static final int heightScreen = Toolkit.getDefaultToolkit().getScreenSize().height;        //Alto pantalla
    private JPanel rectangle;             //panel central de la pantalla dividido en (7filas x 1columna) transparentes para organizar los componentes
    private JButton jbInicioSesion;       //boton para iniciar sesion
    private JButton jbToRegistro;         //boton para cambiar a la pantalla de registro
    private JTextField login;             //campo de texto para escribir el usuario o correo electrónico para iniciar sesion
    private JPasswordField contrasenya;       //campo de texto para escribir la contraseña

    /**
     * Inicialitza la vista
     */
    public LoginView() {
        runInicioSesion();
    }

    /**
     * Carrega els components de Swing.
     */
    private void runInicioSesion() {
        //Creamos un fondo de pantalla (imagen dividida en 3 columnas y jPanel para el centro de la pantalla)
        JImagePanel viewImagePanel = creaFondoVista();

        //Dividimos los componentes del jPanel central en 7 regiones.

        /*Como se añaden secuencialmente sirve para que ocupe la casilla 1 sin que
        parezca nada y la deje en transparente para que se vea el fondo. Deberiamos
        añadir otra linia de codigo igual para la casilla 7 pero no es necesario porque ya la deja libre por defecto*/
        rectangle.add(new JLabel(""));
        //El resto de casillas divididas en 5 secciones (casillas 2 a la 6)
        topView();
        upperView();
        centralView();
        bottomView();
        lowestView();

        //añadimos el jpanel en medio de la imagen
        viewImagePanel.add(new JLabel(""));
        viewImagePanel.add(rectangle);
        viewImagePanel.add(new JLabel(""));

        //definimos el layout de la propia clase y añadimos la imagen al panel
        setLayout(new BorderLayout());
        add(viewImagePanel);
    }

    /**
     * Crea un pop Up
     */
    public void createPopUp(String message) {
        JOptionPane.showMessageDialog(null, message, "Atención: " + "Error inicio sesión", JOptionPane.ERROR_MESSAGE, new NoScalingIcon(new ImageIcon(Url.urlAtencion)));
    }

    /**
     * Funcion que crea un JImagePanel con la imagen de fondo del tamaño de la pantalla del ordenador y la divide en 3 columnas (regiones)
     * Ademas crea el jPanel que situaremos en el centro de la pantalla y lo dividimos en 7 filas para añadir los componentes facilmente
     *
     * @return viewImagePanel
     */
    private JImagePanel creaFondoVista() {
        JImagePanel viewImagePanel = new JImagePanel(Url.urlBackground);
        viewImagePanel.setSize(widthScreen, heightScreen);
        viewImagePanel.setLayout(new GridLayout(1, 3));

        rectangle = new JPanel();
        rectangle.setOpaque(false);
        rectangle.setLayout(new GridLayout(7, 1));

        return viewImagePanel;
    }

    /**
     * Vista superior donde aparece el logo y nombre de la app
     */
    private void topView() {
        //Declaramos un panel con flow layout para que se pongan el logo y el nombre de la app una al lado de otra
        JPanel top = createJPanel("Flow Layout");
        //ponemos el fondo de la casilla en negro y hacemos un borde superior para que no quede pegado
        top.setBackground(Color.black);
        top.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        //Añadimos el titulo y logo
        JLabel titleAndLogo = new EJLabel("Espotyfai", 30, 255, 255, 255, 0, false);
        titleAndLogo.setIcon(new NoScalingIcon(new ImageIcon(Url.urlLogo)));
        top.add(titleAndLogo);

        //lo añadimos al jPanel central de la pantalla
        rectangle.add(top);
    }

    /**
     * Vista de arriba (casilla 3) donde aparece el slogan
     */
    private void upperView() {
        JPanel arriba = createJPanel("Flow Layout");
        arriba.setBackground(Color.black);
        JLabel slogan = new EJLabel("Love the songs you love.", 25, 255, 255, 255, 0, true);
        arriba.add(slogan);
        rectangle.add(arriba);
    }

    /**
     * Vista central donde estan los campos para introducir el login (correo o nombre de usuario) y la contraseña
     */
    private void centralView() {
        /*Creamos un JPanel para introducir los componentes JTextfield en formato
         BoxLayout para que salga uno debajo del otro y ponemos el fondo en negro de la casilla*/
        JPanel camposInicio = createJPanel("Box Layout");
        camposInicio.setBackground(Color.black);

        //JTextFields de login y contraseña
        login = new EJTextField(100, "Usuario/Correo electrónico", false, false, true, true);

        contrasenya = new EJPasswordField("Contraseña", 100, 0);
        contrasenya.setEchoChar((char) 0);

        //Añadimos los componentes al panel con un espacio entre ellos
        camposInicio.add(login);
        camposInicio.add(new JLabel(" "));
        camposInicio.add(contrasenya);

        //Añadimos el panel al JPanel dividido en 7 regiones (lo añadimos en la 4 casilla)
        rectangle.add(camposInicio);
    }

    /**
     * Vista inferior donde añadimos el boton para iniciar sesion (casilla 5)
     */
    private void bottomView() {
        JPanel abajo = createJPanel("Flow Layout");
        abajo.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        abajo.setBackground(Color.black);

        //boton para iniciar sesión
        jbInicioSesion = new EJButton("INICIAR SESIÓN", new NoScalingIcon(new ImageIcon(Url.urlButton)), true, false, false);
        //le añadimos una comanda para que inicie sesion
        jbInicioSesion.setActionCommand(Try.tryLOGIN);
        abajo.add(jbInicioSesion);
        rectangle.add(abajo);
    }

    /**
     * Vista la mas baja (casilla 6) donde aparece el texto para registrarse y el botón para cambiar de pantalla a la de registro
     */
    private void lowestView() {
        JPanel registro = createJPanel("Flow Layout");
        registro.setBorder(BorderFactory.createEmptyBorder(20, 0, 100, 0));
        registro.setBackground(Color.black);

        //Texto que pregunta si te quieres registrar
        JLabel jlbToRegistro = new EJLabel("¿No tienes cuenta? ", 15, 155, 155, 155, 0, false);
        //Boton para cambiar a la vista de registro
        jbToRegistro = new EJButton("Regístrate", false, false);
        ///le añadimos una comanda para que cambie a la vista de registro
        jbToRegistro.setActionCommand(Try.toREGISTER);

        registro.add(jlbToRegistro);
        registro.add(jbToRegistro);
        rectangle.add(registro);
    }


    /**
     * Configua un JPanel
     *
     * @param layout
     * @return
     */
    private JPanel createJPanel(String layout) {
        JPanel jPanel = new JPanel();
        jPanel.setOpaque(true);
        if (layout.equals("Flow Layout")) {
            jPanel.setLayout(new FlowLayout());
        } else if (layout.equals("Box Layout")) {
            jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        }
        return jPanel;
    }

    public List<JTextField> getProperties() {
        List<JTextField> objects = new ArrayList<>();
        objects.add(login);
        objects.add(contrasenya);
        return objects;
    }

    public void modifyMouseLogin(int i) {
        if (i == 0) {
            jbToRegistro.setForeground(new Color(252, 194, 208));
        } else {
            jbToRegistro.setForeground(new Color(255, 255, 255));
        }
    }

    /**
     * Añadimos la accion al boton de iniciar y al de cambiar de pantalla a la de registro
     *
     * @param listener
     */
    public void loginListener(EventListener listener) {
        jbToRegistro.addActionListener((ActionListener) listener);
        jbInicioSesion.addActionListener((ActionListener) listener);
        login.addFocusListener((FocusListener) listener);
        contrasenya.addFocusListener((FocusListener) listener);
        jbToRegistro.addMouseListener((MouseListener) listener);
    }

    /**
     * Aconseguir les dades
     *
     * @return Retorna les dades
     */
    public List<String> getData() {
        ArrayList<String> datos = new ArrayList<>();
        datos.add(login.getText().trim());
        datos.add(String.valueOf(contrasenya.getPassword()).trim());
        return datos;
    }

    /**
     * Modifica el focus del login
     *
     * @param s String amb el text
     */
    public void modifyFocusLogin(String s) {
        login.setText(s);
    }

    /**
     * Canvia el focus del password
     *
     * @param s string
     * @param i tipus.
     */
    public void modifyFocusPassword(String s, int i) {
        contrasenya.setText(s);
        if (i == 0) {
            contrasenya.setEchoChar(('*'));
        } else {
            contrasenya.setEchoChar((char) 0);
        }
    }
}