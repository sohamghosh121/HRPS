/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Soham G
 */
public class CURmenus {

    private static Scanner sc = new Scanner(System.in);


    public static void showCURmenu(String what)
    {
        System.out.println("\n\t1. Create new "+what);
        System.out.println("\t2. Update "+what+" details");
        System.out.println("\t3. Remove "+what);
        System.out.print("Enter choice: ");
    }

    //ROOM CUR MENUS
    public static int promptRoomNumber()
    {
        System.out.println("Enter room number: ");
        int number = sc.nextInt();

        while (RoomManager.roomExists(number))//room already exists
        {
            System.err.println("Room "+number+" already exists, enter again: ");
            number = sc.nextInt();
        }
        return number;
    }

    public static int promptBedType()
    {
        System.out.println("Enter bedtype: ");
        System.out.println("\t1. Single");
        System.out.println("\t2. Double");
        System.out.println("\t3. Master");

        int choice = sc.nextInt(), bedType;
        switch(choice)
        {
            case 1:
                bedType = RoomType.SINGLE;
                break;
            case 2:
                bedType = RoomType.DOUBLE;
                break;
            case 3:
                bedType = RoomType.MASTER;
                break;
            default:
                System.err.println("Invalid value entered.");
                bedType = promptAvailability();
        }

        return bedType;
    }

    public static boolean promptSmoking()
    {
        System.out.println("Smoking? (Y/N): ");
        String smoking= sc.next();
        System.out.println(smoking);
        boolean isSmoking = (smoking.equals("Y")||smoking.equals("y")) ? true: false;
        return isSmoking;
    }

    public static boolean promptWiFi()
    {
        System.out.println("Has WiFi? (Y/N): ");
        String wifi= sc.next();
        boolean hasWifi = (wifi.equals("Y")||wifi.equals("y")) ? true: false;
        return hasWifi;
    }

    static String promptFacing() {
        System.out.println("Facing: ");
        String facing = sc.next();
        return facing;
    }

    static int promptAvailability() {
        System.out.println("Enter availability: ");
        System.out.println("\t1. Vacant");
        System.out.println("\t2. Occupied");
        System.out.println("\t3. Reserved");
        System.out.println("\t4. Under maintentance");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        int availability;

        switch(choice)
        {
            case 1:
                availability = Room.VACANT;
                break;
            case 2:
                availability = Room.OCCUPIED;
                break;
            case 3:
                availability = Room.RESERVED;
                break;
            case 4:
                availability = Room.UNDER_MAINTENANCE;
                break;
            default:
                System.err.println("Invalid value entered.");
                availability = promptAvailability();
        }

        return availability;
    }


    //GUEST CUR MENUS

    static void promptEditGuestMenu()
    {
            System.out.println("Choose what to edit: ");
            System.out.println("1. Name");
            System.out.println("2. Address");
            System.out.println("3. Gender");
            System.out.println("4. Passport Number");
            System.out.println("5. Nationality");
            System.out.println("6. Contact");
            System.out.print("Enter choice: ");
    }

    static String promptName() {
        sc.nextLine();
        System.out.print("Enter name: ");
        return sc.nextLine();
    }

    static String promptAddress() {
        System.out.print("Enter address: ");
        return sc.nextLine();
    }

    static int promptGender() {
        System.out.print("Enter gender (M/F): ");
        String sex= sc.next();
        int gender = (sex.equals("M")||sex.equals("m")) ? Guest.MALE : Guest.FEMALE;
        return gender;
    }

    static String promptPPnum() {
        System.out.print("Enter passport number: ");
        String pp = sc.next();
        return pp;
    }

    static String promptNationality() {
        System.out.print("Enter nationality: ");
        String nat = sc.next();
        return nat;
    }

    static String promptContact() {
        System.out.print("Enter contact: ");
        String contact = sc.next();
        return contact;
    }

// RESERVATION CUR MENUS
    static void promptEditReservationMenu()
    {
            System.out.println("Choose what to edit: ");
            System.out.println("1. Credit card number");
            System.out.println("2. Check in date");
            System.out.println("3. Check out date");
            System.out.println("4. Reservation status");
            System.out.print("Enter choice: ");
    }


    static String promptCreditCardNo()
    {
        String cc;
        while(true)
            {
                try
                {
                    System.out.print("Enter credit card no: ");
                    cc = sc.next();
                    if (cc.length()!=16)
                        throw new InvalidCreditCardException();
                    else
                    {//check if valid by Luhn's algorithm
                        int ckDig = Integer.parseInt(cc.substring(15));
                        int d, i=1,ckSum = 0;
                        while(i<16)
                        {
                            d = Integer.parseInt(cc.substring(15-i,16-i));

                            if ((i-1)%2==0)
                            {
                                d*=2;
                                if (d>10)
                                    d= (d%10)+d;
                            }
                            ckSum += d;
                            i++;

                        }
                        if ((ckSum+ckDig)%10 !=0)
                            throw new InvalidCreditCardException();
                        else
                            break;


                    }
                }
                catch (InvalidCreditCardException ex)
                {
                System.err.println(ex.getMessage());
                }
        }
        return cc;
    }


    static int promptnChildren()
    {
        System.out.print("Enter number of children: ");
            int nChildren = sc.nextInt();
        return nChildren;
    }


    static int promptnAdults()
    {
        System.out.print("Enter number of adults: ");
            int nAdults = sc.nextInt();
        return nAdults;
    }

    static Calendar promptDate(String what)
    {
        while (true)
        {
            try
            {
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
                System.out.print("Enter "+what+" date: (dd/mm/yyyy)");
                String date = sc.next();
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(date));
                c.set(Calendar.HOUR, 0);
                c.set(Calendar.AM_PM, Calendar.PM);//check in time is at 12 noon
                c.set(Calendar.MINUTE, 0);
                if (c.before(Calendar.getInstance()))
                {
                    throw new InvalidDateException();
                }


            return c;
            }
            catch (ParseException ex)
            {
                System.err.println("Error in date.");
            }
            catch (InvalidDateException ex)
            {
                System.err.println(ex.getMessage());
            }
        }
    }

    static int promptReservationStatus()
    {
        int choice;
        do
        {
            System.out.println("Enter new status: ");
            System.out.println("1. Confirmed");
            System.out.println("2. In waitlist");
            System.out.println("3. In enquiry");
            System.out.println("4. Expired");

            choice = sc.nextInt();
            switch(choice)
            {
                case 1: return Reservation.CONFIRMED;
                case 2: return Reservation.IN_WAITLIST;
                case 3: return Reservation.INQUIRY;
                case 4: return Reservation.EXPIRED;
                default: System.err.println("Invalid input");
            }
        }while(choice<1 || choice > 4);

        return -1;
    }


    static int promptTypeOfCharges()
    {
        int type, choice;

        do
        {
            System.out.println("Choose type of charges: ");
            System.out.println("\t1. Room charges");
            System.out.println("\t2. Room service");
            System.out.println("\t3. Food charges");
            System.out.println("\t4. Transportation charges");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice)
            {
                case 1: type = Charge.ROOM_CHARGE; break;
                case 2: type = Charge.ROOM_SERVICE; break;
                case 3: type = Charge.FOOD_CHARGE; break;
                case 4: type = Charge.TRANSPORT_CHARGE; break;
                //case 5: type = Charge.TAX; break;
                default: type = 0; System.err.println("Invalid choice.");
            }
        }while(type == 0);

        return type;

    }
}
