package controller;

import DAO.*;
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

    public int apptId;
    public int customerId;
    public int userId;
    public int contactId;
    public String apptTitle;
    public String apptDesc;
    public String apptLocation;
    public String apptType;
    public LocalDateTime startDtTime;
    public LocalDateTime endDtTime;
    public LocalDate startDate;
    public LocalDate endDate;
    public LocalTime startTime;
    public LocalTime endTime;
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
                showError("Error: Please fill in all fields.");
                return;
            }

            AppointmentDAO apptDao = new AppointmentDAOImpl();

            if (!isValidTimes(apptDao, startDateTime, endDateTime)) return;

            if (startDateTime.toLocalTime().isAfter(endDateTime.toLocalTime())) {
                showError("Error: End time must be after start time.");
                return;
            }

            if (apptDao.checkNewApptForOverlap(customer.getCustomerId(), startDateTime.toLocalDate(), endDateTime.toLocalDate(), startDateTime.toLocalTime(), endDateTime.toLocalTime())) {
                showError("Error: Appointment overlaps with an existing appointment.");
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
                showError("Error: Failed to add the appointment.");
            }
        } catch (Exception e) {
            showError("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isValidInput(String title, String description, String location, String type) {
        return !(title.isBlank() || description.isBlank() || location.isBlank() || type.isBlank());
    }

    private boolean isValidTimes(AppointmentDAO apptDao, LocalDateTime start, LocalDateTime end) {
        System.out.println("Checking the start time: " + start);
        if (!apptDao.checkApptStartTime(start)){
            showError("Error: Invalid start.");
            return false;
        }
        if(!apptDao.checkApptEndTime(end)){
            showError("Error: Invalid end time.");
            return false;
        }
        return true;
    }

    private void showError(String errorMessage) {
        System.err.println(errorMessage);
        // Implement your UI error message display here
    }

    private void loadMainAppointmentsView() {
        try {
            Stage stage = (Stage) addAppointmentSaveButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Handle the IOException if loading the main view fails.
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Appointment: I am initialized!");
            try {
                ZoneId opsZoneId = ZoneId.systemDefault();
                ZoneId officZoneId = ZoneId.of("America/New_York");
                LocalTime srtTime = LocalTime.of(8, 0);
                LocalTime endTime = LocalTime.of(17, 0);
                int hoursOfOperation = 10;

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

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }
}