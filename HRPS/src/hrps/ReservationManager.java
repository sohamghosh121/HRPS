/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Soham G
 */
public class ReservationManager{
    private List reservations = (ArrayList)SerializeDB.readSerializedObject("reservations.dat");


    public void makeReservation()
    {
        //new guest? if yes proceed to making new guest
        //else search for guest. by name.
        //take necessary details
        //create reservation object and add it to list
    }

    public int findReservation()
    {
        Reservation r = new Reservation();

        System.out.println("Reservation found:");
        r.printReceipt();
        return 1;
    }

    private void saveReservationsDB()//private because we don't want other objects to affect db
    {
        SerializeDB.writeSerializedObject("reservations.dat", reservations);
    }
}
