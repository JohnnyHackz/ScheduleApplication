package model;

/**
 * Represents a Contact in the system.
 */
public class Contact {
    private int contactId;
    private String contactsName;
    private String contactsEmail;


    /**
     * Constructs a new Contact instance.
     *
     * @param contactId      The unique identifier for the contact.
     * @param contactsName   The name of the contact.
     * @param contactsEmail  The email address of the contact.
     */
    public Contact(int contactId, String contactsName, String contactsEmail){
        this.contactId = contactId;
        this.contactsName = contactsName;
        this.contactsEmail = contactsEmail;
    }

    /**
     * Sets the contact's unique identifier.
     *
     * @param contactId The unique identifier for the contact.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Sets the contact's name.
     *
     * @param contactsName The name of the contact.
     */
    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    /**
     * Sets the contact's email address.
     *
     * @param contactsEmail The email address of the contact.
     */
    public void setContactsEmail(String contactsEmail) {
        this.contactsEmail = contactsEmail;
    }

    /**
     * Retrieves the contact's unique identifier.
     *
     * @return The unique identifier of the contact.
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Retrieves the contact's name.
     *
     * @return The name of the contact.
     */
    public String getContactsName() {
        return contactsName;
    }

    /**
     * Retrieves the contact's email address.
     *
     * @return The email address of the contact.
     */
    public String getContactsEmail() {
        return contactsEmail;
    }

    /**
     * Returns a string representation of the contact, primarily consisting of the contact's ID and name.
     *
     * @return The string representation of the contact.
     */
    @Override
    public String toString() {
        return new StringBuilder()
                .append("[").append(contactId).append("]")
                .append(contactsName)
                .toString();
    }
}


