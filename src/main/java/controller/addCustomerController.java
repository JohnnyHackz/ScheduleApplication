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
/**
 * Controller class for the 'Add Customer' view.
 * This class provides methods to interact with the GUI components
 * and handle events such as saving or canceling the customer addition.
 */
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
    /**
     * Navigates the user back to the main view.
     *
     * @param event The event triggered by the user action.
     */
    private void navigateToMainView(ActionEvent event) {
        try {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            if (stage == null) {
                System.err.println("Stage is null. Cannot set the scene.");
                return;
            }
            scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load the FXML file.");
        }
    }

    /**
     * Event handler for the 'Cancel' button.
     *
     * @param event The event triggered by the button click.
     * @throws IOException If an error occurs while loading the FXML file.
     */
   @FXML
    void onActionAddCustomerCancelButton(ActionEvent event) throws IOException {
        navigateToMainView(event);
    }

    /**
     * Event handler for the 'Cancel' button.
     *
     * @param event The event triggered by the button click.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @FXML
    void onActionAddCustomerSaveButton(ActionEvent event) throws IOException {
        //Get the values entered by the user from the form fields.
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
        navigateToMainView(event);
    }

    /**
     * Event handler for selecting a country from the dropdown.
     * Filters the state/providence dropdown based on the selected country.
     *
     * @param actionEvent The event triggered by the dropdown selection.
     */
    public void onActionPickCountry(ActionEvent actionEvent) {
        countryId = addCustomerCountryComboBox.getValue().getCountryId();
        addCustomerStateProvidenceComboBox.setItems(ListManager.getFilteredDivisions(countryId));
        addCustomerStateProvidenceComboBox.getSelectionModel().selectFirst();
    }

    /**
     * Initializes the add customer controller.
     * This method is called after the FXML file has been loaded.
     *
     * @param url The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
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