/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.java.services;

import java.util.ArrayList;
import java.util.List;
import lt.bit.java.datamodel.Address;

/**
 *
 * @author adoma
 */
public class ListAddresses {
    private final List<Address> ADDRESSES;
    
    public static int nextId = 0;
    
    public ListAddresses() {
        ADDRESSES = new ArrayList<>();
        
    }
    
    public List<Address> getListItems() {
        return ADDRESSES;
    }
    
    public int getNextId() {
        return ADDRESSES.size() + 1;
    }
    
    public Address getAddress(Integer id) {
        if (id == null) {
            return null;
        }
        
        for (int i = 0; i < ADDRESSES.size(); i++) {
            if (id.equals(ADDRESSES.get(i).getId())) {
                return ADDRESSES.get(i);
            }
        }
        
        return null;
    }
    
    public void saveAddress(Address p) {
        if (!ADDRESSES.contains(p)) {
            ADDRESSES.add(p);
        } else {
            updateAddress(p);
        }
    }
    
    public void updateAddress(Address p) {
        int found = -1;
        for (int i = 0; i < ADDRESSES.size(); i++) {
            if (p.getId().equals(ADDRESSES.get(i).getId())) {
                found = i;                
            }
        }
        
        if (found == -1) {
            return;
        }
        
        ADDRESSES.set(found, p);
    }
    
    public void removeAddress(Integer id) {
        if (id == null) {
            return;
        }
        
        int found = -1;
        for (int i = 0; i < ADDRESSES.size(); i++) {
            Address p = ADDRESSES.get(i);
            
            if (id.equals(p.getId())) {
                found = i;
            }
        }
        
        if (found >= 0) {
            ADDRESSES.remove(found);
        }
    }
}
