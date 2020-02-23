/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bit.koreguotini;

import org.bit.datamodel.Person;
import org.bit.datamodel.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bronius
 */
public class Destytojo1 {

    public static List<Person> getPersonsList(Connection conn) throws SQLException {

        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from person")) {
            List<Person> ret = new ArrayList<>();
            while (rs.next()) {
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setFirstName(rs.getString("first_name"));

                ret.add(p);
            }
            return ret;
        }
    }

    public static List<Address> getPersonAddress(Connection conn, Integer id) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement("select * from address where person_id=?")) {
            if (id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, id);
            }
            try (ResultSet rs = pst.executeQuery()) {
                List<Address> ret = new ArrayList<>();
                while (rs.next()) {
                    Address addr = new Address();
                    addr.setId(rs.getInt("id"));
                    addr.setPersonId(rs.getInt("person_id"));
                    addr.setAddress(rs.getString("address"));
                    addr.setCity(rs.getString("city"));
                    addr.setPostalCode(rs.getString("postal_code"));

                    ret.add(addr);
                }
                return ret;
            }
        }

    }

}
