/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Soham G
 */
public class Reservation implements Serializable {
    public static final int CONFIRMED = 1, IN_WAITLIST = 2, INQUIRY = 3, CHECKED_IN = 4, CHECKED_OUT = 5, EXPIRED = -1;

    private String id;
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
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public Reservation (Room r, Guest g, String ccNo, Calendar checkIn, Calendar checkOut, int nAdults, int nChildren)
    {

        this.room = r;
        this.guest = g;
        this.creditCardNo = ccNo;
        this.nAdults = nAdults;
        this.nChildren = nChildren;
        this.status = CONFIRMED;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        expiryDate = (Calendar)this.checkInDate.clone();
        expiryDate.set(Calendar.HOUR, checkInDate.get(Calendar.HOUR)+1);
        SimpleDateFormat s = new SimpleDateFormat("ddMM");
        id = r.getRoomNumber()+g.getPassportNumber().substring(3)+s.format(checkIn.getTime())+s.format(checkOut.getTime());
    }

    public Reservation (Room r, Guest g, String ccNo, Calendar checkIn, Calendar checkOut, int nAdults, int nChildren, boolean waitlist)
    {

        this(r, g, ccNo, checkIn, checkOut, nAdults, nChildren);
        if (waitlist)
            this.status = IN_WAITLIST;
        else
            this.status = CONFIRMED;

    }
    
    public Calendar getCheckInDate() {
        return checkInDate;
    }

    public Calendar getCheckOutDate() {
        return checkOutDate;
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

    public String getId() {
        return id;
    }


    public int getStatus()
    {
        return status;
    }

    int getRoomNumber() {
        return this.room.getRoomNumber();
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public void setCheckInDate(Calendar checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(Calendar checkOutDate) {
        this.checkOutDate = checkOutDate;
    }






    public void printReceipt()
    {
        System.out.println("Reservation receipt");
        System.out.println("----------------------------");
        System.out.append("ID: "+this.id);
        System.out.println("Name: "+guest.getName());
        System.out.println("Contact: "+guest.getContact());
        System.out.println("Check in: "+sdf.format(this.checkInDate.getTime()));
        System.out.println("Check out: "+sdf.format(this.checkOutDate.getTime()));
        System.out.println(showCreditCardNo());
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
        if (now.after(this.expiryDate))
        {
            this.status = Reservation.EXPIRED;
            System.out.println("Expired!!!");
            return true;
        }
        else
            return false;
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
        System.out.println("ID:"+id+"\t\t"+guest.getName()+"\tRoom#"+room.getRoomNumber()+"\t"+"Check in: "+sdf.format(this.checkInDate.getTime())+"\t\t"+"Check out: "+sdf.format(this.checkOutDate.getTime())+"\t"+getStatusString());
    }


}
