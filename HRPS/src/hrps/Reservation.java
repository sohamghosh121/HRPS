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
    public static final int CONFIRMED = 1, IN_WAITLIST = 2, INQUIRY = 3, CHECKED_IN = 4, CHECKED_OUT = 5, EXPIRED = -1;

    private Guest guest;
    private Room room;
    private String creditCardNo;
    private Calendar checkInDate;
    private Calendar checkOutDate;
    private int nAdults;
    private int nChildren;
    private int status;
    private Calendar creationDate;
    private Calendar expiryDate;
    private Bill bill;

    public Reservation (Room r, Guest g, String ccNo, int nAdults, int nChildren)
    {

        this.room = r;
        this.guest = g;
        this.creditCardNo = ccNo;
        this.nAdults = nAdults;
        this.nChildren = nChildren;
        this.status = CONFIRMED;
        creationDate = Calendar.getInstance();
        expiryDate = (Calendar)creationDate.clone();
        expiryDate.set(Calendar.HOUR, creationDate.get(Calendar.HOUR)+1);
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
        System.out.println("STATUS: "+getStatusString());


    }

    private String showCreditCardNo()
    {
        String cc = "xxxx-xxxx-xxxx-"+creditCardNo.substring(12);
        return cc;
    }

    public boolean checkExpired()
    {
        Calendar now = Calendar.getInstance();
        if (now.compareTo(expiryDate)>0)
        {
            this.status = Reservation.EXPIRED;
            return true;
        }
        else
            return false;
    }

    private String getStatusString()
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
            default:
                s = "NO IDEA";
        }
        return s;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public void checkIn()
    {
        status = this.CHECKED_IN;
        checkInDate = Calendar.getInstance();
        System.out.println("Check In Successful!");
    }

    public void checkout(){
        status=this.CHECKED_OUT;
        checkOutDate=Calendar.getInstance();
    }
    public void printReservationReceipt()
    {
        System.out.println("\nReservation receipt:");
        System.out.println(guest.getName()+guest.getContact());
        System.out.println(showCreditCardNo());
        System.out.println("Room# "+room.getRoomNumber());
        System.out.println(this.getStatusString());
    }

    public Bill getBill()
    {
        return bill;
    }

    public void addCharges(Charge c)
    {
        bill.add(c);
    }

    public void showReservation()
    {
        System.out.println(guest.getName()+"\tRoom#"+room.getRoomNumber()+"\t"+getStatusString());
    }

    int getRoomNumber() {
        return this.room.getRoomNumber();
    }
}
