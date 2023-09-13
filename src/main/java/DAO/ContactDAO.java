package DAO;

import javafx.collections.ObservableList;
import model.Contact;

import java.sql.SQLException;

public interface ContactDAO {
    public ObservableList<Contact> getAllContacts();

    public Contact getContact(int contactId) throws SQLException;

    public int updateContact(Contact contact) throws SQLException;

    public int insertContact(String contactName) throws SQLException;

    public int deleteContact(int contactId);

}
