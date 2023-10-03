package model;

/**
 * Represents a division with an associated country.
 * This class encapsulates details of a division like its ID, name, and the country it is associated with.
 */
public class Division {

    /** The ID of the division. */
    private int divisionId;

    /** The name of the division. */
    private String divisionName;

    /** The ID of the associated country. */
    private int countryId;

    /** The name of the associated country. */
    private String countryName;

    /**
     * Gets the ID of the division.
     *
     * @return The division ID.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Sets the ID of the division.
     *
     * @param divisionId The division ID to set.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

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
     * @param divisionName The division name to set.
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * Gets the ID of the associated country.
     *
     * @return The country ID.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets the ID of the associated country.
     *
     * @param countryId The country ID to set.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Gets the name of the associated country.
     *
     * @return The name of the country.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the name of the associated country.
     *
     * @param countryName The country name to set.
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Constructs a Division object with specified values.
     *
     * @param divisionId   The ID of the division.
     * @param divisionName The name of the division.
     * @param countryId    The ID of the associated country.
     * @param countryName  The name of the associated country.
     */
    public Division(int divisionId, String divisionName, int countryId, String countryName){
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * Returns the string representation of the division, which is its name.
     *
     * @return The name of the division.
     */
    @Override
    public String toString(){
        return divisionName;
    }
}
