package DAO;

import javafx.collections.ObservableList;
import model.User;

public interface UserDAO {
    ObservableList<User> getAllUsers();

    User getUser(int userId);

    int updateUserPassword(String userName, String newPassword, String currentPassword);

    int updateUserName(String currentUserName, String newUserName, String password);

    int deleteUser(int userId);
}
