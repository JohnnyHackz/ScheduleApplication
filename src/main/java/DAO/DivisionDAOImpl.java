package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static helper.JDBC.connection;

public class DivisionDAOImpl implements DivisionDAO {

    ObservableList<Division> divIdByCountry = FXCollections.observableArrayList();
    ObservableList<Division> everyDivision = FXCollections.observableArrayList();



    @Override
    public ObservableList<Division> getAllDivisions() {
        try {
            String sql = "SELECT * FROM client_schedule.first_level_divisions, client_schedule.countries WHERE first_level_divisions.Country_ID "
                    + "= countries.Country_ID";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
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

    @Override
    public Division getDivisionId(int divisionId) {
        try {
            String sql = "SELECT * FROM client_schedule.first_level_divisions WHERE Division_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, divisionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Division divResult = null;
            if (resultSet.next()) {
                divisionId = resultSet.getInt("Division_ID");
                String divisionName = resultSet.getString("Division");
                int countryId = resultSet.getInt("Country_ID");
                String countryName = resultSet.getString("Country");
                divResult = new Division(divisionId, divisionName, countryId, countryName);
            }
            return divResult;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public ObservableList<Division> getDivByCountries(int countryId) {
        try {
            String sql = "SELECT * FROM client_schedule.first_level_divisions, client_schedule.countries WHERE first_level_divisions.Country_ID = countries.Country_ID AND first_level_divisions.Country_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, countryId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Division division = new Division(divisionId, divisionName, countryId, countryName);
                divIdByCountry.add(division);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divIdByCountry;
    }
        @Override
    public int addDivisionByName(String divisionName, int countryId) {
            int rowsAffected = 0;
            try {
                String sql = "INSERT INTO first_level_divisions (Division, Country_ID) VALUES(?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, divisionName);
                ps.setInt(2, countryId);
                rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Division INSERT was successful!");
                } else {
                    System.out.println("Division INSERT failed!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            return rowsAffected;

    }

    @Override
    public int updateDivNamebyCountry(String currentDivisionName, int countryId, String newDivisionName) {
        int rowsAffected = 0;
            try {
                String sql = "UPDATE first_level_divisions SET Division=? WHERE Division=? AND Country_ID=?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, newDivisionName);
                ps.setString(2, currentDivisionName);
                ps.setInt(3, countryId);
                rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println(currentDivisionName + " division UPDATE was successful!");
                    System.out.println("New division name: " + newDivisionName);
                } else {
                    System.out.println(currentDivisionName + " division name UPDATE Failed!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            return rowsAffected;
    }

    @Override
    public void updateDivCountry(String divisionName, int currentCountryId, int newCountryId) {

    }

    @Override
    public void deleteDivisonIdAndName(int divisionId, String divisionName) {

    }
}
