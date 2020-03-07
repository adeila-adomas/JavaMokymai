/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * 
 */
public class Database {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Address_Book");
    protected static final EntityManager em = emf.createEntityManager();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public static EntityManager getEntityManager() {
        return em;
    }
    
    public static List<Person> getPersons() {
        Query q = em.createQuery("select p from Person p order by p.firstName");
        List<Person> l = q.getResultList();
        
        return l;
    }
    
    public static Person getPerson(Integer id) {
        return em.find(Person.class, id);
    }
    
    public static Person addPerson(String firstName, String lastName, Date birtDate, BigDecimal salary) {
        Person p = new Person(firstName, lastName, birtDate, salary);
        em.persist(p);
        return p;
    }
    
    public static void removePerson(Integer id) {
        Person p = em.find(Person.class, id);
        if (p != null) {
            em.remove(p);
        }
    }
    
    public static void updatePerson(Person p) {
        if (p == null) {
            return;
        }
        
        Person existing = em.find(Person.class, p.getId());
        if (existing != null) {
            System.out.println("existing: " + existing);
            existing.setFirstName(p.getFirstName());
            existing.setLastName(p.getLastName());
            existing.setBirthDate(p.getBirthDate());
            existing.setSalary(p.getSalary());
        }
    }
    /*
    public static List<Address> getPersonsAddresses(Connection conn, Integer id) throws SQLException {
        List<Address> personsAddressList = new ArrayList();
        String sql = "SELECT * from address WHERE person_id=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            if (id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, id);
            }
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Address a = new Address();
                a.setId(rs.getInt(1));
                a.setPerson_id(rs.getInt(2));
                a.setAddress(rs.getString(3));
                a.setCity(rs.getString(4));
                a.setPostalCode(rs.getString(5));
                personsAddressList.add(a);
            }
        }
        return personsAddressList;
    }
    */
    /*
    public static Address getAddress(Connection conn, Integer id) throws SQLException {
        String sql = "SELECT * from address WHERE id=?";
        Address a = null;
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                a = new Address();
                a.setId(rs.getInt(1));
                a.setPerson_id(rs.getInt(2));
                a.setAddress(rs.getString(3));
                a.setCity(rs.getString(4));
                a.setPostalCode(rs.getString(5));
            }
        }
        return a;
    }
    */
    /*
    public static void addAddress(Connection conn, Integer id, Address a) throws SQLException {
        Person p = getPerson(conn, id);
        if (p == null || a == null) {
            return;
        }
        String sql = "INSERT INTO address (person_id, address, city, postal_code) "
                + "VALUES(?,?,?,?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setString(2, a.getAddress());
            pst.setString(3, a.getCity());
            pst.setString(4, a.getPostalCode());
            pst.execute();
        }
    }
    */
    /*
    public static void removeAddress(Connection conn, Integer id) throws SQLException {
        Address existing = getAddress(conn, id);
        if (existing != null) {
            String sql = "DELETE FROM address WHERE id=?";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, id);
                pst.execute();
            }
        }
    }
    */
    /*
    public static void updateAddress(Connection conn, Address a) throws SQLException {
        if (a == null) {
            return;
        }
        Address existing = getAddress(conn, a.getId());
        if (existing != null) {
            String sql = "UPDATE address SET address=?,city=?,postal_code=? "
                    + "WHERE id=?";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, a.getAddress());
                pst.setString(2, a.getCity());
                pst.setString(3, a.getPostalCode());
                pst.setInt(4, a.getId());
                pst.execute();
            }
        }
    }
    */
    /*
    public static List<Contact> getPersonsContacts(Connection conn, Integer id) throws SQLException {
        List<Contact> personsContactsList = new ArrayList();
        String sql = "SELECT * from contact WHERE person_id=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            if (id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, id);
            }
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Contact c = new Contact();
                c.setId(rs.getInt(1));
                c.setPerson_id(rs.getInt(2));
                c.setContact(rs.getString(3));
                c.setType(rs.getString(4));
                personsContactsList.add(c);
            }
        }
        return personsContactsList;
    }
    */
    /*
    public static Contact getContact(Connection conn, Integer id) throws SQLException {
        String sql = "SELECT * from contact WHERE id=?";
        Contact c = null;
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c = new Contact();
                c.setId(rs.getInt(1));
                c.setPerson_id(rs.getInt(2));
                c.setContact(rs.getString(3));
                c.setType(rs.getString(4));
            }
        };

        return c;
    }
    */
    /*
    public static void addContact(Connection conn, Integer id, Contact c) throws SQLException {
        Person p = getPerson(conn, id);
        if (p == null || c == null) {
            return;
        }
        String sql = "INSERT INTO contact (person_id, contact_type, contact) "
                + "VALUES(?,?,?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setString(2, c.getType());
            pst.setString(3, c.getContact());
            pst.execute();
        }
    }
    */
    /*
    public static void removeContact(Connection conn, Integer id) throws SQLException {
        Contact existing = getContact(conn, id);
        if (existing != null) {
            String sql = "DELETE FROM contact WHERE id=?";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, id);
                pst.execute();
            }
        }
    }
    */
    
    /*
    public static void updateContact(Connection conn, Contact c) throws SQLException {
        if (c == null) {
            return;
        }
        Contact existing = getContact(conn, c.getId());
        if (existing != null) {
            String sql = "UPDATE contact SET contact=?,contact_type=? "
                    + "WHERE id=?";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, c.getContact());
                pst.setString(2, c.getType());
                pst.setInt(3, c.getId());
                pst.execute();
            }
        }
    }
    */

}
