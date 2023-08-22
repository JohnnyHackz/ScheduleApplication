package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class modifyAppointmentController implements Initializable {
    @FXML
    private AnchorPane modAppointmentStage;
    @FXML
    private TextField modAppointmentID;
    @FXML
    private Button modAppointmentSaveButton;
    @FXML
    private Button modAppointmentCancelButton;
    @FXML
    private DatePicker modAppointmentStartDate;
    @FXML
    private DatePicker modAppointmentEndDate;
    @FXML
    private ChoiceBox modAppointmentStartTime;
    @FXML
    private ChoiceBox modAppointmentEndTime;
    @FXML
    private ChoiceBox modAppointmentCustomerID;
    @FXML
    private ChoiceBox modAppointmentUserID;
    @FXML
    private ChoiceBox modAppointmentContact;
    @FXML
    private TextField modAppointmentLocation;
    @FXML
    private TextField modAppointmentTitle;
    @FXML
    private TextField modAppointmentType;
    @FXML
    private TextField modAppointmentDescription;

    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionModSaveButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionModCancelButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
