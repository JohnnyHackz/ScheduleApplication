package helper;

import DAO.DivisionDAO;
import DAO.DivisionDAOImpl;
import javafx.collections.ObservableList;
import model.Division;

public class ListManager {
    /**
     * This is the get filtered divisions method.
     * This method calls the "get divisions by country" method from the Division DAO and returns a list of divisions.
     *
     * @param countryId the country ID in question
     * @return the divisions(s) associated with the country ID
     */
    public static ObservableList<Division> getFilteredDivisions(int countryId){
        DivisionDAO divisionDAO = new DivisionDAOImpl();
        return divisionDAO.getDivByCountries(countryId);
    }
}