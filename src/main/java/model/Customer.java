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

    public String getCustCountryName() {
        return custCountryName;
    }

    public void setCustCountryName(String custCountryName) {
        this.custCountryName = custCountryName;
    }

    public String getCustDivisionName() {
        return custDivisionName;
    }

    public void setCustDivisionName(String custDivisionName) {
        this.custDivisionName = custDivisionName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPostalCode() {
        return custPostalCode;
    }

    public void setCustPostalCode(String custPostalCode) {
        this.custPostalCode = custPostalCode;
    }

    public String getCustPhoneNumber() {
        return custPhoneNumber;
    }

    public void setCustPhoneNumber(String custPhoneNumber) {
        this.custPhoneNumber = custPhoneNumber;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }






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


}