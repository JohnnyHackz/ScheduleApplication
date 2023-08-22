package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    Stage stage;
    Parent scene;

    @FXML
    private Button cancelButtonField;

    @FXML
    private Label locationText;

    @FXML
    private Label loginField;

    @FXML
    private TextField loginPageLocation;

    @FXML
    private Button loginPageLogin;

    @FXML
    private TextField loginPagePassword;

    @FXML
    private TextField loginPageUserName;

    @FXML
    private Label passwordField;

    @FXML
    private Label usernameField;

    @FXML
    void loginPageCancelButton(ActionEvent event) {

    }

    @FXML
    void loginPageLoginButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }

}

