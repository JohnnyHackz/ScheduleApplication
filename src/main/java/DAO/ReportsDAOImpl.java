package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Reports;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static helper.JDBC.connection;

/**
 * Implementation class for the Reports Data Access Object interface.
 */
public class ReportsDAOImpl implements ReportsDAO {

    /** List to hold all the reports */
    private ObservableList<Reports> allReports = FXCollections.observableArrayList();

    /**
     * Retrieves all the reports from the database.
     *
     * <p>
     * This method queries the database to group and count appointments based on their start month and type.
     * The result is then transformed into a list of Reports objects and returned.
     * </p>
     *
     * @return an ObservableList containing all the reports
     */
    @Override
    public ObservableList<Reports> getAllReports() {
        try {
            String sql = "Select monthname(start), type, count(*) as total\n" +
                    "From client_schedule.appointments Group by monthname(start), type;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String date = rs.getString("monthname(start)");
                String type = rs.getString("type");
                int total = rs.getInt("total");

                Reports reports = new Reports(date, type, total);
                allReports.add(reports);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allReports;
    }
}
