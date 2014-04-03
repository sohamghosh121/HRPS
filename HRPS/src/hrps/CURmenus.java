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
                bedType = Room.SINGLE;
                break;
            case 2:
                bedType = Room.DOUBLE;
                break;
            case 3:
                bedType = Room.MASTER;
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
        String smoking= sc.nextLine();
        boolean isSmoking = (smoking.equals("Y")) ? true: false;
        return isSmoking;
    }

    public static boolean promptWiFi()
    {
        System.out.println("Has WiFi? (Y/N): ");
        String wifi= sc.nextLine();
        boolean hasWifi = (wifi.equals("Y")) ? true: false;
        return hasWifi;
    }

    static String promptFacing() {
        System.out.println("Facing: ");
        String facing = sc.nextLine();
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

}
