package model;

public class Contact {
    public int id;
    public String contactsName;
    public String contactsEmail;

    public int getId() {
        return id;
    }

    public String getContactsName() {
        return contactsName;
    }

    public String getContactsEmail() {
        return contactsEmail;
    }

    public Contact(int id, String contactsName, String contactsEmail){
        this.id = id;
        this.contactsName = contactsName;
        this.contactsEmail = contactsEmail;
    }


}
