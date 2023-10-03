package DAO;

import javafx.collections.ObservableList;
import model.Country;

/**
 * Interface for managing operations related to the Country model.
 */
public interface CountryDAO {

    /**
     * Retrieves all countries from the data source.
     *
     * @return an ObservableList containing all country objects.
     */
    public ObservableList<Country> getAllCountries();

    /**
     * Retrieves a specific country based on its ID.
     *
     * @param countryId the ID of the country to be retrieved.
     * @return the Country object associated with the given ID, or null if not found.
     */
    public Country getCountryId(int countryId);

    /**
     * Retrieves a specific country based on its name.
     *
     * @param nameOfCountry the name of the country to be retrieved.
     * @return the Country object with the given name, or null if not found.
     */
    public Country getCountryByName(String nameOfCountry);

    /**
     * Retrieves countries based on their ID.
     *
     * @return an ObservableList containing Country objects associated with the given IDs.
     */
    public ObservableList<Country> getCountryById();

}
