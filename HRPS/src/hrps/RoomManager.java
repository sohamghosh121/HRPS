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
public class RoomManager extends HotelManager{

    private List rooms = new ArrayList();
    public RoomManager()
    {
        rooms = (ArrayList)SerializeDB.readSerializedObject("rooms.dat");

    }

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
        //TODO: create room logic
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
