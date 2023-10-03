package DAO;

import javafx.collections.ObservableList;
import model.Customer;

/**
 * Interface defining the necessary CRUD operations for the Customer model.
 */
public interface CustomerDAO {

    /**
     * Retrieves all customers from the data source.
     *
     * @return an ObservableList containing all Customer objects.
     */
    ObservableList<Customer> getAllCustomers();

    /**
     * Retrieves a specific customer based on its ID.
     *
     * @param customerId the ID of the customer to be retrieved.
     * @return the Customer object associated with the given ID, or null if not found.
     */
    Customer getCustomerId(int customerId);

    /**
     * Retrieves all customers associated with a specific division ID.
     *
     * @param divisionId the ID of the division.
     * @return an ObservableList containing Customer objects of the specified division.
     */
    ObservableList<Customer> getCustomerByDivisionID(int divisionId);

    /**
     * Adds a new customer to the data source.
     *
     * @param custName        the name of the new customer.
     * @param custAddress     the address of the new customer.
     * @param custPhoneNumber the phone number of the new customer.
     * @param divisionId      the division ID the customer is associated with.
     * @param custPostalCode  the postal code of the new customer.
     * @return an integer representing the status of the operation (e.g., number of records affected).
     */
    int addCustomer(String custName, String custAddress, String custPhoneNumber, int divisionId,
                    String custPostalCode);

    /**
     * Updates a specific customer's details in the data source.
     *
     * @param custName        the updated name of the customer.
     * @param custAddress     the updated address of the customer.
     * @param custPhoneNumber the updated phone number of the customer.
     * @param divisionId      the updated division ID the customer is associated with.
     * @param custPostalCode  the updated postal code of the customer.
     * @param customerId      the ID of the customer to be updated.
     * @return an integer representing the status of the operation (e.g., number of records affected).
     */
    int customerUpdate(String custName, String custAddress, String custPhoneNumber, int divisionId,
                       String custPostalCode, int customerId);

    /**
     * Deletes a specific customer from the data source.
     *
     * @param customerId the ID of the customer to be deleted.
     * @return an integer representing the status of the operation (e.g., number of records affected).
     */
    int customerDelete(int customerId);
}
