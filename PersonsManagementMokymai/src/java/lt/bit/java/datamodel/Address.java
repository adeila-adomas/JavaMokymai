/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lt.bit.java.datamodel;

import java.util.Objects;
import lt.bit.java.services.ListPersons;

/**
 *
 * @author Bronius
 */
public class Address {
    private Integer id;
    private String address;
    private String city;
    private String postalCode;
    
    public Address() {
        this.id = ListPersons.getNextAddressId();
    }

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (this == null) {
            return false;
        }
        
        if (getClass() != o.getClass()) {
            return false;
        }
        
        final Address other = (Address) o;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}


