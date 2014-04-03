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

    public void checkIn()
    {
        Scanner sc = new Scanner(System.in);
        Guest g;
        int choice, rn;
        String search;
        System.out.println("Enter Room Number:");
        rn=sc.nextInt();
        int res_id = findReservation(rn);
        Reservation r = (Reservation)reservations.get(res_id);
        if (r.checkExpired())
            r.checkIn();
    }



    public int findReservation(int roomNo)
    {
        int i;
        Reservation r;
        for (i=0; i<reservations.size(); i++)
        {
            r = (Reservation)reservations.get(i);
            if (r.getRoomNumber()== roomNo)
                return i;
        }
          System.err.println("No reservation found.");
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

    public void addCharges()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter room number: ");
        int rn = sc.nextInt(), type;
        int index = findReservation(rn);

        if (index != -1)
        {
            Reservation r = (Reservation)reservations.get(index);
            //reservation exists
            if (r.getStatus()==Reservation.CHECKED_IN)
            {

                System.out.println("Enter description: ");
                String des = sc.next();

                System.out.println("Choose type of charges: ");
                System.out.println("\t1. Room charges");
                System.out.println("\t2. Room service");
                System.out.println("\t3. Food charges");
                System.out.println("\t4. Transportation charges");
                System.out.println("\t5. Tax");
                int choice = sc.nextInt();

                switch (choice)
                {
                    case 1: type = Charge.ROOM_CHARGE; break;
                    case 2: type = Charge.ROOM_SERVICE; break;
                    case 3: type = Charge.FOOD_CHARGE; break;
                    case 4: type = Charge.TRANSPORT_CHARGE; break;
                    case 5: type = Charge.TAX; break;
                    default: type = 0; System.err.println("Invalid choice.");
                }

                System.out.println("Enter amount: ");
                double amt = sc.nextDouble();

                double discount;
                System.out.println("Discount (Y/N");
                String dis = sc.next();
                if (dis.toLowerCase().equals("y"))
                {
                    System.out.println("Enter discount: ");
                    discount = sc.nextDouble();
                }
                else
                    discount = 0.0;

                boolean isWeekEnd;
                Calendar now = Calendar.getInstance();
                if ((now.get(Calendar.DAY_OF_WEEK)%7)<=1)
                    isWeekEnd = true;
                else
                    isWeekEnd = false;


                Charge c = new Charge(des, amt, type, isWeekEnd, discount);
                r.addCharges(c);
            }

        }
    }

    public void showReservations()
    {
        int i;
        Reservation r;
        System.out.println("Reservations:");
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
