package model;

public class Contact {
    private int contactId;
    private String contactsName;
    private String contactsEmail;

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public void setContactsEmail(String contactsEmail) {
        this.contactsEmail = contactsEmail;
    }

    public int getContactId() {return contactId;
    }

    public String getContactsName() {return contactsName;
    }

    public String getContactsEmail() {return contactsEmail;
    }

    public Contact(int contactId, String contactsName, String contactsEmail){
        this.contactId = contactId;
        this.contactsName = contactsName;
        this.contactsEmail = contactsEmail;
    }

    @Override
    public String toString(){
        return ("[" + Integer.toString(contactId) + "]" + contactsName);
    }
}

