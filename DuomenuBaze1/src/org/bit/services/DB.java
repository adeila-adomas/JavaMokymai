/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bit.services;

import org.bit.datamodel.Person;
import org.bit.datamodel.Address;
import org.bit.datamodel.Contact;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bronius
 */
public class DB {

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
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static Person getPerson(Integer id, Connection conn) {

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
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Address getAddress(Integer id, Connection conn) {
        String queryText = "SELECT * FROM address WHERE id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(queryText);
            if (id == null) {
                pst.setInt(1, -1);
            } else {
                pst.setInt(1, id);
            }

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
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Contact getContact(Integer id, Connection conn) {
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
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void removeContact(Integer id, Connection conn) {
        try {
            
            Contact c = DB.getContact(id, conn);
            if (c == null) {
                System.out.println("Address not found.");
                return;
            }

            PreparedStatement pst = conn.prepareStatement("delete from contact where id=?");
            if (id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, id);
            }
            pst.execute();
            pst.close();
            System.out.println("Contact " + c.getContact() + " has been deleted");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Delete Person from database and related Addresses and Contacts also.
     * @param id
     * @param conn 
     */
    public static void removePerson(Integer id, Connection conn) {
        try {
            Person p = DB.getPerson(id, conn);
            if (p == null) {
                System.out.println("No such person found.");
                return;
            }
            
            for (Address a : p.getAddresses(conn)) {
                DB.removeAddress(a.getId(), conn);
            }
            
            for (Contact c : p.getContacts(conn)) {
                DB.removeContact(c.getId(), conn);
            }
            
            PreparedStatement pst = conn.prepareStatement("delete from person where id=?");
            pst.setInt(1, id);
            
            pst.execute();
            pst.close();
            System.out.println("Person " + p.getFirstName() + " " + p.getLastName() + " has been deleted.");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void removeAddress(Integer id, Connection conn) {
        try {
            Address addr = DB.getAddress(id, conn);
            if (addr == null) {
                System.out.println("Address not found.");
                return;
            }
            
            PreparedStatement pst = conn.prepareStatement("delete from address where id=?");
            pst.setInt(1, id);
            
            /*
            id tikrinimas sioje vietoje nera reikalingas, kadangi kvieciame metoda: getAddress kur jau patikriname ar ID nera null.
            jeigu getAddress nekviestume sitas tikrinimas butinas
            
            if (id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, id);
            }
            */
            
            pst.execute();
            pst.close();
            System.out.println("Address " + addr.getAddress() + " has been deleted.");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void AddAddress(Address addr, Connection conn) {
        try (PreparedStatement pst = conn.prepareStatement("INSERT INTO address (person_id, address, city, postal_code) VALUES (?, ?, ?, ?)")) {    
            
            pst.setInt(1, addr.getPersonId());
            pst.setString(2, addr.getAddress());
            pst.setString(3, addr.getCity());
            pst.setString(4, addr.getPostalCode());
            
            pst.execute();
            System.out.println("Address has been added");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void AddContact(Contact c, Connection conn) {
        try (PreparedStatement pst = conn.prepareStatement("INSERT INTO contact (person_id, contact, contact_type) VALUES (?, ?, ?)")) {    
            
            pst.setInt(1, c.getPersonId());
            pst.setString(2, c.getContact());
            pst.setString(3, c.getContactType());
            
            pst.execute();
            System.out.println("Contact has been added");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void AddPerson(Person person, Connection conn) {
        
        try (PreparedStatement pst = conn.prepareStatement("INSERT INTO person (first_name, last_name, birth_date, salary) VALUES (?, ?, ?, ?)")) {    
            
            pst.setString(1, person.getFirstName());
            pst.setString(2, person.getLastName());
            
            // Java Date konvertavimas i SQL Date
            pst.setDate(3, new java.sql.Date(person.getBirthDate().getTime()));
            pst.setBigDecimal(4, person.getSalary());
            
            pst.execute();
            System.out.println("Person " + person.getFirstName() + " " + person.getLastName() + " has been added");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
