/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bit.koreguotini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bronius
 */
public class DuomenuBaze11 {

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
        pst.setString(1,f);
        ResultSet rs=pst.executeQuery();
        
       
         while(rs.next()){
            
            System.out.println(rs.getInt("id"));
            System.out.println(rs.getString(2));
            System.out.println(rs.getBigDecimal("salary"));
            //System.out.println(rs.getString("birth_date"));
            System.out.println(rs.getDate("birth_date"));
            System.out.println("=======================");

        }  
        
     //   rs.close();
     pst.close();
        conn.close();
        
        

    }

}
