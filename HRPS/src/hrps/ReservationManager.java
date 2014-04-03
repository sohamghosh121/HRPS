/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Soham G
 */
public class ReservationManager{
    private List reservations = (ArrayList)SerializeDB.readSerializedObject("reservations.dat");





        //new guest? if yes proceed to making new guest
        //else search for guest by passport number
        //take necessary details- credcardno, nAdults, nChildren
        //create reservation object and add it to list. status default:
    
    public void checkIn()
    {
        Scanner sc = new Scanner(System.in);
        Guest g;
        int choice, lp;
        String search;

        System.out.println("Search by\n1. Guest Passport Number 2. Reservation ID 3. Room number");
        choice = sc.nextInt();
        switch(choice)
        {
            case 1:
                System.out.println("Enter passport number of guest");
                search = sc.nextLine();
                g = GuestManager.findGuest(search);
                break;
            case 2:
                System.out.println("Enter Reservation ID");
                search = sc.nextLine();
                
                break;
            case 3: 
                System.out.println("Enter Room Number:");
                lp=sc.nextInt();
                RoomManager.roomExists(lp);
            default: System.err.println("Please enter a valid choice(1-3):");
        }

        int res_id = findReservation();
        Reservation r = (Reservation)reservations.get(res_id);
        r.checkIn();


    }

    public int findReservation(int roomNo)
    {int R= new 
        Reservation r = new Reservation();

        System.out.println("Reservation found:");
        r.printReceipt();
        return 1;
    }
    
    public void addReservation(Reservation r)
    {
        reservations.add(r);
    }

    private void saveReservationsDB()//private because we don't want other objects to affect db
    {
        SerializeDB.writeSerializedObject("reservations.dat", reservations);
    }
}
