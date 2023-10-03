package DAO;

import javafx.collections.ObservableList;
import model.Reports;
/**
 * The ReportsDAO interface defines the methods for database operations related to the Reports.
 * Classes that implement this interface should provide concrete implementations for these methods.
 */
public interface ReportsDAO {

    /**
     * Fetches all the Reports records from the database.
     *
     * @return an ObservableList containing all the Reports records.
     */
    public ObservableList<Reports> getAllReports();

}
