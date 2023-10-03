package model;

/**
 * Represents a user with attributes: userId, userName, and userPassword.
 * This class encapsulates the details of a user, capturing the unique identifier,
 * the name, and the password associated with a user.
 */
public class User {
    private int userId;
    private String userName;
    private String userPassword;

    /**
     * Gets the unique identifier of the user.
     *
     * @return The user's unique identifier.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param userId The unique identifier to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the name of the user.
     *
     * @return The user's name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the name of the user.
     *
     * @param userName The name to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the password associated with the user.
     *
     * @return The user's password.
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the password for the user.
     *
     * @param userPassword The password to set.
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Constructs a User object with the specified values.
     *
     * @param userId       The unique identifier for the user.
     * @param userName     The name of the user.
     * @param userPassword The password associated with the user.
     */
    public User(int userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     * Overrides built-in toString() method for display purposes.
     *
     * @return The name of the user.
     */
    @Override
    public String toString() {
        return userName;
    }
}
