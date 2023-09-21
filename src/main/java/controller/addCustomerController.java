package controller;

import DAO.CountryDAO;
import DAO.CountryDAOImpl;
import DAO.CustomerDAO;
import DAO.CustomerDAOImpl;
import helper.JDBC;
import helper.ListManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Country;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addCustomerController implements Initializable {

    @FXML
    private TextField addCustomerAddress;

    @FXML
    private Button addCustomerCancelButton;

    @FXML
    private ComboBox<Country> addCustomerCountryComboBox;

    @FXML
    private TextField addCustomerID;

    @FXML
    private TextField addCustomerName;

    @FXML
    private TextField addCustomerPhoneNumber;

    @FXML
    private Button addCustomerSaveButton;

    @FXML
    private ComboBox<Division> addCustomerStateProvidenceComboBox;

    @FXML
    private AnchorPane addCustomerView;

    @FXML
    private TextField addCustomerZipCode;

    private int countryId;


    Stage stage;
    Parent scene;

   @FXML
    void onActionAddCustomerCancelButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddCustomerSaveButton(ActionEvent event) throws IOException {
        // Get the values entered by the user from the form fields
        //int customerId = Integer.parseInt(addCustomerID.getText());
        String custName = addCustomerName.getText();
        String custAddress = addCustomerAddress.getText();
        String custPhoneNumber = addCustomerPhoneNumber.getText();
        int divisionId = addCustomerStateProvidenceComboBox.getSelectionModel().getSelectedItem().getDivisionId();
        int countryId = addCustomerCountryComboBox.getSelectionModel().getSelectedItem().getCountryId();
        String custPostalCode = addCustomerZipCode.getText();


        // Create an instance of CustomerDAO to add the customer
        CustomerDAO customerDAO = new CustomerDAOImpl();

        // Call the addCustomer method to add the customer to the database
        int rowsAffected = customerDAO.addCustomer(custName, custAddress, custPhoneNumber, divisionId, custPostalCode);

        if (rowsAffected > 0) {
            // If the customer was added successfully, navigate back to the main view
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            // Handle the case where the customer couldn't be added (e.g., display an error message)
            // You can implement this according to your application's requirements.
            System.out.println("Error: Customer not added.");
        }

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void onActionPickCountry(ActionEvent actionEvent) {
        countryId = addCustomerCountryComboBox.getValue().getCountryId();
        addCustomerStateProvidenceComboBox.setItems(ListManager.getFilteredDivisions(countryId));
        addCustomerStateProvidenceComboBox.getSelectionModel().selectFirst();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       try {
           JDBC.openConnection();
           CountryDAO countryDAO = new CountryDAOImpl();
           addCustomerCountryComboBox.setItems(countryDAO.getAllCountries());
           addCustomerCountryComboBox.getSelectionModel().selectFirst();


           countryId = addCustomerCountryComboBox.getValue().getCountryId();

           addCustomerStateProvidenceComboBox.setItems(ListManager.getFilteredDivisions(countryId));
           addCustomerStateProvidenceComboBox.getSelectionModel().selectFirst();


       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
}