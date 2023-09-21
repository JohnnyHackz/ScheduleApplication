package controller;

import DAO.AppointmentDAO;
import DAO.AppointmentDAOImpl;
import DAO.CustomerDAO;
import DAO.CustomerDAOImpl;
import helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class appointmentsMainController {


    public TableView mainScreenCustomersTable;
    public TableColumn customerIDCol;
    public TableColumn customerNameCol;
    public TableColumn customerAddressCol;
    public TableColumn customerPhoneNumberCol;
    public TableColumn customerStateCol;
    public TableColumn customerPostalCodeCol;
    public TableView mainScreenAppointmentsTable;
    public TableColumn appIDCol;
    public TableColumn appTitleCol;
    public TableColumn appDescriptionCol;
    public TableColumn appLocationCol;
    public TableColumn appTypeCol;
    public TableColumn appStartDateTimeCol;
    public TableColumn appEndDateTimeCol;
    public TableColumn appContactCol;
    public TableColumn appCustomerIdCol;
    public TableColumn appUserIdCol;
    public RadioButton appointmentsCurrentWeek;
    public ToggleGroup appointmentTG;
    public RadioButton appointmentsCurrentMonth;
    public RadioButton appointmentsAllAppointments;
    @FXML
    private AnchorPane AppointmentCustomersMain;

    Stage stage;
    Parent scene;


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

    public void onActionCustomerUpdate(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/modifyCustomer.fxml"));
            Parent scene = loader.load();

            //this is where I need to modify my code. I need to write in the logic for the save customer method so I can then
            //call it here (UpdateCustomer is their version of SAVE customer.)
            modifyCustomerController updateCust = loader.getController();

            Customer pickedCustomer = (Customer) mainScreenCustomersTable.getSelectionModel().getSelectedItem();

            updateCust.updateCustomer(pickedCustomer);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch (RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Customer Selected");
            alert.setContentText("Please select a customer to update!");
            alert.showAndWait();
        }
    }
    public void onActionCustomersDelete(ActionEvent event) {
        Customer selectedCustomer = (Customer) mainScreenCustomersTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Error: Please select a customer.");
            alert.showAndWait();
            return;
        }
        JDBC.openConnection();
        CustomerDAO customerDAO = new CustomerDAOImpl();
        Customer clickedCustomer = (Customer) mainScreenCustomersTable.getSelectionModel().getSelectedItem();
        int customerId = clickedCustomer.getCustomerId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("The selected \"Customer\" and their corresponding \"Appointment(s)\" will be deleted. Do you wish to continue?");
        alert.showAndWait();
        Optional<ButtonType> rs = alert.showAndWait();

        if((rs.isPresent() && rs.get()== ButtonType.OK)){
            System.out.println(customerDAO.customerDelete(customerId));
            JDBC.openConnection();
            mainScreenCustomersTable.setItems(customerDAO.getAllCustomers());
        }


    }


    public void onActionReports(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsMain.fxml"));
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


    @FXML
    public void initialize() throws SQLException {

        System.out.println("Appointment Schedule (Main Menu): I am initialized!");

        //userTimeZoneLbl.setText("Your Time Zone: " + String.valueOf(ZoneId.systemDefault()));

        // Appointments tableview
        appIDCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        appDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("apptDesc"));
        appCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appLocationCol.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        appContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        appStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        appStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDtTime"));
        appEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        appEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDtTime"));

        appUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        //Customer Tableview
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("custName"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        customerPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("custPhoneNumber"));
        customerStateCol.setCellValueFactory(new PropertyValueFactory<>("custDivisionName"));
        customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("custPostalCode"));

        JDBC.openConnection();
        AppointmentDAO appointmentDao = new AppointmentDAOImpl();
        mainScreenAppointmentsTable.setItems(appointmentDao.getAllAppointments());
        CustomerDAO customerDao = new CustomerDAOImpl();
        mainScreenCustomersTable.setItems(customerDao.getAllCustomers());


//I understand that normally I would pull this from the local storage but I need to figure how to pull this from the database, store it temp, and display it.

        //mainScreenPartsTable.setItems(Inventory.getAllParts());
    }
}