package Presentation.Views;

import Presentation.Try;
import Presentation.Url;
import Presentation.Views.CustomComponents.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.EventListener;

/**
 * Vista de la barra de So.
 */
public class SoundbarView extends JPanel {
    private static final int widthScreen = Toolkit.getDefaultToolkit().getScreenSize().width;         //Ancho pantalla
    private static final int heightScreen = Toolkit.getDefaultToolkit().getScreenSize().height;       //Alto pantalla
    private JButton btn_playMusic;
    private JButton btn_pauseMusic;
    private JButton btn_stopMusic;
    private JButton btn_stopMusicDisabled;
    private JButton btn_loopMusic;
    private JButton btn_nextSong;
    private JButton btn_previousSong;
    private JButton btn_loopList;
    private JLabel tiempoTransc;
    private JLabel tiempoTotal;
    private EJSlider progressBar;
    private JLabel nombreCancion;
    private JButton btn_nextSongDisabled;
    private JButton btn_previousSongDisabled;
    private JButton btn_loopListDisabled;
    private JButton btn_loopMusicDisabled;

    /**
     * Inicia la vista de la barra de progrès.
     */
    public SoundbarView() {
        runSoundbar();
    }

    /**
     * Executa la soundbar
     */
    private void runSoundbar() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(new Color(20, 20, 20));
        setPreferredSize(new Dimension(widthScreen, 100));
        setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));

        JPanel infoCancion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoCancion.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        infoCancion.setOpaque(false);
        infoCancion.setPreferredSize(new Dimension(400, heightScreen));
        nombreCancion = new EJLabel("", 13, 255, 255, 255, 0, true);
        nombreCancion.setIcon(new NoScalingIcon(new ImageIcon(Url.urlAlbum)));

        infoCancion.add(nombreCancion);

        JPanel reproduccion = new JPanel(new BorderLayout());
        reproduccion.setOpaque(false);

        initializeButtons();

        JPanel interactReproduct = new JPanel(new FlowLayout(FlowLayout.CENTER));
        interactReproduct.setOpaque(false);
        interactReproduct.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        interactReproduct.add(btn_loopList);
        interactReproduct.add(btn_loopListDisabled);
        interactReproduct.add(btn_previousSong);
        interactReproduct.add(btn_previousSongDisabled);
        interactReproduct.add(btn_playMusic);
        interactReproduct.add(btn_pauseMusic);
        interactReproduct.add(btn_nextSongDisabled);
        interactReproduct.add(btn_nextSong);
        interactReproduct.add(btn_loopMusic);
        interactReproduct.add(btn_loopMusicDisabled);
        interactReproduct.add(btn_stopMusic);
        interactReproduct.add(btn_stopMusicDisabled);

        JPanel progressInfoCancion = new JPanel();
        progressInfoCancion.setOpaque(false);
        progressInfoCancion.setBorder(new EmptyBorder(0, 0, 10, 0));

        progressBar = new EJSlider(0, 100);

        progressBar.setPreferredSize(new Dimension(400, 20));
        progressBar.setOpaque(false);
        progressBar.setValue(0);
        progressBar.setUI(new CustomSliderUI(progressBar));

        btn_previousSong.setActionCommand(Try.toPreviousSong);
        btn_playMusic.setActionCommand(Try.toStartMusic);
        btn_pauseMusic.setActionCommand(Try.toPauseMusic);
        btn_stopMusic.setActionCommand(Try.toStopMusic);
        btn_nextSong.setActionCommand(Try.toNextSong);
        btn_loopMusic.setActionCommand(Try.toLoopSong);
        btn_loopList.setActionCommand(Try.toLoopList);

        tiempoTransc = new EJLabel("0:00", 12, 255, 255, 255, 0, true);
        tiempoTotal = new EJLabel("0:00", 12, 255, 255, 255, 0, true);

        progressInfoCancion.add(tiempoTransc);
        progressInfoCancion.add(progressBar);
        progressInfoCancion.add(tiempoTotal);

        JPanel east = new JPanel();
        east.setPreferredSize(new Dimension(400, heightScreen));
        east.setOpaque(false);

        reproduccion.add(interactReproduct, BorderLayout.NORTH);
        reproduccion.add(progressInfoCancion, BorderLayout.SOUTH);

        add(infoCancion, BorderLayout.WEST);
        add(reproduccion, BorderLayout.CENTER);
        add(east, BorderLayout.EAST);
    }

    /**
     * Metodó para iniciar todos los botones necesarios para la barra de reproducción.
     */
    private void initializeButtons() {
        btn_playMusic = new EJButton(false, false);
        btn_playMusic.setIcon(new NoScalingIcon(new ImageIcon(Url.urlPlay)));

        btn_pauseMusic = new EJButton(false, false);
        btn_pauseMusic.setIcon(new NoScalingIcon(new ImageIcon(Url.urlPause)));
        btn_pauseMusic.setVisible(false);

        btn_nextSong = new EJButton(false, false);
        btn_nextSong.setIcon(new NoScalingIcon(new ImageIcon(Url.urlSiguiente)));
        btn_nextSong.setVisible(false);

        btn_nextSongDisabled = new EJButton(false, false);
        btn_nextSongDisabled.setIcon(new NoScalingIcon(new ImageIcon(Url.urlSiguienteBlocked)));

        btn_previousSong = new EJButton(false, false);
        btn_previousSong.setIcon(new NoScalingIcon(new ImageIcon(Url.urlAtras)));
        btn_previousSong.setVisible(false);

        btn_previousSongDisabled = new EJButton(false, false);
        btn_previousSongDisabled.setIcon(new NoScalingIcon(new ImageIcon(Url.urlAtrasBlocked)));

        btn_stopMusic = new EJButton(false, false);
        btn_stopMusic.setIcon(new NoScalingIcon(new ImageIcon(Url.urlStop)));
        btn_stopMusic.setVisible(false);

        btn_stopMusicDisabled = new EJButton(false, false);
        btn_stopMusicDisabled.setIcon(new NoScalingIcon(new ImageIcon(Url.urlStopBlocked)));

        btn_loopMusic = new EJButton(false, false);
        btn_loopMusic.setIcon(new NoScalingIcon(new ImageIcon(Url.urlCancionRepete)));
        btn_loopMusic.setVisible(false);

        btn_loopMusicDisabled = new EJButton(false, false);
        btn_loopMusicDisabled.setIcon(new NoScalingIcon(new ImageIcon(Url.urlCancionRepeteBlocked)));

        btn_loopList = new EJButton(false, false);
        btn_loopList.setIcon(new NoScalingIcon(new ImageIcon(Url.urlListRepete)));
        btn_loopList.setVisible(false);

        btn_loopListDisabled = new EJButton(false, false);
        btn_loopListDisabled.setIcon(new NoScalingIcon(new ImageIcon(Url.urlListRepeteBlocked)));
    }

    /**
     * Listeners de la soundbar per registrarlos al seu controller
     *
     * @param eventListener Listener
     */
    //anteriormente se llamaba linkListeners
    public void soundbarListener(EventListener eventListener) {
        btn_previousSong.addActionListener((ActionListener) eventListener);
        btn_playMusic.addActionListener((ActionListener) eventListener);
        btn_pauseMusic.addActionListener((ActionListener) eventListener);
        btn_stopMusic.addActionListener((ActionListener) eventListener);
        btn_nextSong.addActionListener((ActionListener) eventListener);
        btn_loopMusic.addActionListener((ActionListener) eventListener);
        btn_loopList.addActionListener((ActionListener) eventListener);
        progressBar.addMouseListener((MouseListener) eventListener);
    }

    /**
     * Ensenyar la visibilitat del boto de play
     */
    public void showPlay() {
        btn_pauseMusic.setVisible(false);
        btn_playMusic.setVisible(true);
    }

    /**
     * visibilitzar el stop.
     */
    public void showStop() {
        btn_pauseMusic.setVisible(true);
        btn_playMusic.setVisible(false);
    }

    /**
     * Ensenyar botons desactivats.
     *
     * @param flag
     */
    public void showDisabledButtons(boolean flag) {
        btn_previousSongDisabled.setVisible(flag);
        btn_nextSongDisabled.setVisible(flag);
        btn_loopListDisabled.setVisible(flag);
        btn_nextSong.setVisible(!flag);
        btn_previousSong.setVisible(!flag);
        btn_loopList.setVisible(!flag);
        btn_loopMusic.setVisible(true);
        btn_loopMusicDisabled.setVisible(false);
        btn_stopMusic.setVisible(true);
        btn_stopMusicDisabled.setVisible(false);
    }

    /**
     * Ensenyar desactivats de stop.
     */
    public void showDisabledButtonsStop() {
        btn_previousSongDisabled.setVisible(true);
        btn_nextSongDisabled.setVisible(true);
        btn_loopListDisabled.setVisible(true);
        btn_loopMusicDisabled.setVisible(true);
        btn_stopMusicDisabled.setVisible(true);

        btn_nextSong.setVisible(false);
        btn_previousSong.setVisible(false);
        btn_loopList.setVisible(false);
        btn_loopMusic.setVisible(false);
        btn_stopMusic.setVisible(false);
    }

    /**
     * Posar valor a la progressbar
     *
     * @param progress Valor del progres
     */
    public void setValue(int progress) {
        progressBar.setValue(progress);
    }

    /**
     * Aconseguir el valor del slider
     *
     * @return Retorna el valor on ens trobem
     */
    public int getSliderValue() {
        return progressBar.getValue();
    }

    /**
     * Setejar el temps transcorregut
     *
     * @param tiempoTransc Retorna el temps transcorregut.
     */
    public void setTiempoTransc(String tiempoTransc) {
        this.tiempoTransc.setText(tiempoTransc);
    }

    /**
     * Setejar el temps total
     *
     * @param tiempoTotal temps a setejar
     */
    public void setTiempoTotal(String tiempoTotal) {
        this.tiempoTotal.setText(tiempoTotal);
    }

    /**
     * Activar el boto de loop
     */
    public void changeLoopSongIconToActive() {
        btn_loopMusic.setIcon(new NoScalingIcon(new ImageIcon(Url.urlCancionRepeteActivo)));
    }

    /**
     * Canviar boto de loop
     */
    public void changeLoopSongIconToDefault() {
        btn_loopMusic.setIcon(new NoScalingIcon(new ImageIcon(Url.urlCancionRepete)));
    }

    /**
     * Canviar al loop al default de la playlist
     */
    public void changeLoopPlaylistIconDefault() {
        btn_loopList.setIcon(new NoScalingIcon(new ImageIcon(Url.urlListRepete)));
    }

    /**
     * Canviar el loop de licone de la playlist
     */
    public void changeLoopPlaylistIconToActive() {
        btn_loopList.setIcon(new NoScalingIcon(new ImageIcon(Url.urlListRepeteActivo)));
    }

    /**
     * Actualitzar el titol de la musica
     *
     * @param name nom de la canço
     */
    public void updateMusicTitle(String name) {
        nombreCancion.setText(name);
    }

    /**
     * Resetejar el valor de la canço.
     */
    public void reset() {
        tiempoTotal.setText("0:00");
        nombreCancion.setText("");
    }
}
