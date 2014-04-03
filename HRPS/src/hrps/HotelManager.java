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
