package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML private Button exitButton;
    @FXML private ImageView mainMenuBackground;
    @FXML private Button startButton;
    @FXML private Button settingButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        mainMenuBackground.setImage(new Image("file:images/ui/start.jpg"));
    }

    @FXML public void startButtonPushed() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/GameScreen.fxml"));

        Stage deckViewWindow = (Stage) startButton.getScene().getWindow();
        deckViewWindow.setScene(new Scene(root));
    }

    // setting
    @FXML public void settingsButtonPushed() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/SettingView.fxml"));

        Stage deckViewWindow = (Stage) settingButton.getScene().getWindow();
        deckViewWindow.setScene(new Scene(root));
    }

    // exit
    @FXML public void exitButtonPushed() throws IOException
    {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }

}
