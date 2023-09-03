package main.scheduleapp;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerts {

    public static boolean Alert(String titleSet, String contentSet) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titleSet);
        alert.setHeaderText("Confirm");
        alert.setContentText(contentSet);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
}
