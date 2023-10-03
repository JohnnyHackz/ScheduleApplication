package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DivisionData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static helper.JDBC.connection;

/**
 * The DivisionDataDAOImpl class provides an implementation of the DivisionDataDAO interface.
 * This class allows for database operations related to DivisionData.
 */
public class DivisionDataDAOImpl implements DivisionDataDAO {

    /**
     * Fetches all the DivisionData records from the database.
     * Each record provides information about the division name and the total number of customers associated with that division.
     *
     * @return an ObservableList containing all the DivisionData records.
     */
    @Override
    public ObservableList<DivisionData> getAllDivisionData() {
        ObservableList<DivisionData> allDivisionData = FXCollections.observableArrayList();

        try {
            String sql = "SELECT\n" +
                    "  first_level_divisions.Division AS FirstLevelDivision,\n" +
                    "  COUNT(customers.Customer_ID) AS TotalCustomers\n" +
                    "FROM\n" +
                    "  client_schedule.first_level_divisions AS first_level_divisions\n" +
                    "JOIN\n" +
                    "  client_schedule.customers AS customers ON first_level_divisions.Division_ID = customers.Division_ID\n" +
                    "GROUP BY\n" +
                    "  first_level_divisions.Division;\n";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String divisionName = resultSet.getString("FirstLevelDivision");
                int totalCustomers = resultSet.getInt("TotalCustomers");

                DivisionData divisionData = new DivisionData(divisionName, totalCustomers);
                allDivisionData.add(divisionData);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return allDivisionData;
    }
}
