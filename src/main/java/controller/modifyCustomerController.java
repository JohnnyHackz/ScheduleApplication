package controller;

import DAO.CountryDAO;
import DAO.CountryDAOImpl;
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
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;
import DAO.CustomerDAO;
import DAO.CustomerDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class modifyCustomerController implements Initializable {

    @FXML
    private TextField modCustomerAddress;

    @FXML
    private Button modCustomerCancel;

    @FXML
    private ComboBox<Country> modCustomerCountry;

    @FXML
    private TextField modCustomerID;

    @FXML
    private TextField modCustomerName;

    @FXML
    private TextField modCustomerPhoneNumber;

    @FXML
    private TextField modCustomerPostalCode;

    @FXML
    private Button modCustomerSave;

    @FXML
    private ComboBox<Division> modCustomerStateProvince;

    Customer pCustomer = null;

    private int countryId;


    private int divisionId;

    Stage stage;
    Parent scene;

    @FXML
    void onActionModCustomerCancelButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModCustomerSaveButton(ActionEvent event) throws IOException {
        CustomerData customerData = getCustomerDataFromForm();

        if (customerData == null) {
            System.out.println("Error: Invalid customer data.");
            return;
        }

        // Create an instance of CustomerDAO to update the customer
        CustomerDAO customerDAO = new CustomerDAOImpl();

        // Call the customerUpdate method to update the customer in the database
        int rowsAffected = customerDAO.customerUpdate(
                customerData.name,
                customerData.address,
                customerData.phoneNumber,
                customerData.divisionId,
                customerData.postalCode,
                customerData.id);

        if (rowsAffected > 0) {
            switchToMainView(event);
        } else {
            System.out.println("Error: Customer not updated.");
        }
    }

    private CustomerData getCustomerDataFromForm() {
        try {
            return new CustomerData(
                    Integer.parseInt(modCustomerID.getText()),
                    modCustomerName.getText(),
                    modCustomerAddress.getText(),
                    modCustomerPhoneNumber.getText(),
                    modCustomerStateProvince.getSelectionModel().getSelectedItem().getDivisionId(),
                    modCustomerPostalCode.getText(),
                    modCustomerCountry.getSelectionModel().getSelectedItem().getCountryId()
            );
        } catch (NumberFormatException e) {
            // Handle the invalid data
            return null;
        }
    }

    private void switchToMainView(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    // Helper class to encapsulate customer data
    private class CustomerData {
        int id;
        String name;
        String address;
        String phoneNumber;
        int divisionId;
        String postalCode;
        int countryId;

        public CustomerData(int id, String name, String address, String phoneNumber, int divisionId, String postalCode, int countryId) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.divisionId = divisionId;
            this.postalCode = postalCode;
            this.countryId = countryId;
        }
    }


    public void updateCustomer(Customer pickedCustomer) {
        JDBC.openConnection();
        CountryDAO countryDAO = new CountryDAOImpl();
        pCustomer = pickedCustomer;

        modCustomerID.setText(String.valueOf(pCustomer.getCustomerId()));
        modCustomerName.setText(String.valueOf(pCustomer.getCustName()));
        modCustomerAddress.setText(String.valueOf(pCustomer.getCustAddress()));
        modCustomerPhoneNumber.setText(String.valueOf(pCustomer.getCustPhoneNumber()));
        modCustomerPostalCode.setText(String.valueOf(pCustomer.getCustPostalCode()));

        Country pCountry = null;
        for (Country country : countryDAO.getAllCountries()) {
            if (country.getCountryId() == pCustomer.getCountryId()) {
                pCountry = country;
                break;
            }
        }
        modCustomerCountry.getSelectionModel().select(pCountry);
        countryId = pCountry.getCountryId();

        modCustomerStateProvince.setItems(ListManager.getFilteredDivisions(countryId));
        Division selDivision = null;
        for (Division division : ListManager.getFilteredDivisions(countryId)) {
            if (division.getDivisionId() == pCustomer.getDivisionId()) {
                selDivision = division;
                break;
            }
        }
        modCustomerStateProvince.getSelectionModel().select(selDivision);
        divisionId = selDivision.getDivisionId();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JDBC.openConnection();

        CountryDAO countryDAO = new CountryDAOImpl();
        modCustomerCountry.setItems(countryDAO.getAllCountries());
    }

    public void onActionPickCountry(ActionEvent actionEvent) {
        countryId = modCustomerCountry.getValue().getCountryId();
        modCustomerStateProvince.setItems(ListManager.getFilteredDivisions(countryId));
        modCustomerStateProvince.getSelectionModel().selectFirst();
    }
}
