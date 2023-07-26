module main.scheduleapp {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens main.scheduleapp to javafx.fxml;
    exports main.scheduleapp;
}