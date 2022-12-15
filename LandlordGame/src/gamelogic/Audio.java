package gamelogic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Audio
{
    private File directory;
    private File[] files;

    private ArrayList<File> songs;
    private int songNumber;

    private Timer timer;
    private TimerTask task;
    private boolean running;

    @FXML
    private Button pauseButton;
    @FXML
    Slider volumeSlider;

    public Media media;
    public MediaPlayer mediaPlayer;

    public void Play()
    {
        songs = new ArrayList<File>();
        directory = new File("audio");
        files = directory.listFiles();

        if(files != null) {
            for (File file : files) {
                songs.add(file);
            }
        }
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void playMusic()
    {
        mediaPlayer.play();
    }

    public void pauseMusic()
    {
        mediaPlayer.pause();
    }
}
