module main.scheduleapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    //requires mysql.connector.java;


    opens main.scheduleapp to javafx.fxml;
    exports main.scheduleapp;
    exports controller;
    opens controller to javafx.fxml;
    opens model to javafx.base;
}