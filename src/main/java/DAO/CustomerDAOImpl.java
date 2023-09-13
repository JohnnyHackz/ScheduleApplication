package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static helper.JDBC.connection;

public class CustomerDAOImpl implements CustomerDAO{
    ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    @Override
    public ObservableList<Customer> getAllCustomers() {
        try {
            String sql = "select * from client_schedule.customers, client_schedule.first_level_divisions, client_schedule.countries \n" +
                    "where customers.Division_ID = first_level_divisions.Division_ID\n" +
                    "and first_level_divisions.Country_ID = countries.Country_ID";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int customerId = result.getInt("Customer_ID");
                String custName = result.getString("Customer_Name");
                int countryId = result.getInt("Country_ID");
                String custAddress = result.getString("Address");
                String custPostalCode = result.getString("Postal_Code");
                String custPhoneNumber = result.getString("Phone");
                int divisionId = result.getInt("Division_ID");
                String custcountryName = result.getString("Country");
                String custDivisionName = result.getString("Division");

                Customer customer = new Customer(customerId, custName, countryId, custAddress, custPostalCode, custPhoneNumber,
                        divisionId, custcountryName, custDivisionName);
                allCustomers.add(customer);

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return allCustomers;
    }

    @Override
    public Customer getCustomerID(int customerID) {
        return null;
    }

    @Override
    public ObservableList<Customer> getCustomerByDivisionID() {
        return null;
    }

    @Override
    public int addCustomer(int customerID, String custName, String custAddress, String custPostalCode, String custPhoneNumber, int divisionId) {
        return 0;
    }

    @Override
    public int customerUpdate(int customerID, String custName, String custPostalCode, String custPhoneNumber, int divisionId) {
        return 0;
    }

    @Override
    public int customerDelete(int customerID, String custName, int divisionId) {
        return 0;
    }
}
