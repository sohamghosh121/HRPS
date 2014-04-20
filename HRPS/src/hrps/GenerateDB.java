/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**1
 *
 * @author Soham G
 */
public class GenerateDB {
    public static void main(String[] args) {
        //To generate ROOMS


        int floor = 100, rn = 1, roomNum;
        int availability;
        double rand = Math.random();
        if (rand<0.05)
            availability = Room.UNDER_MAINTENANCE;
        else
            availability = Room.VACANT;
        int bedType;
        boolean isSmoking = false;
        boolean hasWiFi = true;
        String facing = "Beach";
        List listOfRooms = new ArrayList();
        Room r;
        for (floor = 100; floor <=1000; floor +=100)
        {
            for(rn = 1; rn<=20; rn++)
            {
                roomNum = floor+rn;
                if (rn < 5)
                    bedType = RoomType.SINGLE;
                else if (rn < 15)
                    bedType = RoomType.DOUBLE;
                else
                    bedType = RoomType.MASTER;

                r = new Room(roomNum, isSmoking, hasWiFi, facing, bedType, availability );
                listOfRooms.add(r);




            }
        }


    ArrayList listOfPeople = new ArrayList();
    listOfPeople.add(new Guest("Sam Winchester", "Rockford, Illinois", Guest.MALE, "A123456", "American", "sam@gmail.com"));
    listOfPeople.add(new Guest("Dean Winchester", "Rockford, Illinois", Guest.MALE, "B123456", "American", "dean@gmail.com"));
    listOfPeople.add(new Guest("Harry Potter", "4 Privet Drive", Guest.MALE, "C123456", "American", "harry@gmail.com"));
    listOfPeople.add(new Guest("Bill Gates", "Medina, Washington", Guest.MALE, "D123456", "American", "bill@gmail.com"));


    DBoperations.writeSerializedObject("rooms.dat", listOfRooms);
    DBoperations.writeSerializedObject("guests.dat", listOfPeople);
    DBoperations.writeSerializedObject("reservations.dat", new ArrayList());
    DBoperations.writeSerializedObject("bills.dat", new ArrayList());
    }

}