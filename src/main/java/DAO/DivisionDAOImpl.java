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
        // Clear the list to avoid adding duplicate entries upon subsequent calls
        everyDivision.clear();

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
     * @return an {@code ObservableList<Division>} containing the divisions related to the specified country
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


    /**
     * Executes the insert operation on the database for the provided division name and country ID.
     *
     * This is a helper method that performs the actual SQL insert. It prepares and executes
     * an SQL statement to add a new division with the given name and associated country ID.
     *
     * @param divisionName the name of the division to be inserted
     * @param countryId the ID of the country to which the division belongs
     * @return the number of rows affected by the insert operation, or 0 if an exception occurred
     */
    private int executeInsert(String divisionName, int countryId) {
        String sql = "INSERT INTO first_level_divisions (Division, Country_ID) VALUES(?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, divisionName);
            ps.setInt(2, countryId);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error inserting division: " + e.getMessage());
            return 0;
        }
    }


    /**
     * Logs the result of the insert operation.
     *
     * This method provides feedback on whether the insert operation was successful or not.
     * It logs a success message if rows were affected (i.e., the insert was successful),
     * otherwise, it logs a failure message.
     *
     * @param rowsAffected the number of rows that were affected by the last insert operation
     */
    private void logInsertResult(int rowsAffected) {
        if (rowsAffected > 0) {
            System.out.println("Division INSERT was successful!");
        } else {
            System.out.println("Division INSERT failed!");
        }
    }


    /**
     * Executes the update operation on the database for the division's name.
     *
     * This is a helper method that performs the actual SQL update. It prepares and executes
     * an SQL update statement to modify the division's name for a specific country.
     *
     * @param currentDivisionName the current name of the division to be updated
     * @param countryId the ID of the country where the division resides
     * @param newDivisionName the new name to be set for the division
     * @return the number of rows affected by the update, or 0 if an exception occurred
     */
    private int executeUpdate(String currentDivisionName, int countryId, String newDivisionName) {
        String sql = "UPDATE first_level_divisions SET Division=? WHERE Division=? AND Country_ID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newDivisionName);
            ps.setString(2, currentDivisionName);
            ps.setInt(3, countryId);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating division name: " + e.getMessage());
            return 0;
        }
    }


    /**
     * Logs the result of the update operation.
     *
     * This method provides feedback on whether the update operation was successful or not.
     * It logs a success message if rows were affected (i.e., the update was successful),
     * otherwise, it logs a failure message.
     *
     * @param currentDivisionName the current name of the division
     * @param newDivisionName the new name that was attempted to set for the division
     * @param rowsAffected the number of rows that were affected by the last update operation
     */
    private void logUpdateResult(String currentDivisionName, String newDivisionName, int rowsAffected) {
        if (rowsAffected > 0) {
            System.out.println(currentDivisionName + " division UPDATE was successful!");
            System.out.println("New division name: " + newDivisionName);
        } else {
            System.out.println(currentDivisionName + " division name UPDATE Failed!");
        }
    }
}