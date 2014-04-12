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
public class ReservationManager{
    private List reservations = (ArrayList)SerializeDB.readSerializedObject("reservations.dat");

    public void checkIn(int rn)
    {
        int res_id = findReservation(rn);
        if (res_id == -1)
            return;
        Reservation r = (Reservation)reservations.get(res_id);
        if (!r.checkExpired())
        {
            r.checkIn();
        }
        saveReservationsDB();
    }

    public int findReservation(int roomNo)
    {
        int i;
        Reservation r;
        for (i=0; i<reservations.size(); i++)
        {
            r = (Reservation)reservations.get(i);
            if (r.getRoomNumber()== roomNo && r.getStatus()==Reservation.CONFIRMED)
                return i;
        }
          System.err.println("No existing reservation found.");
          return -1;
    }

    public Reservation getReservation(int roomNo)
    {
        int index = findReservation(roomNo);
        if (index != -1)//if room is found
        {
         System.out.println("Reservation found:");
         return (Reservation)reservations.get(index);
        }
        else return null;
    }

    public boolean existsReservation(int roomNo)
    {
        int index = findReservation(roomNo);
        Reservation r;
        if (index != -1)
        {
            r = (Reservation)reservations.get(index);
            return !r.checkExpired();//return true if reservation is not expired (ie a valid reservation exists)
        }
        else
            return false;
    }


    public void editReservation()
    {
        System.out.print("Enter room number: ");
        Scanner sc = new Scanner(System.in);
        int roomNo = sc.nextInt();
        int res_id = findReservation(roomNo);
        Reservation res;
        if (res_id != -1)
        {
            res = (Reservation)reservations.get(res_id);
        }
        else
            System.err.println("No reservation found for room# "+roomNo);
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
        int res_id = findReservation(roomNum);
        Reservation res;
        if (res_id != -1)
        {
            reservations.remove(res_id);
            System.out.println("Reservation for room#"+roomNum+" removed.");
            saveReservationsDB();
        }
        else
            System.err.println("No reservations found for room#"+roomNum);
    }

    public void addReservation(Reservation r)
    {
        reservations.add(r);
        saveReservationsDB();
    }



    public void showReservations()
    {
        int i;
        Reservation r;
        System.out.println("\nReservations:\n-----------------------------------------");
        if (reservations.size() == 0)
        {
            System.out.println("No results found.");
            return;
        }
        for (i= 0; i<reservations.size();i++)
        {
            r = (Reservation)reservations.get(i);
            r.showReservation();
        }
    }

    private void saveReservationsDB()//private because we don't want other objects to affect db
    {
        SerializeDB.writeSerializedObject("reservations.dat", reservations);
    }
}
