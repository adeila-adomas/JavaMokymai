/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.java.services;

import java.util.ArrayList;
import java.util.List;
import lt.bit.java.datamodel.Contact;


/**
 *
 * @author adoma
 */
public class ListContacts {
    private List<Contact> CONTACTS = new ArrayList<>();
    
    public  List<Contact> getListItems() {
        return CONTACTS;
    }
    
    public int getNextId() {
        return CONTACTS.size() + 1;
    }
    
    public Contact getContact(Integer id) {
        if (id == null) {
            return null;
        }
        
        for (int i = 0; i < CONTACTS.size(); i++) {
            if (id.equals(CONTACTS.get(i).getId())) {
                return CONTACTS.get(i);
            }
        }
        
        return null;
    }
    
    public void saveContact(Contact p) {
        if (!CONTACTS.contains(p)) {
            CONTACTS.add(p);
        } else {
            updateContact(p);
        }
    }
    
    public void updateContact(Contact p) {
        int found = -1;
        for (int i = 0; i < CONTACTS.size(); i++) {
            if (p.getId().equals(CONTACTS.get(i).getId())) {
                found = i;                
            }
        }
        
        if (found == -1) {
            return;
        }
        
        CONTACTS.set(found, p);
    }
    
    public void removeContact(Integer id) {
        if (id == null) {
            return;
        }
        
        int found = -1;
        for (int i = 0; i < CONTACTS.size(); i++) {
            Contact p = CONTACTS.get(i);
            
            if (id.equals(p.getId())) {
                found = i;
            }
        }
        
        if (found >= 0) {
            CONTACTS.remove(found);
        }
    }
}
