package DAO;

import javafx.collections.ObservableList;
import model.DivisionData;

/**
 * Interface for Data Access Object related to DivisionData.
 * Provides an abstraction for database operations related to DivisionData.
 */
public interface DivisionDataDAO {

    /**
     * Fetches all the DivisionData records from the database.
     *
     * @return an ObservableList containing all the DivisionData records.
     */
    ObservableList<DivisionData> getAllDivisionData();
}
