package main.scheduleapp;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * The Main class is the entry point for the Schedule Application.
 * It initializes and displays the login view and manages the JDBC connection.
 *
 * <p>It extends the JavaFX Application class.
 * </p>
 */
public class Main extends Application {

    /**
     * Overrides the start method from Application class.
     * Loads the login view and sets it on the primary stage.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     * @throws IOException if there is an issue loading the FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 361, 297);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method - the entry point of the application.
     * Opens a JDBC connection, launches the JavaFX application,
     * and then closes the JDBC connection once the application is finished.
     *
     * @param args command-line arguments (not used).
     * @throws Exception if any exception occurs during the execution.
     */
    public static void main(String[] args) throws Exception{
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();

    }
}