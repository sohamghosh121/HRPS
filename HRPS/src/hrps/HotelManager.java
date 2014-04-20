/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.util.Calendar;
import java.util.Scanner;

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

    public void makeReservation() throws ReservationFailedException
    {
        System.out.println("Create reservation:");
        System.out.print("New guest: (Y/N)");
        String input = sc.next();
        Guest g = new Guest();
        if (input.toLowerCase().equals("y"))
        {
            g = guestManager.createGuest();
        }
        else if (input.toLowerCase().equals("n"))
        {

            while(true)
            {
                System.out.print("Enter Passport Number of guest: ");
                String pp = sc.next();
                try
                {
                    g = guestManager.findGuest(pp);
                    break;
                }
                catch (GuestNotFoundException ex)
                {
                }
            }

        }


             System.out.println("Reservation for");
                g.showGuest();

            while(true)
            {
                try
                {
                    Room r = roomManager.chooseRoom();
                    if (r == null || r.getAvailability() == Room.UNDER_MAINTENANCE)
                        throw new RoomNotFoundException();
                    String cc = CURmenus.promptCreditCardNo();
                    int nAdults = CURmenus.promptnAdults();
                    int nChildren = CURmenus.promptnChildren();
                    Calendar checkInDate = CURmenus.promptDate("check-in");
                    if (reservationManager.existsReservation(r.getRoomNumber()))
                    {
                        Reservation existingReservation = reservationManager.getReservation(r.getRoomNumber());
                        if (existingReservation.getCheckInDate().before(checkInDate)&&existingReservation.getCheckOutDate().after(checkInDate))//there is an overlap of reservations
                            throw new ReservationFailedException();
                    }
                    while(true)
                    {
                        try
                        {
                            Calendar checkOutDate = CURmenus.promptDate("check-out");
                            if (checkOutDate.before(checkInDate))
                                throw new InvalidDateException();
                            Reservation newReservation = new Reservation(r, g, cc, checkInDate, checkOutDate, nAdults, nChildren);
                            reservationManager.addReservation(newReservation);
                            newReservation.printReceipt();
                            break;
                        }

                        catch (InvalidDateException ex)
                        {
                            System.err.println(ex.getMessage()+" Enter again: ");
                        }

                     }
                    break;
                }
            catch (RoomNotFoundException ex)
                {
                    System.err.println(ex.getMessage());
                }
            }



    }

    public void checkIn()
    {
        Scanner sc = new Scanner(System.in);
        Guest g;
        String id;
        System.out.print("Enter reservation ID:");
        id=sc.next();
        try
        {
            int rn = reservationManager.checkIn(id);
            billManager.createNewBill(rn);
            roomManager.setStatus(rn, Room.OCCUPIED);
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }

    }

    public void checkOut()
    {
        int rr,p;
        Guest g;
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter room number to check out:");
        rr=sc.nextInt();
        Reservation r = reservationManager.getReservation(rr);
        if (r != null)
        {
            r.showReservation();
            billManager.showBill(rr);
            reservationManager.checkOut(rr);
            billManager.removeBill(rr);
            roomManager.makeAvailable(rr);
        }


    }

    public void addCharges()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter room number: ");
        int rn = sc.nextInt();
        System.out.print("Enter description: ");
        String des = sc.next();


        //System.out.print("\t5. Tax");
        int type = CURmenus.promptTypeOfCharges();

        System.out.print("Enter amount: ");
        double amt = sc.nextDouble();

        double discount;
        System.out.print("Discount (Y/N): ");
        String dis = sc.next();

        if (dis.toLowerCase().equals("y"))
        {
            System.out.print("Enter discount (%): ");
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
        Charge c_tax = new Charge("Tax", amt*Bill.TAX_RATE*(100-discount)/100, Charge.TAX, isWeekEnd, 0);
        billManager.addCharges(c, rn);
        billManager.addCharges(c_tax, rn);
    //}

    }
}





