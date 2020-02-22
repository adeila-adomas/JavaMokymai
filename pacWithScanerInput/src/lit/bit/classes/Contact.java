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
public class Contact {
    private Integer id;
    private Integer person_id;
    private String contact;
    private String contact_type;
    
    public Contact(Integer id, Integer person_id, String contact, String contact_type){
        this.id = id;
        this.person_id = person_id;
        this.contact = contact;
        this.contact_type = contact_type;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public String getContact() {
        return contact;
    }

    public String getContact_type() {
        return contact_type;
    }
    
}
