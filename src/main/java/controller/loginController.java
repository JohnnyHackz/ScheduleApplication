package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.scheduleapp.Alerts;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML private Button cancelButtonField;
    @FXML private Label locationText;
    @FXML private Label loginField;
    @FXML private TextField loginPageLocation;
    @FXML private Button loginPageLogin;
    @FXML private TextField loginPagePassword;
    @FXML private TextField loginPageUserName;
    @FXML private Label passwordField;
    @FXML private Label usernameField;


    @FXML
    public void loginPageCancelButton(ActionEvent event) throws IOException {
        // Your cancel button logic here
        Alerts.Alert("Exit", "Are you sure you would like to exit the program?");
        {
            System.exit(0);
        }
    }

    @FXML
    private void loginPageLoginButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Locale locale = Locale.getDefault();
            Locale.setDefault(locale);

            ZoneId zoneId = ZoneId.systemDefault();

            rb = ResourceBundle.getBundle("/language/loginPropPage", Locale.getDefault());
            usernameField.setText(rb.getString("Username"));
            passwordField.setText(rb.getString("Password"));
            locationText.setText(rb.getString("Location"));
            loginField.setText(rb.getString(String.valueOf(zoneId)));
            loginPageLogin.setText(rb.getString("Login"));
            cancelButtonField.setText(rb.getString("Exit"));


        } catch (MissingResourceException e) {
            System.out.println("Resource bundle file is missing: " + e);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}