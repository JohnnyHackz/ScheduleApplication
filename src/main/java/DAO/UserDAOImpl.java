package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private final Connection connection;
    private final ObservableList<User> allUsers;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
        this.allUsers = FXCollections.observableArrayList();
        // Load all users from the database when the DAO is created
        loadAllUsers();
    }

    private void loadAllUsers() {
        try {
            String query = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int userId = result.getInt("User_ID");
                String userName = result.getString("User_Name");
                String password = result.getString("Password");
                User user = new User(userId, userName, password);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error loading users from the database.", e);
        }
    }

    public ObservableList<User> getAllUsers() {
        return FXCollections.unmodifiableObservableList(allUsers);
    }

    public User getUser(int userId) {
        for (User user : allUsers) {
            if (user.getUserID() == userId) {
                return user;
            }
        }
        return null;
    }

    public int updateUserPassword(String userName, String newPassword, String currentPassword) {
        try {
            String query = "UPDATE users SET Password=? WHERE User_Name=? AND Password=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newPassword);
            statement.setString(2, userName);
            statement.setString(3, currentPassword);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(userName + " password UPDATE was successful!");
            } else {
                System.out.println(userName + " password UPDATE Failed!");
            }
            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating password.", e);
        }
    }

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
