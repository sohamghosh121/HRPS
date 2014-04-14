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
public class HotelManager {

    protected RoomManager roomManager = new RoomManager();
    protected ReservationManager reservationManager = new ReservationManager();
    protected GuestManager guestManager = new GuestManager();
    protected BillManager billManager = new BillManager();

    static Scanner sc = new Scanner(System.in);

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
            try
            {
                g = guestManager.findGuest(pp);
            }
            catch (GuestNotFoundException ex)
            {
            }
        }
        try
        {
             System.out.println("Reservation for");
                g.showGuest();
                Room r = roomManager.chooseRoom();
                if (r == null || r.isAvailable())
                    throw new RoomNotFoundException();
                if (!reservationManager.existsReservation(r.getRoomNumber()))
                {
                    String cc = CURmenus.promptCreditCardNo();
                    int nAdults = CURmenus.promptnAdults();
                    int nChildren = CURmenus.promptnChildren();
                    Calendar checkInDate = CURmenus.promptDate("check-in");
                    try
                    {
                        Calendar checkOutDate = CURmenus.promptDate("check-out");
                        if (checkOutDate.before(checkInDate))
                            throw new InvalidDateException();
                        Reservation newReservation = new Reservation(r, g, cc, checkInDate, checkOutDate, nAdults, nChildren);
                        reservationManager.addReservation(newReservation);
                        newReservation.printReservationReceipt();
                    }

                    catch (InvalidDateException ex)
                    {
                        System.err.println(ex.getMessage()+" Enter again: ");
                    }
                }
                else
                {
                    System.err.println("Exisiting reservation already found at this room. Proceed (Y/N)?");
                    input = sc.next();
                    if (input.toLowerCase().equals("y"))
                    {
                        String cc = CURmenus.promptCreditCardNo();
                        int nAdults = CURmenus.promptnAdults();
                        int nChildren = CURmenus.promptnChildren();
                        Calendar checkInDate = CURmenus.promptDate("check-in");
                        try
                        {
                            Calendar checkOutDate = CURmenus.promptDate("check-out");
                            if (checkOutDate.before(checkInDate))
                                throw new InvalidDateException();
                            Reservation newReservation = new Reservation(r, g, cc, checkInDate, checkOutDate, nAdults, nChildren, true);
                            reservationManager.addReservation(newReservation);
                            newReservation.printReservationReceipt();
                        }
                        catch (InvalidDateException ex)
                        {
                            System.err.println(ex.getMessage()+" Enter again: ");
                        }
                    }
                    else if (input.toLowerCase().equals("n"))
                    {
                        System.out.println("Reservation cancelled.");
                    }

                }

        }
        catch (RoomNotFoundException ex)
        {
        }



    }

    public void checkIn()
    {
         Scanner sc = new Scanner(System.in);
        Guest g;
        int choice, rn;
        String search;
        System.out.println("Enter Room Number:");
        rn=sc.nextInt();
        reservationManager.checkIn(rn);
        billManager.createNewBill(rn);
    }

    public void checkOut()
    {
        int rr,p;
        Guest g;
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter room number to check out:");
        rr=sc.nextInt();
        Reservation r = reservationManager.getReservation(rr);
        if (r != null)
        {
            r.showReservation();
            billManager.showBill(rr);
            //reservationManager.deleteReservation(rr);
            reservationManager.checkOut(rr);
            billManager.removeBill(rr);
            roomManager.makeAvailable(rr);
        }


    }

    public void addCharges()
    {
                Scanner sc = new Scanner(System.in);
        System.out.println("Enter room number: ");
        int rn = sc.nextInt(), type;
        Reservation r = reservationManager.getReservation(rn);
        if (r ==null)
            return;
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
            //System.out.println("\t5. Tax");
            int choice = sc.nextInt();

            switch (choice)
            {
                case 1: type = Charge.ROOM_CHARGE; break;
                case 2: type = Charge.ROOM_SERVICE; break;
                case 3: type = Charge.FOOD_CHARGE; break;
                case 4: type = Charge.TRANSPORT_CHARGE; break;
                //case 5: type = Charge.TAX; break;
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
            Charge c_tax = new Charge("Tax: "+des, amt*Bill.TAX_RATE, Charge.TAX, isWeekEnd, discount);
            billManager.addCharges(c, rn);
            billManager.addCharges(c_tax, rn);
        }

    }
}





