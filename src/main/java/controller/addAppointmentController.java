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

import static helper.JDBC.connection;

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
                boolean formatError = false;
                String title = addAppointmentTitle.getText();
                String description = addAppointmentDescription.getText();
                String location = addAppointmentLocation.getText();
                String type = addAppointmentType.getText();
                Contact contact = addAppointmentContact.getValue();
                Customer customer = addAppointmentCustomerID.getValue();
                User user = addAppointmentUserID.getValue();
                LocalDate startDate = addAppointmentStartDate.getValue();
                LocalDate endDate = addAppointmentEndDate.getValue();
                LocalTime startTime = addAppointmentStartTime.getValue();
                LocalTime endTime = addAppointmentEndTime.getValue();
                LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
                LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

                if (title.isBlank() || description.isBlank() || location.isBlank() || type.isBlank()) {
                    formatError = true;
                    // You should implement how you want to display the error message here.
                    // For example, using a dialog box, label, or some other UI component.
                    System.err.println("Error: Please fill in all fields.");
                }

                if (!formatError) {
                    AppointmentDAO apptDao = new AppointmentDAOImpl();

                    if (apptDao.checkApptStartTime(startDateTime) && apptDao.checkApptEndTime(endDateTime)) {
                        if (startDateTime.toLocalTime().isBefore(endDateTime.toLocalTime())) {
                            if (!apptDao.checkNewApptForOverlap(customer.getCustomerId(), startDate, endDate, startTime, endTime)) {
                                int rowsAffected = apptDao.addAppt(
                                        customer.getCustomerId(),
                                        user.getUserID(),
                                        contact.getContactId(),
                                        title,
                                        description,
                                        location,
                                        type,
                                        startDateTime,
                                        endDateTime
                                );

                                if (rowsAffected > 0) {
                                    // Appointment added successfully, navigate back to the main appointments view
                                    loadMainAppointmentsView();
                                } else {
                                    // Handle the case where the appointment was not added
                                    // You should implement how you want to display this error message.
                                    System.err.println("Error: Failed to add the appointment.");
                                }
                            } else {
                                // You should implement how you want to display this error message.
                                System.err.println("Error: Appointment overlaps with an existing appointment.");
                            }
                        } else {
                            // You should implement how you want to display this error message.
                            System.err.println("Error: End time must be after start time.");
                        }
                    } else {
                        // You should implement how you want to display this error message.
                        System.err.println("Error: Invalid start or end time.");
                    }
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }

    private void loadMainAppointmentsView() {
        try {
            Stage stage = (Stage) addAppointmentSaveButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
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
                ZoneId officZoneId = ZoneId.of("America/Los_Angeles");
                LocalTime srtTime = LocalTime.of(7, 0);
                //LocalTime endTime = LocalTime.of(17, 0);
                int hoursOfOperation = 10;

                JDBC.openConnection();
                ContactDAO contactDao = new ContactDAOImpl();
                CustomerDAO customerDao = new CustomerDAOImpl();
                UserDAO userDao = new UserDAOImpl(connection);


                addAppointmentContact.setItems(contactDao.getAllContacts());
                addAppointmentContact.getSelectionModel().selectFirst();
                addAppointmentCustomerID.setItems(customerDao.getAllCustomers());
                addAppointmentCustomerID.getSelectionModel().selectFirst();
                addAppointmentUserID.setItems(userDao.getAllUsers());
                addAppointmentUserID.getSelectionModel().selectFirst();


                addAppointmentStartDate.setValue(LocalDate.now());
                addAppointmentEndDate.setValue(LocalDate.now());

                // ComboBox start/end time
                addAppointmentStartTime.setItems(TimeUtil.generateBusinessHours(opsZoneId, officZoneId, srtTime, hoursOfOperation));
                addAppointmentStartTime.getSelectionModel().selectFirst();

                addAppointmentEndTime.setItems(TimeUtil.generateBusinessHours(opsZoneId, officZoneId, srtTime, hoursOfOperation));
                addAppointmentEndTime.getSelectionModel().selectFirst();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }
}