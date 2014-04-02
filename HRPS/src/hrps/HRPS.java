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
public class HRPS {

    /**
     * @param args the command line arguments
     */
    public static void showMenu()
    {

        System.out.println("1. Create/Update/Remove rooms details");
        System.out.println("2. Create/Update/Remove guests detail");
        System.out.println("3. Create/Update/Remove reservation");
        System.out.println("4. Print reservation receipt");
        System.out.println("5. Check availability of a room");
        System.out.println("6. Check-in");
        System.out.println("7. Check-out and print bill invoice");
        System.out.println("8. Print Room Occupancy report (by room type, by floor level, etc)");
        System.out.println("9. Exit");
        System.out.print("Enter choice: ");
    }

    public static void showCURmenu(String what)
    {
        System.out.println("\n\t1. Create new "+what);
        System.out.println("\t2. Update "+what+" details");
        System.out.println("\t3. Remove "+what);
        System.out.print("Enter choice: ");
    }

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("---------------------------------------------");
        System.out.println("\tWELCOME to HRPS");
        System.out.println("---------------------------------------------");



        Scanner sc = new Scanner(System.in);
        HotelManager hotel = new HotelManager();


        showMenu();

        int choice = sc.nextInt(), curchoice;

        while (choice != 9)
        {
            switch (choice)
            {
                case 1:
                    showCURmenu("room");
                    curchoice = sc.nextInt();
                    switch(curchoice)
                    {
                        case 1://create room
                            hotel.roomManager.createRoom();
                            break;
                        case 2:
                            hotel.roomManager.editRoom();
                            break;
                        case 3:
                            hotel.roomManager.removeRoom();
                            break;

                    }
                    break;
                case 2:
                    showCURmenu("guest");
                    curchoice = sc.nextInt();
                    switch (curchoice)
                    {
                        case 1:
                            hotel.guestManager.createGuest();
                            break;
                    }
                    break;
                case 3:
                    showCURmenu("reservation");
                    curchoice = sc.nextInt();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                default:
                    System.err.println("INVALID INPUT. Enter again.");

            }
            showMenu();
            choice = sc.nextInt();
        }
        System.out.println("System exit.");

    }
}