package model;

public class Customer {

    private int custID;
    private String custName;
    private String custAddress;
    private String custPostalCode;
    private String custPhoneNumber;
    private int custDivisionID;


    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
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
        return custDivisionID;
    }

    public void setCustDivisionID(int custDivisionID) {
        this.custDivisionID = custDivisionID;
    }

    public Customer (int custID, String custName, String custAddress, String custPostalCode,
                     String custPhoneNumber, int custDivisionID){
        this.custID = custID;
        this.custName = custName;
        this.custAddress = custAddress;
        this.custPostalCode = custPostalCode;
        this.custPhoneNumber = custPhoneNumber;
        this.custDivisionID = custDivisionID;

    }


}
