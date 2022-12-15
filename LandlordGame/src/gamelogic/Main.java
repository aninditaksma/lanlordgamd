package gamelogic;


import controller.SettingViewController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application
{

    Button button;
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/MainMenu.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Fight the Landlord");
        primaryStage.setScene(scene);
        primaryStage.show();

//        SettingViewController settingViewController = new SettingViewController();
//        settingViewController.playMusic();
    }
}