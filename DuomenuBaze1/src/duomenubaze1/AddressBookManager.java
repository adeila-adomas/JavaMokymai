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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bronius
 */
public class AddressBookManager {

    public static List<Person> getPersonsList(Connection conn) {
        List<Person> list = new ArrayList<>();

        String queryText = "SELECT * FROM person";
        try {
            PreparedStatement pst = conn.prepareStatement(queryText);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {

                    Person p = new Person();
                    p.setId(rs.getInt("id"));
                    p.setFirstName(rs.getString("first_name"));
                    p.setLastName(rs.getString("last_name"));
                    p.setSalary(rs.getBigDecimal("salary"));
                    p.setBirthDate(rs.getDate("birth_date"));

                    list.add(p);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressBookManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static List<Address> getAddressesList(Connection conn) {
        List<Address> list = new ArrayList<>();
        
        String queryText = "SELECT * FROM address";
        try {
            PreparedStatement pst = conn.prepareStatement(queryText);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {

                   Address addr = new Address();
                   addr.setId(rs.getInt("id"));
                   addr.setPersonId(rs.getInt("person_id"));
                   addr.setAddress(rs.getString("address"));
                   addr.setCity(rs.getString("city"));
                   addr.setPostalCode(rs.getString("postal_code"));
                   
                   list.add(addr);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressBookManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static List<Contact> getContactsList(Connection conn) {
        List<Contact> list = new ArrayList<>();

        String queryText = "SELECT * FROM contact";
        try {
            PreparedStatement pst = conn.prepareStatement(queryText);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {

                   Contact contact = new Contact();
                   contact.setId(rs.getInt("id"));
                   contact.setPersonId(rs.getInt("person_id"));
                   contact.setContact(rs.getString("contact"));
                   contact.setContactType(rs.getString("contact_type"));
                   
                   list.add(contact);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressBookManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    public static Person getPerson(int id, Connection conn) {

        String queryText = "SELECT * FROM person WHERE id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(queryText);
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Person p = new Person();
                    p.setId(rs.getInt("id"));
                    p.setFirstName(rs.getString("first_name"));
                    p.setLastName(rs.getString("last_name"));
                    p.setSalary(rs.getBigDecimal("salary"));
                    p.setBirthDate(rs.getDate("birth_date"));

                    return p;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressBookManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public static Address getAddress(int id, Connection conn) {
        String queryText = "SELECT * FROM address WHERE id = ?";
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
                   
                   return addr;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressBookManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Contact getContact(int id, Connection conn) {
        String queryText = "SELECT * FROM contact WHERE id = ?";
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
                   
                   return contact;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressBookManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
