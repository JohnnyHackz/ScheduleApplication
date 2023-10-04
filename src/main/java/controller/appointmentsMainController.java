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
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

/**
 * The appointmentsMainController class serves as a controller for the application's main interface.
 */
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


    /**
     * Handles the event to add an appointment.
     *
     * @param event the action event
     * @throws IOException in case of issues loading the fxml file.
     */
    public void onActionAppointmentAdd(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }


    /**
     * Handles the event to update an appointment.
     *
     * @param actionEvent the action event
     * @throws IOException in case of issues loading the fxml file.
     */
    public void onActionAppointmentUpdate(ActionEvent actionEvent) throws IOException {
        Appointment pickedAppointment = (Appointment) mainScreenAppointmentsTable.getSelectionModel().getSelectedItem();

        if (pickedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Appointment Selected");
            alert.setContentText("Please select an Appointment to update!");
            alert.showAndWait();
            return;  // Exit the method if no appointment is selected
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/modifyAppointment.fxml"));
            Parent scene = loader.load();

            modifyAppointmentController updateAppointments = loader.getController();
            updateAppointments.updateAppointment(pickedAppointment);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (Exception e) {
            // Catch general exceptions for better debugging
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("An unexpected error occurred!");
            alert.showAndWait();
        }
    }


    /**
     * Handles the event to delete an appointment.
     *
     * @param actionEvent the action event
     */
    public void onActionAppointmentDelete(ActionEvent actionEvent) {
        // Get the selected appointment
        Appointment selectedAppointment = (Appointment) mainScreenAppointmentsTable.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Error: Please select an appointment.");
            alert.showAndWait();
            return;
        }

        JDBC.openConnection();
        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
        int appointmentId = selectedAppointment.getApptId();
        int customerId = selectedAppointment.getCustomerId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("The selected \"Appointment\" will be deleted. Do you wish to continue?");
        Optional<ButtonType> rs = alert.showAndWait();

        if(rs.isPresent() && rs.get() == ButtonType.OK){
            int rowsAffected = appointmentDAO.deleteAppt(appointmentId, customerId);

            if(rowsAffected > 0) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Deletion Successful");
                successAlert.setContentText("Appointment with ID: " + appointmentId + " and type: " + selectedAppointment.getApptType() + " was successfully canceled.");
                successAlert.showAndWait();

                mainScreenAppointmentsTable.setItems(appointmentDAO.getAllAppointments());
            } else {
                Alert failureAlert = new Alert(Alert.AlertType.ERROR);
                failureAlert.setTitle("Deletion Failed");
                failureAlert.setContentText("Failed to delete the appointment.");
                failureAlert.showAndWait();
            }
        }
    }


    /**
     * Handles the event to add a customer.
     *
     * @param actionEvent the action event
     * @throws IOException in case of issues loading the fxml file.
     */
    public void onActionCustomerAdd(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Handles the event to update a customer.
     *
     * @param actionEvent the action event
     * @throws IOException in case of issues loading the fxml file.
     */
    public void onActionCustomerUpdate(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/modifyCustomer.fxml"));
            Parent scene = loader.load();

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


    /**
     * Handles the event to delete a customer.
     *
     * @param event the action event
     */
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
        Optional<ButtonType> rs = alert.showAndWait();

        if((rs.isPresent() && rs.get()== ButtonType.OK)){
            int rowsAffected = customerDAO.customerDelete(customerId);

            if(rowsAffected > 0) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Deletion Successful");
                successAlert.setContentText("Customer with ID: " + customerId + " was successfully deleted.");
                successAlert.showAndWait();

                mainScreenCustomersTable.setItems(customerDAO.getAllCustomers()); // Refresh the customers table
            } else {
                Alert failureAlert = new Alert(Alert.AlertType.ERROR);
                failureAlert.setTitle("Deletion Failed");
                failureAlert.setContentText("Failed to delete the customer.");
                failureAlert.showAndWait();
            }
        }


    }


    /**
     * Handles the event to display reports.
     *
     * @param actionEvent the action event
     * @throws IOException in case of issues loading the fxml file.
     */
    public void onActionReports(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }


    /**
     * Handles the logout event.
     *
     * @param actionEvent the action event
     * @throws IOException in case of issues loading the fxml file.
     */
    public void onActionLogout(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    /**
     * Handles the event to filter appointments for the current month.
     *
     * @param actionEvent the action event
     */
    public void onActionCurrentMonthRadioButton(ActionEvent actionEvent) {
        LocalDate today = LocalDate.now();

        // Opens database connection
        JDBC.openConnection();
        AppointmentDAO appointmentDao = new AppointmentDAOImpl();

        // Fetches the appointments for the current month using the combined method.
        mainScreenAppointmentsTable.setItems(appointmentDao.upcomingAppts(today, "month"));

    }


    /**
     * Handles the event to display all appointments.
     *
     * @param actionEvent the action event
     */
    public void onActionAllAppointmentsRadioButton(ActionEvent actionEvent) {
        // Opens database connection
        JDBC.openConnection();
        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

        // Fetches all appointments
        mainScreenAppointmentsTable.setItems(appointmentDAO.getAllAppointments());

    }


    /**
     * Initializes the controller. This method is automatically called when the FXML is loaded.
     *
     * @throws SQLException in case of issues connecting to the database.
     */
    @FXML
    public void initialize() throws SQLException {

        System.out.println("Appointment Schedule (Main Menu): I am initialized!");


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

    }

    /**
     * Handles the event to filter appointments for the current week.
     *
     * @param actionEvent the action event
     */
    public void onActionCurrentWeekRadioButton(ActionEvent actionEvent) {
        LocalDate today = LocalDate.now();

        // Opens database connection
        JDBC.openConnection();
        AppointmentDAO appointmentDao = new AppointmentDAOImpl();

        // Fetches the appointments for the current month using the combined method.
        mainScreenAppointmentsTable.setItems(appointmentDao.upcomingAppts(today, "week"));

    }
}