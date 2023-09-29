package controller;

import helper.JDBC;
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
import model.User;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class loginController implements Initializable {

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
    public void loginPageCancelButton(ActionEvent event) {
        Alerts.Alert("Exit", "Are you sure you would like to exit the program?");
        System.exit(0);
    }

    @FXML
    private void loginPageLoginButton(ActionEvent actionEvent) {
        try {
            String username = loginPageUserName.getText();
            String password = loginPagePassword.getText();

            User validUser = loginQuery(username, password);

            if (validUser != null) {
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                Alerts.Alert("Login Failed", "Invalid username or password. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onActionResetUserPass(ActionEvent actionEvent) {
        // ... your logic to reset user password
    }

    public User loginQuery(String userName, String UserPassword) {
        Connection conn = null;
        try {
            System.out.println("Username: " + userName); // Debug print
            System.out.println("Password: " + UserPassword); // Debug print

            conn = JDBC.openConnection();
            String sql = "SELECT * FROM users WHERE User_Name=? AND Password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, UserPassword);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
                int userId = result.getInt("User_ID");
                String user = result.getString("User_Name");
                String pass = result.getString("Password");

                System.out.println("Match found in DB for user: " + user); // Debug print

                return new User(userId, user, pass);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (conn != null) {
                JDBC.closeConnection();
            }
        }
        return null;
    }



    public static LocalDateTime getLoginLDT() {
        return LocalDateTime.now(ZoneId.systemDefault());
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        try {
            rb = ResourceBundle.getBundle("/language/loginPropPage", Locale.getDefault());
            loginPageLocation.setText(ZoneId.systemDefault().toString());

            usernameField.setText(rb.getString("Username"));
            passwordField.setText(rb.getString("Password"));
            locationText.setText(rb.getString("Location"));
            loginField.setText(rb.getString(ZoneId.systemDefault().toString()));
            loginPageLogin.setText(rb.getString("Login"));
            cancelButtonField.setText(rb.getString("Exit"));

        } catch (MissingResourceException e) {
            System.out.println("Resource bundle file is missing: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
