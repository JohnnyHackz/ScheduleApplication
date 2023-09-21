package DAO;

import javafx.collections.ObservableList;
import model.Customer;

public interface CustomerDAO {
    public ObservableList<Customer> getAllCustomers();

    public Customer getCustomerId(int customerId);

    public ObservableList<Customer> getCustomerByDivisionID(int divisionId);

    public int addCustomer(String custName, String custAddress, String custPhoneNumber, int divisionId,
                           String custPostalCode);

    public int customerUpdate(String custName, String custAddress, String custPhoneNumber, int divisionId,
                              String custPostalCode, int customerId);
    public int customerDelete(int customerId);


}
