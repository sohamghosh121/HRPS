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
    private static ArrayList guests = (ArrayList)SerializeDB.readSerializedObject("guests.dat");


    public void createGuest()
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
        System.out.println("Guest has been added to database:");
        g.showGuest();
    }

    public void editGuest()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter passport number of guest: ");
        String passportNumber = sc.nextLine();
        int guestIndex = findGuest(passportNumber);
        if (guestIndex != -1)//if room is found
        {
            //look for guest
            Guest g = (Guest)guests.get(guestIndex);
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
        }
    }

    public void removeGuest()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter passport number of guest to remove: ");
        String ppNum= sc.nextLine();

        int index = findGuest(ppNum);

        if (index != -1)
        {
            Guest g = (Guest)guests.get(index);
            g.showGuest();
            guests.remove(index);
            System.out.println("Guest has been removed.");
            saveGuestsDB();
        }
        else {
            System.out.print("Guest does not exist.");
        }
    }

    public static boolean guestExists(String ppNum)
    {
        return (findGuest(ppNum)!=-1);
    }

    private static int findGuest(String ppNum)
    {
        int i;
        Guest g;
        for (i = 0; i < guests.size(); i++)
        {
            g= (Guest)guests.get(i);
            if (g.getPassportNumber()==ppNum);
                return i;

        }
        return -1;
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
