package DAO;

import javafx.collections.ObservableList;
import model.Customer;

public interface CustomerDAO {
    public ObservableList<Customer> getAllCustomers();

    public Customer getCustomerID(int customerId);

    public ObservableList<Customer> getCustomerByDivisionID();

    public int addCustomer(int customerId, String custName, String custAddress, String custPostalCode, String custPhoneNumber,
                           int divisionId);

    public int customerUpdate(int customerId, String custName, String custPostalCode, String custPhoneNumber,
                              int divisionId);
    public int customerDelete(int customerId, String custName, int divisionId);


}
