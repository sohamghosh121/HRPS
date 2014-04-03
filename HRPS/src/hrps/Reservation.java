/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Soham G
 */
public class Reservation implements Serializable {
    public static final int CONFIRMED = 1, IN_WAITLIST = 2, INQUIRY = 3, CHECKED_IN = 4, EXPIRED = -1;

    private Guest guest;
    private Room room;
    private String id;
    private String creditCardNo;
    private Calendar checkInDate;
    private Calendar checkOutDate;
    private int nAdults;
    private int nChildren;
    private int status;
    private Calendar creationDate;


    public Reservation (Room r, Guest g, String ccNo, int nAdults, int nChildren)
    {

        this.room = r;
        this.guest = g;
        this.creditCardNo = ccNo;
        this.nAdults = nAdults;
        this.nChildren = nChildren;

        creationDate = Calendar.getInstance();
        String id = room.getRoomNumber()+guest.getPassportNumber().substring(1, 4)+this.creditCardNo.substring(1,4);
    }


    public void printReceipt()
    {
        System.out.println("Reservation receipt");
        System.out.println("----------------------------");
        System.out.println("Name: "+guest.getName());
        System.out.println("Contact: "+guest.getContact());
        System.out.println(showCreditCardNo());
        //System.out.println("Check in: "+checkInDate.toString());
        //System.out.println("Check out: "+checkOutDate.toString());
        System.out.println(nAdults+" adults, "+nChildren+" children");
        System.out.println("STATUS: "+getStatus());


    }

    private String showCreditCardNo()
    {
        String cc = "xxxx-xxxx-xxxx-"+creditCardNo.substring(12);
        return cc;
    }

    

    private String getStatus()
    {
        String s="";
        switch(status)
        {
            case CONFIRMED:
                s="Confirmed";
                break;
            case IN_WAITLIST:
                s="In waitlist";
                break;
            case INQUIRY:
                s="Inquiry";
                break;
            case CHECKED_IN:
                s="Checked in";
                break;
            case EXPIRED:
                s="Expired";
                break;
        }
        return s;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public void checkIn()
    {
        status = this.CHECKED_IN;
        checkInDate = Calendar.getInstance();
    }

    public void printReservationReceipt()
    {
        System.out.println("\nReservation receipt:");
        System.out.println("ID: "+this.id);
        System.out.println(guest.getName()+guest.getContact());
        System.out.println(showCreditCardNo());
        System.out.println("Room# "+room.getRoomNumber());
        System.out.println(this.getStatus());


    }
}
