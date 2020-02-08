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
import java.util.Date;
import java.util.List;

/**
 *
 * @author adoma
 */
public class ListPersons {
    
    private static final List<Person> PERSONS = new ArrayList<>();
    
    /* Pradiniai saraso duomenys */
    static {
        
        Person p1 = new Person();
        p1.setId(1);
        p1.setFirstName("Zmogas");
        p1.setLastName("Zmogeliukas");
        p1.setAge(new Integer(25));
        p1.setSalary(new BigDecimal(2000));
        p1.setBirthDate(new Date());
        PERSONS.add(p1);


        p1 = new Person();
        p1.setId(2);
        p1.setFirstName("Papuga");
        p1.setLastName("Papugiukas");
        p1.setAge(new Integer(23));
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
        
        p1.getAddresses().add(d1);
        p1.getAddresses().add(d2);
        
        PERSONS.add(p1);
        
        
    }
    
    public static List<Person> getListItems() {
        return PERSONS;
    }
    
    public static int getNextId() {
        return PERSONS.size() + 1;
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
