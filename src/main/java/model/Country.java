package model;

/**
 * Represents a country with a unique ID and name.
 */
public class Country {
    private int countryId;
    private String nameOfCountry;

    /**
     * Retrieves the unique identifier for the country.
     *
     * @return The country's ID.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets the unique identifier for the country.
     *
     * @param countryId The ID to set for the country.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Retrieves the name of the country.
     *
     * @return The name of the country.
     */
    public String getNameOfCountry() {
        return nameOfCountry;
    }

    /**
     * Sets the name of the country.
     *
     * @param countryName The name to set for the country.
     *                    Note: Parameter should be 'nameOfCountry' to match instance variable.
     */
    public void setCountryName(String countryName) {
        this.nameOfCountry = countryName;
    }

    /**
     * Constructs a new Country object with the specified ID and name.
     *
     * @param countryId The unique identifier for the country.
     * @param nameOfCountry The name of the country.
     */
    public Country(int countryId, String nameOfCountry){
        this.countryId = countryId;
        this.nameOfCountry = nameOfCountry;
    }

    /**
     * Provides a string representation of the country, showing its name.
     *
     * @return The name of the country.
     */
    @Override
    public String toString() {
        return (nameOfCountry);
    }
}
