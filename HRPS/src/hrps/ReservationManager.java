/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Soham G
 */
public class ReservationManager {
    private List<Reservation> reservations = (ArrayList)DBoperations.readSerializedObject("reservations.dat");

    public void checkIn(int rn)
    {
        try
        {
            int res_id = findReservation(rn);
            Reservation r = reservations.get(res_id);
            if (!r.checkExpired())
            {
                r.checkIn();
            }
            saveReservationsDB();
        }
        catch (ReservationNotFoundException ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    public void checkOut(int rn)
    {
        try {
            int res_id = findReservation(rn);
            Reservation r = reservations.get(res_id);
            r.setStatus(Reservation.CHECKED_OUT);
            saveReservationsDB();
        } catch (ReservationNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public int findReservation(int roomNo) throws ReservationNotFoundException
    {
        int i;
        Reservation r;
        for (i=0; i<reservations.size(); i++)
        {
            r = reservations.get(i);
            if (r.getRoomNumber()== roomNo && r.getStatus()==Reservation.CONFIRMED)
                return i;
        }
          throw new ReservationNotFoundException();
    }

    public Reservation getReservation(int roomNo)
    {
        try
        {
            int index = findReservation(roomNo);
            if (index != -1)//if room is found
            {
             System.out.println("Reservation found:");
             return reservations.get(index);
            }
        }
        catch (ReservationNotFoundException ex)
        {
            System.err.println(ex.getMessage());

        }
        return null;
    }

    public boolean existsReservation(int roomNo)
    {
        try
        {
            int index = findReservation(roomNo);
            Reservation r;
            r = reservations.get(index);
            return !r.checkExpired();//return true if reservation is not expired (ie a valid reservation exists)
        }
        catch (ReservationNotFoundException ex)
        {
            return false;
        }
    }


    public void editReservation()
    {
        System.out.print("Enter room number: ");
        Scanner sc = new Scanner(System.in);
        int roomNo = sc.nextInt();
        int res_id;
        try
        {
            res_id = findReservation(roomNo);
            Reservation res;
            res = reservations.get(res_id);
            //TODO: code for editing reservation
        }
        catch (ReservationNotFoundException ex)
        {
           System.err.println(ex.getMessage());
        }

    }

    public void deleteReservation()
    {
        System.out.print("Enter room number: ");
        Scanner sc = new Scanner(System.in);
        int roomNo = sc.nextInt();
        deleteReservation(roomNo);
    }

    public void deleteReservation(int roomNum)
    {
        try
        {
            int res_id = findReservation(roomNum);
            Reservation res;
            reservations.remove(res_id);
            System.out.println("Reservation for room#"+roomNum+" removed.");
            saveReservationsDB();
        }
        catch (ReservationNotFoundException ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    public void addReservation(Reservation r)
    {
        reservations.add(r);
        saveReservationsDB();
    }



    public void showReservations()
    {
        int i;
        boolean anyChange=false;
        Reservation r;
        System.out.println("\nReservations:\n-----------------------------------------");
        if (reservations.size() == 0)
        {
            System.out.println("No results found.");
            return;
        }
        for (i= 0; i<reservations.size();i++)
        {
            r = reservations.get(i);
            if (r.checkExpired())
                anyChange = true;
            r.showReservation();
        }
        if (anyChange)
            saveReservationsDB();
    }

    private void saveReservationsDB()//private because we don't want other objects to affect db
    {
        DBoperations.writeSerializedObject("reservations.dat", reservations);
    }
}
