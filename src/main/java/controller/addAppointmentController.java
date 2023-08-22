package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class addAppointmentController {

    @FXML
    private Button addAppointmentCancelButton;

    @FXML
    private ChoiceBox<?> addAppointmentContact;

    @FXML
    private ChoiceBox<?> addAppointmentCustomerID;

    @FXML
    private TextField addAppointmentDescription;

    @FXML
    private DatePicker addAppointmentEndDate;

    @FXML
    private ChoiceBox<?> addAppointmentEndTime;

    @FXML
    private TextField addAppointmentID;

    @FXML
    private TextField addAppointmentLocation;

    @FXML
    private Button addAppointmentSaveButton;

    @FXML
    private DatePicker addAppointmentStartDate;

    @FXML
    private ChoiceBox<?> addAppointmentStartTime;

    @FXML
    private TextField addAppointmentTitle;

    @FXML
    private TextField addAppointmentType;

    @FXML
    private ChoiceBox<?> addAppointmentUserID;

    @FXML
    private AnchorPane addAppointmentView;
    Stage stage;
    Parent scene;

    @FXML
    void onActionAddAppointmentCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddAppointmentSaveButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}