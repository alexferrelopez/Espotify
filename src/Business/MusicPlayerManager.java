package Business;

import Presentation.Controllers.MusicPlayerController;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for music managar that controls the logic for the music playback.
 */
public class MusicPlayerManager {
    private final MusicPlayer musicPlayer;
    private final List<Music> queue;
    public boolean loopingItself = false;
    private boolean playingMusic = false;
    private boolean loopingList = false;
    private boolean singleSongMode = false;
    private int index;

    /**
     * Constructor for the MusicPlayerManager
     * Creates the music player.
     * Creates the list of songs.
     */
    public MusicPlayerManager() {
        musicPlayer = MusicPlayer.getInstance();
        queue = new ArrayList<>();
    }

    /**
     * Method to start the music.
     */
    public void play() {
        musicPlayer.startMusic();
    }

    /**
     * Method to pause the music.
     */
    public void pause() {
        musicPlayer.stopMusic();
    }

    /**
     * Method to stop music completely.
     */
    public void stop() {
        musicPlayer.endMusic();
        queue.clear();
    }

    /**
     * Method to calculate the next index in the queue. Accounts for Single mode
     * and Playlist mode.
     *
     * @param change change to be applied to the index that controls the queue.
     * @return string to know the status.
     */
    public String calcNextIndex(int change) {
        if (!loopingItself) {
            if (singleSongMode) {                         //MODO: CANCION UNICA
                if (index + change < 0) {                           //ESCENARIO 1: modo cancion unica y le da previous (se supone que no se puede)
                    index = 0;
                } else if (index + change == queue.size()) {        //ESCENARIO 2: modo cancion unica, se acaba la cancion o pasa a la siguiente (se supone que no se puede)
                    playingMusic = false;
                    return "END";
                }                                                   //NO HAY MAS CASOS, NUNCA HAY MAS DE 1 CANCION
            } else {                                              //MODO PLAYLIST
                if (index + change < 0) {                           //ESCENARIO 1: modo playlist, le da previous
                    index = 0;
                } else if (index + change == queue.size()) {         //ESCENARIO 2: modo playlist, le da a la siguiente o se acaba
                    if (loopingList) {                                //CASO 1: loop playlist activado, volvemos al inicio
                        index = 0;
                    } else {                                            //CASO 2: no hay loop, se acaba la lista, cerramos el player.
                        playingMusic = false;
                        return "END";
                    }
                } else {                                             //ESCENARIO 3: volvemos atras o adelante en la lista
                    index += change;
                }
            }
        }
        playingMusic = true;
        return "CONTINUE";
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true when there 1 or more elements in the queue.
     */
    public boolean isQueueFull() {
        return !queue.isEmpty();
    }

    /**
     * Method to start the music.
     *
     * @param desiredFrame desired frame to start the playback from.
     */
    public void toStartMusic(int desiredFrame) {
        if (desiredFrame >= musicPlayer.getFrameCount()) {
            desiredFrame = (int) musicPlayer.getFrameCount();
        }
        musicPlayer.setClipFramePosition(desiredFrame);
    }

    /**
     * Getter for the ID of the song playing.
     *
     * @return id.
     */
    public int getIDMusicPlaying() {
        return queue.get(index).getIdMusic();
    }

    /**
     * Gets the song time.
     *
     * @param path path of the song to get the total time of.
     * @return time in miliseconds.
     */
    public long getSongTime(String path) {
        return musicPlayer.getSongTime(path);
    }

    /**
     * Checks if there is a song ready for playback.
     *
     * @return true when active.
     */
    public boolean clipIsActive() {
        return musicPlayer.clipIsActive();
    }

    /**
     * Setter for LoopingItself.
     *
     * @param loopingItself true when looping.
     */
    public void setLoopingItself(boolean loopingItself) {
        this.loopingItself = loopingItself;
    }

    /**
     * Sets up a playlist to be played.
     *
     * @param musicList list of songs.
     * @param index     index to start from
     */
    public void setupPlaylist(List<Music> musicList, int index) {
        queue.clear();
        queue.addAll(musicList);
        this.index = index;
    }

    /**
     * Sets a song to be played
     *
     * @param music song to be played
     */
    public void setupSong(Music music) {
        queue.clear();
        queue.add(music);
        index = 0;
    }

    /**
     * Getter for the frame count.
     *
     * @return total number of frames.
     */
    public long getFrameCount() {
        return musicPlayer.getFrameCount();
    }

    /**
     * Getter for the microsecond position.
     *
     * @return position in microsec.
     */
    public long getMicrosecondPosition() {
        return musicPlayer.getMicrosecondPosition();
    }

    /**
     * Changes state of loopingItself.
     */
    public void changeLoopingItself() {
        loopingItself = !loopingItself;
    }

    /**
     * Checks if singleSongMode is active
     *
     * @return true when active
     */
    public boolean isSingleSongMode() {
        return singleSongMode;
    }

    /**
     * Sets single mode.
     *
     * @param singleSongMode single mode.
     */
    public void setSingleSongMode(boolean singleSongMode) {
        this.singleSongMode = singleSongMode;
    }

    /**
     * Changes state of loopingList.
     */
    public void changeLoopingList() {
        loopingList = !loopingList;
    }

    /**
     * Checks if loopingList is active.
     *
     * @return true when active.
     */
    public boolean isLoopingList() {
        return loopingList;
    }

    /**
     * Sets looping list mode.
     *
     * @param loopingList looping list mode.
     */
    public void setLoopingList(boolean loopingList) {
        this.loopingList = loopingList;
    }

    /**
     * Method to end the music.
     */
    public void endMusic() {
        musicPlayer.endMusic();
    }

    /**
     * Checks if is playing music.
     *
     * @return true when playing.
     */
    public boolean isPlayingMusic() {
        return playingMusic;
    }

    /**
     * Sets playing music boolean.
     *
     * @param playingMusic playing song.
     */
    public void setPlayingMusic(boolean playingMusic) {
        this.playingMusic = playingMusic;
    }

    /**
     * Method to load music.
     *
     * @param musicPlayerController controller to be linked to the player.
     */
    public void loadMusic(MusicPlayerController musicPlayerController) {
        musicPlayer.loadMusic(queue.get(index).getUrl(), musicPlayerController);
    }

    /**
     * Sets frame in the song
     *
     * @param frame frame to be set.
     */
    public void setMusicFrame(int frame) {
        if (frame >= musicPlayer.getFrameCount()) {
            frame = (int) musicPlayer.getFrameCount();
        }
        if (!musicPlayer.clipIsNull()) {
            musicPlayer.setClipFramePosition(frame);
        }
    }

    /**
     * Getter for the microsecond lenght of a song-
     *
     * @return lenght in microseconds.
     */
    public long getClipMicrosecondLength() {
        return musicPlayer.getClipMicrosecondLength();
    }

    /**
     * Getter for the frameposition
     *
     * @return position in frames.
     */
    public int getClipFramePosition() {
        return musicPlayer.getClipFramePosition();
    }

    /**
     * Getter for the name of the song playing.
     *
     * @return name of the song.
     */
    public String getNameMusicPlaying() {
        return queue.get(index).getName();
    }
}
