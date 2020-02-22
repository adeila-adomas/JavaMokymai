/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duomenubaze1;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Person {
    
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private BigDecimal salary = new BigDecimal(0);
            
    public Person() {
        
    }
    
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = "";
        if (this.getBirthDate() != null) {
            dateString = formatter.format(this.getBirthDate());
        } else {
            dateString = "null";
        }
        
        return "[id: " + this.getId() + " " + this.getFirstName() + " " + this.getLastName() + " " + dateString + " " + this.getSalary() + " ]";
    }
    
    // Paskutine uzduotis sukurti metoda personui kuris grazintu jo paties adresus
    public List<Address> getAddresses(Connection conn) {
        List<Address> listAddresses = new ArrayList<>();
        
        String queryText = "SELECT * FROM address WHERE person_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(queryText);
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {

                   Address addr = new Address();
                   addr.setId(rs.getInt("id"));
                   addr.setPersonId(rs.getInt("person_id"));
                   addr.setAddress(rs.getString("address"));
                   addr.setCity(rs.getString("city"));
                   addr.setPostalCode(rs.getString("postal_code"));
                   
                   listAddresses.add(addr);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressBookManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listAddresses;
    } 
    
    // Paskutine uzduotis sukurti metoda personui kuris grazintu jo paties kontaktus
    public List<Contact> getContacts(Connection conn) {
        List<Contact> listContacts = new ArrayList<>();
        
        String queryText = "SELECT * FROM contact WHERE person_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(queryText);
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {

                   Contact contact = new Contact();
                   contact.setId(rs.getInt("id"));
                   contact.setPersonId(rs.getInt("person_id"));
                   contact.setContact(rs.getString("contact"));
                   contact.setContactType(rs.getString("contact_type"));
                   
                   listContacts.add(contact);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressBookManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listContacts;
    } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
