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

}
