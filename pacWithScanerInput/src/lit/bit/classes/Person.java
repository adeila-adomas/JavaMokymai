/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit.bit.classes;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author barka
 */
public class Person {
    private Integer id;
    private String first_name;
    private String last_name;
    private Date birt_date;
    private BigDecimal salary;
    
    public Person(){
    
    }
    
    
    public Person( Integer id, String first_name, String last_name, Date birt_date, BigDecimal salary){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birt_date = birt_date;
        this.salary = salary;
    
    }

    public Integer getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Date getBirt_date() {
        return birt_date;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
