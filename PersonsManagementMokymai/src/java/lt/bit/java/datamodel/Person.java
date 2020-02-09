/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.java.datamodel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import lt.bit.java.services.ListAddresses;
import lt.bit.java.services.ListContacts;
import lt.bit.java.services.ListPersons;

public class Person {
    
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Date birthDate;
    private BigDecimal salary = new BigDecimal(0);
    
    private ListAddresses addresses;
    private ListContacts contacts;
            
    public Person() {
        this.id = ListPersons.getNextId();
        
        this.addresses = new ListAddresses();
        this.contacts = new ListContacts();
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
        
        final Person other = (Person) o;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    public ListAddresses getAddresses() {
        return addresses;
    }

    public void setAddresses(ListAddresses addresses) {
        this.addresses = addresses;
    }

    public ListContacts getContacts() {
        return contacts;
    }

    public void setContacts(ListContacts contacts) {
        this.contacts = contacts;
    }
    
}
