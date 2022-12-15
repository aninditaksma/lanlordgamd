package controller;

import gamelogic.Audio;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class SettingViewController implements Initializable {


    @FXML private ProgressBar audiobar;
    @FXML private Button audioMinus;
    @FXML private Button audioPlus;

    @FXML private Button exitButton;
    @FXML private Button backButton;

    DoubleProperty audioValueProperty  = new SimpleDoubleProperty(0.54);

    //music
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
    public static MediaPlayer mediaPlayer;
    public Audio audio;

    //contructor
    public SettingViewController()
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        /// this should be removed and updated once the new music control is setup
        audiobar.progressProperty().bind(audioValueProperty);
        //musicbar.progressProperty().bind(musicValueProperty);

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

                audio.mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });

    }

    public void playMusic()
    {
        mediaPlayer.play();
    }

    public void pauseMusic()
    {
        mediaPlayer.pause();
    }

    public void resetMedia(){
        audio.mediaPlayer.seek(Duration.seconds(0));
    }

    // add timer function if needed

    @FXML
    public void audioMinusPushed () throws IOException
    {
        audioValueProperty.setValue(audioValueProperty.get()-0.1);

    }

    @FXML
    public void audioPlusPushed () throws IOException
    {
        audioValueProperty.setValue(audioValueProperty.get()+0.1);
    }

    //exit from setting screen
    @FXML public void exitButtonPushed() throws IOException{
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }

    //back
    @FXML public void backButtonPushed() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/MainMenu.fxml"));

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
