/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.java.services;

import lt.bit.java.datamodel.Address;
import lt.bit.java.datamodel.Person;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import lt.bit.java.datamodel.Contact;


public class ListPersons {
    
    private static final List<Person> PERSONS = new ArrayList<>();
    
    /* Pradiniai saraso duomenys */
    static {
        
        Person p1 = new Person();
        p1.setId(1);
        p1.setFirstName("Zmogas");
        p1.setLastName("Zmogeliukas");
        p1.setAge(25);
        p1.setSalary(new BigDecimal(2000));
        p1.setBirthDate(new Date());
        
        PERSONS.add(p1);
        
        Contact c1 = new Contact();
        c1.setId(1);
        c1.setContact("mail@mail.lt");
        c1.setType("Email");
        
        p1.getContacts().getListItems().add(c1);
        

        p1 = new Person();
        p1.setId(2);
        p1.setFirstName("Papuga");
        p1.setLastName("Papugiukas");
        p1.setAge(23);
        p1.setSalary(new BigDecimal(0));
        p1.setBirthDate(new Date());
         
        Address d1 = new Address();
        d1.setId(1);
        d1.setAddress("Sauletekio al. 17");
        d1.setCity("Vilnius");
        d1.setPostalCode("09303");
        
        Address d2 = new Address();
        d2.setId(2);
        d2.setAddress("Baznycios al. 1");
        d2.setCity("Vilnius");
        d2.setPostalCode("kapines");
        
        p1.getAddresses().getListItems().add(d1);
        p1.getAddresses().getListItems().add(d2);
        
        c1 = new Contact();
        c1.setId(2);
        c1.setContact("865278208");
        c1.setType("Mobile");
        
        p1.getContacts().getListItems().add(c1);
        
        PERSONS.add(p1);
    }
    
    public static List<Person> getListItems() {
        return PERSONS;
    }
    
    public static int getNextId() {
        // OLD
        // return PERSONS.size() + 1;
        
        // ieskom didziausio ID sarase
        Person maxPerson = PERSONS
                .stream()
                .max(Comparator.comparing(Person::getId))
                .orElse(null);
        
        // Jeigu lambda rado objekta graziname jo ID + 1;
        if (maxPerson != null) {
            return (int) maxPerson.getId() + 1;
        } else {
            // Jeigu nerado jokio objekto, kaip pvz irasant pirma irasa kada dar sarasa tuscias, graziname -1
            return 1;
        }
    }
    
    /***
     * Calculate next id for new address object
     * Iterated all persons to sum how many addresses contains
     * 
     * @return 
     */
    public static int getNextAddressId() {
        
        // listas surinkti visiems adresams is visu personu
        ArrayList<Address> tempAddr = new ArrayList<>();
        
        // skaitome visus personus ir surenkame adresus
        for (Person p : PERSONS) {
            tempAddr.addAll(p.getAddresses().getListItems());
        }
        
        // ieskome max id is listo aprasyto auksciau.
        Address maxAddr = tempAddr
                .stream()
                .max(Comparator.comparing(Address::getId))
                .orElse(null);
        
        if (maxAddr != null) {
            return (int) maxAddr.getId() + 1;
        } else {
            // Jeigu nerado jokio objekto, kaip pvz irasant pirma irasa kada dar sarasa tuscias, graziname 1
            return 1;
        }
        
    }
    
    /***
     * Calculate next id for new contact object
     * Iterated all persons to sum how many contacts contains
     * 
     * @return 
     */
    public static int getNextContactId() {
        int nextId = 0;
        
        for (Person p : PERSONS) {
            nextId += p.getContacts().getListItems().size();
        }
        
        return nextId + 1;
    }
    
    public static Person getPerson(Integer id) {
        if (id == null) {
            return null;
        }
        
        for (int i = 0; i < PERSONS.size(); i++) {
            if (id.equals(PERSONS.get(i).getId())) {
                return PERSONS.get(i);
            }
        }
        
        return null;
    }
    
    public static void savePerson(Person p) {
        if (!PERSONS.contains(p)) {
            PERSONS.add(p);
        } else {    
            updatePerson(p);
        }
    }
    
    public static void updatePerson(Person p) {
        int found = -1;
        for (int i = 0; i < PERSONS.size(); i++) {
            if (p.getId().equals(PERSONS.get(i).getId())) {
                found = i;                
            }
        }
        
        if (found == -1) {
            return;
        }
        
        PERSONS.set(found, p);
    }
    
    public static void removePerson(Integer id) {
        if (id == null) {
            return;
        }
        
        int found = -1;
        for (int i = 0; i < PERSONS.size(); i++) {
            Person p = PERSONS.get(i);
            
            if (id.equals(p.getId())) {
                found = i;
            }
        }
        
        if (found >= 0) {
            PERSONS.remove(found);
        }
    }
}
