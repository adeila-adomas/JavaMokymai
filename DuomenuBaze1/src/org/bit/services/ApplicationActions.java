/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bit.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.bit.datamodel.Address;
import org.bit.datamodel.Contact;
import org.bit.datamodel.Person;

/**
 *
 */
public class ApplicationActions {

    public static int requestId() {
        int id = 0;
        try {
            Scanner scant = new Scanner(System.in);
            System.out.print("Enter ID listed above: ");
            id = scant.nextInt();

        } catch (Exception ex) {
            System.out.println("Invalid ID");
        }
        return id;
    }

    /**
     * Input request for Number
     *
     * @param label
     * @return String
     */
    public static int requestNumberInput(String label) {
        int n = 0;

        try {
            Scanner scanInt = new Scanner(System.in);
            System.out.print("Enter " + label + ": ");
            n = scanInt.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
            n = requestNumberInput(label);
        }

        return n;
    }

    /**
     * Input request for Text
     *
     * @param label
     * @return
     */
    public static String requestTextInput(String label) {
        String s = null;

        try {
            Scanner scanInt = new Scanner(System.in);
            System.out.print("Enter " + label + ": ");
            s = scanInt.nextLine();
        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
            s = requestTextInput(label);
        }

        return s;
    }

    public static void ListAllPersons(final Connection conn) {
        List<Person> personsList = DB.getPersonsList(conn);
        for (Person person : personsList) {
            System.out.println(person.toString());
        }
    }

    public static void ListPersonAddresses(Integer personId, final Connection conn) {
        Person p = DB.getPerson(personId, conn);
        List<Address> listAddresses = p.getAddresses(conn);
        System.out.println("List Addresses for " + p.getFirstName() + " " + p.getLastName());
        for (Address addr : listAddresses) {
            System.out.println(addr.toString());
        }
        System.out.println("");
    }

    public static void ListPersonContacts(Integer personId, final Connection conn) {
        Person p = DB.getPerson(personId, conn);
        List<Contact> listContacts = p.getContacts(conn);
        System.out.println("List Contacts for " + p.getFirstName() + " " + p.getLastName());
        for (Contact cont : listContacts) {
            System.out.println(cont.toString());
        }

        System.out.println("");
    }

    public static void RemoveAddress(Integer addressId, final Connection conn) {
        DB.removeAddress(addressId, conn);
    }

    public static void RemoveContact(Integer contactId, final Connection conn) {
        DB.removeContact(contactId, conn);
    }

    public static void RemovePerson(Integer personId, final Connection conn) {
        DB.removePerson(personId, conn);
    }

    public static void AddPerson(final Connection conn) {

        // Ivestis aprasoma atskirais kintamaisiais person sukurimui
        // Galima ju ir neaprasineti atskiraip kaip padaryta AddAdddress (ziureti zemiau)
        String firstName = ApplicationActions.requestTextInput("First Name");
        String lastName = ApplicationActions.requestTextInput("Last Name");

        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(ApplicationActions.requestTextInput("Birth Date [yyyy-MM-dd]"));
        } catch (ParseException e) {
            System.out.println("Error: Invalid date input.");
        }

        BigDecimal salary = new BigDecimal(ApplicationActions.requestNumberInput("Salary"));
        Person newPerson = new Person();
        newPerson.setFirstName(firstName);
        newPerson.setLastName(lastName);
        newPerson.setBirthDate(birthDate);
        newPerson.setSalary(salary);

        DB.AddPerson(newPerson, conn);
    }
    
    public static void AddAddress(int personId, Connection conn) {
        Person p = DB.getPerson(personId, conn);
        if (p == null) {
            System.out.println("No such person found.");
            return;
        }
        
        // Seteriai nustatomi is scannerio reiksmiu
        Address addr = new Address();
        addr.setPersonId(personId);
        addr.setAddress(ApplicationActions.requestTextInput("Address"));
        addr.setCity(ApplicationActions.requestTextInput("City"));
        addr.setPostalCode(ApplicationActions.requestTextInput("Postal Code"));
                
        DB.AddAddress(addr, conn);
    }
    
    public static void AddContact(int personId, Connection conn) {
        Person p = DB.getPerson(personId, conn);
        if (p == null) {
            System.out.println("No such person found.");
            return;
        }
        
        Contact c = new Contact();
        c.setPersonId(personId);
        c.setContact(ApplicationActions.requestTextInput("Contact"));
        c.setContactType(ApplicationActions.requestTextInput("Type"));
        
        DB.AddContact(c, conn);
        
        
    }
}
