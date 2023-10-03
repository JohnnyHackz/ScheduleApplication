package controller;

import DAO.AppointmentDAO;
import DAO.AppointmentDAOImpl;
import helper.AlertHelper;
import helper.JDBC;
import javafx.collections.ObservableList;
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
import model.Appointment;
import model.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Controller for the login view of the application.
 * Handles user authentication and login process.
 */
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


    /**
     * Handles the cancellation of the login process.
     *
     * @param event the action event
     */
    @FXML
    public void loginPageCancelButton(ActionEvent event) {
        AlertHelper.Alert("Exit", "Are you sure you would like to exit the program?");
        System.exit(0);
    }

    /**
     * Handles the login button action. Validates user credentials and logs the user into the application.
     *
     * @param actionEvent the action event
     */
    @FXML
    private void loginPageLoginButton(ActionEvent actionEvent) {
        try {
            String username = loginPageUserName.getText();
            String password = loginPagePassword.getText();

            User validUser = loginQuery(username, password);

            if (validUser != null) {
                // Fetch all appointments for the logged-in user
                AppointmentDAO apptDAO = new AppointmentDAOImpl();
                ObservableList<Appointment> allAppointmentsForUser = apptDAO.getAllAppointmentsForUser(validUser.getUserId());

                // Check for upcoming appointments within the next 15 minutes locally, not using a DB query
                LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
                LocalDateTime limit = now.plusMinutes(15);
                List<Appointment> upcomingAppointments = new ArrayList<>();

                for(Appointment appt : allAppointmentsForUser) {
                    if(appt.getStartDtTime().isAfter(now) && appt.getStartDtTime().isBefore(limit)) {
                        upcomingAppointments.add(appt);
                    }
                }

                logUserActivity(username, true);  // log successful login attempt
                if (upcomingAppointments.isEmpty()) {
                    AlertHelper.Alert("Upcoming Appointments", "You currently have no appointments scheduled in the next 15 minutes.");
                } else {
                    Appointment firstUpcomingAppt = upcomingAppointments.get(0);
                    String alertMessage = String.format("You have an appointment scheduled within the next 15 minutes. ID: %d, Date: %s, Time: %s",
                            firstUpcomingAppt.getApptId(),
                            firstUpcomingAppt.getStartDate(),
                            firstUpcomingAppt.getStartTime());
                    AlertHelper.Alert("Appointment Alert", alertMessage);
                }
                // Continue to main appointment screen
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                AlertHelper.showLocalizedLoginError();
                logUserActivity(username, false);  // log failed login attempt
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Logs user activity (successful or unsuccessful login attempts) to a file.
     *
     * @param username the username for which activity is being logged
     * @param success  a boolean representing the success of the login attempt
     */
    private void logUserActivity(String username, boolean success) {
        try (FileWriter fw = new FileWriter("login_activity.txt", true); // true means append mode
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
            String logEntry = String.format("%s - User: %s, Login Attempt: %s%n", now.toString(), username, success ? "Successful" : "Failed");
            pw.println(logEntry);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Queries the database to authenticate a user.
     *
     * @param userName     the username to be authenticated
     * @param UserPassword the password corresponding to the username
     * @return a User object if authentication is successful, null otherwise
     */
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

                System.out.println("Match found in DB for user: " + user); //Debug information

                return new User(userId, user, pass);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            // Consider using a logger or more refined error handling here.
        }
        // No need for finally block since try-with-resources will handle the resource closure
        return null;
    }

    private PreparedStatement createPreparedStatement(Connection conn, String userName, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name=? AND Password=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setString(2, password);
        return ps;
    }

    /**
     * Initialization method for the loginController.
     * It sets the default language for the login page based on the system's locale settings.
     *
     * @param url an optional location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb  the resources used to localize the root object
     */
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        try {
            rb = ResourceBundle.getBundle("/language/loginPropPage", Locale.getDefault());
            loginPageLocation.setText(ZoneId.systemDefault().toString());

            usernameField.setText(rb.getString("Username"));
            passwordField.setText(rb.getString("Password"));
            locationText.setText(rb.getString("Location"));
            loginPageLogin.setText(rb.getString("Login"));
            cancelButtonField.setText(rb.getString("Exit"));

        } catch (MissingResourceException e) {
            System.out.println("Resource bundle file is missing: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
