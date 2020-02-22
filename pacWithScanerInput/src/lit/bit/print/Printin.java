/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lit.bit.print;

import java.sql.SQLException;
import java.util.Scanner;
import lt.bit.data.DB;

/**
 *
 * @author barka
 */
public class Printin {
    
    public static String insertConstruct(){
    String lettering = null;
    Scanner sc = new Scanner(System.in);
    lettering= sc.nextLine();
    return lettering;
    } 

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //DB.printTxt();
        DB.printAction();
//        System.out.println("hi");
//        int numberRegisters;
//        String line;

//        Scanner readInput = new Scanner(System.in);
//
//        numberRegisters = Integer.parseInt(readInput.nextLine());
      
//        while (!(line = readInput.nextLine()).isEmpty()) {
//            System.out.println(line + "<");
//        }
//insertConstruct();
    }
}
