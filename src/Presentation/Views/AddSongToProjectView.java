package Presentation.Views;

import Presentation.Try;
import Presentation.Url;
import Presentation.Views.CustomComponents.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import static Presentation.Views.CustomComponents.FileChooser.windowsJFileChooser;

/**
 * Vista per afegir una canço al Projecte
 */
public class AddSongToProjectView extends JPanel {
    public JTextField title;
    public JTextField gender;
    public JTextField album;
    public JTextField singer;
    public JLabel errorTitle;              //texto error de titel
    public JLabel errorGender;              //texto error
    public JLabel errorAlbum;              //texto error
    public JLabel errorSinger;              //texto error
    public JLabel errorFile;              //texto error
    private JButton addButton;
    private String filePath;
    private String fileName;
    private JButton fileChooser;
    private JButton gestionCuenta;

    /**
     * Inicialitza la vista
     */
    public AddSongToProjectView() {
        runAddSong();
    }

    /**
     * Components de Swing starting...
     */
    private void runAddSong() {
        setLayout(new BorderLayout());
        centralView();
    }

    /**
     * Vista central.
     */
    private void centralView() {
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(new Color(20, 20, 20));

        gestionCuenta = new EJButton("Gestión de cuenta", new NoScalingIcon(new ImageIcon(Url.urlBotonGenerico)), true, false, true);
        gestionCuenta.setActionCommand(Try.tryGestionCuenta);

        JPanel norte = new JPanel(new GridLayout(1, 2));
        norte.setOpaque(false);
        norte.setPreferredSize(new Dimension(0, 200));

        JPanel gestionYbusqueda = new JPanel();
        gestionYbusqueda.setLayout(new BorderLayout());
        gestionYbusqueda.setOpaque(false);

        //centro
        JPanel centro = new JPanel(new GridLayout(1, 3));
        centro.setOpaque(false);

        JPanel panelAddMusica = new JPanel(new BorderLayout());

        JPanel centralViewCenter = centralViewCenter();

        gestionYbusqueda.add(gestionCuenta, BorderLayout.NORTH);

        norte.add(new LogoAndTextView("Añade una canción...", 1));
        norte.add(gestionYbusqueda);

        panelAddMusica.add(centralViewCenter, BorderLayout.CENTER);

        centro.add(new JLabel(""));
        centro.add(panelAddMusica);
        centro.add(new JLabel(""));

        panelCentral.add(norte, BorderLayout.NORTH);
        panelCentral.add(centro, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);
    }

    /**
     * centre de la central view
     *
     * @return el JPanel
     */
    private JPanel centralViewCenter() {
        JPanel centrarCampos = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centrarCampos.setBackground(new Color(20, 20, 20));
        centrarCampos.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JPanel camposMusica = new JPanel();
        camposMusica.setLayout(new BoxLayout(camposMusica, BoxLayout.Y_AXIS));
        camposMusica.setOpaque(false);

        title = new EJTextField(50, "Título", false, true, false, false);
        gender = new EJTextField(50, "Género", false, true, false, false);
        album = new EJTextField(50, "Album", false, true, false, false);
        singer = new EJTextField(50, "Autor", false, true, false, false);


        JPanel panelErrorTitulo = createJPanelError();
        JPanel panelErrorGenero = createJPanelError();
        JPanel panelErrorAlbum = createJPanelError();
        JPanel panelErrorAutor = createJPanelError();


        errorTitle = new EJLabel("", 12, 255, 255, 255, 0, true);
        errorGender = new EJLabel("", 12, 255, 255, 255, 0, true);
        errorAlbum = new EJLabel("", 12, 255, 255, 255, 0, true);
        errorSinger = new EJLabel("", 12, 255, 255, 255, 0, true);


        errorTitle.setText("");
        errorGender.setText("");
        errorAlbum.setText("");
        errorSinger.setText("");

        JPanel fileChooserYError = new JPanel();
        fileChooserYError.setLayout(new BoxLayout(fileChooserYError, BoxLayout.Y_AXIS));
        fileChooserYError.setOpaque(false);

        JPanel centrarFileChooser = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centrarFileChooser.setOpaque(false);

        JPanel panelErrorFile = createJPanelError();

        JPanel panelAdd = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelAdd.setOpaque(false);


        fileChooser = new EJButton(" Escoge una canción aquí", new NoScalingIcon(new ImageIcon(Url.urlFile)), false, false, false);
        fileChooser.setFont(new Font("Apple Casual", Font.BOLD, 13));
        fileChooser.setActionCommand(Try.tryToFileChoose);

        errorFile = new EJLabel("", 12, 255, 255, 255, 0, true);
        errorFile.setText("");

        addButton = new EJButton("Añadir", new NoScalingIcon(new ImageIcon(Url.urlBotonGenerico)), true, false, true);
        addButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        addButton.setActionCommand(Try.tryAddSong);

        centrarFileChooser.add(fileChooser);
        panelErrorFile.add(errorFile);
        panelAdd.add(addButton);

        fileChooserYError.add(centrarFileChooser);
        fileChooserYError.add(panelErrorFile);


        panelErrorTitulo.add(errorTitle);
        panelErrorGenero.add(errorGender);
        panelErrorAlbum.add(errorAlbum);
        panelErrorAutor.add(errorSinger);

        camposMusica.add(title);
        camposMusica.add(panelErrorTitulo);
        camposMusica.add(gender);
        camposMusica.add(panelErrorGenero);
        camposMusica.add(album);
        camposMusica.add(panelErrorAlbum);
        camposMusica.add(singer);
        camposMusica.add(panelErrorAutor);
        camposMusica.add(fileChooserYError);
        camposMusica.add(panelAdd);

        centrarCampos.add(camposMusica);
        return centrarCampos;
    }

    /**
     * Crear error del JPanel
     *
     * @return Retorna el Panell.
     */
    private JPanel createJPanelError() {
        JPanel jPanel = new JPanel();
        jPanel.setOpaque(false);
        jPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        jPanel.setPreferredSize(new Dimension(30, 20));
        return jPanel;
    }

    /**
     * Crea un pop up
     */
    private void createPopUp() {
        JFileChooser jFileChooser = windowsJFileChooser(new JFileChooser());
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Archivos wav (.wav)", "wav"));
        jFileChooser.setAcceptAllFileFilterUsed(false);
        int result = jFileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
            fileName = selectedFile.getName();
            filePath = selectedFile.getPath();
        }
    }

    /**
     * demanar l'arxiu de la canço.
     */
    public void showRequestFileSong() {
        createPopUp();
    }

    /**
     * Aconseguir les propietats
     *
     * @return Propietats
     */
    public java.util.List<JTextField> getProperties() {
        List<JTextField> objects = new ArrayList<>();
        objects.add(title);
        objects.add(gender);
        objects.add(album);
        objects.add(singer);

        return objects;
    }

    /**
     * Canvide focus
     *
     * @param i          Tipus
     * @param jTextField Text
     * @param string     String
     */
    public void focusChange(int i, JTextField jTextField, String string) {
        if (i == 1) {
            jTextField.setText("");
        } else {
            jTextField.setText(string);
        }
    }

    /**
     * Setejar l'error de text
     *
     * @param jLabel    Label
     * @param errorText error del text
     */
    public void setErrorText(JLabel jLabel, String errorText) {
        jLabel.setText(errorText);
    }

    /**
     * Actualitzar els camps.
     */
    public void updateFields() {
        title.setText("Título");
        gender.setText("Género");
        album.setText("Album");
        singer.setText("Autor");
    }

    /**
     * Aconseguir les dades
     *
     * @return retorna les dades
     */
    public ArrayList<String> getData() {
        ArrayList<String> addSong = new ArrayList<>();
        addSong.add(title.getText().trim());
        addSong.add(gender.getText().trim());
        addSong.add(album.getText().trim());
        addSong.add(singer.getText().trim());
        addSong.add(filePath);
        addSong.add(fileName);
        return addSong;
    }

    /**
     * registrar controller a la vista per els listeners
     *
     * @param listener Event
     */
    public void addSongListener(EventListener listener) {
        title.addFocusListener((FocusListener) listener);
        gender.addFocusListener((FocusListener) listener);
        album.addFocusListener((FocusListener) listener);
        singer.addFocusListener((FocusListener) listener);
        addButton.addActionListener((ActionListener) listener);
        fileChooser.addActionListener((ActionListener) listener);
        gestionCuenta.addActionListener((ActionListener) listener);
    }
}
