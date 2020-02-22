/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lit.bit.classes.Address;
import lit.bit.classes.Contact;
import lit.bit.classes.Person;

/**
 *
 * @author barka
 */
public class DB {

    private final static List<Person> records = new ArrayList();
    private static List<Address> adresai = new ArrayList();
    private static List<Address> adresai2 = new ArrayList();
    private static List<Contact> contact = new ArrayList();
    private static List<Contact> contact1 = new ArrayList();

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    static String d1 = "2020-01-05";
    static String d2 = "2020-05-01";

//    static {
//        
//           adresai.add(new Addres("Naugarduko", "Vilnius", "LT-8888"));
//           adresai.add(new Addres("Naugarduko", "Vilnius", "LT-8888"));
//           adresai2.add(new Addres("Svitrigaila", "Vilnius", "LT-8888"));
//           contact.add(new Contact("Mobilus", "86665314"));
//           contact.add(new Contact("Namu", "86665314"));
//           contact1.add(new Contact("Mobilus", "86665314"));
//        
//        
//        
//        try {
//            records.add(new Person("Petras", "Petraitis", sdf.parse(d1), new BigDecimal(1500.50), adresai, contact ));
//            records.add(new Person("Jonas", "Jonaitis", sdf.parse(d2), new BigDecimal(1550.50), adresai2, contact1 ));
//        } catch (ParseException ex) {
//        }
//    }
    //add person
    public static void addPerson(Person p) {
        if (!records.contains(p)) {
            records.add(p);
        }
    }
//Get persons BY ID
    public static Person getPerson(Integer id) {
        for (int i = 0; i < records.size(); i++) {
            Person p = records.get(i);
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
// Personu listas
    public static List<Person> getPersons(Connection conn) {
        try {
            PreparedStatement pst = conn.prepareStatement("select * from person");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                records.add(new Person(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birt_date"),
                        rs.getBigDecimal("salary")));
            }
            rs.close();
            pst.close();
            //conn.close();
            for (Person p : records) {
                System.out.println(p.getId());
                System.out.println(p.getFirst_name());
                System.out.println(p.getLast_name());
                System.out.println((p.getBirt_date() != null) ? p.getBirt_date() : "nera iraso");
                System.out.println((p.getSalary() != null) ? p.getSalary() : "nera iraso");
                System.out.println("");
            }

        } catch (SQLException ex) {
            //Ignore
            //Logger.getLogger(PersonAddressContactMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }

    //update data
    public static void updatePerson(Person p) {
        if (p == null) {
            return;
        }
        Person existing = DB.getPerson(p.getId());
//Principas  jis yra tinkamas, tik del to, kad kai dirbsime su duomenu bazemes bus papraciau ir tapiau laiko atzvilgiu
        if (existing != null) {
//            existing.setFirstName(p.getFirstName());
//            existing.setLastName(p.getLastName());
//            existing.setBirthDay(p.getBirthDay());
//            existing.setSalary(p.getSalary());
//            existing.setAddresses(p.getAddresses());
//            existing.setContacts(p.getContacts());

        }

    }

    //Delete id
    
    public static void removePerson(Connection conn) {
        Integer id = DB.getPersonId("Person");
         try {
            PreparedStatement pst = conn.prepareStatement("delete from person where id=?");
            //Patikrinimas //DELETE
            if (id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, id);
            }
            pst.execute();
            pst.close();
            System.out.println("Person is delete");
        } catch (SQLException ex) {
            //Ignore
            //Logger.getLogger(PersonAddressContactMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//Method for Addres
// Person Address List

    public static List<Address> getPersonAddresses(Connection conn) {
       Integer person_id = DB.getPersonId("Person");
        List<Address> address = new ArrayList();
        try {
            PreparedStatement pst = conn.prepareStatement("select * from address where person_id=?");
            //PAtikrinimas
            if (person_id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, person_id);
            }

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                address.add(new Address(
                        rs.getInt("id"),
                        rs.getInt("person_id"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("postal_code")));
            }
            rs.close();
            pst.close();
            // conn.close();

        } catch (SQLException ex) {
            //Ignore
            //Logger.getLogger(PersonAddressContactMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (address.size() > 0) {
            for (Address a : address) {
                System.out.print(a.getId() + " ");
                System.out.print(a.getPerson_id() + " ");
                System.out.print(a.getAddress() + " ");
                System.out.print(a.getCity() + " ");
                System.out.println(a.getPostal_code());
                System.out.println("");
            }
            return address;
        } else {
            System.out.println("Listas tuscias");
            return new ArrayList();
        }
    }
    //Gauname Address
    public static Address getAddres(Integer id) {

        return null;
    }
    
//Remove addresas
    public static void removeAddress(Connection conn) {
          Integer id = DB.getPersonId("Address");
         try {
            PreparedStatement pst = conn.prepareStatement("delete from address where id=?");
            //Patikrinimas //DELETE
            if (id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, id);
            }
            pst.execute();
            pst.close();
            System.out.println("Address is delete");
        } catch (SQLException ex) {
            //Ignore
            //Logger.getLogger(PersonAddressContactMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//Add address
    public static void addAddres(Connection conn) {
        Integer person_id = DB.getPersonId("Person, who wants to add address");
        try {
            PreparedStatement pst1 = conn.prepareStatement("select * from person where id=?");
            if (person_id == null) {
                pst1.setNull(1, Types.INTEGER);
                System.out.println("Nera to persono");
            } else {
                pst1.setInt(1, person_id);
            }

            ResultSet rs = pst1.executeQuery();
            if (rs.next() == false) {
                System.out.println("There are not this id");
                return;
            }
        } catch (SQLException ex) {
            //Ignore
            //Logger.getLogger(PersonAddressContactMysql.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            PreparedStatement pst = conn.prepareStatement("insert into address (person_id, address, city, postal_code)values (?, ?, ?, ?)");
            //PAtikrinimas
            String address = DB.getStringTxt("Street");
            String city = DB.getStringTxt("city");
            String postal = DB.getStringTxt("postal code");
            pst.setInt(1, person_id);
            pst.setString(2, address);
            pst.setString(3, city);
            pst.setString(4, postal);

            //ADD new address
            pst.execute();
            pst.close();

        } catch (SQLException ex) {
            //Ignore
            //Logger.getLogger(PersonAddressContactMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//Update Address
    public static void updateAddres(Address a) {
        if (a == null) {
            return;
        }
        Address existing = getAddres(a.getId());
        if (existing != null) {
//            existing.setAddress(a.getAddress());
//            existing.setCity(a.getCity());
//            existing.setPostalCode(a.getPostalCode());
        }

    }

    //Method for Contact
    //Persono Contact Listas       
   public static List<Contact> getPersonContact(Connection conn) {
       Integer person_id = DB.getPersonId("Person");
       List<Contact> contact = new ArrayList();
       try {
            PreparedStatement pst = conn.prepareStatement("select * from contact where person_id=?");
           //PAtikrinimas
            if (person_id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, person_id);
            }
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                contact.add(new Contact(
                        rs.getInt("id"),
                        rs.getInt("person_id"),
                        rs.getString("contact"),
                        rs.getString("contact_type")));
            }
            rs.close();
            pst.close();
            // conn.close();

        } catch (SQLException ex) {
            //Ignore
            //Logger.getLogger(PersonAddressContactMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (contact.size() > 0) {
            for (Contact c : contact) {
                System.out.print(c.getId() + " ");
                System.out.print(c.getPerson_id() + " ");
                System.out.print(c.getContact_type() + " ");
                System.out.print(c.getContact());
                System.out.println("");
            }
            return contact;
        } else {
            System.out.println("Listas tuscias");
            return new ArrayList();
        }
    }

    //Gauname Contact id  
    public static Contact getContact(Integer id) {

        return null;
    }
    
//Remove Conatct
    public static void removeContact(Connection conn) {
         Integer id = DB.getPersonId("Contact");
         try {
            PreparedStatement pst = conn.prepareStatement("delete from contact where id=?");
            //Patikrinimas //DELETE
            if (id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, id);
            }
            pst.execute();
            pst.close();
            System.out.println("Contact is delete");
        } catch (SQLException ex) {
            //Ignore
            //Logger.getLogger(PersonAddressContactMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//Add Contact
    public static void addConatct(Connection conn) {
        Integer person_id = DB.getPersonId("Person, who wants to add address");
        try {
            PreparedStatement pst1 = conn.prepareStatement("select * from person where id=?");
            if (person_id == null) {
                pst1.setNull(1, Types.INTEGER);
            } else {
                pst1.setInt(1, person_id);
            }

            ResultSet rs = pst1.executeQuery();
            if (rs.next() == false) {
                System.out.println("There are not this id");
                return;
            }
        } catch (SQLException ex) {
            //Ignore
            //Logger.getLogger(PersonAddressContactMysql.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            PreparedStatement pst = conn.prepareStatement("insert into contact (person_id, contact, contact_type)values (?, ?, ?)");
            //PAtikrinimas
            String contact = DB.getStringTxt("Contact no.");
            String contact_type = DB.getStringTxt("Contact type");
            pst.setInt(1, person_id);
            pst.setString(2, contact);
            pst.setString(3, contact_type);

            //ADD new contact
            pst.execute();
            pst.close();

        } catch (SQLException ex) {
            //Ignore
            //Logger.getLogger(PersonAddressContactMysql.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//Update Contact
    public static void updateContact(Contact c) {
        if (c == null) {
            return;
        }
        Contact existing = getContact(c.getId());
        if (existing != null) {
//            existing.setContact(c.getContact());
//            existing.setType(c.getType());
        }

    }

//PRint action list
    public static String inputTxt() {
        Scanner myObj = new Scanner(System.in);
        String chooseNumber = null;

        System.out.println("ACTION");
        System.out.println("------------");
        System.out.println("1. Persons List");
        System.out.println("2. Persons Address");
        System.out.println("3. Persons Contact");
        System.out.println("4. Add Persons");
        System.out.println("5. Add Address");
        System.out.println("6. Add Contact");
        System.out.println("7. Delete Persons");
        System.out.println("8. Delete Address");
        System.out.println("9. Delete Contact");
        System.out.println("0. Exit");
        System.out.println("------------");
        System.out.println("CHOOSE NUMBER OF ACTION, WHAT YOU WANT TO DO");

        chooseNumber = myObj.next();

        return chooseNumber;
    }

//Get person ID by scanner
    public static int getPersonId(String name) {
        int person_id=0;
        try {
            Scanner scant = new Scanner(System.in);
            System.out.println("Enter "+name+" ID");
            person_id = scant.nextInt();
           
        } catch (Exception ex) {
            System.out.println("CIA NE ID!!!");
        }
         return person_id;
    }
    
    //get String
  public static String getStringTxt(String name) {
        String text = null;
            Scanner scant = new Scanner(System.in);
            System.out.println("Enter "+name);
            text = scant.nextLine();
     
         return text;
    }  
    
    
//Main method, which execute all actions    
    public static void printAction() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?serverTimezone=UTC", "root", "root");
        String choose = DB.inputTxt();
        System.out.println("You choose: " + choose);
        if (choose.equals("1")) {
            DB.getPersons(conn);
        } else if (choose.equals("2")) {
            DB.getPersonAddresses(conn);
        } else if (choose.equals("3")) {
            DB.getPersonContact(conn);
        } else if (choose.equals("4")) {
            System.out.println("Add Person");
        } else if (choose.equals("5")) {
            DB.addAddres(conn);
        } else if (choose.equals("6")) {
            DB.addConatct(conn);
        } else if (choose.equals("7")) {
            DB.removePerson(conn);
        } else if (choose.equals("8")) {
            DB.removeAddress(conn);
        } else if (choose.equals("9")) {
            DB.removeContact(conn);
        } else if (choose.equals("0")) {
            return;
        } else {
            System.out.println("Tokio nera, rinkis iš naujo");
            DB.printAction();
        }

        conn.close();

    }

}
