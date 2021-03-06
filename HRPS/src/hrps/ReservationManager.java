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

    public int checkIn(String id) throws ReservationNotFoundException,CheckInFailedException
    {

            int res_id = findReservation(id);
            Reservation r = reservations.get(res_id);
            if (!r.checkExpired())// if not expired
            {
                Calendar now = Calendar.getInstance();
                if (now.after(r.getCheckInDate())||now.equals(r.getCheckInDate()))
                {
                    r.checkIn();
                }
                else
                {
                    throw new CheckInFailedException("You cannot check in yet.");
                }
            }
            else
            {
                throw new CheckInFailedException("Reservation expired.");
            }



            saveReservationsDB();
            return r.getRoomNumber();

    }

    public void checkOut(int rr)
    {
        try {
            int res_id = findReservation(rr, Reservation.CHECKED_IN);
            Reservation r = reservations.get(res_id);
            r.setStatus(Reservation.CHECKED_OUT);
            saveReservationsDB();
        } catch (ReservationNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public int findReservation(String id) throws ReservationNotFoundException
    {
        int i;
        Reservation r;
        for (i=0; i<reservations.size(); i++)
        {
            r = reservations.get(i);
            if (r.getId().equals(id))
                return i;
        }
          throw new ReservationNotFoundException();
    }

    public int findReservation(int rn) throws ReservationNotFoundException
    {
        int i;
        Reservation r;
        for (i=0; i<reservations.size(); i++)
        {
            r = reservations.get(i);
            if (r.getRoomNumber() == rn && (r.getStatus()!=Reservation.CHECKED_OUT || r.getStatus()!= Reservation.CHECKED_IN))
                return i;
        }
          throw new ReservationNotFoundException();
    }

    public int findReservation(int rn, int status) throws ReservationNotFoundException
    {
        int i;
        Reservation r;
        for (i=0; i<reservations.size(); i++)
        {
            r = reservations.get(i);
            if (r.getRoomNumber() == rn &&  r.getStatus() == status)
                return i;
        }
          throw new ReservationNotFoundException();
    }

    public boolean existsReservation(int rn)
    {
        try
        {
            int index = findReservation(rn);
            Reservation r;
            r = reservations.get(index);
            return !r.checkExpired();//return true if reservation is not expired (ie a valid reservation exists)
        }
        catch (ReservationNotFoundException ex)
        {
            return false;
        }
    }

    public Reservation getReservation(String id)
    {
        try
        {
             int index = findReservation(id);
             System.out.println("Reservation found:");
             return reservations.get(index);

        }
        catch (ReservationNotFoundException ex)
        {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    public Reservation getReservation(int rn)
    {
        try
        {
             int index = findReservation(rn);
             System.out.println("Reservation found:");
             return reservations.get(index);

        }
        catch (ReservationNotFoundException ex)
        {
            System.err.println(ex.getMessage());
            return null;

        }

    }




    public void editReservation()
    {
        System.out.print("Enter reservation ID: ");
        Scanner sc = new Scanner(System.in);
        String roomNo = sc.next();
        int res_id;
        try
        {
            res_id = findReservation(roomNo);
            Reservation res;
            res = reservations.get(res_id);
            CURmenus.promptEditReservationMenu();
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                {
                    res.setCreditCardNo(CURmenus.promptCreditCardNo().toString());
                    break;
                }
                case 2:
                {
                    res.setCheckInDate(CURmenus.promptDate("check-in"));
                    break;
                }
                case 3:
                {
                    res.setCheckOutDate(CURmenus.promptDate("check-out"));
                    break;
                }
                case 4:
                {
                    res.setStatus(CURmenus.promptReservationStatus());
                    break;
                }
                default:
                {
                    System.err.println("Invalid input.");
                    break;
                }
            }
            //TODO: code for editing reservation
        }
        catch (ReservationNotFoundException ex)
        {
           System.err.println(ex.getMessage());
        }

    }

    public void deleteReservation()
    {
        System.out.print("Enter reservation ID: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        try
        {
            int res_id = findReservation(id);
            Reservation res = reservations.get(res_id);
            reservations.remove(res_id);
            System.out.println("Reservation for room#"+res.getRoomNumber()+" removed.");
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
