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

        System.out.println("Enter credit card no: ");
        String cc = sc.nextLine();

        System.out.println("Enter number of adults: ");
        int nAdults = sc.nextInt();

        System.out.println("Enter number of children: ");
        int nChildren = sc.nextInt();


        Reservation newReservation = new Reservation(r, g, cc, nAdults, nChildren);
        reservationManager.addReservation(newReservation);


    }

    public void checkOut()
    {

    }

    public void addCharges()
    {
        System.out.println("Enter room number: ");
        //get room, check for reservation
        //if there is reservation, then ask for amount, ask for type
        //print successfully added charges

        //else if no reservation (room not occupied)
    }

}
