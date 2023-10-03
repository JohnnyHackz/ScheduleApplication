package DAO;

import javafx.collections.ObservableList;
import model.User;

/**
 * The UserDAO interface defines the methods for database operations related to the User.
 * Classes that implement this interface should provide concrete implementations for these methods.
 */
public interface UserDAO {

    /**
     * Fetches all User records from the database.
     *
     * @return an ObservableList containing all User records.
     */
    ObservableList<User> getAllUsers();

    /**
     * Fetches a specific User based on the provided user ID.
     *
     * @param userId the ID of the user to be fetched.
     * @return the User associated with the provided ID, or null if not found.
     */
    User getUser(int userId);


    /**
     * Updates the user name for a specific user in the database.
     *
     * @param currentUserName the current name of the user.
     * @param newUserName the new name to be set.
     * @param password the current password of the user.
     * @return the number of rows affected.
     */
    int updateUserName(String currentUserName, String newUserName, String password);

    /**
     * Deletes a specific user based on the provided user ID.
     *
     * @param userId the ID of the user to be deleted.
     * @return the number of rows affected.
     */
    int deleteUser(int userId);
}

