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
public class ReservationManager{
    private List reservations = (ArrayList)SerializeDB.readSerializedObject("reservations.dat");


    public void makeReservation()
    {
        //new guest? if yes proceed to making new guest
        //else search for guest by passport number
        //take necessary details- credcardno, nAdults, nChildren
        //create reservation object and add it to list. status default:
    }

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

        int res_id = findReservation();
        Reservation r = (Reservation)reservations.get(res_id);
        r.setStatus(Reservation.CHECKED_IN);

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
