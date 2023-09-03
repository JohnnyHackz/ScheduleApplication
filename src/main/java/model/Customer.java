package model;

public class Customer {

    private int customerId;
    private String custName;
    private String custAddress;
    private String custPostalCode;
    private String custPhoneNumber;
    private int divisionId;


    public int getCustomerID() {
        return customerId;
    }

    public void setCustomerID(int customerId) {
        this.customerId = customerId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
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

    public int getCustDivisionID() {
        return divisionId;
    }

    public void setCustDivisionID(int divisionId) {
        this.divisionId = divisionId;
    }

    public Customer (int customerID, String custName, String custAddress, String custPostalCode,
                     String custPhoneNumber, int divisionId){
        this.customerId = customerID;
        this.custName = custName;
        this.custAddress = custAddress;
        this.custPostalCode = custPostalCode;
        this.custPhoneNumber = custPhoneNumber;
        this.divisionId = divisionId;

    }


}
