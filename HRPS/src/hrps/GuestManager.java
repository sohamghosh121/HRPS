/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Soham G
 */
public class GuestManager {
    private ArrayList guests = (ArrayList)SerializeDB.readSerializedObject("guests.dat");


    public void createGuest()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter address: ");
        String add = sc.nextLine();
        System.out.println("Enter gender (M/F): ");
        String sex= sc.nextLine();
        int gender = (sex.equals("M")) ? Guest.MALE : Guest.FEMALE;
        System.out.println("Enter passport number: ");
        String pp = sc.nextLine();
        System.out.println("Enter nationality: ");
        String nat = sc.nextLine();
        System.out.println("Enter contact: ");
        String contact = sc.nextLine();

        Guest g = new Guest(name, add, gender, pp, nat, contact);
        guests.add(g);
        saveGuestsDB();
        System.out.println("Guest has been added to database:");
        g.showGuest();
    }



    public void showGuests()
    {
        int i;
        Guest g;
        System.out.println("Guests:");
        for (i= 0; i<guests.size();i++)
        {
            g = (Guest)guests.get(i);
            g.showGuest();
        }
    }


    private void saveGuestsDB()//private because we don't want other objects to affect db
    {
        SerializeDB.writeSerializedObject("guests.dat", guests);
    }

}
