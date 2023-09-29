package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static helper.JDBC.connection;

public class UserDAOImpl implements UserDAO {

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
