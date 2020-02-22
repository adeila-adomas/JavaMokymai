/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duomenubaze1;

/**
 *
 * @author Bronius
 */
public class Contact {
    
    private int id;
    private int personId;
    private String contact;
    private String contactType;

    public Contact() {
        
    }
    
    @Override
    public String toString() {
        return "[id: " + this.getId() + " person: " + this.getPersonId() + " " + this.getContact() +" "+ this.getContactType() + " ]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }
    
    
}
