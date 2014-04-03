/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Soham G
 */
public class HotelManager {

    protected RoomManager roomManager = new RoomManager();;
    protected ReservationManager reservationManager = new ReservationManager();
    protected GuestManager guestManager = new GuestManager();



    public void makeReservation()
    {
        System.out.println("Create reservation:");
        System.out.println("New guest: (Y/N)");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        Guest g = new Guest();
        if (input.toLowerCase().equals("y"))
        {
            g = guestManager.createGuest();
        }
        else if (input.toLowerCase().equals("n"))
        {

            System.out.println("Enter Passport Number of guest: ");
            String pp = sc.next();
            g = guestManager.findGuest(pp);
        }
        System.out.println("Reservation for");
        g.showGuest();

        Room r = roomManager.chooseRoom();
        if (r.equals(null))
        {
            System.err.println("Room not found.");
        }
        if (!reservationManager.existsReservation(r.getRoomNumber()))
        {
            System.out.println("Enter credit card no: ");
            String cc = sc.next();

            System.out.println("Enter number of adults: ");
            int nAdults = sc.nextInt();

            System.out.println("Enter number of children: ");
            int nChildren = sc.nextInt();


            Reservation newReservation = new Reservation(r, g, cc, nAdults, nChildren);
            reservationManager.addReservation(newReservation);
            newReservation.printReservationReceipt();
        }



    }

    public void checkOut()
    {
        int rr,p;
        Guest g;
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter room number to check out:");
        rr=sc.nextInt();
        reservationManager.deleteReservation(rr);
        Reservation r = reservationManager.getReservation(rr);
        roomManager.makeAvailable(rr);
        r.getBill().printBill();
    }


    }


