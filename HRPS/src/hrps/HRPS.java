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


    public static void showMenu()
    {
        System.out.println("\n\n");
        System.out.println("1. Create/Update/Remove rooms details");
        System.out.println("2. Create/Update/Remove guests detail");
        System.out.println("3. Create/Update/Remove reservation");
        System.out.println("4. Print reservation receipt");
        System.out.println("5. Check availability of a room");
        System.out.println("6. Check-in");
        System.out.println("7. Check-out and print bill invoice");
        System.out.println("8. Print Room Occupancy report (by room type, by floor level, etc)");
        System.out.println("9. Add charges");
        System.out.println("10. Show all reservations");
        System.out.println("11. Show all guests");
        System.out.println("12. Print bill");
        System.out.println("13. Exit");
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

        while (choice != 13)
        {
            switch (choice)
            {
                case 1:
                {
                    CURmenus.showCURmenu("room");
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
                }
                case 2:
                {
                    CURmenus.showCURmenu("guest");
                    curchoice = sc.nextInt();
                    switch (curchoice)
                    {
                        case 1:
                            hotel.guestManager.createGuest();
                            hotel.guestManager.showGuests();
                            break;
                        case 2:
                            hotel.guestManager.editGuest();
                            break;
                        case 3:
                            hotel.guestManager.removeGuest();
                            break;
                        default:
                            System.err.println("Invalid choice.");
                    }
                    break;
                }
                case 3:
                {
                    CURmenus.showCURmenu("reservation");
                    curchoice = sc.nextInt();
                    switch (curchoice)
                    {
                        case 1:
                            try
                            {
                                hotel.makeReservation();
                            }
                            catch(ReservationFailedException ex)
                            {
                                System.err.println(ex.getMessage());
                            }
                            break;
                        case 2:
                            hotel.reservationManager.editReservation();
                            break;
                        case 3:
                            hotel.reservationManager.deleteReservation();
                            break;
                        default:
                            System.err.println("Invalid choice.");
                    }
                    break;
                }

                case 4:
                    {
                        System.out.println("Enter reservation ID: ");
                        String id = sc.next();
                        Reservation r = hotel.reservationManager.getReservation(id);
                        if (r != null) {
                            r.printReceipt();
                        }
                        break;
                    }
                case 5:
                {
                    System.out.println("Enter room number: ");
                    int rn = sc.nextInt();
                    hotel.roomManager.checkAvailability(rn);
                    break;
                }
                case 6:
                {
                    hotel.checkIn();
                    break;
                }
                case 7:
                {
                    hotel.checkOut();
                    break;
                }
                case 8:
                {
                    hotel.roomManager.roomOccupancyReport();
                    break;
                }
                case 9:
                {
                    hotel.addCharges();
                    break;
                }
                case 10:
                {
                    hotel.reservationManager.showReservations();
                    break;
                }
                case 11:
                {
                    hotel.guestManager.showGuests();
                    break;
                }
                case 12:
                {
                    System.out.println("Enter room number: ");
                    int rn = sc.nextInt();
                    hotel.billManager.showBill(rn);
                    break;
                }
                default:
                    System.err.println("INVALID INPUT. Enter again.");

            }
            showMenu();
            choice = sc.nextInt();
        }
        System.out.println("System exit.");

    }
}