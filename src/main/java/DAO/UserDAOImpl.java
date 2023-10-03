package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static helper.JDBC.connection;

/**
 * Implementation class for the User Data Access Object interface.
 */
public class UserDAOImpl implements UserDAO {

    /**
     * Retrieves all users from the database.
     *
     * @return an ObservableList containing all the users
     */
    ObservableList<User> everyUser = FXCollections.observableArrayList();
    public ObservableList<User> getAllUsers() {
        try{
            String sql = "Select * From users";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password");
                User user = new User(userId, userName, userPassword);
                everyUser.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return everyUser;
    }


    /**
     * Retrieves a specific user based on the provided User ID.
     *
     * @param userId The ID of the user to retrieve
     * @return a User object or null if the user does not exist
     */
    public User getUser(int userId) {
        try{
            String sql = "Select * From users Where User_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ps.setInt(1, userId);

            User user = null;
            if(rs.next()){
                userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password");
                user = new User(userId, userName, userPassword);
            }return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the user name of a specific user, identified by the current user name and password.
     *
     * @param currentUserName The current name of the user
     * @param newUserName     The new user name
     * @param password        The password of the user (for verification)
     * @return the number of rows affected by the update
     */
    public int updateUserName(String currentUserName, String newUserName, String password) {
        try {
            String query = "UPDATE users SET User_Name=? WHERE User_Name=? AND Password=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newUserName);
            statement.setString(2, currentUserName);
            statement.setString(3, password);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(currentUserName + " username UPDATE was successful!");
                System.out.println("New username: " + newUserName);
            } else {
                System.out.println(currentUserName + " username UPDATE Failed!");
            }
            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating username.", e);
        }
    }

    /**
     * Deletes a user based on the provided User ID.
     *
     * @param userId The ID of the user to delete
     * @return the number of rows affected by the delete
     */
    public int deleteUser(int userId) {
        try {
            String query = "DELETE FROM users WHERE User_ID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User number " + userId + " was successfully deleted!");
            } else {
                System.out.println("User DELETE failed!");
            }
            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user.", e);
        }
    }
}
