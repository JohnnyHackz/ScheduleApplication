package DAO;

import javafx.collections.ObservableList;
import model.Contact;
import java.sql.SQLException;

/**
 * ContactDAO interface for managing operations related to the Contact model.
 */
public interface ContactDAO {

    /**
     * Retrieves all contacts.
     *
     * @return an ObservableList containing all contacts.
     */
    ObservableList<Contact> getAllContacts();

    /**
     * Retrieves a specific contact based on its ID.
     *
     * @param contactId the ID of the contact to be retrieved.
     * @return the Contact object associated with the given ID.
     * @throws SQLException if a database access error occurs.
     */
    Contact getContact(int contactId) throws SQLException;

    /**
     * Updates the details of a given contact.
     *
     * @param contact the Contact object containing updated information.
     * @return an integer indicating the number of rows affected in the database.
     * @throws SQLException if a database access error occurs.
     */
    int updateContact(Contact contact) throws SQLException;

    /**
     * Inserts a new contact into the database.
     *
     * @param contactName the name of the contact to be inserted.
     * @return an integer representing the generated ID for the inserted contact.
     * @throws SQLException if a database access error occurs.
     */
    int insertContact(String contactName) throws SQLException;

    /**
     * Deletes a specific contact based on its ID.
     *
     * @param contactId the ID of the contact to be deleted.
     * @return an integer indicating the number of rows affected in the database.
     */
    int deleteContact(int contactId);

}

