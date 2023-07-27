module main.scheduleapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens main.scheduleapp to javafx.fxml;
    exports main.scheduleapp;
    exports controller;
    opens controller to javafx.fxml;
}