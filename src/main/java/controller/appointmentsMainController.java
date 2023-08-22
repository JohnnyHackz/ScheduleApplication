package controller;

import DAO.AppointmentDetailsDOA;
import helper.JDBC;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.sql.SQLException;

public class appointmentsMainController {


    Stage stage;
    Parent scene;
    @FXML
    private ToggleGroup appointmentTG;
    @FXML
    private AnchorPane AppointmentCustomersMain;
    @FXML
    private TableView mainScreenCustomersTable;
    @FXML
    private TableColumn customerIDCol;
    @FXML
    private TableColumn customerNameCol;
    @FXML
    private TableColumn customerAddressCol;
    @FXML
    private TableColumn customerPhoneNumberCol;
    @FXML
    private TableColumn customerPostalCodeCol;
    @FXML
    private TableColumn customerStateCol;
    @FXML
    private TableView mainScreenAppointmentsTable;
    @FXML
    private TableColumn appIDCol;
    @FXML
    private TableColumn appTitleCol;
    @FXML
    private TableColumn appDescriptionCol;
    @FXML
    private TableColumn appTypeCol;
    @FXML
    private TableColumn appLocationCol;
    @FXML
    private TableColumn appStartDateTimeCol;
    @FXML
    private TableColumn appEndDateTimeCol;
    @FXML
    private TableColumn appContactCol;
    @FXML
    private TableColumn appCustomerIdCol;
    @FXML
    private TableColumn appUserIdCol;
    @FXML
    private RadioButton appointmentsCurrentMonth;
    @FXML
    private RadioButton appointmentsAllAppointments;
    @FXML
    private RadioButton appointmentsCurrentWeek;


    private JDBC jdbc; //Initialize JDBC instance


    public void onActionAppointmentAdd(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }

    public void onActionAppointmentUpdate(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/modifyAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionAppointmentDelete(ActionEvent actionEvent) {

    }

    public void onActionCustomerAdd(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionCustomerUpdate(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/modifyCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionCustomersDelete(ActionEvent event){
    }

    public void onActionReports(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }

    public void onActionLogout(ActionEvent actionEvent) {

    }

    public void onActionCurrentWeekRadioButton(ActionEvent actionEvent) {
    }

    public void onActionCurrentMonthRadioButton(ActionEvent actionEvent) {
    }

    public void onActionAllAppointmentsRadioButton(ActionEvent actionEvent) {
    }



    public void initialize() throws SQLException {
        jdbc = new JDBC();
        jdbc.openConnection();

        ObservableList<Appointment> allAppointmentsList = AppointmentDetailsDOA.getAllAppointments(jdbc.getConnection());

        appIDCol.setCellValueFactory(new PropertyValueFactory<>("appID"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("appTitle"));
        appDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appDescription"));
        appLocationCol.setCellValueFactory(new PropertyValueFactory<>("appLocation"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("appType"));
        appStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("appStartTime"));
        appEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("appEndTime"));
        appCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appContactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

        mainScreenAppointmentsTable.setItems(allAppointmentsList);

//I understand that normally I would pull this from the local storage but I need to figure how to pull this from the database, store it temp, and display it.

        //mainScreenPartsTable.setItems(Inventory.getAllParts());
    }
}
