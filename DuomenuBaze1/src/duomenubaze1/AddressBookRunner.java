/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duomenubaze1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 *
 * @author Bronius
 */
public class AddressBookRunner {
    public static void main(String[] args) throws ClassNotFoundException {
        // MySQL driveriu uzkrovimas
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        
        
        
        // Prisijungimas prie duomenu bazes
        // Prisijungimas kiekviena kart kartojamas mokymo tikslais
        // Prisijungimas galetu tik viena karta padaromas ir try bloke naudojamas tik 'conn'
        // Blocko pabaigoje automatiskai atsijungia  nuo mysql del to kad naudojama try-with-resource
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?serverTimezone=UTC", "root", "Harley2ride!")) {
            System.out.println("Persons: ");   
            
            List<Person> personsList = AddressBookManager.getPersonsList(conn);
            for (Person person : personsList) {
                System.out.println(person.toString());
            }
        } catch (Exception e) {
            
            System.err.println("Klaida: " + e.getMessage());
        }
        
        System.out.println("===============");
        
        
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?serverTimezone=UTC", "root", "Harley2ride!")) {
            System.out.println("Addresses: ");
            List<Address> addressList = AddressBookManager.getAddressesList(conn);
            for (Address addr : addressList) {
                System.out.println(addr.toString());
            }
        } catch (Exception e) {
            
            System.err.println("Klaida: " + e.getMessage());
        }
        
        System.out.println("===============");
        
        
        
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?serverTimezone=UTC", "root", "Harley2ride!")) {
            System.out.println("List Contacts: ");
            List<Contact> contactsList = AddressBookManager.getContactsList(conn);
            for (Contact contact : contactsList) {
                System.out.println(contact.toString());
            }
        } catch (Exception e) {
            System.err.println("Klaida: " + e.getMessage());
        }
        
        System.out.println("===============");
        
        
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?serverTimezone=UTC", "root", "Harley2ride!")) {
            System.out.println("Person by ID: ");
            Person p1 = AddressBookManager.getPerson(1, conn);
            System.out.println(p1);
            
            System.out.println("===============");
            
            List<Address> listAddresses = p1.getAddresses(conn);
            System.out.println("Addresses of " + p1.getFirstName() + " " + p1.getLastName());
            for (Address addr : listAddresses) {
                System.out.println(addr.toString());
            }
            
            System.out.println("===============");
            
            List<Contact> listContacts = p1.getContacts(conn);
            System.out.println("Contacts of " + p1.getFirstName() + " " + p1.getLastName());
            for (Contact cont : listContacts) {
                System.out.println(cont.toString());
            }
            
        } catch (Exception e) {
            System.err.println("Klaida: " + e.getMessage());
        }
        System.out.println("===============");
        
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?serverTimezone=UTC", "root", "Harley2ride!")) {
            System.out.println("ADDRESS by ID: ");
            Address addr1 = AddressBookManager.getAddress(1, conn);
            System.out.println(addr1.toString());
        } catch (Exception e) {
            System.err.println("Klaida: " + e.getMessage());
        } 
        System.out.println("===============");
        
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?serverTimezone=UTC", "root", "Harley2ride!")) {
            Contact cont1 = AddressBookManager.getContact(1, conn);
            System.out.println("CONTACT by ID: ");  
            Contact contact = AddressBookManager.getContact(1, conn);
            System.out.println(cont1);
        } catch (Exception e) {
            System.err.println("Klaida: " + e.getMessage());
        }
        System.out.println("===============");
        
        
    }
}
