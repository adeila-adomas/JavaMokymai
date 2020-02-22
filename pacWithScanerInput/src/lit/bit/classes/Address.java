/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit.bit.classes;

/**
 *
 * @author barka
 */
public class Address {
    private Integer id;
    private Integer person_id;
    private String address;
    private String city;
    private String postal_code;
    
    public Address(Integer id, Integer person_id, String address, String city, String postal_code){
        this.id = id;
        this.person_id = person_id;
        this.address = address;
        this.city = city;
        this.postal_code = postal_code;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostal_code() {
        return postal_code;
    }
    
}
