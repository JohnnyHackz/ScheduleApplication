package controller;

import DAO.AppointmentDAO;
import DAO.AppointmentDAOImpl;
import DAO.ContactDAO;
import DAO.ContactDAOImpl;
import helper.JDBC;
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
import model.Contact;

import java.io.IOException;

public class reportsContactController {
    public TableView reportsContactTableView;
    public TableView reportsTotalAppointmentsTableview;
    public TableView reportsTotalCustOfDivisionTableview;

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
    public ComboBox <Contact> reportsContactInformationContactsComboBox;
    @FXML
    private TableColumn reportsAppointmentInformationMonthAppointCol;
    @FXML
    private TableColumn reportsAppointmentInformationTypeAppointCol;
    @FXML
    private TableColumn reportsAppointmentInformationTotalAppointCol;
    @FXML
    private TableColumn reportsDivisionInformationDivNameCol;
    @FXML
    private TableColumn reportsDivisionInformationTotalCustomersCol;

    @FXML
    private TableColumn contactInformationIDCol;
    @FXML
    private TableColumn reportsContactInformationTitleCol;

    Stage stage;
    Parent scene;


    public void onActionReportsContactInformationComboBox(ActionEvent actionEvent) {
    }



    public void onActionReportsBackButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsMain.fxml"));
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

    public void onPull(ActionEvent actionEvent){
        StringBuilder sb = new StringBuilder("");
        Contact contact = reportsContactInformationContactsComboBox.getSelectionModel().getSelectedItem();

        if(contact == null){
            sb.append("ComboxBox: null");
        }
        else{
            sb.append(contact.getContactsName());
        }
    }

    public void fillTable(ActionEvent actionEvent){
        JDBC.openConnection();
        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
        int contactId = reportsContactInformationContactsComboBox.getSelectionModel().getSelectedItem().getContactId();
        reportsContactTableView.setItems(appointmentDAO.getApptsByContact(contactId));
    }

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



        JDBC.openConnection();
        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

        reportsContactTableView.setItems(appointmentDAO.getAllAppointments());
        ContactDAO contactDAO = new ContactDAOImpl();
        reportsContactInformationContactsComboBox.setItems(contactDAO.getAllContacts());

        JDBC.closeConnection();

    }
}
