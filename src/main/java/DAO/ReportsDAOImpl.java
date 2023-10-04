package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Reports;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
     *
     * This method queries the database to group and count appointments based on their start month and type.
     * The result is then transformed into a list of Reports objects and returned.
     *
     *
     * @return an ObservableList containing all the reports
     */
    @Override
    public ObservableList<Reports> getAllReports() {
        String sql = "SELECT monthname(start) AS month, type, count(*) as total " +
                "FROM client_schedule.appointments " +
                "GROUP BY monthname(start), type";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reports report = buildReportFromResultSet(rs);
                allReports.add(report);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allReports;
    }

    /**
     * Builds and returns a Reports object from the provided ResultSet.
     *
     * @param rs The ResultSet from which the report data is to be fetched.
     * @return The Reports object.
     * @throws SQLException If there's an issue accessing the ResultSet data.
     */
    private Reports buildReportFromResultSet(ResultSet rs) throws SQLException {
        String date = rs.getString("month");
        String type = rs.getString("type");
        int total = rs.getInt("total");

        return new Reports(date, type, total);
    }
}
