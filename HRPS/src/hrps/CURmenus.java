/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.util.Scanner;

/**
 *
 * @author Soham G
 */
public class CURmenus {

    private static Scanner sc = new Scanner(System.in);


    public static void showCURmenu(String what)
    {
        System.out.println("\n\t1. Create new "+what);
        System.out.println("\t2. Update "+what+" details");
        System.out.println("\t3. Remove "+what);
        System.out.print("Enter choice: ");
    }

    //ROOM CUR MENUS
    public static int promptRoomNumber()
    {
        System.out.println("Enter room number: ");
        int number = sc.nextInt();

        while (RoomManager.roomExists(number))//room already exists
        {
            System.err.println("Room "+number+" already exists, enter again: ");
            number = sc.nextInt();
        }
        return number;
    }

    public static int promptBedType()
    {
        System.out.println("Enter bedtype: ");
        System.out.println("\t1. Single");
        System.out.println("\t2. Double");
        System.out.println("\t3. Master");

        int choice = sc.nextInt(), bedType;
        switch(choice)
        {
            case 1:
                bedType = RoomType.SINGLE;
                break;
            case 2:
                bedType = RoomType.DOUBLE;
                break;
            case 3:
                bedType = RoomType.MASTER;
                break;
            default:
                System.err.println("Invalid value entered.");
                bedType = promptAvailability();
        }

        return bedType;
    }

    public static boolean promptSmoking()
    {
        System.out.println("Smoking? (Y/N): ");
        String smoking= sc.next();
        System.out.println(smoking);
        boolean isSmoking = (smoking.equals("Y")||smoking.equals("y")) ? true: false;
        return isSmoking;
    }

    public static boolean promptWiFi()
    {
        System.out.println("Has WiFi? (Y/N): ");
        String wifi= sc.next();
        boolean hasWifi = (wifi.equals("Y")||wifi.equals("y")) ? true: false;
        return hasWifi;
    }

    static String promptFacing() {
        System.out.println("Facing: ");
        String facing = sc.next();
        return facing;
    }

    static int promptAvailability() {
        System.out.println("Enter availability: ");
        System.out.println("\t1. Vacant");
        System.out.println("\t2. Occupied");
        System.out.println("\t3. Reserved");
        System.out.println("\t4. Under maintentance");

        int choice = sc.nextInt();
        int availability;

        switch(choice)
        {
            case 1:
                availability = Room.VACANT;
                break;
            case 2:
                availability = Room.OCCUPIED;
                break;
            case 3:
                availability = Room.RESERVED;
                break;
            case 4:
                availability = Room.UNDER_MAINTENANCE;
                break;
            default:
                System.err.println("Invalid value entered.");
                availability = promptAvailability();
        }

        return availability;
    }


    //GUEST CUR MENUS

    static void promptEditGuestMenu()
    {
            System.out.println("Choose what to edit: ");
            System.out.println("1. Name");
            System.out.println("2. Address");
            System.out.println("3. Gender");
            System.out.println("4. Passport Number");
            System.out.println("5. Nationality");
            System.out.println("6. Contact");
    }

    static String promptName() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static String promptAddress() {
        System.out.println("Enter address: ");
        return sc.nextLine();
    }

    static int promptGender() {
        System.out.println("Enter gender (M/F): ");
        String sex= sc.nextLine();
        int gender = (sex.equals("M")||sex.equals("m")) ? Guest.MALE : Guest.FEMALE;
        return gender;
    }

    static String promptPPnum() {
        System.out.println("Enter passport number: ");
        String pp = sc.nextLine();
        return pp;
    }

    static String promptNationality() {
        System.out.println("Enter nationality: ");
        String nat = sc.nextLine();
        return nat;
    }

    static String promptContact() {
        System.out.println("Enter contact: ");
        String contact = sc.nextLine();
        return contact;
    }



}
