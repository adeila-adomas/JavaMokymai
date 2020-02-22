/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duomenubaze1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bronius
 */
public class DuomenuBaze1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?serverTimezone=UTC", "root", "root");

        //Statement st = conn.createStatement();
        String f = "%as%";
        //ResultSet rs = st.executeQuery("select* from person where first_name like '" + f + "'");

        PreparedStatement pst = conn.prepareStatement(
                "select* from person where first_name like?");
        pst.setString(1, f);
        ResultSet rs = pst.executeQuery();

        pst.close();

        pst = conn.prepareStatement("insert into person(first_name, last_name) values(?,?)");
        pst.setString(1, "Ona");
        pst.setString(2, "Oniene");
        pst.execute();
        pst.close();

        pst = conn.prepareStatement("update person set first_name = ?, last_name = ? where id=?");
        pst.setString(1, "Ona1");
        pst.setString(2, "Oniene1");
        pst.setInt(3,5);
        pst.execute();
        pst.close();
        
        pst = conn.prepareStatement("delete from person where id=?");
        pst.setInt(1,5);
        pst.execute();
        pst.close();
        
        conn.close();
    }

}
