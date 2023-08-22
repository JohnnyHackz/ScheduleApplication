package main.scheduleapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 361, 297);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws Exception{
        //JDBC.openConnection();
        //launch(args);
        ResourceBundle rb = ResourceBundle.getBundle("language/loginPropPage", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("fr")){
            System.out.println(rb.getString("Login") + " " + rb.getString("again"));
        } else {System.out.println(rb.getString("Login") + " " + rb.getString("again"));
        }

        ;
        //JDBC.closeConnection();

    }
}