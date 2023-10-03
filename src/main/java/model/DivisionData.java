package model;

/**
 * Represents data associated with a division, particularly the name of the division and its total customers.
 * This class encapsulates details of a division and the number of customers it has.
 */
public class DivisionData {

    /** The name of the division. */
    private String divisionName;

    /** The total number of customers in the division. */
    private int totalCustomers;

    /**
     * Gets the name of the division.
     *
     * @return The name of the division.
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Sets the name of the division.
     *
     * @param divisionName The name of the division to set.
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * Gets the total number of customers in the division.
     *
     * @return The total number of customers.
     */
    public int getTotalCustomers() {
        return totalCustomers;
    }

    /**
     * Sets the total number of customers in the division.
     *
     * @param totalCustomers The total number of customers to set.
     */
    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    /**
     * Constructs a DivisionData object with the specified values.
     *
     * @param divisionName  The name of the division.
     * @param totalCustomers The total number of customers in the division.
     */
    public DivisionData(String divisionName, int totalCustomers) {
        this.divisionName = divisionName;
        this.totalCustomers = totalCustomers;
    }
}
