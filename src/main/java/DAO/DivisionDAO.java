package DAO;

import javafx.collections.ObservableList;
import model.Division;

/**
 * Interface for Data Access Object operations related to Division entities.
 */
public interface DivisionDAO {

    /**
     * Fetches all divisions from the data source.
     *
     * @return An ObservableList containing all divisions.
     */
    ObservableList<Division> getAllDivisions();

    /**
     * Retrieves a specific division based on the provided division ID.
     *
     * @param divisionId The ID of the division to fetch.
     * @return The Division object with the specified ID, or null if not found.
     */
    Division getDivisionId(int divisionId);

    /**
     * Retrieves all divisions associated with a specific country ID.
     *
     * @param countryId The ID of the country to fetch its divisions.
     * @return An ObservableList containing divisions of the specified country.
     */
    ObservableList<Division> getDivByCountries(int countryId);

    /**
     * Adds a new division with the specified name and associated country ID.
     *
     * @param divisionName The name of the new division to add.
     * @param countryId The ID of the country associated with the new division.
     * @return The ID of the added division.
     */
    int addDivisionByName(String divisionName, int countryId);

    /**
     * Updates the name of an existing division based on its current name and associated country ID.
     *
     * @param currentDivisionName The current name of the division to update.
     * @param countryId The ID of the country associated with the division.
     * @param newDivisionName The new name to set for the division.
     * @return The number of rows affected by the update.
     */
    int updateDivNamebyCountry(String currentDivisionName, int countryId, String newDivisionName);
}
