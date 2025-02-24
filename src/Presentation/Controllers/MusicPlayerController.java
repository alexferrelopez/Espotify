package Presentation.Controllers;

import Business.Music;
import Business.MusicPlayerManager;
import Presentation.Try;
import Presentation.Views.SoundbarView;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * Controller for the music player and its view.
 */
public class MusicPlayerController implements ActionListener, MouseListener, LineListener {
    private final Timer playTimer;
    private final SoundbarView soundbarView;
    private final MusicPlayerManager musicPlayerManager;


    /**
     * Constructor for the class.
     * Links the view associated to it.
     * Creates a timer that updates the view every certain period to follow the music playback advancement.
     */
    public MusicPlayerController(SoundbarView soundbarView, MusicPlayerManager musicPlayerManager) {
        this.soundbarView = soundbarView;
        this.musicPlayerManager = musicPlayerManager;
        playTimer = new Timer(100, e -> updateState());
    }

    /**
     * Listener for all the buttons associated to the soundbar.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (musicPlayerManager.isQueueFull()) {
            switch (e.getActionCommand()) {
                case Try.toPreviousSong -> {
                    if (musicPlayerManager.getMicrosecondPosition() > 5000000) changeSong(0);
                    else changeSong(-1);
                }
                case Try.toStartMusic -> {
                    musicPlayerManager.toStartMusic(getDesiredFrame());
                    play();
                }
                case Try.toPauseMusic -> {
                    musicPlayerManager.setPlayingMusic(false);
                    pause();
                }
                case Try.toNextSong -> changeSong(1);
                case Try.toLoopSong -> {
                    musicPlayerManager.changeLoopingItself();
                    musicPlayerManager.setLoopingList(false);
                    if (musicPlayerManager.isSingleSongMode()) soundbarView.changeLoopPlaylistIconDefault();
                    if (musicPlayerManager.loopingItself) {
                        soundbarView.changeLoopSongIconToActive();
                    } else {
                        soundbarView.changeLoopSongIconToDefault();
                    }
                }
                case Try.toLoopList -> {
                    musicPlayerManager.setLoopingItself(false);
                    musicPlayerManager.changeLoopingList();
                    soundbarView.changeLoopSongIconToDefault();
                    if (musicPlayerManager.isLoopingList()) {
                        soundbarView.changeLoopPlaylistIconToActive();
                    } else {
                        soundbarView.changeLoopPlaylistIconDefault();
                    }
                }
                case Try.toStopMusic -> stop();
            }
        }
    }

    /**
     * Method to start the music, change the sprite in the view and start the playTimer.
     */
    private void play() {
        playTimer.start();
        musicPlayerManager.play();
        soundbarView.showStop();
    }

    /**
     * Method to pause the music, change the sprite in the view and pause the playTimer.
     */
    private void pause() {
        musicPlayerManager.pause();
        playTimer.stop();
        soundbarView.showPlay();
    }

    /**
     * Method to stop music completely, change the sprites in the view, clear the music queue and disable all buttons.
     */
    public void stop() {
        musicPlayerManager.stop();
        soundbarView.showPlay();
        soundbarView.reset();
        soundbarView.showDisabledButtonsStop();
    }

    /**
     * Method to change song. Ends previous song and, if the conditions allow it, continues playing the next song.
     *
     * @param change change to be applied to the index that controls the queue.
     */
    private void changeSong(int change) {
        String status = musicPlayerManager.calcNextIndex(change);

        if ("END".equals(status)) stop();

        musicPlayerManager.endMusic();

        if (musicPlayerManager.isPlayingMusic()) {
            musicPlayerManager.loadMusic(this);
            soundbarView.updateMusicTitle(musicPlayerManager.getNameMusicPlaying());
            play();
        }
    }

    /**
     * Method to play from now on in Single Mode.
     * Disables the buttons in the view.
     * Clears the queue.
     * Adds the song to be played to the queue.
     * Resets the index and changes song to the first in the list (0 change since index is 0 already).
     *
     * @param music song to be played.
     */
    public void playInSingleSongMode(Music music) {
        musicPlayerManager.setSingleSongMode(true);
        soundbarView.showDisabledButtons(true);
        musicPlayerManager.setupSong(music);
        changeSong(0);
    }

    /**
     * Method to play from now on in Playlist Mode.
     * Checks for empty lists.
     * Activates the buttons in the view.
     * Clears the queue.
     * Adds the songs to be played to the queue.
     * Changes index to the specified by parameter (0 change since index has to be the current one).
     *
     * @param musicList list of songs to be played
     * @param index     index of the song to be played from the list.
     */
    public void playPlaylist(List<Music> musicList, int index) {
        if (!musicList.isEmpty()) {
            musicPlayerManager.setSingleSongMode(false);
            soundbarView.showDisabledButtons(false);
            musicPlayerManager.setupPlaylist(musicList, index);
            changeSong(0);
        }
    }

    /**
     * Method used by the timer to update the progressBar according to the time passed in the song.
     * Resets it to 0 if no clip is active.
     */
    private void updateState() {
        if (musicPlayerManager.clipIsActive()) {
            int frame = musicPlayerManager.getClipFramePosition();
            int progress = (int) (((double) frame / (double) musicPlayerManager.getFrameCount()) * 100);
            soundbarView.setValue(progress);
            soundbarView.setTiempoTransc(formatMicroseconds(musicPlayerManager.getMicrosecondPosition()));
            soundbarView.setTiempoTotal(formatMicroseconds(musicPlayerManager.getClipMicrosecondLength()));
        } else {
            soundbarView.setValue(0);
            soundbarView.setTiempoTransc(formatMicroseconds(0));
            soundbarView.setTiempoTotal(formatMicroseconds(0));
        }
    }

    /**
     * Gets the id of the song being played.
     *
     * @return id of the song.
     */
    public int getIdMusicPlay() {
        if (musicPlayerManager.isQueueFull())
            return musicPlayerManager.getIDMusicPlaying();
        return -1;
    }

    /**
     * Method to format microseconds into a minute:second format.
     *
     * @param time amount of microseconds.
     * @return String formatted to min:sec.
     */
    private String formatMicroseconds(long time) {
        time = time / 1000;
        int minutes = (int) (time / (60 * 1000));
        int seconds = (int) ((time / 1000) % 60);
        return String.format("%d:%02d", minutes, seconds);
    }

    /**
     * Method to calculate the appropriate frame based on the value of the progressBar.
     *
     * @return frame of the music to be set to.
     */
    private int getDesiredFrame() {
        int progress = soundbarView.getSliderValue();
        double frame = ((double) musicPlayerManager.getFrameCount() * ((double) progress / 100.0));
        return (int) frame;
    }

    /**
     * Listener to change the progressBar to the desired position chosen when releasing the bar.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int frame = getDesiredFrame();
        musicPlayerManager.setMusicFrame(frame);
    }

    /**
     * @param event a line event that describes the change
     */
    @Override
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.STOP && event.getFramePosition() == musicPlayerManager.getFrameCount()) {
            musicPlayerManager.setPlayingMusic(false);
            changeSong(1);
        }
    }

    /**
     * Gets the song time formatted.
     *
     * @param path path of the song to get the total time of.
     * @return string containing the time in format mm:ss
     */
    public String getSongTime(String path) {
        return formatMicroseconds(musicPlayerManager.getSongTime(path));
    }

    /**
     * Ignored listener
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Ignored listener
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Ignored listener
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Ignored listener
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
