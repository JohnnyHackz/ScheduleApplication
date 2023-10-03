package controller;

import DAO.*;
import helper.AlertHelper;
import helper.JDBC;
import helper.TimeUtil;
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
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
/**
 * Controller for adding appointments.
 * This class provides functionality for adding appointments with the necessary details.
 * The appointments are then saved to a data source.
 */
public class addAppointmentController implements Initializable {

    @FXML
    private Button addAppointmentCancelButton;

    @FXML
    private ChoiceBox<Contact> addAppointmentContact;

    @FXML
    private ChoiceBox<Customer> addAppointmentCustomerID;

    @FXML
    private TextField addAppointmentDescription;

    @FXML
    private DatePicker addAppointmentEndDate;

    @FXML
    private ChoiceBox<LocalTime> addAppointmentEndTime;

    @FXML
    private TextField addAppointmentID;

    @FXML
    private TextField addAppointmentLocation;

    @FXML
    private Button addAppointmentSaveButton;

    @FXML
    private DatePicker addAppointmentStartDate;

    @FXML
    private ChoiceBox<LocalTime> addAppointmentStartTime;

    @FXML
    private TextField addAppointmentTitle;

    @FXML
    private TextField addAppointmentType;

    @FXML
    private ChoiceBox<User> addAppointmentUserID;

    @FXML
    private AnchorPane addAppointmentView;


    public int customerId;
    Stage stage;
    Parent scene;

    /**
     * Cancels the add appointment action and loads the main appointment view.
     *
     * @param event The event triggered by clicking the cancel button.
     * @throws IOException if there is an issue loading the FXML.
     */
    @FXML
    void onActionAddAppointmentCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Handles the action event for the "Add Appointment Save" button.
     * <p>
     * This method attempts to:
     * <ol>
     *     <li>Retrieve and validate input from various appointment fields.</li>
     *     <li>Check for time validity and overlapping appointments.</li>
     *     <li>Add the appointment to the database.</li>
     *     <li>Load the main appointments view upon successful addition.</li>
     * </ol>
     * Any errors or issues encountered during these operations will trigger appropriate error alerts.
     * </p>
     *
     * @param event The action event that initiated this method call.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    void onActionAddAppointmentSaveButton(ActionEvent event) throws IOException {
        try {
            String title = addAppointmentTitle.getText();
            String description = addAppointmentDescription.getText();
            String location = addAppointmentLocation.getText();
            String type = addAppointmentType.getText();
            Contact contact = addAppointmentContact.getValue();
            Customer customer = addAppointmentCustomerID.getValue();
            User user = addAppointmentUserID.getValue();
            LocalDateTime startDateTime = LocalDateTime.of(addAppointmentStartDate.getValue(), addAppointmentStartTime.getValue());
            LocalDateTime endDateTime = LocalDateTime.of(addAppointmentEndDate.getValue(), addAppointmentEndTime.getValue());

            if (!isValidInput(title, description, location, type)) {
                AlertHelper.showInfo("Error: Please fill in all fields.");
            }

            AppointmentDAO apptDao = new AppointmentDAOImpl();

            if (!isValidTimes(apptDao, startDateTime, endDateTime)) return;

            if (startDateTime.toLocalTime().isAfter(endDateTime.toLocalTime())) {
                AlertHelper.showError("Error: ", "End time must be after start time.");
                return;
            }
            if (startDateTime.toLocalTime().equals(endDateTime.toLocalTime())){
                AlertHelper.showError("Invalid Input", "Error: Invalid end appointment time. Select a new end appointment time.");
                return;
            }

            if (apptDao.appointmentOverlap(customer.getCustomerId(), startDateTime, endDateTime)) {
                AlertHelper.showError("Error: ", "Appointment overlaps with an existing appointment.");
                return;
            }

            int rowsAffected = apptDao.addAppt(
                    customer.getCustomerId(),
                    user.getUserId(),
                    contact.getContactId(),
                    title,
                    description,
                    location,
                    type,
                    startDateTime,
                    endDateTime
            );

            if (rowsAffected > 0) {
                loadMainAppointmentsView();
            } else {
                AlertHelper.showError("Error: ", "Failed to add the appointment.");
            }
        } catch (Exception e) {
            AlertHelper.displayError("Error: " + e.getMessage(), e);
        }
    }


    /**
     * Checks if the provided appointment details are valid.
     *
     * @param title       The title of the appointment.
     * @param description The description of the appointment.
     * @param location    The location of the appointment.
     * @param type        The type of the appointment.
     * @return true if the input is valid; false otherwise.
     */
    private boolean isValidInput(String title, String description, String location, String type) {
        return !(title.isBlank() || description.isBlank() || location.isBlank() || type.isBlank());
    }


    /**
     * Validates the start and end times of the appointment.
     *
     * @param apptDao The AppointmentDAO instance.
     * @param start   The start time of the appointment.
     * @param end     The end time of the appointment.
     * @return true if the times are valid; false otherwise.
     */
    private boolean isValidTimes(AppointmentDAO apptDao, LocalDateTime start, LocalDateTime end) {
        System.out.println("Checking the start time: " + start);
        if (!apptDao.checkApptStartTime(start)){
            AlertHelper.showError("Error: ", "Invalid start.");
            return false;
        }
        if(!apptDao.checkApptEndTime(end)){
            AlertHelper.showError("Error: ", "Invalid end time.");
            return false;
        }
        return true;
    }



    /**
     * Loads the main appointments view.
     */
    private void loadMainAppointmentsView() {
        try {
            Stage stage = (Stage) addAppointmentSaveButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            AlertHelper.displayError("Error: ", e);
        }
    }

    /**
     * Initializes the add appointment view with default values.
     * This method is called after the fxml file has been loaded.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Appointment: I am initialized!");
            try {
                ZoneId opsZoneId = ZoneId.systemDefault();
                ZoneId officZoneId = ZoneId.of("America/New_York");
                LocalTime srtTime = LocalTime.of(8, 0);
                LocalTime endTime = LocalTime.of(17, 0);

                JDBC.openConnection();
                ContactDAO contactDao = new ContactDAOImpl();
                CustomerDAO customerDao = new CustomerDAOImpl();
                UserDAO userDao = new UserDAOImpl();


                addAppointmentContact.setItems(contactDao.getAllContacts());
                addAppointmentContact.getSelectionModel().selectFirst();
                addAppointmentCustomerID.setItems(customerDao.getAllCustomers());
                addAppointmentCustomerID.getSelectionModel().selectFirst();
                addAppointmentUserID.setItems(userDao.getAllUsers());
                addAppointmentUserID.getSelectionModel().selectFirst();


                addAppointmentStartDate.setValue(LocalDate.now());
                addAppointmentEndDate.setValue(LocalDate.now());


                //addAppointmentStartTime.setItems(TimeUtil.generateBusinessHours(opsZoneId, officZoneId, srtTime, hoursOfOperation));
                addAppointmentStartTime.setItems(TimeUtil.openForBusiness(opsZoneId, officZoneId, srtTime, endTime));
                addAppointmentStartTime.getSelectionModel().selectFirst();

                //addAppointmentEndTime.setItems(TimeUtil.generateBusinessHours(opsZoneId, officZoneId, srtTime, hoursOfOperation));
                addAppointmentEndTime.setItems(TimeUtil.openForBusiness(opsZoneId, officZoneId, srtTime, endTime));
                addAppointmentEndTime.getSelectionModel().selectFirst();

                if (addAppointmentEndTime.getItems().contains(LocalTime.of(17, 0))) {
                    addAppointmentEndTime.getSelectionModel().select(LocalTime.of(17, 0));
                } else {
                    addAppointmentEndTime.getSelectionModel().selectFirst();
                }

                // 1st lambda expressions here
                addAppointmentStartDate.valueProperty().addListener((obs, oldDate, newDate) -> {
                    if (newDate.isAfter(addAppointmentEndDate.getValue())) {
                        addAppointmentEndDate.setValue(newDate);
                    }
                });

                // 2nd lambda expressions here
                addAppointmentStartTime.valueProperty().addListener((obs, oldTime, newTime) -> {
                    if (newTime.isAfter(addAppointmentEndTime.getValue())) {
                        addAppointmentEndTime.setValue(newTime.plusHours(1));
                    }
                });

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }
}