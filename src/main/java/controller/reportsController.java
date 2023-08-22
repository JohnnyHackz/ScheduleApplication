package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class reportsController {
    @FXML
    private TableColumn reportsContactInformationType;
    @FXML
    private TableColumn reportsContactInformationDescription;
    @FXML
    private TableColumn reportsContactInformationLocation;
    @FXML
    private TableColumn reportsContactInformationStartDateTime;
    @FXML
    private TableColumn reportsContactInformationEndDateTime;
    @FXML
    private TableColumn reportsContactInformationCustomerID;
    @FXML
    private ComboBox reportsContactInformationContactsComboBox;
    @FXML
    private TableColumn reportsAppointmentInformationMonthAppoint;
    @FXML
    private TableColumn reportsAppointmentInformationTypeAppoint;
    @FXML
    private TableColumn reportsAppointmentInformationTotalAppoint;
    @FXML
    private TableColumn reportsDivisionInformationDivName;
    @FXML
    private TableColumn reportsDivisionInformationTotalCustomers;

    @FXML
    private TableColumn contactInformationID;
    @FXML
    private TableColumn reportsContactInformationTitle;

    Stage stage;
    Parent scene;

    public void onActionContactInformationTab(Event event) {
    }

    public void onActionReportsContactInformationComboBox(ActionEvent actionEvent) {
    }

    public void onActionAppointmentInformationTab(Event event) {
    }

    public void onActionDivisionInformationTab(Event event) {
    }

    public void onActionReportsBackButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }

    public void onActionReportsLogoutButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }
}
