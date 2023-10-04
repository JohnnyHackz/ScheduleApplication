package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static helper.JDBC.connection;
/**
 * DivisionDAOImpl provides the implementation of the DivisionDAO interface.
 */
public class DivisionDAOImpl implements DivisionDAO {

    private ObservableList<Division> everyDivision = FXCollections.observableArrayList();

    /**
     * Fetches all divisions along with their associated country information from the database.
     *
     * @return an ObservableList of all divisions.
     */
    @Override
    public ObservableList<Division> getAllDivisions() {
        // Clear the list to avoid adding duplicate entries

        String sql = "SELECT first_level_divisions.Division_ID, first_level_divisions.Division, " +
                "countries.Country_ID, countries.Country " +
                "FROM client_schedule.first_level_divisions " +
                "JOIN client_schedule.countries " +
                "ON first_level_divisions.Country_ID = countries.Country_ID";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int divisionId = resultSet.getInt("Division_ID");
                String divisionName = resultSet.getString("Division");
                int countryId = resultSet.getInt("Country_ID");
                String countryName = resultSet.getString("Country");
                Division division = new Division(divisionId, divisionName, countryId, countryName);
                everyDivision.add(division);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return everyDivision;
    }


    /**
     * Retrieves a specific division by its ID from the database.
     *
     * @param divisionId the ID of the division to be fetched
     * @return a Division object that will represent the division if found; will be null otherwise
     */
    @Override
    public Division getDivisionId(int divisionId) {
        String sql = "SELECT Division_ID, Division, Country_ID, Country " +
                "FROM client_schedule.first_level_divisions WHERE Division_ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, divisionId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("Division_ID");
                    String divisionName = resultSet.getString("Division");
                    int countryId = resultSet.getInt("Country_ID");
                    String countryName = resultSet.getString("Country");
                    return new Division(id, divisionName, countryId, countryName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Retrieves a list of divisions associated with a specific country.
     *
     * This method queries the database to obtain divisions based on a given country ID.
     * The results include details about the divisions as well as their associated country.
     *
     * @param countryId the ID of the country for which the divisions are to be fetched
     * @return anObservableList containing the divisions related to the specified country
     * @throws SQLException if any database operation fails during the process
     */
    @Override
    public ObservableList<Division> getDivByCountries(int countryId) {
        ObservableList<Division> divisions = FXCollections.observableArrayList();

        String sql = "SELECT first_level_divisions.Division_ID, first_level_divisions.Division, " +
                "countries.Country_ID, countries.Country " +
                "FROM client_schedule.first_level_divisions " +
                "JOIN client_schedule.countries " +
                "ON first_level_divisions.Country_ID = countries.Country_ID " +
                "WHERE first_level_divisions.Country_ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, countryId);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int divisionId = rs.getInt("Division_ID");
                    String divisionName = rs.getString("Division");
                    int localCountryId = rs.getInt("Country_ID");
                    String countryName = rs.getString("Country");
                    Division division = new Division(divisionId, divisionName, localCountryId, countryName);
                    divisions.add(division);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisions;
    }


}