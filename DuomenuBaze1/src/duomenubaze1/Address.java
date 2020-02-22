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
public class Address {
    
    private int id;
    private int personId;
    
    private String address;
    private String city;
    private String postalCode;
    
    public Address() {
        
    }
    
    @Override
    public String toString() {
        return "[id: " + this.getId() + " person: " + this.getPersonId() + " " + this.getAddress() +" "+ this.getCity() + " " + this.getPostalCode() + " ]";
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }  
    
}
