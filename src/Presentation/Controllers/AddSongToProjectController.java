package Presentation.Controllers;

import Business.Music;
import Business.MusicManager;
import Business.SingerManager;
import Presentation.ErrorMessage;
import Presentation.Try;
import Presentation.Views.AddSongToProjectView;
import Presentation.Views.FrameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller per afegir una canço .wav al projecte. Listeners: ActionListener, FocusListener.
 *
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public class AddSongToProjectController implements ActionListener, FocusListener {
    private final FrameView frameView;
    private final AddSongToProjectView viewAddSong;
    private final MusicPlayerController musicPlayerController;
    private final HomeController homeController;
    private final MusicManager musicManager;
    private final SingerManager singerManager;
    private List<JTextField> objectes;
    private String path;

    /**
     * Constructor
     *
     * @param frameView             GeneralController de la practica
     * @param musicPlayerController Controlador del music player.
     * @param homeController        Home controller.
     * @param viewAddSong           Vista per afegir una canço.
     * @param musicManager          Manager de la musica.
     * @param singerManager         Manager del cantant.
     */
    public AddSongToProjectController(FrameView frameView, MusicPlayerController musicPlayerController, HomeController homeController, AddSongToProjectView viewAddSong, MusicManager musicManager, SingerManager singerManager) {
        this.frameView = frameView;
        this.musicPlayerController = musicPlayerController;
        this.homeController = homeController;
        this.viewAddSong = viewAddSong;
        this.musicManager = musicManager;
        this.singerManager = singerManager;
    }

    /**
     * Action listener per els diferents botons.
     *
     * @param e Event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Try.tryAddSong -> {
                if (checkFields() && (!musicManager.isValidSong(viewAddSong.getData().get(0)))) {
                    createMusic();
                    addDuration();
                    homeController.getAllMusic(); // Actualizar tabla
                    updateFields();
                    frameView.changePanel(FrameView.changeToMainGUIView);
                }
            }
            case Try.tryToFileChoose -> {
                //se llama desde aqui al file chooser
                viewAddSong.showRequestFileSong();
                if (viewAddSong.getData().get(5) != null) {
                    viewAddSong.setErrorText(viewAddSong.errorFile, "");
                    addSongToFile();
                }
            }
            case Try.tryGestionCuenta -> {
                updateFields();
                frameView.changePanel(FrameView.changeToGestionCuentaView);
            }
        }
    }

    /**
     * Funcio per a comprovar els camps si son correctes
     *
     * @return Si son correctes.
     */
    private boolean checkFields() {
        if (viewAddSong.getData().get(0) == null || "Título".equals(viewAddSong.getData().get(0))) {
            viewAddSong.setErrorText(viewAddSong.errorTitle, ErrorMessage.emptyField);
            return false;
        }
        if ("Género".equals(viewAddSong.getData().get(1))) {
            viewAddSong.setErrorText(viewAddSong.errorGender, ErrorMessage.emptyField);
            return false;
        }
        if ("Album".equals(viewAddSong.getData().get(2))) {
            viewAddSong.setErrorText(viewAddSong.errorAlbum, ErrorMessage.emptyField);
            return false;
        }
        if ("Autor".equals(viewAddSong.getData().get(3))) {
            viewAddSong.setErrorText(viewAddSong.errorSinger, ErrorMessage.emptyField);
            return false;
        }
        if (viewAddSong.getData().get(5) == null) {
            viewAddSong.setErrorText(viewAddSong.errorFile, ErrorMessage.fileSong);
            return false;
        }
        return true;
    }

    /**
     * Funcio per a crear un autor
     *
     * @param autorName Nom de l'autor
     * @return Id del cantant
     */
    private int crearAutor(String autorName) {
        if (!singerManager.verificationSinger(autorName)) {
            singerManager.addSinger(autorName);
        }
        return singerManager.getIdSinger(autorName);
    }

    /**
     * Afegir una canço al arxiu.
     */
    private void addSongToFile() {
        // File Path
        String filePath = viewAddSong.getData().get(4);
        boolean fileCreatedSuccessfully = musicManager.addSongToFile(filePath);
        /// Ver
        if (!fileCreatedSuccessfully) System.out.println("There was an error creating the file");
    }

    /**
     * Funcio per a crear musica.
     */
    private void createMusic() {
        List<String> song = viewAddSong.getData();
        // Comprobar que el autor existe o no si no existe te lo crea
        String name = song.get(0);
        String genre = song.get(1);
        String album = song.get(2);
        path = "FileSongs/" + song.get(5);

        int idOwner = frameView.getIdUser();

        int idSinger = crearAutor(song.get(3));

        // Crear cancion
        musicManager.addMusic(name, genre, album, path, idOwner, idSinger);
    }

    /**
     * Funcio per actualitzar els camps
     */
    public void updateFields() {
        viewAddSong.updateFields();
    }

    /**
     * Funcio per afegir duracio a la canço.
     */
    private void addDuration() {
        Music music = new Music();
        music.setUrl(path);
        music.setName(viewAddSong.getData().get(0));
        music.setDuration(musicPlayerController.getSongTime(music.getUrl()));
        musicManager.updateMusic(music.getName(), music.getDuration());
    }

    /**
     * Funcio per el focus dels diferents camps
     *
     * @param e Event.
     */
    @Override
    public void focusGained(FocusEvent e) {
        objectes = viewAddSong.getProperties();
        List<String> data = viewAddSong.getData();

        // Titel
        if (e.getSource() == objectes.get(0)) {
            if (data.get(0).isEmpty() || "Título".equals(data.get(0))) {
                viewAddSong.focusChange(1, viewAddSong.title, "Título");
            }
        }

        // Gender
        if (e.getSource() == objectes.get(1)) {
            if (data.get(1).isEmpty() || "Género".equals(data.get(1))) {
                viewAddSong.focusChange(1, viewAddSong.gender, "Género");
            }
        }

        // Album
        if (e.getSource() == objectes.get(2)) {
            if (data.get(2).isEmpty() || "Album".equals(data.get(2))) {
                viewAddSong.focusChange(1, viewAddSong.album, "Album");
            }
        }

        // Singer
        if (e.getSource() == objectes.get(3)) {
            if (data.get(3).isEmpty() || "Autor".equals(data.get(3))) {
                viewAddSong.focusChange(1, viewAddSong.singer, "Autor");
            }
        }
    }

    /**
     * Focus event lost per quan es canvia de objectiu
     *
     * @param e Event
     */
    @Override
    public void focusLost(FocusEvent e) {
        objectes = viewAddSong.getProperties();
        ArrayList<String> data = viewAddSong.getData();

        // Titel
        if (e.getSource() == objectes.get(0)) {
            if (data.get(0).isEmpty()) {
                viewAddSong.focusChange(0, viewAddSong.title, "Título");
            }
            if (musicManager.isValidSong(data.get(0))) {
                viewAddSong.setErrorText(viewAddSong.errorTitle, ErrorMessage.titelSong);
                return;
            } else {
                viewAddSong.setErrorText(viewAddSong.errorTitle, "");
            }
        }

        // Gender
        if (e.getSource() == objectes.get(1)) { //Contraseña
            if (data.get(1).isEmpty()) {
                viewAddSong.focusChange(0, viewAddSong.gender, "Género");
            }
            if ("Género".equals(viewAddSong.getData().get(1))) {
                viewAddSong.setErrorText(viewAddSong.errorGender, ErrorMessage.emptyField);
            } else {
                viewAddSong.setErrorText(viewAddSong.errorGender, "");
            }
        }

        // Album
        if (e.getSource() == objectes.get(2)) { //login
            if (data.get(2).isEmpty()) {
                viewAddSong.focusChange(0, viewAddSong.album, "Album");
            }
            if ("Album".equals(viewAddSong.getData().get(2))) {
                viewAddSong.setErrorText(viewAddSong.errorAlbum, ErrorMessage.emptyField);
            } else {
                viewAddSong.setErrorText(viewAddSong.errorAlbum, "");
            }
        }

        // Singer
        if (e.getSource() == objectes.get(3)) { // Confirm contraseña
            if (data.get(3).isEmpty()) {
                viewAddSong.focusChange(0, viewAddSong.singer, "Autor");
            }
            if ("Autor".equals(viewAddSong.getData().get(3))) {
                viewAddSong.setErrorText(viewAddSong.errorSinger, ErrorMessage.emptyField);
            } else {
                viewAddSong.setErrorText(viewAddSong.errorSinger, "");
            }
        }
    }
}
