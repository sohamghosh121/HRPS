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
public class RoomManager {

    private List rooms = (ArrayList)SerializeDB.readSerializedObject("rooms.dat");


    public void roomOccupancyReport()//no filters
    {
        int i;
        Room r;
        System.out.println("Room Occupancy:");
        for (i= 0; i<rooms.size();i++)
        {
            r = (Room)rooms.get(i);
            r.showRoom();
        }
    }


    public void createRoom()
    {
        System.out.println("Creating new room:");
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter room number: ");
        int number = sc.nextInt();
        while (findRoom(number)==-1)
        {
            System.err.println("Room "+number+" already exists, enter again: ");
            number = sc.nextInt();
        }

        System.out.println("Enter bedtype: ");
        System.out.println("\t1. Single");
        System.out.println("\t2. Double");
        System.out.println("\t3. Master");
        int bedType = sc.nextInt();

        //by default add rooms as available
        int availability = Room.VACANT;

        System.out.println("Smoking? (Y/N): ");
        String smoking= sc.nextLine();
        boolean isSmoking = (smoking.equals("Y")) ? true: false;

        System.out.println("Has WiFi? (Y/N): ");
        String wifi= sc.nextLine();
        boolean hasWifi = (wifi.equals("Y")) ? true: false;

        System.out.println("Facing: ");
        String facing = sc.nextLine();

        Room r = new Room(number, isSmoking, hasWifi, facing, bedType, availability);
        System.out.println("Room has been added to database:");
        r.showRoom();
    }

    public void editRoom()
    {
        //TODO: edit room logic
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter room number to remove: ");
        int roomNum = sc.nextInt();
        int roomIndex = findRoom(roomNum);
        if (roomIndex != -1)//if room is found
        {
            //look for room
            Room r = (Room)rooms.get(roomIndex);
            System.out.println("Room found:");
            r.showRoom();

            //show edit menu
            System.out.println("Choose what to edit: ");
            System.out.println("1. Type");
            System.out.println("2. Bed Type");
            System.out.println("3. Availability");

            int editChoice = sc.nextInt();
            switch(editChoice)
            {
                case 1: //edit Type
                    break;
                case 2: //edit Bed Type
                    break;
                case 3: //edit Availability
                    break;
            }
        }
    }

    public void removeRoom()
    {
        //TODO: remove room logic
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter room number to remove: ");
        int roomNum = sc.nextInt();
        rooms.remove(findRoom(roomNum));
        System.out.println("Room "+roomNum+" has been removed.");
        saveRoomDB();
    }


    private int findRoom(int roomNum)
    {
        int i;
        Room r;
        for (i = 0; i < rooms.size(); i++)
        {
            r= (Room)rooms.get(i);
            if (r.getRoomNumber()==roomNum)
                return i;

        }
        return -1;
    }
    private void saveRoomDB()//private because we don't want other objects to affect db
    {
        SerializeDB.writeSerializedObject("rooms.dat", rooms);
    }
}
