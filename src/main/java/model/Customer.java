package model;

public class Customer {

    private int customerId;
    private String custName;
    private int countryId;
    private String custAddress;
    private String custPostalCode;
    private String custPhoneNumber;
    private int divisionId;
    private String custCountryName;
    private String custDivisionName;

    /**
     * Retrieves the name of the country associated with the customer.
     *
     * @return The name of the customer's country.
     */
    public String getCustCountryName() {
        return custCountryName;
    }

    /**
     * Sets the name of the country associated with the customer.
     *
     * @param custCountryName The name of the customer's country.
     */
    public void setCustCountryName(String custCountryName) {
        this.custCountryName = custCountryName;
    }

    /**
     * Retrieves the name of the division associated with the customer.
     *
     * @return The name of the customer's division.
     */
    public String getCustDivisionName() {
        return custDivisionName;
    }

    /**
     * Sets the name of the division associated with the customer.
     *
     * @param custDivisionName The name of the customer's division.
     */
    public void setCustDivisionName(String custDivisionName) {
        this.custDivisionName = custDivisionName;
    }

    /**
     * Retrieves the unique identifier of the customer.
     *
     * @return The ID of the customer.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the unique identifier of the customer.
     *
     * @param customerId The ID of the customer.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getCustName() {
        return custName;
    }

    /**
     * Sets the name of the customer.
     *
     * @param custName The name of the customer.
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * Retrieves the identifier of the country associated with the customer.
     *
     * @return The ID of the customer's country.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets the identifier of the country associated with the customer.
     *
     * @param countryId The ID of the customer's country.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Retrieves the address of the customer.
     *
     * @return The customer's address.
     */
    public String getCustAddress() {
        return custAddress;
    }

    /**
     * Sets the address of the customer.
     *
     * @param custAddress The customer's address.
     */
    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    /**
     * Retrieves the postal code of the customer.
     *
     * @return The customer's postal code.
     */
    public String getCustPostalCode() {
        return custPostalCode;
    }

    /**
     * Sets the postal code of the customer.
     *
     * @param custPostalCode The customer's postal code.
     */
    public void setCustPostalCode(String custPostalCode) {
        this.custPostalCode = custPostalCode;
    }

    /**
     * Retrieves the phone number of the customer.
     *
     * @return The customer's phone number.
     */
    public String getCustPhoneNumber() {
        return custPhoneNumber;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param custPhoneNumber The customer's phone number.
     */
    public void setCustPhoneNumber(String custPhoneNumber) {
        this.custPhoneNumber = custPhoneNumber;
    }

    /**
     * Retrieves the identifier of the division associated with the customer.
     *
     * @return The ID of the customer's division.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Sets the identifier of the division associated with the customer.
     *
     * @param divisionId The ID of the customer's division.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }


    /**
     * Constructs a new Customer object with specified details.
     *
     * @param customerId The unique identifier for the customer.
     * @param custName The name of the customer.
     * @param countryId The identifier for the country associated with the customer.
     * @param custAddress The address of the customer.
     * @param custPostalCode The postal code of the customer.
     * @param custPhoneNumber The phone number of the customer.
     * @param divisionId The identifier for the division associated with the customer.
     * @param custCountryName The name of the country associated with the customer.
     * @param custDivisionName The name of the division associated with the customer.
     */
    public Customer (int customerId, String custName, int countryId, String custAddress, String custPostalCode,
                     String custPhoneNumber, int divisionId, String custCountryName, String custDivisionName){
        this.customerId = customerId;
        this.custName = custName;
        this.countryId = countryId;
        this.custAddress = custAddress;
        this.custPostalCode = custPostalCode;
        this.custPhoneNumber = custPhoneNumber;
        this.divisionId = divisionId;
        this.custCountryName = custCountryName;
        this.custDivisionName = custDivisionName;

    }

    /**
     * Provides a string representation of the customer, showing its unique ID.
     *
     * @return The customer's ID as a string.
     */
    public String toString(){
        return Integer.toString(customerId);
    }


}
