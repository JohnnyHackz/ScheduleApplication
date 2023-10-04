package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static helper.JDBC.connection;

/**
 * Implementation of the CountryDAO interface for managing operations related to the Country model.
 */
public class CountryDAOImpl implements CountryDAO {

    /**
     * List to hold all countries retrieved from the database.
     */
    private ObservableList<Country> allCountries = FXCollections.observableArrayList();

    /**
     * List to hold countries based on specific queries.
     */
    private ObservableList<Country> countryList = FXCollections.observableArrayList();

    /**
     * Retrieves all countries from the database.
     *
     * @return an ObservableList containing all country objects.
     */
    @Override
    public ObservableList<Country> getAllCountries() {
        try {
            // Query to select all countries from the database.
            String sql = "SELECT * FROM client_schedule.countries";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Loop through the result set and add each country to the list.
            while(rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String nameOfcountry = rs.getString("Country");
                Country country = new Country(countryId, nameOfcountry);
                allCountries.add(country);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allCountries;
    }

    /**
     * Retrieves a specific country based on its ID.
     *
     * @param countryId the ID of the country to be retrieved.
     * @return the Country object associated with the given ID, or null if not found.
     */
    @Override
    public Country getCountryId(int countryId) {
        try {
            // Query to select a country based on its ID.
            String sql = "SELECT * FROM client_schedule.countries WHERE Country_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, countryId);
            ResultSet rs = ps.executeQuery();

            Country countryRS = null;

            // Loop through the result set.
            while(rs.next()) {
                countryId = rs.getInt("Country_ID");
                String nameOfcountry = rs.getString("Country");
                countryRS= new Country(countryId, nameOfcountry);
                countryList.add(countryRS);
            }

            return countryRS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a specific country based on its name.
     *
     * @param nameOfCountry the name of the country to be retrieved.
     * @return null, as this method is currently not implemented.
     */
    @Override
    public Country getCountryByName(String nameOfCountry) {
        return null;
    }

    /**
     * Retrieves countries based on their ID.
     *
     * @return null, as this method is currently not implemented.
     */
    @Override
    public ObservableList<Country> getCountryById() {
        return null;
    }
}

