package DAO;

import javafx.collections.ObservableList;
import model.User;

public interface UserDAO {
    ObservableList<User> getAllUsers();

    User getUser(int userID);

    int updateUserPassword(String userName, String newPassword, String currentPassword);

    int updateUserName(String currentUserName, String newUserName, String password);

    int deleteUser(int userId);
}



/*package DAO;

import javafx.collections.ObservableList;
import model.User;

public abstract class UserDAO {
    public ObservableList<User> getAllUsers;

    public User getUser(int userID) {
        return null;
    }

    public int updateUserPassword() {
        return 0;
    }

    public int updateUsersName() {
        return 0;
    }

    public int deleteUser() {
        return 0;
    }
}*/
