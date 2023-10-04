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
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
/**
 * Controller for the modify appointment view in a JavaFX application.
 *
 * This controller is responsible for handling actions related to modifying existing appointments, including
 * saving changes to the database, validating form data, and populating fields with the selected appointment's details.
 *
 */
public class modifyAppointmentController implements Initializable {
    @FXML
    private ComboBox<LocalTime> modAppointmentStartTime;
    @FXML
    private ComboBox<LocalTime> modAppointmentEndTime;
    @FXML
    private ComboBox<Customer> modAppointmentCustomerID;
    @FXML
    private ComboBox<User> modAppointmentUserID;
    @FXML
    private ComboBox<Contact> modAppointmentContact;
    @FXML
    private AnchorPane modAppointmentStage;
    @FXML
    private Label modAppointmentID;
    @FXML
    private Button modAppointmentSaveButton;
    @FXML
    private Button modAppointmentCancelButton;
    @FXML
    private DatePicker modAppointmentStartDate;
    @FXML
    private DatePicker modAppointmentEndDate;
    @FXML
    private TextField modAppointmentLocation;
    @FXML
    private TextField modAppointmentTitle;
    @FXML
    private TextField modAppointmentType;
    @FXML
    private TextField modAppointmentDescription;

    Appointment pickedApt = null;

    Stage stage;
    Parent scene;


    /**
     * Handles the save button action.
     *
     * Retrieves appointment details from the form, validates input, checks for overlapping appointments,
     * and updates the appointment in the database.
     *
     *
     * @param event the ActionEvent triggered by the button click
     * @throws IOException if there's an issue loading the next view
     */
    public void onActionModSaveButton(ActionEvent event) throws IOException {
        AppointmentData apptData = getAppointmentDataFromForm();

        if (apptData == null) {
            System.out.println("Error: Invalid appointment data.");
            return;
        }

        if(!isValidInput(apptData.apptTitle, apptData.apptDesc, apptData.apptLocation, apptData.apptType)){
            AlertHelper.showError("Error", "Please fill in all fields.");
            return;
        }

        AppointmentDAO apptDAO = new AppointmentDAOImpl();

        // Check for overlapping appointments
        if (apptDAO.appointmentOverlapExceptCurrent(apptData.customerId, apptData.startDtTime, apptData.endDtTime, apptData.apptId)) {
            AlertHelper.showError("Error", "The modified appointment overlaps with an existing appointment.");
            return;
        }


        // Call the appointmentUpdate method to update the appointment in the database
        int rowsAffected = apptDAO.updateAppt(
                apptData.apptId,
                apptData.customerId,
                apptData.userId,
                apptData.contactId,
                apptData.apptTitle,
                apptData.apptDesc,
                apptData.apptLocation,
                apptData.apptType,
                apptData.startDtTime,
                apptData.endDtTime
        );

        if (rowsAffected > 0) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            System.out.println("Error: Appointment not updated.");
        }
    }


    /**
     * Checks if the given input fields are filled out and not blank.
     *
     * @param apptTitle       title of the appointment
     * @param apptDesc        description of the appointment
     * @param apptLocation    location of the appointment
     * @param apptType        type of the appointment
     * @return true if all fields are filled, false otherwise
     */
    private boolean isValidInput(String apptTitle, String apptDesc, String apptLocation, String apptType) {
        return !(apptTitle.isBlank() || apptDesc.isBlank() || apptLocation.isBlank() || apptType.isBlank());
    }


    /**
     * Inner class representing the structured data of an appointment.
     * <p>
     * This is used to store form data in an organized way for easy processing and validation.
     * </p>
     */
    private class AppointmentData {
        int apptId;
        int customerId;
        int userId;
        int contactId;
        String apptTitle;
        String apptDesc;
        String apptLocation;
        String apptType;
        LocalDateTime startDtTime;
        LocalDateTime endDtTime;

        public AppointmentData(int apptId, int customerId, int userId, int contactId, String apptTitle, String apptDesc,
                               String apptLocation, String apptType, LocalDateTime startDtTime, LocalDateTime endDtTime) {
            this.apptId = apptId;
            this.customerId = customerId;
            this.userId = userId;
            this.contactId = contactId;
            this.apptTitle = apptTitle;
            this.apptDesc = apptDesc;
            this.apptLocation = apptLocation;
            this.apptType = apptType;
            this.startDtTime = startDtTime;
            this.endDtTime = endDtTime;

        }
    }

    /**
     * Extracts appointment details from the form fields and creates an AppointmentData instance.
     *
     * This method reads the values from the form fields, creates a LocalDateTime for the start and end times,
     * and initializes a new AppointmentData object using these details. The method provides error handling
     * for common exceptions like NumberFormatException and NullPointerException, showing appropriate alert dialogs
     * using the AlertHelper utility.
     *
     *
     * @return an instance of AppointmentData containing the extracted form details, or null if any error occurs
     * @throws NumberFormatException if parsing numeric fields like ID fails
     * @throws NullPointerException if any of the required fields are null
     * @see AppointmentData
     */
    private AppointmentData getAppointmentDataFromForm() {
        try {
            LocalDateTime startDateTime = LocalDateTime.of(modAppointmentStartDate.getValue(), (LocalTime) modAppointmentStartTime.getValue());
            LocalDateTime endDateTime = LocalDateTime.of(modAppointmentEndDate.getValue(), (LocalTime) modAppointmentEndTime.getValue());

            return new AppointmentData(
                    Integer.parseInt(modAppointmentID.getText()),
                    modAppointmentCustomerID.getValue().getCustomerId(),
                    modAppointmentUserID.getValue().getUserId(),
                    modAppointmentContact.getValue().getContactId(),
                    modAppointmentTitle.getText(),
                    modAppointmentDescription.getText(),
                    modAppointmentLocation.getText(),
                    modAppointmentType.getText(),
                    startDateTime,
                    endDateTime
            );
        } catch (NumberFormatException e) {
            AlertHelper.showError("Number Format Error", "Please make sure all number fields are filled in correctly.");
            return null;
        } catch (NullPointerException e) {
            AlertHelper.showError("Null Data Error", "Please make sure all required fields are filled in.");
            return null;
        } catch (Exception e) {
            AlertHelper.showError("Error", "An unexpected error occurred. Details: " + e.getMessage());
            return null;
        }
    }

    /**
     * Handles the cancel button action.
     *
     * Returns the user to the main appointment view without saving any changes.
     *
     *
     * @param event the ActionEvent triggered by the button click
     * @throws IOException if there's an issue loading the main view
     */
    public void onActionModCancelButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Populates the form fields with the details of the selected appointment.
     *
     * This is typically called when transitioning to the modify appointment view, to pre-fill the form with the existing details.
     *
     *
     * @param pickedAppointment the appointment selected for modification
     */
    public void updateAppointment(Appointment pickedAppointment) {
        JDBC.openConnection();
        ContactDAO contactDAO = new ContactDAOImpl();
        CustomerDAO customerDao = new CustomerDAOImpl();
        UserDAO userDao = new UserDAOImpl();

        pickedApt = pickedAppointment;
        modAppointmentID.setText(String.valueOf(pickedApt.getApptId()));
        modAppointmentTitle.setText(String.valueOf(pickedApt.getApptTitle()));
        modAppointmentDescription.setText(String.valueOf(pickedApt.getApptDesc()));
        modAppointmentLocation.setText(String.valueOf(pickedApt.getApptLocation()));
        modAppointmentType.setText(String.valueOf(pickedApt.getApptType()));

        Contact pickedContact = null;
        for(Contact contact : contactDAO.getAllContacts()) {
            if(contact.getContactId() == pickedApt.getContactId()) {
                pickedContact = contact;
                break;
            }
        }
        modAppointmentContact.getSelectionModel().select(pickedContact);

        Customer pickedCustomer = null;
        for(Customer customer : customerDao.getAllCustomers()) {
            if(customer.getCustomerId() == pickedApt.getCustomerId()){
                pickedCustomer = customer;
                break;
            }
        }
        modAppointmentCustomerID.getSelectionModel().select(pickedCustomer);

        User pickedUser = null;
        for(User user : userDao.getAllUsers()) {
            if(user.getUserId() == pickedApt.getUserId()) {
                pickedUser = user;
                break;
            }
        }
        modAppointmentUserID.getSelectionModel().select(pickedUser);

        modAppointmentStartDate.setValue(pickedAppointment.getStartDate());
        modAppointmentEndDate.setValue(pickedAppointment.getEndDate());
        modAppointmentStartTime.getSelectionModel().select(pickedAppointment.getStartTime());
        modAppointmentEndTime.getSelectionModel().select(pickedAppointment.getEndTime());
    }

    /**
     * Initializes the view components.
     *
     * Sets default values and populates comboboxes. This method is called automatically by JavaFX.
     *
     *
     * @param url a reference to the location of the FXML file
     * @param resourceBundle a reference to the localized strings, if any
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId opsZoneId = ZoneId.systemDefault();
        ZoneId officZoneId = ZoneId.of("America/New_York");
        LocalTime srtTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        int hoursOfOperation = 10;

        //modAppointmentStartTime.setItems(TimeUtil.generateBusinessHours(opsZoneId, officZoneId, srtTime, hoursOfOperation));
        modAppointmentStartTime.setItems(TimeUtil.openForBusiness(opsZoneId, officZoneId, srtTime, endTime));
        modAppointmentEndTime.getSelectionModel().selectFirst();


        //modAppointmentEndTime.setItems(TimeUtil.generateBusinessHours(opsZoneId, officZoneId, srtTime, hoursOfOperation));
        modAppointmentEndTime.setItems(TimeUtil.openForBusiness(opsZoneId, officZoneId, srtTime, endTime));
        modAppointmentEndTime.getSelectionModel().selectFirst();
        try {
            ContactDAO contactDAO = new ContactDAOImpl();
            CustomerDAO customerDAO = new CustomerDAOImpl();
            UserDAO userDAO = new UserDAOImpl();

            modAppointmentCustomerID.setItems(customerDAO.getAllCustomers());
            modAppointmentContact.setItems(contactDAO.getAllContacts());
            modAppointmentUserID.setItems(userDAO.getAllUsers());
        } catch (Exception e) {
            // Handle the exception, for instance, by showing an alert to the user.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database Error");
            alert.setContentText("There was an error retrieving data from the database: " + e.getMessage());
            alert.showAndWait();
        }

    }
}
