/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Soham G
 */
public class GuestManager {
    private static List<Guest> guests = (ArrayList)DBoperations.readSerializedObject("guests.dat");


    public Guest createGuest()
    {
        String name = CURmenus.promptName();
        String add = CURmenus.promptAddress();
        int gender = CURmenus.promptGender();
        String pp = CURmenus.promptPPnum();
        String nat = CURmenus.promptNationality();
        String contact = CURmenus.promptContact();


        Guest g = new Guest(name, add, gender, pp, nat, contact);
        guests.add(g);
        saveGuestsDB();
        System.out.println("\nGuest has been added to database:");
        g.showGuest();
        System.out.println("---------------------------------\n");
        return g;
    }

    public void editGuest()
    {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter passport number of guest: ");
            String passportNumber = sc.nextLine();
            int guestIndex = findGuestIndex(passportNumber);
                Guest g = guests.get(guestIndex);
                System.out.println("Guest found:");
                g.showGuest();

                //show edit menu
                CURmenus.promptEditGuestMenu();


                int editChoice = sc.nextInt();
                switch(editChoice)
                {
                    case 1:
                        String name = CURmenus.promptName();
                        g.setName(name);
                        break;
                    case 2:
                        String add = CURmenus.promptAddress();
                        g.setAddress(add);
                        break;
                    case 3:
                        int gender = CURmenus.promptGender();
                        g.setGender(gender);
                        break;
                    case 4:
                        String pp = CURmenus.promptPPnum();
                        g.setPassportNumber(pp);
                        break;
                    case 5:
                        String nat = CURmenus.promptNationality();
                        g.setNationality(nat);
                        break;
                    case 6:
                        String c = CURmenus.promptContact();
                        g.setContact(c);
                        break;
                    default:
                        System.err.println("Invalid choice.");

                }
                System.out.println("Guest details updated");
                g.showGuest();
                saveGuestsDB();
        } catch (GuestNotFoundException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void removeGuest()
    {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter passport number of guest to remove: ");
            String ppNum= sc.nextLine();
            int index = findGuestIndex(ppNum);
            Guest g = guests.get(index);
            g.showGuest();
            guests.remove(index);
            System.out.println("Guest has been removed.");
            saveGuestsDB();
        } catch (GuestNotFoundException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public static boolean guestExists(String ppNum)
    {
        boolean returnVal;
        try {
            returnVal =  (findGuestIndex(ppNum)!=-1);
        } catch (GuestNotFoundException ex) {
            returnVal = false;
        }
        return returnVal;
    }

    public static int findGuestIndex(String ppNum) throws GuestNotFoundException
    {
        int i;
        Guest g;
        for (i = 0; i < guests.size(); i++)
        {
            g = guests.get(i);
            if (g.getPassportNumber().equals(ppNum))
                return i;

        }
        System.err.println("Guest not found");
        return -1;
    }

    public static Guest findGuest(String ppNum) throws GuestNotFoundException
    {
        int index = findGuestIndex(ppNum);
        return guests.get(index);
    }

    public void showGuests()
    {
        int i;
        Guest g;
        System.out.println("\nGuests:\n---------------------------------------------");
        if (guests.size() == 0)
        {
            System.out.println("No results found.");
            return;
        }
        for (i= 0; i<guests.size();i++)
        {
            g = guests.get(i);
            g.showGuest();
        }
    }


    private void saveGuestsDB()//private because we don't want other objects to affect db
    {
        DBoperations.writeSerializedObject("guests.dat", guests);
    }

}
