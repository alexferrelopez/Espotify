package Business;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Àlex Ferre, Aroa García, Marti Rebollo, Sandra Corral y Sami Amin
 * @version 1.0
 */
public final class MusicPlayer {
    private static MusicPlayer musicPlayer = null;
    private static Clip clip;   //static or not makes no difference, it only makes it clearer. As only one clip exists in execution.
    private static long frameCount; //static or not mak es no difference, it only makes it clearer. As only one framecount in execution.

    /**
     * Constructor is private in order to make the class a Singleton.
     */
    private MusicPlayer() {
    }

    /**
     * Method to get the instance of the player, it is created the
     * first time, and passed if it has already been created
     *
     * @return unique instance of the player
     */
    public static MusicPlayer getInstance() {
        if (musicPlayer == null) {
            musicPlayer = new MusicPlayer();
        }
        return musicPlayer;
    }

    /**
     * Method to load new song into the player, it does not control whether one is already playing or not.
     *
     * @param filepath path to the file in question to be played.
     * @param listener listener to be linked with the controller. It is renewed every new song.
     */
    public void loadMusic(String filepath, LineListener listener) {
        try {
            File music = new File(filepath);

            if (music.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(music);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                frameCount = audioInputStream.getFrameLength();
                clip.addLineListener(listener);
            }
        } catch (UnsupportedAudioFileException e) {
            System.out.println("The audio file is not supported.");
        } catch (LineUnavailableException e) {
            System.out.println("The player is not available.");
        } catch (IOException e) {
            System.out.println("The element your trying to load doesn't do so.");
        }
    }

    /**
     * Method that opens a new clip to get the time of a song (in microseconds).
     *
     * @param path path to the file in question to be played.
     * @return lenght of the song in microseconds.
     */
    public long getSongTime(String path) {
        long time = 0;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip1 = AudioSystem.getClip();
            clip1.open(audioInputStream);
            time = clip1.getMicrosecondLength();
            clip1.close();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("The audio file is not supported.");
        } catch (LineUnavailableException e) {
            System.out.println("The player is not available.");
        } catch (IOException e) {
            System.out.println("The element your trying to load doesn't do so.");
        }
        return time;
    }

    /**
     * Method to start playing a song. A clip need to have been attached to a song to work properly.
     */
    public void startMusic() {
        clip.start();
    }

    /**
     * Method to end playing a song. A clip need to have been attached to a song to work properly.
     */
    public void endMusic() {
        if (clip != null) clip.close();
    }

    /**
     * Method to pause a song. A clip need to have been attached to a song to work properly.
     */
    public void stopMusic() {
        clip.stop();
    }

    /**
     * Method to get the total frames of a song. A clip need to have been attached to a song to work properly.
     */
    public long getFrameCount() {
        return frameCount;
    }

    /**
     * Method to get the total time of a song. A clip need to have been attached to a song to work properly.
     *
     * @return microseconds of lenght of the song being played.
     */
    public long getClipMicrosecondLength() {
        return clip.getMicrosecondLength();
    }

    /**
     * Method to check if a clip engaging in active I/O.
     *
     * @return true if it is active.
     */
    public boolean clipIsActive() {
        return clip.isActive();
    }

    /**
     * The clip has no line (song) attached to it.
     *
     * @return true if no song is attached.
     */
    public boolean clipIsNull() {
        return clip == null;
    }

    /**
     * Method to get the exact position of the song in microseconds.
     *
     * @return position of the song.
     */
    public long getMicrosecondPosition() {
        return clip.getMicrosecondPosition();
    }

    /**
     * Method to get the exact position of the song in number of frames.
     *
     * @return number of frames that have been played.
     */
    public int getClipFramePosition() {
        return clip.getFramePosition();
    }

    /**
     * Method to set the clip in a specific position.
     *
     * @param position frame desired to play from.
     */
    public void setClipFramePosition(int position) {
        clip.setFramePosition(position);
    }
}
