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

    public void checkIn()
    {
        Scanner sc = new Scanner(System.in);
        Guest g;
        int choice, lp;
        String search;

        System.out.println("Search by\n1. Guest Passport Number 2. Reservation ID 3. Room number");
        choice = sc.nextInt();
        switch(choice)
        {
            case 1:
                System.out.println("Enter passport number of guest");
                search = sc.nextLine();
                break;
            case 2:
                System.out.println("");
                search = sc.nextLine();
                break;
        }

        int res_id = reservationManager.findReservation();

    }

}
