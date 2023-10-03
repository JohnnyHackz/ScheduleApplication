package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static helper.JDBC.connection;

/**
 * Implementation of the ContactDAO interface for managing operations related to the Contact model.
 */
public class ContactDAOImpl implements ContactDAO {

    /**
     * List containing all contact objects.
     */
    private ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    /**
     * Retrieves all contacts from the database.
     *
     * @return an ObservableList containing all contacts.
     */
    @Override
    public ObservableList<Contact> getAllContacts() {
        try {
            if (connection == null || !connection.isValid(5)) {
                // Handle the situation where the connection is null or not valid
            }

            String sql = "Select * from contacts;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactsName = rs.getString("Contact_Name");
                String contactsEmail = rs.getString("Email");
                Contact contact = new Contact(contactId, contactsName, contactsEmail);
                allContacts.add(contact);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allContacts;
    }

    /**
     * Retrieves a specific contact based on its ID.
     *
     * @param contactId the ID of the contact to be retrieved.
     * @return the Contact object associated with the given ID.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public Contact getContact(int contactId) throws SQLException {
        return null; // Placeholder implementation
    }

    /**
     * Updates the details of a given contact in the database.
     *
     * @param contact the Contact object containing updated information.
     * @return an integer indicating the number of rows affected in the database.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public int updateContact(Contact contact) throws SQLException {
        return 0; // Placeholder implementation
    }

    /**
     * Inserts a new contact into the database.
     *
     * @param contactName the name of the contact to be inserted.
     * @return an integer representing the generated ID for the inserted contact.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public int insertContact(String contactName) throws SQLException {
        return 0; // Placeholder implementation
    }

    /**
     * Deletes a specific contact from the database based on its ID.
     *
     * @param contactId the ID of the contact to be deleted.
     * @return an integer indicating the number of rows affected in the database.
     */
    @Override
    public int deleteContact(int contactId) {
        return 0; // Placeholder implementation
    }
}
