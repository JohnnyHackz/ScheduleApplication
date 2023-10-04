package helper;

import DAO.DivisionDAO;
import DAO.DivisionDAOImpl;
import javafx.collections.ObservableList;
import model.Division;

/**
 * Utility class that provides list management capabilities.
 *
 * The class offers helper methods for filtering and obtaining lists from the data access layer.
 * This can include, for example, fetching divisions based on a given country ID.
 *
 */
public class ListManager {


    /**
     * Retrieves a filtered list of divisions based on a specified country ID.
     *
     * This method utilizes the DivisionDAO to fetch divisions that are associated with the given country ID.
     * <
     *
     * @param countryId The ID of the country for which divisions need to be fetched.
     * @return An ObservableList of Division objects associated with the provided country ID.
     */
    public static ObservableList<Division> getFilteredDivisions(int countryId){
        DivisionDAO divisionDAO = new DivisionDAOImpl();
        return divisionDAO.getDivByCountries(countryId);
    }
}