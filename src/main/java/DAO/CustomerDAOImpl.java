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
            String sql = "SELECT * FROM customers";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int customerId = result.getInt("Customer_ID");
                String custName = result.getString("Customer_Name");
                String custAddress = result.getString("Address");
                String custPhoneNumber = result.getString("Phone");
                String custPostalCode = result.getString("Postal_Code");
                int divisionId = result.getInt("Division_ID");
                Customer customer = new Customer(customerId, custName, custAddress, custPostalCode, custPhoneNumber, divisionId);
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
