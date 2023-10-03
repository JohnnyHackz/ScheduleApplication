package controller;

import DAO.AppointmentDAO;
import DAO.AppointmentDAOImpl;
import DAO.ContactDAO;
import DAO.ContactDAOImpl;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;

import java.io.IOException;

/**
 * The reportsContactController class manages the UI for contact-related reports.
 * Users can view and refresh appointments, select contacts from a dropdown, and filter appointments by contact.
 * Additionally, it offers navigation back to the main reports page.
 */
public class reportsContactController {
    @FXML
    private Button refreshTableButton;
    @FXML
    private TableView reportsContactTableView;
    @FXML
    private TableColumn reportsContactInformationAppointmentIDCol;

    @FXML
    private TableColumn reportsContactInformationTypeCol;
    @FXML
    private TableColumn reportsContactInformationDescriptionCol;
    @FXML
    private TableColumn reportsContactInformationLocationCol;
    @FXML
    private TableColumn reportsContactInformationStartDateTimeCol;
    @FXML
    private TableColumn reportsContactInformationEndDateTimeCol;
    @FXML
    private TableColumn reportsContactInformationCustomerIDCol;
    @FXML
    public ComboBox<Contact> reportsContactInformationContactsComboBox;
    @FXML
    private TableColumn contactInformationIDCol;
    @FXML
    private TableColumn reportsContactInformationTitleCol;

    Stage stage;
    Parent scene;

    /**
     * Handles the selection of a contact from the ComboBox and populates the table
     * with appointments related to the selected contact.
     */
    public void onActionReportsContactInformationComboBox() {
        fillTable();

    }

    /**
     * Handles the action of navigating back to the main reports page.
     *
     * @param actionEvent the event triggered by the user's action.
     * @throws IOException if there's an error during the scene transition.
     */
    public void onActionReportsBackButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }

    /**
     * Fills the TableView with appointments related to the selected contact.
     */
    public void fillTable() {
        JDBC.openConnection();

        // Get the selected contactId from the ComboBox
        int contactId = reportsContactInformationContactsComboBox.getSelectionModel().getSelectedItem().getContactId();

        // Fetch all appointments
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        ObservableList<Appointment> allAppointments = appointmentDAO.getAllAppointments();
        ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();

        // Filter appointments based on contactId
        for (Appointment appt : allAppointments) {
            if (appt.getContactId() == contactId) {
                filteredAppointments.add(appt);
            }
        }

        // Set the TableView with the filtered appointments
        reportsContactTableView.setItems(filteredAppointments);

        JDBC.closeConnection();
    }


    /**
     * Refreshes the TableView by fetching all appointments and setting them in the TableView.
     *
     * @param actionEvent the event triggered by the user's action.
     */
    public void onActionRefreshTable(ActionEvent actionEvent) {
        refreshTable();
    }

    private void refreshTable() {
        JDBC.openConnection();
        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

        // Fetch all appointments
        ObservableList<Appointment> allAppointments = appointmentDAO.getAllAppointments();

        // Set the TableView with all the appointments
        reportsContactTableView.setItems(allAppointments);

        JDBC.closeConnection();
    }

    /**
     * Initializes the TableView columns and populates the ComboBox with available contacts.
     * It also sets up an event listener for the ComboBox to update the table upon contact selection.
     */
    @FXML
    public void initialize() {

        //this is the contact table
        contactInformationIDCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        reportsContactInformationTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        reportsContactInformationTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        reportsContactInformationDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("apptDesc"));
        reportsContactInformationLocationCol.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        reportsContactInformationStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        reportsContactInformationEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        reportsContactInformationStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDtTime"));
        reportsContactInformationEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDtTime"));
        reportsContactInformationCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        reportsContactInformationAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));


        JDBC.openConnection();
        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
        reportsContactTableView.setItems(appointmentDAO.getAllAppointments());
        ContactDAO contactDAO = new ContactDAOImpl();
        reportsContactInformationContactsComboBox.setItems(contactDAO.getAllContacts());
        JDBC.closeConnection();

        reportsContactInformationContactsComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fillTable(); // Update table on contact selection
            }
        });

    }

}