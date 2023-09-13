package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static helper.JDBC.connection;

public class ContactDAOImpl implements ContactDAO{

    ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    public ObservableList<Contact> getAllContacts(){
        try{
            String sql = "Select * from contacts;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
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

    @Override
    public Contact getContact(int contactId) throws SQLException {
        return null;
    }

    @Override
    public int updateContact(Contact contact) throws SQLException {
        return 0;
    }

    @Override
    public int insertContact(String contactName) throws SQLException {
        return 0;
    }

    @Override
    public int deleteContact(int contactId) {
        return 0;
    }
}
