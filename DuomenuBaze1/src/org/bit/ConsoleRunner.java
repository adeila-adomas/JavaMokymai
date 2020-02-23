/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.bit.services.ApplicationActions;

/**
 *

 */
public class ConsoleRunner {

    private static boolean stop = false;

    public static int getActions() {

        try {
            Scanner actionsScanner = new Scanner(System.in);

            System.out.println("");
            System.out.println("MENU");
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
            System.out.print("CHOOSE NUMBER OF ACTION, WHAT YOU WANT TO DO: ");

            return actionsScanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Galimi tik skaciai nuo 0 iki 9");
        }
        
        return -1;
    }

    public static void setAction(int choose)  {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?serverTimezone=UTC", "root", "root")) {
            try {
                switch (choose) {
                    case 0:
                        System.out.println("Exit application");
                        stop = true;
                        break;
                    case 1:
                        System.out.println("Persons List:");
                        ApplicationActions.ListAllPersons(conn);
                        break;
                    case 2:
                        System.out.println("Addressess List: ");
                        ApplicationActions.ListAllPersons(conn);
                        ApplicationActions.ListPersonAddresses(ApplicationActions.requestId(), conn);
                        break;
                    case 3:
                        System.out.println("Contacts List:");
                        ApplicationActions.ListAllPersons(conn);
                        ApplicationActions.ListPersonContacts(ApplicationActions.requestId(), conn);
                        break;
                    case 4:
                        System.out.println("Add Person");
                        ApplicationActions.AddPerson(conn);
                        break;
                    case 5:
                        System.out.println("Add Address");
                        ApplicationActions.ListAllPersons(conn);
                        ApplicationActions.AddAddress(ApplicationActions.requestNumberInput("Person ID"), conn);
                        
                        break;
                    case 6:
                        System.out.println("Add Contact");
                        ApplicationActions.ListAllPersons(conn);
                        ApplicationActions.AddContact(ApplicationActions.requestNumberInput("Person ID"), conn);
                        break;
                    case 7:
                        System.out.println("Remove Person and linked addresses and contacts");
                        ApplicationActions.ListAllPersons(conn);
                        ApplicationActions.RemovePerson(ApplicationActions.requestId(), conn);
                        System.out.println("");

                        break;
                    case 8:
                        System.out.println("Address removal");
                        ApplicationActions.ListAllPersons(conn);
                        ApplicationActions.ListPersonAddresses(ApplicationActions.requestId(), conn);
                        System.out.println("Enter id for address to remove: ");
                        int addressId = ApplicationActions.requestId();
                        ApplicationActions.RemoveAddress(addressId, conn);
                        System.out.println("");

                        break;
                    case 9:
                        System.out.println("Contact removal");
                        ApplicationActions.ListAllPersons(conn);
                        ApplicationActions.ListPersonContacts(ApplicationActions.requestId(), conn);
                        System.out.println("Enter id for contact to remove: ");
                        int contactId = ApplicationActions.requestId();
                        ApplicationActions.RemoveContact(contactId, conn);
                        System.out.println("");

                        break;
                    default:
                        // System.out.println("No such action exists. Please choose from list.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Galimi tik skaciai nuo 0 iki 9");
            }
        } catch (SQLException e) {
            System.out.println("DB Klaida: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        while (stop != true) {
            int choose = getActions();
            setAction(choose);
            // System.out.println("Action selected: " + choose);
        }
    }
}
