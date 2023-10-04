package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static helper.JDBC.connection;

/**
 * Implementation of the CustomerDAO interface for managing operations related to the Customer model.
 */
public class CustomerDAOImpl implements CustomerDAO{
    ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /**
     * Fetches all customers with their associated division and country info from the database.
     *
     * This method clears the allCustomers list, then queries the database to populate it
     * with customer details stored in the Customer class.
     *
     * @return ObservableList of customers.
     * @throws SQLException
     */
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


    /**
     * Fetches a customer from the database using the given ID.
     *
     * @param customerId The ID of the customer to fetch.
     * @return Customer object with the specified ID, or null if not found.
     */
    @Override
    public Customer getCustomerId(int customerId) {
        String sql = "SELECT * FROM client_schedule.customers WHERE Customer_ID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet result = ps.executeQuery()) {
                if (result.next()) {
                    return buildCustomerFromResult(result);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting customer by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Constructs a Customer object from a given ResultSet.
     *
     * @param result The ResultSet containing customer details.
     * @return Customer object constructed from the ResultSet.
     * @throws SQLException
     */
    private Customer buildCustomerFromResult(ResultSet result) throws SQLException {
        int customerId = result.getInt("Customer_ID");
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


    /**
     * Fetches a list of customers associated with a specific division ID from the database.
     *
     * @param divisionId The ID of the division by which customers are filtered.
     * @return an ObservableList of customers belonging to the specified division.
     */
    @Override
    public ObservableList<Customer> getCustomerByDivisionID(int divisionId) {
        ObservableList<Customer> customersByDivision = FXCollections.observableArrayList();
        String sql = "SELECT * FROM client_schedule.customers WHERE Division_ID=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, divisionId);
            try (ResultSet result = ps.executeQuery()) {
                while (result.next()) {
                    customersByDivision.add(buildCustomerFromResult(result));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting customers by Division ID: " + e.getMessage());
            e.printStackTrace();
        }

        return customersByDivision;
    }


    /**
     * Adds a new customer to the database.
     *
     * @param custName Name of the customer.
     * @param custAddress Address of the customer.
     * @param custPhoneNumber Phone number of the customer.
     * @param divisionId Division ID associated with the customer.
     * @param custPostalCode Postal code of the customer.
     * @return Number of rows affected, i.e., 1 for success, 0 for failure.
     */
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


    /**
     * Updates the details of a specific customer in the database.
     *
     * @param custName Updated name of the customer.
     * @param custAddress Updated address of the customer.
     * @param custPhoneNumber Updated phone number of the customer.
     * @param divisionId Updated division ID associated with the customer.
     * @param custPostalCode Updated postal code of the customer.
     * @param customerId ID of the customer being updated.
     * @return Number of rows affected.
     */
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
                System.out.println("Update Successful.");
            }

            return rowsAffected;
        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * Deletes a specific customer from the database.
     *
     * @param customerId ID of the customer to be deleted.
     * @return Number of rows affected.
     */
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
