package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static helper.JDBC.connection;

public class CountryDAOImpl implements CountryDAO {

    ObservableList<Country> allCountries = FXCollections.observableArrayList();

    ObservableList<Country> countryList = FXCollections.observableArrayList();

    @Override
    public ObservableList<Country> getAllCountries(){
        try {
            //ObservableList<Country> divisions = FXCollections.observableArrayList();
            String sql = "SELECT * FROM client_schedule.countries";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
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

    @Override
    public Country getCountryId(int countryId) {
        try {
            String sql = "SELECT * FROM client_schedule.countries WHERE Country_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, countryId);
            ResultSet rs = ps.executeQuery();
            Country countryRS = null;
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

    @Override
    public Country getCountryByName(String nameOfCountry) {
        return null;
    }

    @Override
    public ObservableList<Country> getCountryById() {return null;}



}
