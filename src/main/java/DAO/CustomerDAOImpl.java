package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public Customer getCustomerId(int customerId) {
        try {
            String sql = "SELECT * FROM client_schedule.customers WHERE Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                customerId = result.getInt("Customer_ID");
                String custName = result.getString("Customer_Name");
                int countryId = result.getInt("Country_ID");
                String custAddress = result.getString("Address");
                String custPostalCode = result.getString("Postal_Code");
                String custPhoneNumber = result.getString("Phone");
                int divisionId = result.getInt("Division_ID");
                String custcountryName = result.getString("Country");
                String custDivisionName = result.getString("Division");

                return new Customer(customerId, custName, countryId, custAddress, custPostalCode, custPhoneNumber,
                        divisionId, custcountryName, custDivisionName);
            }
        } catch (SQLException e) {
            System.out.println("Error getting customer by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ObservableList<Customer> getCustomerByDivisionID(int divisionId) {
        ObservableList<Customer> customersByDivision = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM client_schedule.customers WHERE Division_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, divisionId);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int customerId = result.getInt("Customer_ID");
                String custName = result.getString("Customer_Name");
                int countryId = result.getInt("Country_ID");
                String custAddress = result.getString("Address");
                String custPostalCode = result.getString("Postal_Code");
                String custPhoneNumber = result.getString("Phone");
                String custcountryName = result.getString("Country");
                String custDivisionName = result.getString("Division");

                Customer customer = new Customer(customerId, custName, countryId, custAddress, custPostalCode, custPhoneNumber,
                        divisionId, custcountryName, custDivisionName);
                customersByDivision.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error getting customers by Division ID: " + e.getMessage());
            e.printStackTrace();
        }

        return customersByDivision;
    }

    @Override
    public int addCustomer(String custName, String custAddress, String custPhoneNumber,
                           int divisionId, String custPostalCode) {
        try {
            String sql = "INSERT INTO client_schedule.customers (Customer_Name, Address, Phone, Division_ID, Postal_Code) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, custName);
            ps.setString(2, custAddress);
            ps.setString(3, custPhoneNumber);
            ps.setInt(4, divisionId);
            ps.setString(5, custPostalCode);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int customerUpdate(String custName, String custAddress, String custPhoneNumber,
                              int divisionId, String custPostalCode, int customerId) {
        try {
            String sql = "UPDATE client_schedule.customers SET Customer_Name=?, Address=?, Phone=?, Postal_Code=?, Division_ID=? WHERE Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, custName);
            ps.setString(2, custAddress);
            ps.setString(3, custPhoneNumber);
            ps.setString(4, custPostalCode);
            ps.setInt(5, divisionId);
            ps.setInt(6, customerId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // If the update was successful, update the local list (assuming you have a method to do this)
                //updateLocalCustomerList(customerId, custName, custPostalCode, custPhoneNumber, divisionId);
                System.out.println("Update Successful.");
            }

            return rowsAffected;
        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public int customerDelete(int customerId) {
        try {
            String sql = "DELETE FROM client_schedule.customers WHERE Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer [" + customerId + "] was successfully deleted.");
            }

            return rowsAffected;
        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
}
